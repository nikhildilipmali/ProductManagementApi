package com.jbk.Product.services;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jbk.Product.dao.ProductDao;
import com.jbk.Product.entity.Product;

@Service
public class ProductServicesImpl implements ProductServices {

	@Autowired
	private ProductDao productDao;

	@Override
	public boolean saveProduct(Product product) {

		String id = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new java.util.Date());
		product.setProductId(id);
		boolean isAdded = productDao.saveProduct(product);

		return isAdded;
	}

	@Override
	public List<Product> getAllProduct() {
		List<Product> list = productDao.getAllProduct();
		return list;
	}

	@Override
	public boolean deleteProductById(String productId) {
		boolean isDelete = productDao.deleteProductById(productId);

		return isDelete;
	}

	@Override
	public boolean updateProduct(Product product) {
		boolean isUpdated = productDao.updateProduct(product);
		return isUpdated;
	}

	@Override
	public Product getProductById(String productId) {
		Product product = productDao.getProductById(productId);

		return product;
	}

	@Override
	public List<Product> getProductByName(String productName) {
		List<Product> list = productDao.getProductByName(productName);
		return list;
	}

	@Override
	public Product getMaxPriceProduct() {
		List<Product> productList = productDao.getAllProduct();
		Product product = null;
		try {
			if (!productList.isEmpty()) {
				product = productList.stream().max(Comparator.comparingDouble(Product::getProductPrice)).get();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;

	}

	@Override
	public List<Product> sortProduct(String sortBy) {
		List<Product> list = productDao.sortProduct(sortBy);
		List<Product> lists = null;
		try {
			if (sortBy.equalsIgnoreCase("productName")) {

				lists = list.stream().sorted(Comparator.comparing(Product::getProductName)).toList();

			} else if (sortBy.equalsIgnoreCase("productId")) {
				lists = list.stream().sorted(Comparator.comparing(Product::getProductId)).toList();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lists;
	}

	@Override
	public long countOfProducts() {

		List<Product> list = productDao.getAllProduct();
		long countofProduct = 0L;
		try {
			countofProduct = list.stream().count();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return countofProduct;
	}

	@Override
	public Product getMinPriceProduct() {
		List<Product> list = productDao.getAllProduct();
		Product product = null;

		try {

			product = list.stream().min(Comparator.comparingDouble(Product::getProductPrice)).get();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}

	@Override
	public double getSumOfProductPrice() {
		List<Product> list = productDao.getAllProduct();
		double sumPrice = 0;
		try {
			if (!list.isEmpty())
				sumPrice = list.stream().mapToDouble(Product::getProductPrice).sum();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sumPrice;
	}

	@Override
	public List<Product> getMaxPriceProductHib() {
		List<Product> list = productDao.getMaxPriceProductHib();

		return list;
	}

	@Override
	public long countOfProductsHib() {

		long count = productDao.countOfProductsHib();
		return count;
	}

	@Override
	public Product getMinPriceProductHib() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getSumOfProductPriceHib() {
		long sumOfPrice = productDao.getSumOfProductPrice();
		return sumOfPrice;
	}

	@Override
	public List<Product> sortByHib(String sort) {
	List<Product> list = productDao.sortByHib(sort);
		return list;
	}

}
