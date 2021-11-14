package kr.co.farmstory2.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import kr.co.farmstory2.bean.MemberBean;
import kr.co.farmstory2.bean.TermsBean;
import kr.co.farmstory2.db.DBConfig;
import kr.co.farmstory2.db.Sql;

public class MemberDao {

	private static MemberDao instance = new MemberDao();
	
	public static MemberDao getInstance() {
		return instance;
	}
	
	private MemberDao() {}
	
	public void insertMember(MemberBean mb) {
		try{
			Connection conn= DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.INSERT_MEMBER);
			psmt.setString(1, mb.getUid());
			psmt.setString(2, mb.getPass());
			psmt.setString(3, mb.getName());
			psmt.setString(4, mb.getNick());
			psmt.setString(5, mb.getEmail());
			psmt.setString(6, mb.getHp());
			psmt.setString(7, mb.getZip());
			psmt.setString(8, mb.getAdd1());
			psmt.setString(9, mb.getAdd2());
			psmt.setString(10, mb.getRegip());
			psmt.executeUpdate();		
			psmt.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public TermsBean selectTerms() {
		
		TermsBean tb = new TermsBean();
		
		try{
			// 1,2단계
			Connection conn= DBConfig.getInstance().getConnection();
			
			// 3단계 - SQL 실행객체 생성
			Statement stmt = conn.createStatement();		
			// 4단계 - SQL 실행
			ResultSet rs = stmt.executeQuery(Sql.SELECT_TERMS);
			
			// 5단계 - 결과셋 처리(SELECT문 경우)
			if(rs.next()){
				tb.setTerms(rs.getString(1));
				tb.setPrivacy(rs.getString(2));			
			}
			
			// 6단계 - 데이터베이스 종료
			rs.close();
			stmt.close();
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return tb;
	}
	
	public int selectCountUserInfo(int type, String checkData) {
			int result = 0;
			PreparedStatement psmt = null;
			try {
				Connection conn  = DBConfig.getInstance().getConnection();
				if(type == 1) {
					 psmt = conn.prepareStatement(Sql.SELECT_COUNT_UID);
				}else if( type ==2 ) {
					 psmt = conn.prepareStatement(Sql.SELECT_COUNT_NICK);
				}else if(type == 3) {
					 psmt = conn.prepareStatement(Sql.SELECT_COUNT_EMAIL);
				}else if(type == 4) {
					psmt = conn.prepareStatement(Sql.SELECT_COUNT_HP);
				}
				psmt.setString(1, checkData);
				ResultSet rs = psmt.executeQuery();
				if(rs.next()) {

				}
				rs.close();
				psmt.close();
				conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			return result;
	}	
	
	public MemberBean selectMember(String uid, String pass) {
		
		MemberBean mb = null;
		
		try{
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_MEMBER);
			psmt.setString(1, uid);
			psmt.setString(2, pass);
			ResultSet rs = psmt.executeQuery();
			
			if(rs.next()) {
				mb = new MemberBean();
				mb.setUid(rs.getString(1));
				mb.setPass(rs.getString(2));
				mb.setName(rs.getString(3));
				mb.setNick(rs.getString(4));
				mb.setEmail(rs.getString(5));
				mb.setHp(rs.getString(6));
				mb.setGrade(rs.getInt(7));
				mb.setZip(rs.getString(8));
				mb.setAdd1(rs.getString(9));
				mb.setAdd2(rs.getString(10));
				mb.setRegip(rs.getString(11));
				mb.setRdate(rs.getString(12));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return mb;
	}
	
	public void selectMembers() {}
	public void updateMember() {}
	public void deleteMember() {}
}
	