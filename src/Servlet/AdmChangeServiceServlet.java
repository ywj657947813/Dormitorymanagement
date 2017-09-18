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
		String Id=request.getParameter("Id");              //报修信息的id（非学号）
		String action=request.getParameter("action");      //管理员的操作
		AdminDao adminDao = new AdminDao();
		JdbcUtil jdbcUtil=new JdbcUtil();
		Connection conn=jdbcUtil.getConnection();
		
		try {
			if(action.equals("accept")){                            //点击接受按钮执行的事件
					int sum=adminDao.changeServiceState(conn, "维修中", Id);
	                if(sum>0){
	                	JSONObject result=new JSONObject();
	                	result.put("success", "true");
	                	result.put("msg", "接受成功，请尽快完成维修！");           
	                	ResponseUtil.write(response, result);
	                }
			}else if(action.equals("finish")){       //点击完成按钮执行的事件
					
					int sum=adminDao.changeServiceState(conn, "已完成", Id);
					if(sum>0){
						JSONObject result=new JSONObject();
						result.put("success", "true");
						result.put("msg", "true1");
						ResponseUtil.write(response, result);
				    }
             }else if(action.equals("delay")){
            	 int sum=adminDao.changeServiceState(conn, "无库存", Id);
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

