package com.example.demo.constant;

import java.util.HashMap;
import java.util.Map;

public class Const {
	public static final String STATUS_REGISTER = "Available";
	public static final int PAGENUMBER_FIRST = 1;
	public static final int PAGE_SIZE = 5;

	public static final Map<Integer, String> LIST_STATUS_MAP = new HashMap<Integer, String>();
	static {
		// TODO Auto-generated method stub
		LIST_STATUS_MAP.put(1, "Received");
		LIST_STATUS_MAP.put(2, "Available");
		LIST_STATUS_MAP.put(3, "Requesting");
	}
	public static final Map<Integer, String> LIST_SITE_MAP = new HashMap<Integer, String>();
	static {
		// TODO Auto-generated method stub
		LIST_SITE_MAP.put(1, "Ha Noi");
		LIST_SITE_MAP.put(2, "Da Nang");
		LIST_SITE_MAP.put(3, "Ho Chi Minh");
		LIST_SITE_MAP.put(4, "Can Tho");
	}
	public static final Map<String, String> LIST_LANGUAGE = new HashMap<String, String>();
	static {
		// TODO Auto-generated method stub
		LIST_LANGUAGE.put("fr", "Franch");
		LIST_LANGUAGE.put("de", "Germany");
		LIST_LANGUAGE.put("kr", "Korean");
	}

}
