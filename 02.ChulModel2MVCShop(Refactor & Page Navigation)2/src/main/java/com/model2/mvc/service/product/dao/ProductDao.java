package com.model2.mvc.service.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;


/**
 * Servlet implementation class ProductDAO
 */

public class ProductDao{
	
	public ProductDao() {
		
	}
	
	public Product findProduct(int prodNo) throws Exception{
		
		Connection con = DBUtil.getConnection();
//		System.out.println("ProductDAO에 fineProduct에 DBUtil 연결 완료");

		String sql = " select * from PRODUCT where PROD_NO=?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, prodNo);

		ResultSet rs = stmt.executeQuery();
//		System.out.println("ProductDAO: 제품select쿼리 입력완료");

		Product product = null;
		while (rs.next()) {
			product = new Product();
			product.setProdNo(rs.getInt("PROD_NO"));
			product.setProdName(rs.getString("PROD_NAME"));
			product.setProdDetail(rs.getString("PROD_DETAIL"));
			product.setManuDate(rs.getString("MANUFACTURE_DAY"));
			product.setPrice(rs.getInt("PRICE"));
			product.setFileName(rs.getString("IMAGE_FILE"));
			product.setRegDate(rs.getDate("REG_DATE"));
			
		}
		
		con.close();
		System.out.println("ProductDAO.findProduct aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		return product;
	}
	
	public void insertProduct(Product product)throws Exception{
		Connection con = DBUtil.getConnection();

		String sql = "	insert into PRODUCT(PROD_NO, PROD_NAME, PROD_DETAIL, MANUFACTURE_DAY, PRICE, IMAGE_FILE, REG_DATE) \r\n"
				+ "			    values (seq_product_prod_no.NEXTVAL, ?, ?, ?, ?, ?, SYSDATE)";
		
		PreparedStatement stmt = con.prepareStatement(sql);
//		stmt.setInt(1, productVO.getProdNo());
		stmt.setString(1, product.getProdName());
		System.out.println("product.getProdName() :"+product.getProdName());
		stmt.setString(2, product.getProdDetail());
		stmt.setString(3, product.getManuDate());
		stmt.setInt(4, product.getPrice());
		stmt.setString(5, product.getFileName());
//		stmt.setDate(7, productVO.getRegDate());
		stmt.executeUpdate();
		System.out.println("ProductDAO.insertProduct bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
		
		con.close();
	}
	
	
	public Map<String,Object> getProductList(Search search) throws Exception{
		
		Map<String , Object>  map = new HashMap<String, Object>();
		
		Connection con = DBUtil.getConnection();
		
		String sql = "select * from product ";
		
		if (search.getSearchCondition() != null) {
			if (search.getSearchCondition().equals("0")) {
				sql += " where PROD_NO LIKE'%" + search.getSearchKeyword()
						+ "%'";
			} else if (search.getSearchCondition().equals("1")) {
				sql += " where PROD_NAME LIKE'%" + search.getSearchKeyword()
						+ "%'";
			} else if (search.getSearchCondition().equals("2")) {
				sql += " where PRICE LIKE'%" + search.getSearchKeyword()
				+ "%'";
			}
		}
		sql += " order by PROD_NO";

		System.out.println("ProductDAO::Original SQL :: " + sql);
		
		//TotalCount Get
		int totalCount = this.getTotalCount(sql);
		System.out.println("ProductDAO :: totalCount  :: " + totalCount);
		
		//==> CurrentPage 게시물만 받도록 Query 다시구성
		sql = makeCurrentPageSql(sql, search);
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();

		System.out.println(search);
		
		List<Product> list = new ArrayList<Product>();
		
		while(rs.next()){
			Product product = new Product();
			product.setProdNo(rs.getInt("prod_no"));
			product.setProdName(rs.getString("user_name"));
			product.setPrice(rs.getInt("price"));
			list.add(product);
		}
		//==> totalCount 정보 저장
				map.put("totalCount", new Integer(totalCount));
				//==> currentPage 의 게시물 정보 갖는 List 저장
				map.put("list", list);

				rs.close();
				stmt.close();
				con.close();
				System.out.println("UserDao.getUserList return map ="+map);
				return map;
		}
		
		
	
	
	public void updateProduct(Product product) throws Exception{
		
		Connection con = DBUtil.getConnection();

		String sql = "	update PRODUCT set PROD_NAME=?, PROD_DETAIL=?, \r\n"
										+ "MANUFACTURE_DAY=?, PRICE=?, IMAGE_FILE=?, REG_DATE=? \r\n"
										+ "WHERE PROD_NO=?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
//		stmt.setInt(1, productVO.getProdNo());
		stmt.setString(1, product.getProdName());
		stmt.setString(2, product.getProdDetail());
		stmt.setString(3, product.getManuDate());
		stmt.setInt(4, product.getPrice());
		stmt.setString(5, product.getFileName());
		stmt.setDate(6, product.getRegDate());
		stmt.setInt(7, product.getProdNo());
		stmt.executeUpdate();
		System.out.println("ProductDAO.updateProduct dddddddddddddddddddddddddddddddddd");
		
		con.close();
	}
	
	private int getTotalCount(String sql) throws Exception {
		
		sql = "SELECT COUNT(*) "+
		          "FROM ( " +sql+ ") countTable";
		
		Connection con = DBUtil.getConnection();
		PreparedStatement pStmt = con.prepareStatement(sql);
		ResultSet rs = pStmt.executeQuery();
		
		int totalCount = 0;
		if( rs.next() ){
			totalCount = rs.getInt(1);
		}
		
		pStmt.close();
		con.close();
		rs.close();
		
		return totalCount;
	}
	
	// 게시판 currentPage Row 만  return 
	private String makeCurrentPageSql(String sql , Search search){
		sql = 	"SELECT * "+ 
					"FROM (		SELECT inner_table. * ,  ROWNUM AS row_seq " +
									" 	FROM (	"+sql+" ) inner_table "+
									"	WHERE ROWNUM <="+search.getCurrentPage()*search.getPageSize()+" ) " +
					"WHERE row_seq BETWEEN "+((search.getCurrentPage()-1)*search.getPageSize()+1) +" AND "+search.getCurrentPage()*search.getPageSize();
		
		System.out.println("ProductDAO :: make SQL :: "+ sql);	
		System.out.println("ProductDao.makeCurrentPageSql return sql ="+sql);
		return sql;
	}
}
