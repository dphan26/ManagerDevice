package com.example.demo.constant;

import java.util.HashMap;
import java.util.Map;

public class Const {
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

}
