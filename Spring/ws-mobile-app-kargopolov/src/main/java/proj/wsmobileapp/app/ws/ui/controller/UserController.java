package proj.wsmobileapp.app.ws.ui.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import proj.wsmobileapp.app.ws.services.userservice.UserService;
import proj.wsmobileapp.app.ws.ui.model.request.UserDetailsRequestModel;
import proj.wsmobileapp.app.ws.ui.model.request.UserDetailsUpdateRequestModel;
import proj.wsmobileapp.app.ws.ui.model.response.UserRest;

@RestController
@RequestMapping("users") // http://localhost:8080/users
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping
	public String getUsers(
			@RequestParam (value="page" , defaultValue= "1"                   ) int    page,
			@RequestParam (value="limit", defaultValue= "50"                  ) int    limit,
			@RequestParam (value="sort" , defaultValue= "desc", required=false) String sort
			) {
		
		return "Get users with page = " + page + " and limit = " + limit + " and sort = " + sort;
	}
	
	
	@GetMapping(path= "/{userId}",
			produces = {
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE
			})
	public ResponseEntity<UserRest> getUser(@PathVariable String userId) {
		
		if (userService.hasUser(userId)){
			return new ResponseEntity<>(userService.getUser(userId), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	
	@PostMapping(
			consumes = {
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE
			},
			produces = {
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE
			})
	public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetails) {
		
		
		UserRest returnValue = userService.createUser(userDetails);		
		return new ResponseEntity<UserRest>(returnValue, HttpStatus.OK);
	}
	
	
	@PutMapping(
			path= "/{userId}",
					consumes = {
							MediaType.APPLICATION_XML_VALUE,
							MediaType.APPLICATION_JSON_VALUE
					},
					produces = {
							MediaType.APPLICATION_XML_VALUE,
							MediaType.APPLICATION_JSON_VALUE
					})
	public UserRest updateUser(@PathVariable String userId, @Valid @RequestBody UserDetailsUpdateRequestModel userDetails) {
		
		 UserRest storedUserDetails = userService.getUser(userId);
		 storedUserDetails.setFirstName(userDetails.getFirstName());
		 storedUserDetails.setLastName(userDetails.getLastName());
		 
		 userService.updateUser(userId, storedUserDetails);
		 return storedUserDetails;
	}
	
	
	@DeleteMapping(path= "/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
		
		userService.deleteUser(userId);
		return ResponseEntity.noContent().build();
	}
}
