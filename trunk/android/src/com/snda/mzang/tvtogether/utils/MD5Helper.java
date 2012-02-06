package com.snda.mzang.tvtogether.utils;

import java.nio.charset.Charset;

import org.apache.commons.lang.StringUtils;

public class MD5Helper {
	private static final char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	public static String getMD5(String content) {
		if (StringUtils.isEmpty(content)) {
			return "";
		}
		StringBuilder ret = new StringBuilder(16 * 2);

		try {
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			md.update(content.getBytes(Charset.forName("UTF-8")));
			// ���Ϊ128λ������������16��8���ֽڡ�
			byte tmp[] = md.digest();
			// ÿ���ֽ��������ַ���ʾ
			// �����Ѿ���䵽��λ
			for (int i = 0; i < 16; i++) {
				byte data = tmp[i];
				// �߼����ƶ���λ����ʾǰ�ĸ��ֽ�
				ret.append(hexDigits[data >>> 4 & 0xf]);
				// Ĩȥǰ�ĸ��ֽڣ�������ĸ��ֽ�
				ret.append(hexDigits[data & 0xf]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret.toString();
	}
}
