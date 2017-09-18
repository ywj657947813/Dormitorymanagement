package Servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.StudentDao;
import Model.Leave;
import Util.JdbcUtil;

public class StuApplyLeaveServlet extends HttpServlet  {
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
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
        HttpSession session =request.getSession();
		String firsttime = request.getParameter("firsttime");        //请假当天
		String lasttime = request.getParameter("lasttime");          //截止时间
		String who = request.getParameter("who");                    //请假人       
		String leavereason = request.getParameter("leavereason");    //请假理由
		String where = request.getParameter("where");                //去向
		String studentsphone = request.getParameter("studentsphone");//学生联系电话
		String Parentsphone = request.getParameter("Parentsphone");  //家长联系方式
		String stuid = (String)session.getAttribute("studentid");           //获取学号
		
		StudentDao studentDao=new StudentDao();
		JdbcUtil jdbcUtil =new JdbcUtil();
		Leave leave=new Leave(stuid,firsttime,lasttime,who,leavereason,where,studentsphone,Parentsphone,"未审批");
		
		Connection conn=null;
		try{
			conn=jdbcUtil.getConnection();
			
			int saveNums=studentDao.LeaveApply(conn, leave, stuid);
			if(saveNums>0){
//				session.setAttribute("success1", "true");
//				response .sendRedirect("leave.jsp");
				request.setAttribute("success", "true");  
	            request.getRequestDispatcher("leave.jsp").forward(request, response);
			}else{
//				session.setAttribute("error1", "true");
//				response .sendRedirect("leave.jsp");
				request.setAttribute("error", "true");  
	            request.getRequestDispatcher("leave.jsp").forward(request, response);
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
