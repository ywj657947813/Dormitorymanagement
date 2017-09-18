package Util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateUtil {
	/**
	 * ��date��������ת��string����
	 * 
	 * @param date
	 * @param format
	 *            //ת�ɵ�ģʽ����ϵͳתΪyyyy-MM-dd
	 * @return
	 */
	public static String formatDate(Date date, String format) {
		String result = "";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		if (date != null) {
			result = sdf.format(date);
		}
		return result;
	}

	/**
	 * ��string��������ת��date����
	 * 
	 * @param date
	 * @param format
	 * @return
	 */

	public static Date formatString(String str, String format) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.parse(str);
	}

	/**
	 * �ж��Ƿ�ȫΪ����
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	/**
	 * ��С������沿��ȥ��
	 */
	public static String deleteZero(String str) {
		if (str.indexOf(".") > 0) {
			// ������
			// str = str.replaceAll("0+?$", "");//ȥ���������õ���
			str = str.replaceAll("[.]$", "");// ��С�������ȫ������ȥ��С����
		}
		return str;
	}

	/*
	 * ���س���Ϊ��strLength�������������ǰ�油0
	 */
	public static String getFixLenthString(int strLength) {

		Random rm = new Random();

		// ��������
		double pross = (1 + rm.nextDouble()) * Math.pow(10, strLength);

		// ����õĻ�������ת��Ϊ�ַ���
		String fixLenthString = String.valueOf(pross);

		// ���ع̶��ĳ��ȵ������
		return fixLenthString.substring(1, strLength + 1);
	}
}
