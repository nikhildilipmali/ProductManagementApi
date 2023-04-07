package com.jbk.Product.dao;

import java.util.List;

import com.jbk.Product.entity.Product;

public interface ProductDao {

	public boolean saveProduct(Product product);

	public List<Product> getAllProduct();

	public boolean deleteProductById(String productId);

	public boolean updateProduct(Product product);

	public Product getProductById(String productId);

	public List<Product> getProductByName(String productName);

	public List<Product> sortProduct(String sortBy);
	
	

	public List<Product> getMaxPriceProductHib();

	
	public long countOfProductsHib();

	public Product getMinPriceProductHib();

	public long getSumOfProductPrice();
	
	public List<Product>sortByHib(String sort);
	
	

}
