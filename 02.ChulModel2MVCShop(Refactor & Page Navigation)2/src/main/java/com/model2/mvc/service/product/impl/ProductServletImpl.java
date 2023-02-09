package com.model2.mvc.service.product.impl;

import java.util.HashMap;

import com.model2.mvc.common.*;
import com.model2.mvc.service.product.dao.*;


public class ProductServletImpl implements ProductService{
	
	private ProductDAO productDAO;
	
	public ProductServletImpl() {
		productDAO = new ProductDAO();
	}

	public void addProduct(Product product) throws Exception {
		productDAO.insertProduct(product);
		System.out.println("P_S_Impl :"+product);
	}
	
	public Product getProduct(int prodNo) throws Exception {
		return productDAO.findProduct(prodNo);
	}
	

	public HashMap<String,Object> getProductList(SearchVO searchVO) throws Exception {
		return productDAO.getProductList(searchVO);
	}

	public void updateProduct(Product product) throws Exception {
		productDAO.updateProduct(product);
	}


}