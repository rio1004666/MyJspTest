package kr.co.farmstory3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.co.farmstory3.db.DBConfig;
import kr.co.farmstory3.db.Sql;
import kr.co.farmstory3.vo.ArticleVo;
import kr.co.farmstory3.vo.FileVo;

public class ArticleDao {
	private static ArticleDao instance = new ArticleDao();
	public static ArticleDao getInstance() {
		return instance;
	}
	private ArticleDao() {}
	
	
	public void insertFile(int seq,String fname,String newName) {
		
		try{
			Connection  conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.INSERT_FILE);
			psmt.setInt(1, seq); //湲�踰덊샇媛� �궡媛� 湲��쓣 �뜥�꽌 db�뿉 ���옣�릺�뒗 �닚媛� 洹� 踰덊샇媛� 湲�踰덊샇媛� �맂�떎 parent
			psmt.setString(2, fname); //�썝�옒 �씠由꾩� oriName=  fname
			psmt.setString(3, newName);
			
			psmt.executeUpdate();
			
			psmt.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
	}
	public void insertComment(ArticleVo vo) {
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.INSERT_COMMENT);
			psmt.setInt(1, vo.getParent());
			psmt.setString(2, vo.getContent());
			psmt.setString(3, vo.getUid());
			psmt.setString(4, vo.getRegip());
			psmt.executeUpdate();
			psmt.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	public int insertArticle(ArticleVo av) {
		
		try {
			
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.INSERT_ARTICLE);
			psmt.setString(1, av.getTitle());
			psmt.setString(2, av.getContent());
			psmt.setInt(3, av.getFile());
			psmt.setString(4, av.getUid());
			psmt.setString(5, av.getRegip());
			int result = psmt.executeUpdate(); // �궫�엯�븯怨좊굹�꽌�룄 媛믪쓣 諛섑솚諛쏆쓣 �닔 �엳�떎 
			psmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return selectMaxSeq(); // 媛��옣 理쒓렐湲��쓽 踰덊샇瑜� 媛��졇�삩�떎 
	}

	public int selectCountTotal(String cate) {
		int total = 0;
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_COUNT_TOTAL);
			psmt.setString(1, cate);
			ResultSet rs = psmt.executeQuery();
			if(rs.next()) {
				total = rs.getInt(1);
			}
			rs.close();
			psmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;
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
	public FileVo selectFile(String fseq) {
		FileVo fv = null;
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_FILE);
			psmt.setString(1, fseq);
			ResultSet rs = psmt.executeQuery();
			if(rs.next()) {
				fv = new FileVo();
				fv.setFseq(rs.getInt(1));
				fv.setParent(rs.getInt(2));
				fv.setOriName(rs.getString(3));
				fv.setNewName(rs.getString(4));
				fv.setDownload(rs.getInt(5));
				fv.setRdate(rs.getString(6));
			}
			rs.close();
			psmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fv;
	}
	public ArticleVo selectArticle(String seq) {
		ArticleVo av = null;
		FileVo fv = null;
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_ARTICLE);
			psmt.setString(1, seq);
			ResultSet rs = psmt.executeQuery();
			
			if(rs.next()) {
				av = new ArticleVo();
				av.setSeq(rs.getInt(1));
				av.setParent(rs.getInt(2));
				av.setComment(rs.getInt(3));
				av.setCate(rs.getString(4));
				av.setTitle(rs.getString(5));
				av.setContent(rs.getString(6));
				av.setFile(rs.getInt(7));
				av.setHit(rs.getInt(8));
				av.setUid(rs.getString(9));
				av.setRegip(rs.getString(10));
				av.setRdate(rs.getString(11));
				
				fv = new FileVo();
				fv.setFseq(rs.getInt(12));
				fv.setParent(rs.getInt(13));
				fv.setOriName(rs.getString(14));
				fv.setNewName(rs.getString(15));
				fv.setDownload(rs.getInt(16));
				fv.setRdate(rs.getString(17));
				av.setFb(fv);
			}
			rs.close();
			psmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return av;
	}

	public List<ArticleVo> selectArticles(int start, String cate){

		List<ArticleVo> articles = new ArrayList<>();
		ArticleVo article = null;
		try {
			
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_ARTICLES);
			psmt.setString(1, cate);
			psmt.setInt(2, start);
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()) {
				article =  new ArticleVo();
				article.setSeq(rs.getInt(1));
				article.setParent(rs.getInt(2));
				article.setComment(rs.getInt(3));
				article.setCate(rs.getString(4));
				article.setTitle(rs.getString(5));
				article.setContent(rs.getString(6));
				article.setFile(rs.getInt(7));
				article.setHit(rs.getInt(8));
				article.setUid(rs.getString(9));
				article.setRegip(rs.getString(10));				
				article.setRdate(rs.getString(11).substring(2,10)); // 誘몃━ substring�쑝濡� �븘�슂�븳 臾몄옄�뿴留� �뙆�떛�븳�떎 
				article.setNick(rs.getString(12)); // 湲��벖�씠�쓽 �땳�꽕�엫�쓣 諛쏆븘�삤湲곗쐞�빐�꽌 
				articles.add(article);
			}
			rs.close();
			psmt.close();
			conn.close();
		} catch (Exception e) {
				e.printStackTrace();
		}
		return articles;
	}
	
public List<ArticleVo> selectLatest(String cate) {
		
		List<ArticleVo> latests = new ArrayList<>();
		
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_LATEST);
			psmt.setString(1, cate);
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()) {
				ArticleVo article = new ArticleVo();
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
	public List<ArticleVo> selectComments(String parent){
		List<ArticleVo> comments = new ArrayList<>();
		ArticleVo comment = null;
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_COMMENTS);
			psmt.setString(1, parent);
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()) {
				comment = new ArticleVo();
				comment.setSeq(rs.getInt(1));
				comment.setParent(rs.getString(2));
				comment.setComment(rs.getInt(3));
				comment.setCate(rs.getString(4));
				comment.setTitle(rs.getString(5));
				comment.setContent(rs.getString(6));
				comment.setFile(rs.getInt(7));
				comment.setHit(rs.getInt(8));
				comment.setUid(rs.getString(9));
				comment.setRegip(rs.getString(10));
				comment.setRdate(rs.getString(11).substring(2,10));
				comment.setNick(rs.getString(12));
				comments.add(comment);
			}
			rs.close();
			psmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return comments;
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
			// 1,2�떒怨�
			Connection conn = DBConfig.getInstance().getConnection();
			// 3�떒怨�
			PreparedStatement psmt = conn.prepareStatement(Sql.UPDATE_ARTICLE_HIT);
			psmt.setString(1, seq);
			// 4�떒怨�
			psmt.executeUpdate();
			// 5�떒怨�
			// 6�떒怨�
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
