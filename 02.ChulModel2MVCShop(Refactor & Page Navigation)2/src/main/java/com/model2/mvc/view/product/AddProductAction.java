package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.user.ProductService;
import com.model2.mvc.service.product.impl.ProductServletImpl;
import com.model2.mvc.service.product.vo.ProductVO;



public class AddProductAction extends Action {

//	@Override
//	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		return null;
//	}

	
	
	public String execute(	HttpServletRequest request,
							HttpServletResponse response) throws Exception {
		
		ProductVO productVO=new ProductVO();
		
//		System.out.println("��ǰ��ȣ: "+Integer.parseInt(request.getParameter("prodNo").trim()));
//		productVO.setProdNo(Integer.parseInt(request.getParameter("prodNo").trim()));
//		System.out.println("��ǰ��ȣ: "+Integer.parseInt(request.getParameter("prodNo").trim()));
//		System.out.println("��ǰ��ȣ �Է¿Ϸ�");
		productVO.setProdName(request.getParameter("prodName"));
		productVO.setProdDetail(request.getParameter("prodDetail"));
		productVO.setManuDate(request.getParameter("manuDate"));
		productVO.setPrice(Integer.parseInt(request.getParameter("price")));
		System.out.println("AddProductAction�� ��ǰ����(PRICE) �Է¿Ϸ�");
		
		productVO.setFileName(request.getParameter("filename"));
//		productVO.setRegDate(request.getParameter("regDate"));
		
//		System.out.println("addProductAction�� productVO�� ���� :"+productVO);
		
		ProductService service=new ProductServletImpl();
		service.addProduct(productVO);
		
		request.setAttribute("ProductVO", productVO);
		System.out.println("AddProductAction 1111111111111111111111");
		
		return "forward:/product/getProduct.jsp";
	}

}