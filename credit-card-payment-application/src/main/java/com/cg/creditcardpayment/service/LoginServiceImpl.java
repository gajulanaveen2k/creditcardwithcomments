package com.cg.creditcardpayment.service;
/**
* <h1>LoginServiceImpl</h1>
* LoginServiceImpl is a program where all the methods in ILoginService are implemented
* <p>
* 
*
* @author  Shambhavi
* @version 1.0
* @since   2021-03-31 
*/
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.creditcardpayment.dao.ILoginRepository;
import com.cg.creditcardpayment.entity.LoginEntity;
import com.cg.creditcardpayment.exception.CustomerException;
import com.cg.creditcardpayment.exception.LoginException;
import com.cg.creditcardpayment.model.ChangePassword;
import com.cg.creditcardpayment.model.SignUp;
import com.cg.creditcardpayment.model.LoginModel;


@Service
public class LoginServiceImpl implements ILoginService {
	/**
	 * This a local variable: {@link #userRepo} defines the object of ILoginRepository
	 * @HasGetter
	 * @HasSetter
	 */
	@Autowired
	private ILoginRepository userRepo;


	/**
	 * This a local variable: {@link #parser} defines the object of EMparse
	 * @HasGetter
	 * @HasSetter
	 */
	@Autowired
	private EMParse parser;
	
	/**
	 * default constructor
	 */
	public LoginServiceImpl() {
		
	}
	
	/**
	 * Parameterized Constructor
	 * @param userRepo  	the ILoginRepository object
	 */
	public LoginServiceImpl(ILoginRepository userRepo) {
		super();
		this.userRepo = userRepo;
		this.parser = new EMParse();
	}

	/**
	 * @return userRepo as IUserRepository
	 */
	public ILoginRepository getUserRepo() {
		return userRepo;
	}
	
