package com.model2.mvc.view.product;

import java.awt.Menu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServletImpl;



public class UpdateProductAction extends Action {

	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		int prodNo=Integer.parseInt(request.getParameter("prodNo"));
//		System.out.println("UpdateProductAction에 request로 받은:"+prodNo);
		
		Product product=new Product();
		product.setProdNo(Integer.parseInt(request.getParameter("prodNo")));
		product.setProdName(request.getParameter("prodName"));
		product.setProdDetail(request.getParameter("prodDetail"));
		product.setPrice(Integer.parseInt(request.getParameter("price")));
		product.setProTranCode(request.getParameter("prodTranCode"));
		
		ProductService service=new ProductServletImpl();
		service.updateProduct(product);
		
//		HttpSession session=request.getSession();
//		String sessionId=String.valueOf(((ProductVO)session.getAttribute("product")).getProdNo());
	
//		if(sessionId.equals(prodNo)){
//			session.setAttribute("product", productVO);
////			System.out.println("UpdateProductAction에 sessionId "+prodNo);
//		}
		request.setAttribute("Product", product);
//		System.out.println("UpdateProductAction에 request에 setAttribute 받은 prodctVO"+productVO);
//		System.out.println("4.여기는 UpdateProductAction 완료");
		System.out.println("UpdateProductAction 4444444444444444444444444444444444444444");
		
		return "forward:/product/getProduct.jsp?menu=manage";
	}
}