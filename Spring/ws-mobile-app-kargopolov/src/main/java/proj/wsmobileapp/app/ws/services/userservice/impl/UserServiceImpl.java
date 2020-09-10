package proj.wsmobileapp.app.ws.services.userservice.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proj.wsmobileapp.app.ws.general.shared.Utils;
import proj.wsmobileapp.app.ws.services.userservice.UserService;
import proj.wsmobileapp.app.ws.ui.model.request.UserDetailsRequestModel;
import proj.wsmobileapp.app.ws.ui.model.response.UserRest;

@Service
public class UserServiceImpl implements UserService{

	Map<String, UserRest> users;
	Utils utils;
	
	public UserServiceImpl() {}
	
	@Autowired
	public UserServiceImpl(Utils utils)
	{
		this.utils =utils;
	}
	
	@Override
	public UserRest createUser(UserDetailsRequestModel userDetails) {
		
		UserRest returnValue = new UserRest();
		String   userId      = utils.generateUserId();
		
		returnValue.setEmail    (userDetails.getEmail());
		returnValue.setFirstName(userDetails.getFirstName());
		returnValue.setLastName (userDetails.getLastName());
		returnValue.setUserId   (userId);
		
		if(users == null) users = new HashMap<>();
		
		users.put(userId, returnValue);
		
		return returnValue;
		
	}

	@Override
	public boolean hasUser(String userId) {

		return users.containsKey(userId);
	}


	@Override
	public UserRest getUser(String userId) {

		return users.get(userId);
	}

	@Override
	public void deleteUser(String userId) {
		
		users.remove(userId);
	}

	@Override
	public void updateUser(String userId, UserRest storedUserDetails) {

		users.put(userId, storedUserDetails);
	}

}