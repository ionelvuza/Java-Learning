package proj.wsmobileapp.app.ws.userservice.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proj.wsmobileapp.app.ws.shared.Utils;
import proj.wsmobileapp.app.ws.ui.model.request.UserDetailsRequestModel;
import proj.wsmobileapp.app.ws.ui.model.response.UserRest;
import proj.wsmobileapp.app.ws.userservice.UserService;

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
		returnValue.setEmail(userDetails.getEmail());
		returnValue.setFirstName(userDetails.getFirstName());
		returnValue.setLastName(userDetails.getLastName());
		
		String userId = utils.generateUserId();
		returnValue.setUserId(userId);
		
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
	public UserRest createUser(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(String userId) {
		
		users.remove(userId);
	}

	@Override
	public void updateUser(String userId, UserRest storedUserDetails) {
		// TODO Auto-generated method stub
		users.put(userId, storedUserDetails);
	}

}