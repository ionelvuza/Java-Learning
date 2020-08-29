package proj.wsmobileapp.app.ws.ui.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

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

import proj.wsmobileapp.app.ws.model.request.UserDetailsRequestModel;
import proj.wsmobileapp.app.ws.model.request.UserDetailsUpdateRequestModel;
import proj.wsmobileapp.app.ws.ui.model.response.UserRest;

@RestController
@RequestMapping("users") // http://localhost:8080/users
public class UserController {

	Map<String, UserRest> users;
	
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
		
		if (users.containsKey(userId)) {
			return new ResponseEntity<>(users.get(userId), HttpStatus.OK);
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
		
		UserRest returnvalue = new UserRest();
		String userId = UUID.randomUUID().toString();
		
		returnvalue.setEmail(userDetails.getEmail());
		returnvalue.setFirstName(userDetails.getFirstName());
		returnvalue.setLastName(userDetails.getLastName());
		returnvalue.setUserId(userId);
		
		if (users == null) users = new HashMap<>();
		users.put(userId, returnvalue);
		
		return new ResponseEntity<UserRest>(returnvalue, HttpStatus.OK);
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
		
		 UserRest storedUserDetails = users.get(userId);
		 storedUserDetails.setFirstName(userDetails.getFirstName());
		 storedUserDetails.setLastName(userDetails.getLastName());
		 
		 users.put(userId, storedUserDetails);
		 
		 return storedUserDetails;
	}
	
	
	@DeleteMapping(path= "/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
		
		users.remove(userId);
		return ResponseEntity.noContent().build();
	}
}
