package Util;

import javax.servlet.http.HttpServletRequest;


/**
 * �ַ���������
 * @author 
 *
 */
public class GetUrlUtil {

	/**
	 * ��ȡ��һ������ĵ�ַ������   ��ѯ������servlet��ַ
	 * @param request
	 * @return
	 */
	public static String getUrl(HttpServletRequest request){
		
//		String  contextPath=request.getContextPath();//��Ŀ��
		String  servletPath=request.getServletPath();//Url��ַ
		String  queryString=request.getQueryString(); //��ȡget�����ַ���ʺź�ߵ�����
		//����pc����ǰҳ�룩���û�����ڼ�ҳ���ύ�ڼ�ҳ������Ҫ�ص���һ������������pcֵ
		if(queryString.contains("&pc=")){
			//��ȡ����
			int index=queryString.lastIndexOf("&pc=");
			queryString=queryString.substring(0, index);
		}
		return servletPath + "?" +queryString;
	}
	
}
