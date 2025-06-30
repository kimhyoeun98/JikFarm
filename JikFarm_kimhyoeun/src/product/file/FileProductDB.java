package product.file;

public interface FileProductDB {
	String DATA_FILE = "./data/productDB";
	void saveProducts();
	void loadProducts();

}
