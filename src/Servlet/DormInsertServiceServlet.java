package Servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import Dao.StudentDao;
import Model.Service;
import Util.JdbcUtil;

public class DormInsertServiceServlet extends HttpServlet {
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
		StudentDao studentDao=new StudentDao();
		JdbcUtil jdbcUtil=new JdbcUtil();
		HttpSession session=request.getSession();
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String stuid=(String) session.getAttribute("studentid");   //获取当前保修学生的学号
		String Dormmun=request.getParameter("Dormmun");         //宿舍楼
		String RoomId=request.getParameter("RoomId");           //房号
		String name=request.getParameter("Dname");              //姓名
		String phone=request.getParameter("phone");             //联系方式
		String servicething=request.getParameter("thing");      //报修物品类型   
		String servicereason=request.getParameter("reason");    //报修理由
		String Time=request.getParameter("NowTime");             //报修时间
		String State="待维修";            //状态
		
		Service service=new Service(stuid,Dormmun,RoomId,servicething,servicereason,name,phone,Time,State);
		Connection conn=null;
		try{
			conn=jdbcUtil.getConnection();
			
			int saveNums=studentDao.serviceAdd(conn, service);
		
		
			if(saveNums>0){
				request.setAttribute("success", "true");  
	            request.getRequestDispatcher("service.jsp").forward(request, response);
			}else{
				
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
