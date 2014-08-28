package com.terascope.amano.incheon.dao.helper;

public class IvpHttpHelper extends HttpHelper{

	private String baseUrl;
	
	public IvpHttpHelper() {
		super();
	}
	
	public IvpHttpHelper(String baseUrl) {
		super();
		this.setBaseUrl(baseUrl);
	}
	
	public void setBaseUrl(String url) {
		this.baseUrl = url;
	}
	
	@Override
	public String httpGet(String url) {
		return super.httpGet(this.baseUrl + url);
	}
}
