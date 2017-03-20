package sunwin.zhangdong.comm;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串处理工具类                   各种数据格式和数据形式的转换！
 *                    				软件  =  数据结构   +  算法
 */
@Component
public class StringUtil {

	private static Log log = LogFactory.getLog(StringUtil.class);

	// 定义script的正则表达式
	private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>";
	// 定义style的正则表达式
	private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>";
	// 定义HTML标签的正则表达式
	private static final String regEx_html = "<[^>]+>";
	//定义空格回车换行符
	private static final String regEx_space = "\\s*|\t|\r|\n";

	/**
	 * 将字符串"null"转换为空字符串""
	 * 空字符串的处理   和 “null”  -----> ""
	 * @param str
	 * @return
	 */
	public static String parseNull(String str) {
        //  调用自己的方法
		if (StringUtil.isEmpty(str)) {
			return "";
			// 如果str == "null"  那么也将str置为空
		} else if (str.equalsIgnoreCase("null")) {
			return "";
		}
		return str;
	}

	/**
	 * 将字符串数字转化为double类型 指定的位数输出
	 */
	public static Double parseDouble(String str,int n){
		Double d=Double.parseDouble(str);
		BigDecimal   b   =   new   BigDecimal(d);
		double   f1   =   b.setScale(n,   BigDecimal.ROUND_HALF_UP).doubleValue();
		return f1;
	}
	/**
	 * 将字符串数字转化为double类型 并保留两位小数
	 */
	public static Double parseDouble(String str){
		Double d=Double.parseDouble(str);
		BigDecimal   b   =   new   BigDecimal(d);
		double   f1   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
		return f1;
	}
	/**
	 * 保留两位小数的double
	 */
	public static Double saveTwoDouble(double d){
		BigDecimal   b   =   new   BigDecimal(d);
		double   f1   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
		return f1;
	}
	/**
	 * 将字符串进行MD5摘要，输出成BASE64形式
	 */
	public static String md5Base64(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {

		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			// 使用给定的 charset 将此 String 编码到 byte 序列，并将结果存储到新的 byte 数组。
			// 先编码--》md5加密 --》 base64编码
			return base64Encode(md5.digest(str.getBytes("utf-8")));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	/***
	 * MD5加码 生成32位md5码
	 */
	public static String string2MD5(String inStr){
		MessageDigest md5 = null;
		try{
			md5 = MessageDigest.getInstance("MD5");
		}catch (Exception e){
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}
		//  字符数组
		char[] charArray = inStr.toCharArray();
		//  字节数组
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			// 强制类型转换    字符转字节
			byteArray[i] = (byte) charArray[i];
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++){
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();

	}

	/**
	 * 加密解密算法 执行一次加密，两次解密
	 */
	public static String convertMD5(String inStr){

		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++){
			a[i] = (char) (a[i] ^ 't');
		}
		String s = new String(a);
		return s;

	}
	public static void main(String[] ar) throws Exception{
		System.out.print(md5Base64("123456"));
		String s = new String("123456");
		System.out.println("原始：" + s);
		System.out.println("MD5后：" + string2MD5(s));
		System.out.println("加密的：" + convertMD5(s));
		System.out.println("解密的：" + convertMD5(convertMD5(s)));
	}
	/**
	 * 返回二进制数组的BASE64编码结果
	 */
	public static String base64Encode(byte[] b) {

		if (b == null) {
			return null;
		}
		return (new BASE64Encoder()).encode(b);
	}
	/**
	 * 将一个字符串中的指定片段全部替换，替换过程中不进行正则处理。<br>
	 * 使用String类的replaceAll时要求片段以正则表达式形式给出，有时较为不便，可以转为采用本方法。
	 */
	public static String replaceEx(String str, String subStr, String reStr) {

		if (str == null) {
			return null;
		}
		if (subStr == null || subStr.equals("") || subStr.length() > str.length() || reStr == null) {
			return str;
		}
		StringBuffer sb = new StringBuffer();
		int lastIndex = 0;
		while (true) {
			int index = str.indexOf(subStr, lastIndex);
			if (index < 0) {
				break;
			} else {
				sb.append(str.substring(lastIndex, index));
				sb.append(reStr);
			}
			lastIndex = index + subStr.length();
		}
		sb.append(str.substring(lastIndex));
		return sb.toString();
	}

	/**
	 * 将长度超过length的字符串截取length长度，若不足，则返回原串
	 */
	public static String subString(String src, int length) {

		if (src == null) {
			return null;
		}
		int i = src.length();
		if (i > length) {
			return src.substring(0, length);
		} else {
			return src;
		}
	}

	/**
	 * 字符串是否为空，null或空字符串时返回true,其他情况返回false
	 */
	public static boolean isEmpty(String str) {

		return str == null || str.length() == 0;
	}

	/**
	 * 字符串是否不为空，null或空字符串时返回false,其他情况返回true
	 */
	public static boolean isNotEmpty(String str) {

		return !StringUtil.isEmpty(str);
	}

	/**
	 * 字符串为空时返回defaultString，否则返回原串
	 */
	public static final String noNull(String string, String defaultString) {

		return isEmpty(string) ? defaultString : string;
	}

	/**
	 * 字符串为空时返回defaultString，否则返回空字符串
	 */
	public static final String noNull(String string) {

		return noNull(string, "");
	}

	/**
	 * 将一个数组拼成一个字符串，数组项之间以逗号分隔
	 */
	public static String join(Object[] arr) {

		return join(arr, ",");
	}

	/**
	 * 将一个二维数组拼成一个字符串，第二维以逗号分隔，第一维以换行分隔
	 */
	public static String join(Object[][] arr) {

		return join(arr, "\n", ",");
	}

	/**
	 * 将一个数组以指定的分隔符拼成一个字符串
	 */
	public static String join(Object[] arr, String spliter) {

		if (arr == null) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			if (i != 0) {
				sb.append(spliter);
			}
			sb.append(arr[i]);
		}
		return sb.toString();
	}

