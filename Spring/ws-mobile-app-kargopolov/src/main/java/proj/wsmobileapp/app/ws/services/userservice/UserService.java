package proj.wsmobileapp.app.ws.services.userservice;

import proj.wsmobileapp.app.ws.ui.model.request.UserDetailsRequestModel;
import proj.wsmobileapp.app.ws.ui.model.response.UserRest;

public interface UserService {
	UserRest createUser(UserDetailsRequestModel userDetails);
	boolean  hasUser   (String userId);
	UserRest getUser   (String userId);
	void     deleteUser(String userId);
	void     updateUser(String userId, UserRest storedUserDetails);
}
