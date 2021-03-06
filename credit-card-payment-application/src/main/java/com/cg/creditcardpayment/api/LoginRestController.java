package com.cg.creditcardpayment.api;
/**
* <h1>LoginRestController</h1>
* The LoginRestController program takes care of mapping the url's 
* to the functions which are specific to the Login
* <p>
* 
*
* @author  Shambhavi
* @version 1.0
* @since   2021-03-31 
*/
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.creditcardpayment.exception.LoginException;
import com.cg.creditcardpayment.model.ChangePassword;
import com.cg.creditcardpayment.model.LoginModel;
import com.cg.creditcardpayment.model.SignUp;
import com.cg.creditcardpayment.service.ILoginService;

@RestController
@RequestMapping("/login")
@CrossOrigin
public class LoginRestController {
	/**
	 * This a local variable: {@link userService} is the object of ILoginService which is used to access the functions in ILoginService 
	 */
	@Autowired
	private ILoginService userService;
	
	/**
	 * This method retrieve all the login details from the findAll method in ILoginService
	 * @return ResponseEntity which contains all the login details of all users
	 */
	@GetMapping("/allUsers")
	public ResponseEntity<List<LoginModel>> findAll() {
		return ResponseEntity.ok(userService.findAll());
	}
	
	/**
	 * This method retrieve the Login details of the given userId
	 * @param userId to find the Login details
	 * @return ResponseEntity, with HTTP status OK, which contains the login details of the given userId
	 * @throws LoginException when there is an exception
	 */
	@GetMapping("/user/{userId}")
	public ResponseEntity<LoginModel> findById(@PathVariable("userId")String userId) throws LoginException {
		return new ResponseEntity<>(userService.findById(userId),HttpStatus.OK);
	}
	
	/**
	 * This method is used to sign the user into the application
	 * @param user which contains the entered login details by user
	 * @return ResponseEntity with HTTP status code and Message whether the given details are mached or not
	 * @throws LoginException
	 */
	@PostMapping("/signIn")
	public ResponseEntity<String> signIn(@RequestBody @Valid LoginModel user,BindingResult result) throws LoginException{
		ResponseEntity<String> response=null;
		if (result.hasErrors()) {
			throw new LoginException(GlobalExceptionHandler.messageFrom(result));
		}
		if(userService.existsById(user.getUserId())) {
			if(userService.signIn(user)) {
				response=new ResponseEntity<>("Signed In "+user.getUserId(),HttpStatus.ACCEPTED);
			}else {
				response=new ResponseEntity<>("Login Id and password did not match",HttpStatus.UNAUTHORIZED);
			}		
		}else {
			response=new ResponseEntity<>("User with "+user.getUserId()+" Does not exists",HttpStatus.NOT_FOUND);
		}
		return response;
	}
	
	/**
	 * This method sends the new user login details to the add method in ILoginService
	 * @param user which contains all the Login details
	 * @return response is a ResponseEntity with HTTP status which contain the newly added Login details
	 * @throws LoginException when an exception occurs
	 */
	@PostMapping("/user")
	public ResponseEntity<String> add(@RequestBody @Valid LoginModel user,BindingResult result) throws LoginException {
		if (result.hasErrors()) {
			throw new LoginException(GlobalExceptionHandler.messageFrom(result));
		}
		userService.add(user);
		return new ResponseEntity<>("User is Added",HttpStatus.CREATED);
	}
	
	/**
	 * This method calls the deleteUser in ILoginService 
	 * @param userId is taken to delete the Login details
	 * @return ResponseEntity with HTTP status 
	 * @throws LoginException when the exception occurs
	 */
	@DeleteMapping("/user/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable("userId") String userId) throws LoginException {
		userService.deleteById(userId);
		return new ResponseEntity<>("User is Deleted",HttpStatus.OK);
	}
	
	/**
	 * This method is called when the user wants to change the password
	 * @param changePassword contains the changed password and userId
	 * @return response which is response entity with HTTP status code
	 * @throws LoginException when exception occurs
	 */
	@PutMapping("/changePassword")
	public ResponseEntity<String> updateUser(@RequestBody @Valid ChangePassword changePassword,BindingResult result ) throws LoginException {
		ResponseEntity<String> response=null;
		if (result.hasErrors()) {
			throw new LoginException(GlobalExceptionHandler.messageFrom(result));
		}
		if(userService.changePassword(changePassword)) {
			response=new ResponseEntity<>("Password Changed Succesfull!",HttpStatus.ACCEPTED);
		}else {
			response=new ResponseEntity<>("Password not Changed",HttpStatus.NOT_ACCEPTABLE);
		}
		return response;
	}
	/**
	 * This method is called when user is first time signing up
	 * @param signUp contains the key and updated password 
	 * @return ResponseEntity with HTTP status code ACCEPTED
	 * @throws LoginException when Exception occurs
	 */
	@PutMapping("/signUp")
	public ResponseEntity<String> signUp(@RequestBody @Valid SignUp signUp, BindingResult result ) throws LoginException {
		ResponseEntity<String> response=null;
		if (result.hasErrors()) {
			throw new LoginException(GlobalExceptionHandler.messageFrom(result));
		}
		userService.signUp(signUp);
		response=new ResponseEntity<>("Signed Up Succesfully",HttpStatus.ACCEPTED);
		return response;
	}
	
}
