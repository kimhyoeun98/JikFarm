package user.file;

public interface FileUserDB {
	String DATA_FILE = "./data/memberDB";
	void saveUsers();
	void loadUsers();
}
