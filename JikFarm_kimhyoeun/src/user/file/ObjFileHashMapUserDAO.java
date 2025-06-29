package user.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

import user.HashMapUserDAO;
import user.UserVO;

public class ObjFileHashMapUserDAO extends HashMapUserDAO implements FileUserDB {
	
	private String dataFilename = DATA_FILE + ".obj";
	public ObjFileHashMapUserDAO() {
		loadUsers();
	}
	
	@Override
	public void saveUsers() {
		try (
				FileOutputStream fos = new FileOutputStream(dataFilename);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
					
			) {
				oos.writeObject(userDB);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	}
	
	@Override
	public void loadUsers() {
		
		try (
				FileInputStream fis = new FileInputStream(dataFilename);
				ObjectInputStream ois = new ObjectInputStream(fis);
			) {
				userDB = (Map<String, UserVO>)ois.readObject();
				for (UserVO user : userDB.values()) {
					if (user.getUserNo() >= userSeq)
						userSeq = user.getUserNo() + 1;
				}
				
			} catch (FileNotFoundException e) {
				System.out.println("[DB로딩] " + dataFilename + "가 없습니다.");
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		
	}
	
	@Override
	public boolean insertUser(UserVO user) {
		boolean result = super.insertUser(user);
		if (result) saveUsers();
		return result;
	}
	
	@Override
	public boolean updateUser(UserVO newUser) {
		boolean result = super.updateUser(newUser);
		if (result) saveUsers();
		return result;
	}
	
	@Override
	public boolean deleteUser(String id) {
		boolean result = super.deleteUser(id);
		if (result) saveUsers();
		return result;
	}

}
	


