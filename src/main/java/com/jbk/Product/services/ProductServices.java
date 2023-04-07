package com.jbk.Product.services;

import java.util.List;

import com.jbk.Product.entity.Product;

public interface ProductServices {

	public boolean saveProduct(Product product);

	public List<Product> getAllProduct();

	public boolean deleteProductById(String productId);

	public boolean updateProduct(Product product);

	public Product getProductById(String productId);

	public List<Product> getProductByName(String productName);

	public Product getMaxPriceProduct();

	public List<Product> sortProduct(String sortBy);

	public long countOfProducts();

	public Product getMinPriceProduct();

	public double getSumOfProductPrice();

	public List<Product> getMaxPriceProductHib();

	public long countOfProductsHib();

	public Product getMinPriceProductHib();

	public long getSumOfProductPriceHib();
	public List<Product>sortByHib(String sort);


}
