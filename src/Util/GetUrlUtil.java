package Util;

import javax.servlet.http.HttpServletRequest;


/**
 * 字符串工具类
 * @author 
 *
 */
public class GetUrlUtil {

	/**
	 * 获取上一次请求的地址栏内容   查询条件跟servlet地址
	 * @param request
	 * @return
	 */
	public static String getUrl(HttpServletRequest request){
		
//		String  contextPath=request.getContextPath();//项目名
		String  servletPath=request.getServletPath();//Url地址
		String  queryString=request.getQueryString(); //获取get请求地址栏问号后边的内容
		//由于pc（当前页码）是用户点击第几页就提交第几页，所以要截掉上一次请求所带的pc值
		if(queryString.contains("&pc=")){
			//截取数据
			int index=queryString.lastIndexOf("&pc=");
			queryString=queryString.substring(0, index);
		}
		return servletPath + "?" +queryString;
	}
	
}
