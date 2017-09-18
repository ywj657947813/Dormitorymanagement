package Servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Util;

import net.sf.json.JSONObject;



import Dao.AdminDao;
import Dao.StudentDao;
import Util.JdbcUtil;
import Util.ResponseUtil;
import Util.StringUtil;

public class DeleteLeaveServlet extends HttpServlet {
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
		
		AdminDao adminDao=new AdminDao();
		StudentDao studentDao=new StudentDao();
		JdbcUtil jdbcUtil=new JdbcUtil();
		String Id=request.getParameter("Id");         //信息编号（用于学生删除信息）
		String Ids=request.getParameter("Ids");       //要删除请假信息的编号（用于管理员）
		Connection conn=null;
		try{
			if(StringUtil.isNotEmpty(Ids)){//Ids不为空，则是管理员删除信息
				conn=jdbcUtil.getConnection();
				int sum=adminDao.deleteLeave(conn,Ids);
				JSONObject result=new JSONObject();
				if(sum>0){
					result.put("success", "true");
					result.put("msg", "true");  
				}else {
					result.put("success", "true");
					result.put("msg", "false");
				}
				ResponseUtil.write(response, result);
			}else {
				//Ids为空，则是学生删除信息
				conn=jdbcUtil.getConnection();
				int sum=studentDao.deleteLeave(conn,Id);
				JSONObject result=new JSONObject();
				if(sum>0){
					result.put("success", "true");
					result.put("msg", "删除成功！");  
				}else {
					result.put("success", "true");
					result.put("msg", "删除失败！");
				}
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
