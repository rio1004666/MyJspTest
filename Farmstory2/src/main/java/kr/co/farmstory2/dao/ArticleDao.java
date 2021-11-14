package kr.co.farmstory2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.co.farmstory2.bean.ArticleBean;
import kr.co.farmstory2.bean.FileBean;
import kr.co.farmstory2.db.DBConfig;
import kr.co.farmstory2.db.Sql;

// DAO(Data Access Object) 클래스
public class ArticleDao {

	// 싱글톤 객체
	private static ArticleDao instance = new ArticleDao();
	
	public static ArticleDao getInstance() {
		return instance;
	}
	
	private ArticleDao() {}
	
	public int selectCountTotal(String cate) {
		int total = 0;
		
		try{
			// 1,2단계
			Connection conn = DBConfig.getInstance().getConnection();
			// 3단계
			PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_COUNT_TOTAL);
			psmt.setString(1, cate);
			// 4단계
			ResultSet rs = psmt.executeQuery();
			// 5단계
			if(rs.next()) {
				total = rs.getInt(1);
			}
			// 6단계
			rs.close();
			psmt.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return total;
	}
	
	public List<ArticleBean> selectLatest(String cate) {
		
		List<ArticleBean> latests = new ArrayList<>();
		
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_LATEST);
			psmt.setString(1, cate);
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()) {
				ArticleBean article = new ArticleBean();
				article.setSeq(rs.getInt(1));
				article.setTitle(rs.getString(2));
				article.setRdate(rs.getString(3));
				
				latests.add(article);
			}
			
			rs.close();
			psmt.close();
			conn.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return latests;
	}
	
	public ArticleBean selectArticle(String seq) {
		
		ArticleBean ab = null;
		FileBean fb = null;
		
		try{
			// 1,2단계
			Connection conn = DBConfig.getInstance().getConnection();
			// 3단계
			PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_ARTICLE);
			psmt.setString(1, seq);
			// 4단계
			ResultSet rs = psmt.executeQuery();
			// 5단계
			if(rs.next()) {
				ab = new ArticleBean();
				ab.setSeq(rs.getInt(1));
				ab.setParent(rs.getInt(2));
				ab.setComment(rs.getInt(3));
				ab.setCate(rs.getString(4));
				ab.setTitle(rs.getString(5));
				ab.setContent(rs.getString(6));
				ab.setFile(rs.getInt(7));
				ab.setHit(rs.getInt(8));
				ab.setUid(rs.getString(9));
				ab.setRegip(rs.getString(10));
				ab.setRdate(rs.getString(11));
				
				fb = new FileBean();
				fb.setFseq(rs.getInt(12));
				fb.setParent(rs.getInt(13));
				fb.setOriName(rs.getString(14));
				fb.setNewName(rs.getString(15));
				fb.setDownload(rs.getInt(16));
				fb.setRdate(rs.getString(17));
				
				ab.setFb(fb);
			}
			// 6단계
			rs.close();
			psmt.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return ab;
	}
	
	public List<ArticleBean> selectArticles(String cate, int start) {
		
		List<ArticleBean> articles = new ArrayList<>();
		
		try{
			// 1,2단계
			Connection conn = DBConfig.getInstance().getConnection();
			// 3단계
			PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_ARTICLES);
			psmt.setString(1, cate);
			psmt.setInt(2, start);
			// 4단계
			ResultSet rs = psmt.executeQuery();
			// 5단계
			while(rs.next()){
				ArticleBean ab = new ArticleBean();
				ab.setSeq(rs.getInt(1));
				ab.setParent(rs.getInt(2));
				ab.setComment(rs.getInt(3));
				ab.setCate(rs.getString(4));
				ab.setTitle(rs.getString(5));
				ab.setContent(rs.getString(6));
				ab.setFile(rs.getInt(7));
				ab.setHit(rs.getInt(8));
				ab.setUid(rs.getString(9));
				ab.setRegip(rs.getString(10));
				ab.setRdate(rs.getString(11));
				ab.setNick(rs.getString(12));
				
				articles.add(ab);
			}
			// 6단계
			rs.close();
			psmt.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return articles;
	}
	
	public List<ArticleBean> selectComments(String seq){
		
		List<ArticleBean> comments = new ArrayList<>();
		
		try{
			// 1,2단계
			Connection conn = DBConfig.getInstance().getConnection();
			// 3단계
			PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_COMMENTS);
			psmt.setString(1, seq);
			// 4단계
			ResultSet rs = psmt.executeQuery();
			// 5단계
			while(rs.next()){
				ArticleBean ab = new ArticleBean();
				ab.setSeq(rs.getInt(1));
				ab.setParent(rs.getInt(2));
				ab.setComment(rs.getInt(3));
				ab.setCate(rs.getString(4));
				ab.setTitle(rs.getString(5));
				ab.setContent(rs.getString(6));
				ab.setFile(rs.getInt(7));
				ab.setHit(rs.getInt(8));
				ab.setUid(rs.getString(9));
				ab.setRegip(rs.getString(10));
				ab.setRdate(rs.getString(11));
				ab.setNick(rs.getString(12));
				
				comments.add(ab);
			}
			// 6단계
			rs.close();
			psmt.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return comments;
	}
	
	public int selectMaxSeq() {
		int seq = 0;
		
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(Sql.SELECT_MAX_SEQ);
			if(rs.next()){
				seq = rs.getInt(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return seq;
	}
	
	public FileBean selectFile(String fseq) {
		FileBean fb = null;
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_FILE);
			psmt.setString(1, fseq);
			ResultSet rs = psmt.executeQuery();
			if(rs.next()) {
				fb = new FileBean();
				fb.setFseq(rs.getInt(1));
				fb.setParent(rs.getInt(2));;
				fb.setOriName(rs.getString(3));
				fb.setNewName(rs.getString(4));
				fb.setDownload(rs.getInt(5));
				fb.setRdate(rs.getString(6));
			}
			rs.close();
			psmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return fb;
	}
	
	public int insertArticle(ArticleBean article) {
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.INSERT_ARTICLE);
			psmt.setString(1, article.getCate());
			psmt.setString(2, article.getTitle());
			psmt.setString(3, article.getContent());
			psmt.setInt(4, article.getFile());
			psmt.setString(5, article.getUid());
			psmt.setString(6, article.getRegip());
			
			psmt.executeUpdate();
			psmt.close();
			conn.close();			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		// 글 등록 후 바로 해당 글번호 리턴 
		return selectMaxSeq();
	}
	
	public void insertFile(int seq, String oriName, String newName) {
		try{
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.INSERT_FILE);
			psmt.setInt(1, seq);
			psmt.setString(2, oriName);
			psmt.setString(3, newName);
			psmt.executeUpdate();
			psmt.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();	
		}
	}
	
	public void insertComment(ArticleBean ab) {
		try {
			// 1,2단계
			Connection conn = DBConfig.getInstance().getConnection();
			// 3단계
			PreparedStatement psmt = conn.prepareStatement(Sql.INSERT_COMMENT);
			psmt.setInt(1, ab.getParent());
			psmt.setString(2, ab.getContent());
			psmt.setString(3, ab.getUid());
			psmt.setString(4, ab.getRegip());
			
			// 4단계
			psmt.executeUpdate();
			// 5단계
			// 6단계
			psmt.close();
			conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateArticle(String title, String content, String seq) {
		
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.UPDATE_ARTICLE);
			psmt.setString(1, title);
			psmt.setString(2, content);
			psmt.setString(3, seq);
			
			psmt.executeUpdate();
			conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateArticleHit(String seq) {
		try{
			// 1,2단계
			Connection conn = DBConfig.getInstance().getConnection();
			// 3단계
			PreparedStatement psmt = conn.prepareStatement(Sql.UPDATE_ARTICLE_HIT);
			psmt.setString(1, seq);
			// 4단계
			psmt.executeUpdate();
			// 5단계
			// 6단계
			psmt.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void updateCommentCount(String parent, int type) {
		try{
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = null;
			if(type > 0) {
				psmt = conn.prepareStatement(Sql.UPDATE_COMMENT_COUNT_PLUS);
			}else {
				psmt = conn.prepareStatement(Sql.UPDATE_COMMENT_COUNT_MINUS);
			}
			psmt.setString(1, parent);
			psmt.executeUpdate();
			psmt.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public int updateComment(String content, String seq) {
		
		int result = 0;
		
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.UPDATE_COMMENT);
			psmt.setString(1, content);
			psmt.setString(2, seq);
			result = psmt.executeUpdate();
			psmt.close();
			conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public void updateFileDownload(String fseq) {
		
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.UPDATE_FILE_DOWNLOAD);
			psmt.setString(1, fseq);
			psmt.executeUpdate();
			psmt.close();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public void deleteArticle(String seq) {
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.DELETE_ARTICLE);
			psmt.setString(1, seq);
			psmt.executeUpdate();
			conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteComment(String seq) {
		try{
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.DELETE_COMMENT);
			psmt.setString(1, seq);
			psmt.executeUpdate();
			psmt.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}