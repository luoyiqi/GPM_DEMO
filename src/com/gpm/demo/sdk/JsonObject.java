package com.gpm.demo.sdk;

import java.util.HashMap;
import java.util.List;


/**
 * 返回值实体类
 * 
 * @author gavinwen
 */
public class JsonObject {

	private int code;
	
	private String msg;
	
	private List<HashMap<String,String>> adverts;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<HashMap<String, String>> getAdverts() {
		return adverts;
	}

	public void setAdverts(List<HashMap<String, String>> adverts) {
		this.adverts = adverts;
	}
	
	
	
	
	
	
	
    

}