	/**
	 * 将一个二维数组拼成一个字符串，第二维以指定的spliter2参数分隔，第一维以换行spliter1分隔
	 */
	public static String join(Object[][] arr, String spliter1, String spliter2) {

		if (arr == null) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			if (i != 0) {
				sb.append(spliter2);
			}
			sb.append(join(arr[i], spliter2));
		}
		return sb.toString();
	}

	/**
	 * 将一个List拼成一个字符串，数据项之间以逗号分隔
	 */
	public static String join(List<Object> list) {

		return join(list, ",");
	}

	/**
	 * 将一个List拼成一个字符串，数据项之间以指定的参数spliter分隔
	 */
	public static String join(List<Object> list, String spliter) {

		if (list == null) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			if (i != 0) {
				sb.append(spliter);
			}
			sb.append(list.get(i));
		}
		return sb.toString();
	}

	public static final Pattern PLetterOrDigit = Pattern.compile("^\\w*$", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);

	public static final Pattern PLetter = Pattern.compile("^[A-Za-z]*$", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);

	public static final Pattern PDigit = Pattern.compile("^\\d*$", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);

	/**
	 * 判断字符串是否全部由数字或字母组成
	 */
	public static boolean isLetterOrDigit(String str) {

		return PLetterOrDigit.matcher(str).find();
	}

	/**
	 * 判断字符串是否全部字母组成
	 */
	public static boolean isLetter(String str) {

		return PLetter.matcher(str).find();
	}

	/**
	 * 判断字符串是否全部由数字组成
	 */
	public static boolean isDigit(String str) {

		if (StringUtil.isEmpty(str)) {
			return false;
		}
		return PDigit.matcher(str).find();
	}

	/**
	 * 删除HTML标签
	 *
	 * @param htmlStr
	 * @return
	 */
	public static String delHtmlTag(String htmlStr) {
		Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
		Matcher m_script = p_script.matcher(htmlStr);
		htmlStr = m_script.replaceAll(""); // 过滤script标签

		Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
		Matcher m_style = p_style.matcher(htmlStr);
		htmlStr = m_style.replaceAll(""); // 过滤style标签

		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
		Matcher m_html = p_html.matcher(htmlStr);
		htmlStr = m_html.replaceAll(""); // 过滤html标签

		Pattern p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);
		Matcher m_space = p_space.matcher(htmlStr);
		htmlStr = m_space.replaceAll(""); // 过滤空格回车标签

		return htmlStr.trim(); // 返回文本字符串
	}

}
