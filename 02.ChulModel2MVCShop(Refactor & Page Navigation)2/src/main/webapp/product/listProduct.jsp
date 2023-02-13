<%@page import="com.model2.mvc.view.product.GetProductAction"%>
<%@ page contentType="text/html; charset=euc-kr" %>

<%@ page import="java.util.List"  %>
<%@ page import="com.model2.mvc.service.domain.Product" %>
<%@ page import="com.model2.mvc.common.Search" %>
<%@page import="com.model2.mvc.common.Page"%>
<%@page import="com.model2.mvc.common.util.*"%>

<%
	List<Product> list= (List<Product>)request.getAttribute("list");
	Page resultPage=(Page)request.getAttribute("resultPage");
	
	Search search = (Search)request.getAttribute("search");
	//==> null 을 ""(nullString)으로 변경
	String searchCondition = CommonUtil.null2str(search.getSearchCondition());
	String searchKeyword = CommonUtil.null2str(search.getSearchKeyword());
	System.out.println("listProduct.jsp search= "+search);
	System.out.println("listProduct.jsp searchCondition= "+searchCondition);
	System.out.println("listProduct.jsp searchKeyword= "+searchKeyword);

	String menu = request.getParameter("menu");
	System.out.println("여기는 listProduct.jsp에 getParameter한 menu값 = "+menu);
%>

<html>
<head>
<title>상품 검색</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
//검색 / page 두가지 경우 모두 Form 전송을 위해 JavaScrpt 이용
function fncGetProductList(currentPage, menu){
	document.getElementById("currentPage").value = currentPage;
	document.getElementById("menu").value = menu;
   	document.detailForm.submit();
}
/* function fncUpdateTranCodeByProd( currentPage, menu, prodNo ) {
	document.getElementById("currentPage").value = currentPage;
	document.getElementById("menu").value = menu;
	document.getElementById("prodNo").value = prodNo;
	document.detailForm.action='/updateTranCodeByProd.do';
   	document.detailForm.submit();		
} */

/* //검색 / page 두가지 경우 모두 Form 전송을 위해 JavaScrpt 이용  
function fncGetProductList(currentPage, searchOrderType, menu) {
	document.getElementById("currentPage").value = currentPage;
	document.getElementById("menu").value = menu;
	document.getElementById("searchOrderType").value = searchOrderType;
   	document.detailForm.submit();		
}

function fncUpdateTranCodeByProd( currentPage, menu, prodNo, tranCode) {
	document.getElementById("currentPage").value = currentPage;
	document.getElementById("menu").value = menu;
	document.getElementById("prodNo").value = prodNo;
	document.getElementById("tranCode").value = tranCode;
	document.detailForm.action='/updateTranCodeByProd.do';
   	document.detailForm.submit();		
} */
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">

<form name="detailForm" action="/listProduct.do" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37">
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">
				<% if (menu.equals("manage")) {%>
					상품 관리
				<% } else {%>
					상품 목록조회
				<% }	%>
				</tr>
			</table>
		</td>
		<td width="12" height="37">
			<img src="/images/ct_ttl_img03.gif" width="12" height="37">
		</td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="right">
			<select name="searchCondition" class="ct_input_g" style="width:80px">
				<option value="0" <%= (searchCondition.equals("0") ? "selected" : "")%>>상품번호</option>
				<option value="1" <%= (searchCondition.equals("1") ? "selected" : "")%>>상품명</option>
				<option value="2" <%= (searchCondition.equals("2") ? "selected" : "")%>>가격</option>
			</select>
			<input 	type="text" name="searchKeyword" value="<%= searchKeyword %>"  class="ct_input_g" 
							style="width:200px; height:20px" >
		</td>
	
		<td align="right" width="70">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="javascript:fncGetProductList('<%=resultPage.getCurrentPage() %>','<%=menu%>');">검색</a>
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td colspan="11" >
		전체  <%= resultPage.getTotalCount()%> 건수, 현재 <%=resultPage.getCurrentPage() %> 페이지</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">상품명</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">가격</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">등록일</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">현재상태</td>
		<td class="ct_line02"></td>
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>
	<%
		for(int i=0; i<list.size(); i++) {
			Product product = list.get(i);
	%>
	<tr class="ct_list_pop">
		<td align="center"><%=i + 1%></td>
		<td></td>
		<td align="left">
			<a href="/getProduct.do?prodNo=<%=product.getProdNo()%>&menu=<%=menu%>"><%= product.getProdName() %></a>
		</td>
		<td></td>
		<td align="left"><%= product.getPrice()%></td>
		<td></td>
		<td align="left"><%= product.getRegDate() %>
		</td>		
	</tr>
	<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1"></td>
	</tr>
	<% } %>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="center">
			<input type="hidden" id="currentPage" name="currentPage" value=""/>
			<input type="hidden" id="menu" name="menu" value=""/>
			<% if( resultPage.getCurrentPage() <= resultPage.getPageUnit() ){ %>
					◀ 이전
			<% }else{ %>
					<a href="javascript:fncGetProductList('<%=resultPage.getCurrentPage()-1%>')">◀ 이전</a>
			<% } %>

			<%	for(int i=resultPage.getBeginUnitPage();i<= resultPage.getEndUnitPage() ;i++){	%>
					<a href="javascript:fncGetProductList('<%=i %>','<%=menu%>');"><%=i %></a>
			<% 	}  %>
			
			<%-- <%	for(int i=resultPage.getBeginUnitPage();i<= resultPage.getEndUnitPage() ;i++){	%>
					<a href="javascript:fncGetProductList('<%=i %>');"><%=i %></a>
			<% 	}  %> --%>
	
			<% if( resultPage.getEndUnitPage() >= resultPage.getMaxPage() ){ %>
					이후 ▶
			<% }else{ %>
					<a href="javascript:fncGetProductList('<%=resultPage.getEndUnitPage()+1%>')">이후 ▶</a>
			<% } %>
		
		
		<%-- <%
			for(int i=1;i<=totalPage;i++){
		%>
					
			<%if (menu.equals("manage")) {%>
			<a href="/listProduct.do?page=<%=i%>&menu=<%=menu%>"><%=i %></a>
			<% }%>
		<%
			}
		%>	 --%>
    	</td>
	</tr>
</table>
<!--  페이지 Navigator 끝 -->
</form>
</div>
<% System.out.println("listProduct.jsp 555555555555555555555555555555"); %>
</body>
</html>