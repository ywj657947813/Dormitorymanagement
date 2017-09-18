package Util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateUtil {
	/**
	 * 将date数据类型转成string类型
	 * 
	 * @param date
	 * @param format
	 *            //转成的模式，本系统转为yyyy-MM-dd
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
	 * 将string数据类型转成date类型
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
	 * 判断是否全为数字
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
	 * 将小数点后面部分去掉
	 */
	public static String deleteZero(String str) {
		if (str.indexOf(".") > 0) {
			// 正则表达
			// str = str.replaceAll("0+?$", "");//去掉后面无用的零
			str = str.replaceAll("[.]$", "");// 如小数点后面全是零则去掉小数点
		}
		return str;
	}

	/*
	 * 返回长度为【strLength】的随机数，在前面补0
	 */
	public static String getFixLenthString(int strLength) {

		Random rm = new Random();

		// 获得随机数
		double pross = (1 + rm.nextDouble()) * Math.pow(10, strLength);

		// 将获得的获得随机数转化为字符串
		String fixLenthString = String.valueOf(pross);

		// 返回固定的长度的随机数
		return fixLenthString.substring(1, strLength + 1);
	}
}
