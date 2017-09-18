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
import Dao.StudentDao;

public class LeaveChangeServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		JdbcUtil jdbcUtil =new JdbcUtil();
		StudentDao studentDao = new StudentDao();
		AdminDao adminDao=new AdminDao();
		String id=request.getParameter("Id");   //�����Ϣ�ı��
		String action=request.getParameter("action");   //�����û�
		String state=request.getParameter("state");     //�޸���Ϣ״̬
		
		Connection conn=null;
		try{
			conn=jdbcUtil.getConnection();
			
			int saveNums;
 		    if("teacher".equals(action)){
 		    	saveNums=adminDao.ChangeLeave(conn, id, state);
 		    }else{
 		    	saveNums=studentDao.ChangeLeave(conn, id);
 		    }
		
			if(saveNums>0){
				JSONObject result=new JSONObject();
            	result.put("success", "true");
            	result.put("msg", "�޸ĳɹ���");  
            	ResponseUtil.write(response, result);
			}else{
				JSONObject result=new JSONObject();
            	result.put("success", "true");
            	result.put("msg", "�޸�ʧ�ܣ�");  
            	ResponseUtil.write(response, result);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			
		}finally{
			try {
				jdbcUtil.close(conn);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	 }
	

		
}