	/**
	 * @param userRepo is ILoginRepository object
	 */
	public void setUserRepo(ILoginRepository userRepo) {
		this.userRepo = userRepo;
	}
	/**
	 * 
	 * @return parser as EMparse
	 */
	public EMParse getParser() {
		return parser;
	}
	/**
	 * 
	 * @param parser which is object of EMParse
	 */
	public void setParser(EMParse parser) {
		this.parser = parser;
	}
	/**
	 * This method add new login details
	 * @param user which contains all the user details
	 * @return LoginModel which is added
	 * @throws LoginException when Exception occurs
	 */
	@Override
	public LoginModel add(LoginModel user) throws LoginException {
		if(user !=null) {
			if(userRepo.existsById(user.getUserId())) {
				throw new LoginException("User "+user.getUserId()+" is already Exists");
			}
			if(!user.getUserId().matches("^[A-Za-z][A-Za-z0-9]{3,20}$")) {
				throw new LoginException("UserId should be non empty and minimum of length 4");
			}
			if(!user.getPassword().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&()])(?=\\S+$).{8,}$")) {
				throw new LoginException("Password should contain upper case, Lower case, Special charecter, numbers and length minimum 8");
			}
			else {
				user=parser.parse(userRepo.save(parser.parse(user)));
			}
		}
		return user;
	}
	/**
	 * This method is used to update the Login details 
	 * @param user which contain updated details
	 * @return LoginModel which is updated
	 * @throws LoginException when exception occurs
	 */
	@Override
	public LoginModel save(LoginModel user) throws LoginException {
		LoginModel old=parser.parse(userRepo.findById(user.getUserId()).orElse(null));
		if(old == null) {
			throw new LoginException("No user with Id "+user.getUserId()+" is present");
		}else if(!user.getPassword().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&()])(?=\\S+$).{8,}$")) {
			throw new LoginException("Password should contain upper case, Lower case, Special charecter, numbers and length minimum 8");
		}else {
			user=parser.parse(userRepo.save(parser.parse(user)));
		}
		return user;
	}
	/**
	 * This method list all the login details
	 * @return List<LoginModel> which contains all the login details
	 */
	@Override
	public List<LoginModel> findAll() {
		return userRepo.findAll().stream().map(parser::parse).collect(Collectors.toList());
	}
	/**
	 * This method deletes the login by its userId
	 * @param userId which should be deleted
	 * @throws CustomerException when exception occurs
	 */
	@Override
	public void deleteById(String userId) throws LoginException {
		if(userId==null) {
			throw new LoginException("User Id Cannot be Null");
		}else if(!userRepo.existsById(userId)) {
			throw new LoginException("User with user Id "+userId+" Does not exists");
		}
		userRepo.deleteById(userId);
	}
	/**
	 * This method search the user by its userId
	 * @param userId to be searched
	 * @return LoginModel when the userId is found
	 * @throws LoginException when the exception occurs
	 */
	@Override
	public LoginModel findById(String userId) throws LoginException {
		if(userId==null) {
			throw new LoginException("User Id Cannot be Null");
		}else if(!userRepo.existsById(userId)) {
			throw new LoginException("User with user Id "+userId+" Does not exists");
		}
		return parser.parse(userRepo.findById(userId).orElse(null));
	}

	/**
	 * This method checks whether the given userId exists or not
	 * @param userId to check the existence 
	 * @return boolean to identify the existence
	 */
	@Override
	public boolean existsById(String userId) {
		return userRepo.existsById(userId);
	}
	/**
	 * This method is user for sign in
	 * @param user which contains the entered login details by user
	 * @return boolean to allow signIn or not
	 * @throws LoginException when the exception occurs
	 */
	@Override
	public boolean signIn(LoginModel user) throws LoginException {
		if(user==null) {
			throw new LoginException("SignIn details Cannot be Null");
		}
		LoginEntity userDetails=userRepo.findById(user.getUserId()).orElse(null);
		if(userDetails==null) {
			throw new LoginException("User Details doesnot Exists");
		}
		return userDetails.getPassword().equals(user.getPassword());
	}
	/**
	 * This method is used for signOut
	 * @param user which contains the login details
	 * @return boolean to allow logout
	 */
	@Override
	public boolean signOut(LoginModel user) {
		return true;
	}
	/**
	 * This method is used to change password
	 * @param changePassword which contains the details of change password
	 * @return boolean to check the password is changed or not
	 * @throws LoginException when the exception occurs
	 */
	@Override
	public boolean changePassword(ChangePassword changePassword) throws LoginException {
		if(changePassword==null) {
			throw new LoginException("Change details should not be null");
		}
		LoginModel user=parser.parse(userRepo.findById(changePassword.getUserId()).orElse(null));
		if(user==null) {
			throw new LoginException("User details Does not exists");
		}
		boolean isChanged=false;
		if(user.getPassword().equals(changePassword.getCurrentPassword())) {
			if(changePassword.getNewPassword().equals(changePassword.getConfirmPassword())) {
				user.setPassword(changePassword.getConfirmPassword());
				userRepo.save(parser.parse(user));
				isChanged= true;
			}else {
				throw new LoginException("New Password and Confirm Password should be Same");
			}
		}else {
			throw new LoginException("Authentication Failed: Password entered is wrong");
		}
		
		return isChanged;
	}
	/**
	 * This method is used of signUp
	 * @param signUp contains all the signUp details
	 * @return LoginModel which contains the login details
	 * @throws LoginException when the exception occurs
	 */ 
	@Override
	public LoginModel signUp(SignUp signUp) throws LoginException {
		if(signUp==null) {
			throw new LoginException("SignUp details cannot be Null");
		}
		LoginModel user=parser.parse(userRepo.findById(signUp.getUserId()).orElse(null));
		if(user==null) {
			throw new LoginException("SignUp details Does not Exists");
		}
		if(user.getPassword().equals(signUp.getKey())) {
			if(signUp.getCreatePassword().equals(signUp.getConfirmPassword())) {
				user.setPassword(signUp.getConfirmPassword());
				user=parser.parse(userRepo.save(parser.parse(user)));
			}else {
				throw new LoginException("New Password and Confirm Password should be Same");
			}
		}else {
			throw new LoginException("Authentication Failed: Key entered is wrong");
		}
		return user;
	}

}
