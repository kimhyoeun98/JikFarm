package product.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.Map;

import product.HashMapProductDAO;
import product.ProductVO;

public class ObjFileHashMapProductDAO extends HashMapProductDAO implements FileProductDB {
	
	private String dataFilename = DATA_FILE + ".obj";
	
	public ObjFileHashMapProductDAO() {
		loadProducts();
	}
	
	@Override
	public void saveProducts() {
		
		try (
			FileOutputStream fos = new FileOutputStream(dataFilename);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
				
		) {
			oos.writeObject(productDB);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void loadProducts() {
		
		try (
			FileInputStream fis = new FileInputStream(dataFilename);
			ObjectInputStream ois = new ObjectInputStream(fis);
		) {
			
			productDB = (Map<Integer, ProductVO>)ois.readObject();
			productSeq = Collections.max(productDB.keySet()) + 1;
			
		} catch (FileNotFoundException e) {
			System.out.println("[DB로딩] " + dataFilename + "가 없습니다.");
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public boolean insertProduct(ProductVO product) {
		boolean result = super.insertProduct(product);
		if (result) saveProducts();
		return result;
	}

	@Override
	public boolean updateProduct(ProductVO newProduct) {
		boolean result = super.updateProduct(newProduct);
		if (result) saveProducts();
		return result;
	}
	
	@Override
	public boolean deleteProduct(int productNo) {
		boolean result = super.deleteProduct(productNo);
		if (result) saveProducts();
		return result;
	}

}
