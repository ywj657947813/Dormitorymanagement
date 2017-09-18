package Servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import Util.JdbcUtil;
import Util.ResponseUtil;

import Dao.StudentDao;

public class StuChangePhoneServlet extends HttpServlet {
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
		HttpSession session=request.getSession();
		String phone=request.getParameter("phone");                //电话号码
		String stuid=(String) session.getAttribute("studentid");   //学生学号

	
	
			Connection conn=null;
			try{
				conn=jdbcUtil.getConnection();
				
				int saveNums=studentDao.ChangePhone(conn, phone, stuid);
			
			
				if(saveNums>0){
					JSONObject result=new JSONObject();
                	result.put("success", "true");
                	result.put("msg", "修改成功！");  
                	ResponseUtil.write(response, result);
				}else{
					JSONObject result=new JSONObject();
                	result.put("success", "true");
                	result.put("msg", "修改失败！");  
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


