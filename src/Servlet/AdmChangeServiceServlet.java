package Servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import Util.JdbcUtil;
import Util.ResponseUtil;

import Dao.AdminDao;


public class AdmChangeServiceServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String Id=request.getParameter("Id");              //������Ϣ��id����ѧ�ţ�
		String action=request.getParameter("action");      //����Ա�Ĳ���
		AdminDao adminDao = new AdminDao();
		JdbcUtil jdbcUtil=new JdbcUtil();
		Connection conn=jdbcUtil.getConnection();
		
		try {
			if(action.equals("accept")){                            //������ܰ�ťִ�е��¼�
					int sum=adminDao.changeServiceState(conn, "ά����", Id);
	                if(sum>0){
	                	JSONObject result=new JSONObject();
	                	result.put("success", "true");
	                	result.put("msg", "���ܳɹ����뾡�����ά�ޣ�");           
	                	ResponseUtil.write(response, result);
	                }
			}else if(action.equals("finish")){       //�����ɰ�ťִ�е��¼�
					
					int sum=adminDao.changeServiceState(conn, "�����", Id);
					if(sum>0){
						JSONObject result=new JSONObject();
						result.put("success", "true");
						result.put("msg", "true1");
						ResponseUtil.write(response, result);
				    }
             }else if(action.equals("delay")){
            	 int sum=adminDao.changeServiceState(conn, "�޿��", Id);
					if(sum>0){
						JSONObject result=new JSONObject();
						result.put("success", "true");
						result.put("msg", "true");
						ResponseUtil.write(response, result);
				    }
             }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

		
}

