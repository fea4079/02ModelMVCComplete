package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServletImpl;


public class AddProductAction extends Action {

//	@Override
//	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		return null;
//	}

	
	
	public String execute(	HttpServletRequest request,
							HttpServletResponse response) throws Exception {
		
		Product product=new Product();
		
//		System.out.println("제품번호: "+Integer.parseInt(request.getParameter("prodNo").trim()));
//		productVO.setProdNo(Integer.parseInt(request.getParameter("prodNo").trim()));
//		System.out.println("제품번호: "+Integer.parseInt(request.getParameter("prodNo").trim()));
//		System.out.println("제품번호 입력완료");
		product.setProdName(request.getParameter("prodName"));
		product.setProdDetail(request.getParameter("prodDetail"));
		product.setManuDate(request.getParameter("manuDate"));
		product.setPrice(Integer.parseInt(request.getParameter("price")));
		System.out.println("AddProductAction.java 상품가격(PRICE) 입력완료");
		
		product.setFileName(request.getParameter("filename"));
//		productVO.setRegDate(request.getParameter("regDate"));
		
//		System.out.println("addProductAction의 productVO의 값은 :"+productVO);
		
		ProductService service=new ProductServletImpl();
		service.addProduct(product);
		
		request.setAttribute("Product", product);
		System.out.println("AddProductAction.java 1111111111111111111111");
		
		return "forward:/product/getProduct.jsp";
	}

}