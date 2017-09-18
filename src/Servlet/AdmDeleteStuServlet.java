package Servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import Dao.AdminDao;
import Util.JdbcUtil;
import Util.ResponseUtil;


public class AdmDeleteStuServlet extends HttpServlet {

	
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
		AdminDao adminDao = new AdminDao();
		JdbcUtil jdbcUtil=new JdbcUtil();
		String stuIds=request.getParameter("stuIds"); //要删除的学号
		
		Connection conn=jdbcUtil.getConnection();
		try {
			int sum=adminDao.deleteStu1(conn, stuIds);   //数据库设置级联删除，删除登陆账号会连信息一起删除
			JSONObject result=new JSONObject();
			if(sum>0){
				result.put("success", "true");
				result.put("msg", "true");
			}else{
				result.put("success", "true");
				result.put("msg", "false");
			}
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
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

