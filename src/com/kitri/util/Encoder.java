package com.kitri.util;

import java.io.UnsupportedEncodingException;

public class Encoder {

	//다른사람이 언제든지 써도 영향을 받지 않으므로 static
	public static String isToEuc(String tmp) {
		String str = "";
		try {
			str = new String(tmp.getBytes("ISO-8859-1"), "EUC-KR");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return str;		
	}
}
