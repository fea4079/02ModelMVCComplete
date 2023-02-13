package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServletImpl;




public class UpdateProductViewAction extends Action{


	public String execute(	HttpServletRequest request,	HttpServletResponse response) throws Exception {
//		int prodNo=Integer.parseInt(request.getParameter("prodNo"));
		Product product = new Product(); 
		product = (Product)request.getAttribute("ProductVO");
		ProductService service=new ProductServletImpl();
//		ProductVO productVO=service.getProduct(prodNo);
		
		String menu = request.getParameter("menu");
		System.out.println("UpdateProductViewAction.java menu="+menu);	
		request.setAttribute("Porduct", product);
		System.out.println("UpdateProductViewAction.java 555555555555555555555555555555555555\n");
		return "forward:/product/updateProductView.jsp";
	}
}
