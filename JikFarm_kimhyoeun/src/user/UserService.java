package user;

import java.util.List;

public interface UserService {
	boolean registUser(UserVO user);
	List<UserVO> listUsers();
	UserVO detailUserInfo(String id);
	UserVO login(String id, String password);
	boolean updatePassword(String id, String oldPassword, String newPassword);
	boolean addUserInfo(String id, String mobile, String email, String address);
	boolean removeUser(String id, String password);
}
