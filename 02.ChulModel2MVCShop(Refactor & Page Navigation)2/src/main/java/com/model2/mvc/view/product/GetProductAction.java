package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServletImpl;






public class GetProductAction extends Action{

	public String execute(	HttpServletRequest request,HttpServletResponse response) throws Exception  {
		int prodNo=Integer.parseInt(request.getParameter("prodNo"));
		System.out.println("GetProductAction.java prodNo= "+prodNo);
//		ProductVO productVO = new ProductVO(); 
//		productVO = (ProductVO)request.getAttribute("ProductVO");
		
		ProductService service=new ProductServletImpl();
		Product product=service.getProduct(prodNo);
		
		String menu = request.getParameter("menu");
		request.setAttribute("Product", product);
		
//		if (menu.equals("manage")) {
//			return "forward:/product/updateProduct.jsp";
		
//		}
		System.out.println("GetProductAction.java 2222222222222222222222222222222222\n");
		if(menu.equals("manage")) {
			return "forward:/updateProductView.do";	
		}else {
			return "forward:/product/getProduct.jsp";
		}
		
	}
	
	
	
}