package com.kitri.util;

import java.io.UnsupportedEncodingException;

public class Encoder {

	//�ٸ������ �������� �ᵵ ������ ���� �����Ƿ� static
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
