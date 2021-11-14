package kr.co.farmstory2.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import kr.co.farmstory2.bean.TermsBean;
import kr.co.farmstory2.db.DBConfig;
import kr.co.farmstory2.db.Sql;

public class TermsDao {
	private static TermsDao instance = new TermsDao();
	public static TermsDao getInstance() {
		return instance;
	}
	private TermsDao(){}

	public TermsBean selectTerms() {
		TermsBean tb = null;
		try {
			 Connection conn = DBConfig.getInstance().getConnection();
			 Statement smt = conn.createStatement();
			 ResultSet rs = smt.executeQuery(Sql.SELECT_TERMS);
			 if(rs.next()) {
				 tb = new TermsBean();
				 tb.setTerms(rs.getString(1));
				 tb.setPrivacy(rs.getString(2));
			 }
			 rs.close();
			 smt.close();
			 conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return tb;
	}
}
