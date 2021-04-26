package com.cg.creditcardpayment.api;
/**
* <h1>DefaultController</h1>
* The DefaultController program performs no operations
*  <p>
* 
*
* @author  P Venkata Sai Reddy
* @version 1.0
* @since   2021-03-31 
*/
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/")
public class DefaultController {
	/**
	 * Just prints Welcome to Credit Card Application
	 * @return ResponseEntity with HTTP status code OK
	 */
	@GetMapping
	public ResponseEntity<String> defaultAction() {
		return new ResponseEntity<>("Welcome to Credit Card Payment Application ",HttpStatus.OK);
	}
}