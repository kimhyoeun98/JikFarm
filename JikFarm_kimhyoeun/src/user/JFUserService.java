package user;

import java.util.List;

public class JFUserService implements UserService {
	
	private UserDAO userDAO;
	
	public JFUserService(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	@Override
	public boolean registUser(UserVO user) {
		return userDAO.insertUser(user);
	}

	@Override
	public List<UserVO> listUsers() {
		return userDAO.selectAllUsers();
	}

	@Override
	public UserVO detailUserInfo(String id) {
		return userDAO.selectUser(id);
	}

	@Override
	public UserVO login(String id, String password) {
		// id, password가 동일한 회원이 있는지 확인
		UserVO user = userDAO.selectUser(id);
				
		if (user != null && user.getPassword().equals(password))
			return user;
		
		return null;
	}

	@Override
	public boolean updatePassword(String id, String oldPassword, String newPassword) {
		UserVO user = userDAO.selectUser(id);
		if (user == null) return false;
		
		if (user.getPassword().equals(oldPassword)) {
			user.setPassword(newPassword);
			return userDAO.updateUser(user);
		}
		
		return false;
	}

	@Override
	public boolean addUserInfo(String id, String mobile, String email, String address) {
		UserVO user = userDAO.selectUser(id);
		if (user == null) return false;
		
		user.setMobile(mobile);
		user.setEmail(email);
		user.setAddress(address);
		
		return userDAO.updateUser(user);
	}

	@Override
	public boolean removeUser(String id, String password) {
		UserVO user = userDAO.selectUser(id);
		if (user == null) return false;
		if (!user.getPassword().equals(password)) return false;
		return userDAO.deleteUser(id);
	}

}