package kr.co.farmstory3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import kr.co.farmstory3.db.DBConfig;
import kr.co.farmstory3.vo.MemberVo;
import kr.co.farmstory3.vo.TermsVo;
import kr.co.farmstory3.db.Sql;

public class MemberDao {
	private static MemberDao instance = new MemberDao();
	public static MemberDao getInstance() {
		return instance;
	}
	private MemberDao() {}
	public void insertMember(MemberVo mv) {
		
		try {
			
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.INSERT_MEMBER);
			
			
			psmt.setString(1, mv.getUid());
			psmt.setString(2, mv.getPass());
			psmt.setString(3, mv.getName());
			psmt.setString(4, mv.getNick());
			psmt.setString(5, mv.getEmail());
			psmt.setString(6, mv.getHp());
			psmt.setString(7, mv.getZip());
			psmt.setString(8, mv.getAdd1());
			psmt.setString(9, mv.getAdd2());
			psmt.setString(10, mv.getRegip());
			psmt.executeUpdate();
			
			
			psmt.close();
			conn.close();
		} catch (Exception e) {
			
			e.printStackTrace();
		
		}
	}
	public TermsVo selectTerms() {
		TermsVo tv = null;
		try {
			
			Connection conn = DBConfig.getInstance().getConnection();
			Statement stmt = conn.createStatement(); // 어떤 조건을 붙일경우에는 preparedstatement사용하면된다 
			
			ResultSet rs = stmt.executeQuery(Sql.SELECT_TERMS);
			if(rs.next()) {
				tv = new TermsVo();
				tv.setTerms(rs.getString(1));
				tv.setPrivacy(rs.getString(2));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return tv;
	}
	public MemberVo selectMember(String uid, String pass) {
		MemberVo mv = null; 
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_MEMBER);
			psmt.setString(1, uid);
			psmt.setString(2, pass);
			ResultSet rs = psmt.executeQuery();
			if(rs.next()) {
				mv = new MemberVo();
				mv.setUid(rs.getString(1));
				mv.setPass(rs.getString(2));
				mv.setName(rs.getString(3));
				mv.setNick(rs.getString(4));
				mv.setEmail(rs.getString(5));
				mv.setHp(rs.getString(6));
				mv.setGrade(rs.getInt(7));
				mv.setZip(rs.getString(8));
				mv.setAdd1(rs.getString(9));
				mv.setAdd2(rs.getString(10));
				mv.setRegip(rs.getString(11));
				mv.setRdate(rs.getString(12));
			}
			rs.close();
			psmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
		
	}
	public int  selectCountUid(String uid) {
		int count = 0;
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_COUNT_UID);
			psmt.setString(1, uid);
			ResultSet rs = psmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	public void selectMembers() {}
	public void updateMember() {}
	public void deleteMember() {}
}
