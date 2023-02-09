package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.impl.ProductServletImpl;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.user.ProductService;


public class GetProductAction extends Action{

	public String execute(	HttpServletRequest request,HttpServletResponse response) throws Exception  {
		int prodNo=Integer.parseInt(request.getParameter("prodNo"));
		System.out.println("GetProductAction"+prodNo);
//		ProductVO productVO = new ProductVO(); 
//		productVO = (ProductVO)request.getAttribute("ProductVO");
		
		ProductService service=new ProductServletImpl();
		ProductVO productVO=service.getProduct(prodNo);
		
		String menu = request.getParameter("menu");
		request.setAttribute("ProductVO", productVO);
		
//		if (menu.equals("manage")) {
//			return "forward:/product/updateProduct.jsp";
		
//		}
		System.out.println("GetProductAction 2222222222222222222222222222222222");
		if(menu.equals("manage")) {
			return "forward:/updateProductView.do";	
		}else {
			return "forward:/product/getProduct.jsp";
		}
		
	}
	
	
	
}