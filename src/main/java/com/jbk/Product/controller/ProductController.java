package com.jbk.Product.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jbk.Product.Exception.CustomExceptionHandler;
import com.jbk.Product.Exception.ProductNotFoundEx;
import com.jbk.Product.entity.Product;
import com.jbk.Product.services.ProductServices;

@RestController
public class ProductController {

	@Autowired
	private ProductServices productServices;

	@PostMapping(value = "/saveProduct")
	public ResponseEntity<Boolean> saveProduct(@Valid @RequestBody Product product) {

		boolean isAdded = productServices.saveProduct(product);

		if (isAdded) {
			return new ResponseEntity<Boolean>(isAdded, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Boolean>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "listOfProduct")
	public ResponseEntity<List<Product>> getListOfProduct() {
		List<Product> list = productServices.getAllProduct();
		if (list != null) {
			return new ResponseEntity<List<Product>>(list, HttpStatus.ACCEPTED);
		} else {
			throw new ProductNotFoundEx("Product is not Available !!!");
		}
	}

	@DeleteMapping(value = "deleteProductById")
	public ResponseEntity<Boolean> deleteProductById(@RequestParam String productId) {
		boolean isDeleted = productServices.deleteProductById(productId);
		if (isDeleted) {
			return new ResponseEntity<Boolean>(isDeleted, HttpStatus.ACCEPTED);

		} else {
			throw new ProductNotFoundEx(productId+" : This ProductId is not Available !!!");
		}

	}

	@PutMapping(value = "updateProductById")
	public ResponseEntity<Boolean> updateProduct(@RequestBody Product product) {
		boolean isUpdated = productServices.updateProduct(product);
		if (isUpdated) {
			return new ResponseEntity<Boolean>(isUpdated, HttpStatus.ACCEPTED);
		} else
			throw new ProductNotFoundEx(" This ProductId is not Available for Update !!!");
	}

	@GetMapping(value = "/getProductById/{productId}")
	public ResponseEntity<Product> getProductById(@PathVariable String productId) {
		Product product = productServices.getProductById(productId);
		if (product != null) {
			return new ResponseEntity<Product>(product, HttpStatus.OK);
		} else
			throw new ProductNotFoundEx(productId+" : this productId is not available !!!");
	}

	@GetMapping(value = "/getProductByName/{productName}")
	public ResponseEntity<List<Product>> getProductByNameList(@PathVariable String productName) {
		List<Product> list = productServices.getProductByName(productName);
		if (list != null) {
			return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
		} else 
			throw new ProductNotFoundEx(productName+" : This Product is not Available !!!");
		
	}

	@GetMapping(value = "maxPriceProduct")
	public ResponseEntity<Product> getMaxPriceProduct() {

		Product product = productServices.getMaxPriceProduct();
		if (product != null) {
			return new ResponseEntity<Product>(product, HttpStatus.OK);
		} else
			throw new ProductNotFoundEx("  Product is not Available !!!");
	}

	@GetMapping(value = "/sortProduct")
	public ResponseEntity<List<Product>> sortProduct(@RequestParam String sortBy) {
		List<Product> list = productServices.sortProduct(sortBy);
		if (list != null) {
			return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
		} else {
			throw new ProductNotFoundEx("  Product is not Available !!!");
		}
	}

	@GetMapping(value = "/productCount")
	public ResponseEntity<Long> countOfProduct() {
		long count = productServices.countOfProducts();
		if (count > 0) {
			return new ResponseEntity<Long>(count, HttpStatus.OK);
		} else
			throw new ProductNotFoundEx("  Product is not Available !!!");
	}

	@GetMapping(value = "/minPriceProduct")
	public ResponseEntity<Product> minPriceProduct() {
		Product product = productServices.getMinPriceProduct();
		if (product != null) {
			return new ResponseEntity<Product>(product, HttpStatus.OK);
		}
		throw new ProductNotFoundEx("  Product is not Available !!!");
	}

	@GetMapping(value = "/sumPrice")
	public ResponseEntity<Double> sumOfPrice() {
		double sum = productServices.getSumOfProductPrice();
		if (sum > 0) {
			return new ResponseEntity<Double>(sum, HttpStatus.OK);
		} else
			throw new ProductNotFoundEx("  Product is not Available !!!");
	}

	@GetMapping(value = "/maxPriceHib")
	public ResponseEntity<List<Product>>getMaxPrice() {
		List<Product> list = productServices.getMaxPriceProductHib();
		 if(list!=null)
			return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
		else
			throw new ProductNotFoundEx("  Product is not Available !!!");
	}

	@GetMapping(value = "/countHib")
	public ResponseEntity<Long> getCount() {
		long count = productServices.countOfProductsHib();
		if (count > 0)
			return new ResponseEntity<Long>(count, HttpStatus.OK);
		else
			throw new ProductNotFoundEx("  Product is not Available !!!");
	}
	
	@GetMapping(value ="/sumOfPriceHib")
	public ResponseEntity<Long>sumOfPriceHib(){
		Long sumprice = productServices.getSumOfProductPriceHib();
		if(sumprice >0)
			return new ResponseEntity<Long>(sumprice, HttpStatus.OK);
		else
			throw new ProductNotFoundEx("  Product is not Available !!!");
	}
	
	@GetMapping(value = "/sortHib")
	public ResponseEntity<List<Product>>sortByHib(@RequestParam String sort){
		List<Product> list = productServices.sortByHib(sort);
		if(!list.isEmpty()) {
			return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
		}
		else
			throw new ProductNotFoundEx("  Product is not Available !!!");
	}
}
