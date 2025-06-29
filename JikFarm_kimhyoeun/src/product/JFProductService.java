package product;

import java.util.List;

public class JFProductService implements ProductService {
	
	private ProductDAO productDAO;
	
	public JFProductService(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}
	
	@Override
	public boolean registProduct(ProductVO product) {
		return productDAO.insertProduct(product);
	}

	@Override
	public List<ProductVO> listProducts() {
		return productDAO.selectAllProducts();
	}

	@Override
	public ProductVO detailProductInfo(int productNo) {
		
		return productDAO.selectProduct(productNo);
	}

	@Override
	public boolean updateProductPrice(int productNo, int price) {
		ProductVO product = productDAO.selectProduct(productNo);
		
		if (product != null) {
			product.setPrice(price);
			productDAO.updateProduct(product);
			return true;
		}
		
		return false;
	}

	@Override
	public boolean updateProductInstock(int productNo, int instock) {
		ProductVO product = productDAO.selectProduct(productNo);
		
		if (product != null) {
			product.setInstock(instock);
			productDAO.updateProduct(product);
			return true;
		}
		
		return false;
	}

	@Override
	public boolean removeProduct(int productNo) {
		return productDAO.deleteProduct(productNo);		
	}

}
