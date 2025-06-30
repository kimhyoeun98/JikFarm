package user;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashMapUserDAO implements UserDAO {
	
	protected Map<String, UserVO> userDB = new HashMap<>();
	protected int userSeq = 111;
	
	@Override
	public boolean insertUser(UserVO user) {
		
		if (userDB.containsKey(user.getId()) ) return false;
		
		user.setUserNo(userSeq++);
		user.setRegDate(new Date());
		userDB.put(user.getId(), user);
		return true;
	}
	
	@Override
	public UserVO selectUser(String id) {
		return userDB.get(id);
	}

	@Override
	public List<UserVO> selectAllUsers() {
		return new ArrayList<>(userDB.values());
	}

	@Override
	public boolean updateUser(UserVO newUser) {
		userDB.put(newUser.getId(), newUser);
		return true;
	}

	@Override
	public boolean deleteUser(String id) {
		return userDB.remove(id) != null;
	}

}

