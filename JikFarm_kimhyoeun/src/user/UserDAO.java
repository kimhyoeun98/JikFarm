package user;

import java.util.List;

public interface UserDAO {
	boolean insertUser(UserVO user);
	UserVO selectUser(String id);
	List<UserVO> selectAllUsers();
	boolean updateUser(UserVO newUser);
	boolean deleteUser(String id);
}
