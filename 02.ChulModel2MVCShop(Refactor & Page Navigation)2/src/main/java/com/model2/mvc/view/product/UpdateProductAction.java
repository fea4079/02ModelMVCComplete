package com.model2.mvc.view.product;

import java.awt.Menu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.impl.ProductServletImpl;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.user.ProductService;



public class UpdateProductAction extends Action {

	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		int prodNo=Integer.parseInt(request.getParameter("prodNo"));
//		System.out.println("UpdateProductAction�� request�� ����:"+prodNo);
		
		ProductVO productVO=new ProductVO();
		productVO.setProdNo(Integer.parseInt(request.getParameter("prodNo")));
		productVO.setProdName(request.getParameter("prodName"));
		productVO.setProdDetail(request.getParameter("prodDetail"));
		productVO.setPrice(Integer.parseInt(request.getParameter("price")));
		productVO.setProTranCode(request.getParameter("prodTranCode"));
		
		ProductService service=new ProductServletImpl();
		service.updateProduct(productVO);
		
//		HttpSession session=request.getSession();
//		String sessionId=String.valueOf(((ProductVO)session.getAttribute("product")).getProdNo());
	
//		if(sessionId.equals(prodNo)){
//			session.setAttribute("product", productVO);
////			System.out.println("UpdateProductAction�� sessionId "+prodNo);
//		}
		request.setAttribute("ProductVO", productVO);
//		System.out.println("UpdateProductAction�� request�� setAttribute ���� prodctVO"+productVO);
//		System.out.println("4.����� UpdateProductAction �Ϸ�");
		System.out.println("UpdateProductAction 4444444444444444444444444444444444444444");
		
		return "forward:/product/getProduct.jsp?menu=manage";
	}
}