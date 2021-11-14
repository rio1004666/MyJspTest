package kr.co.farmstory2.bean;

import kr.co.farmstory2.db.DBConfig;

public class TermsBean {
	
	
	
	private String terms;
	private String privacy;
	
	//DBConfig dbcon = new DBConfig(); // ������ȣ���ؼ� ��ü ���� => private�̶� ȣ�� ����

	public String getTerms() {
		return terms;
	}
	
	
	
	public void setTerms(String terms) {
		this.terms = terms;
	}
	
	
	
	public String getPrivacy() {
		return privacy;
	}
	
	
	public void setPrivacy(String privacy) {
		this.privacy = privacy;
	}
	
}
