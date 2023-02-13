package com.model2.mvc.service.product.impl;

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.dao.ProductDao;
import com.model2.mvc.service.domain.Product;


public class ProductServletImpl implements ProductService{
	
	private ProductDao productDao;
	
	public ProductServletImpl() {
		productDao = new ProductDao();
	}

	public void addProduct(Product product) throws Exception {
		productDao.insertProduct(product);
		System.out.println("ProductServletImpl.addProduct product:"+product);
	}
	
	public Product getProduct(int prodNo) throws Exception {
		return productDao.findProduct(prodNo);
	}
	

	public Map<String,Object> getProductList(Search search) throws Exception {
		return productDao.getProductList(search);
	}

	public void updateProduct(Product product) throws Exception {
		productDao.updateProduct(product);
	}


}