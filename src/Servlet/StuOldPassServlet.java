package Servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Util.JdbcUtil;

import Dao.StudentDao;

public class StuOldPassServlet extends HttpServlet {
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
		String oldpass=request.getParameter("oldpass");   //输入的旧密码
		String newpass=request.getParameter("newpass");   //输入的新密码
		String oldPassword=(String) session.getAttribute("studentps"); //学生真实密码
		String stuid=(String) session.getAttribute("studentid");       //学生学号

		String action=request.getParameter("action");
		
		if(action.equals("choice")){//判断操作页面  ，这个是判断密码是否正确
			if(oldpass.equals(oldPassword)){
				request.setAttribute("password",newpass);   //设置新密码跳转到确认新密码页面
			//	response.sendRedirect("stchangepass.jsp");
                request.getRequestDispatcher("stchangepass.jsp").forward(request, response);
			}else{
				request.setAttribute("error", "密码输入错误！");
				request.setAttribute("oldpass", oldpass);  //输入的旧密码，当错误的时候跳转页面，输入的密码不会清空
				request.setAttribute("newpass", newpass);  //输入的新密码
		    //  response.sendRedirect("studentpass.jsp");
                request.getRequestDispatcher("studentpass.jsp").forward(request, response);
			}
		}else if(action.equals("change")){ //这个是修改密码
			Connection conn=null;
			try{
				conn=jdbcUtil.getConnection();
				
				int saveNums=studentDao.ChangePass(conn, newpass, stuid);
			
			
				if(saveNums>0){
					request.setAttribute("success", "修改成功！");
					session.setAttribute("studentps",newpass);  //修改成功后更新session里的密码数据
					request.getRequestDispatcher("stuchangeOK.jsp").forward(request, response);
//					response.sendRedirect("stuchangeOK.jsp");
				}else{
					session.setAttribute("error", "修改失败！");
					response.sendRedirect("stchangepass.jsp");
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

		
}


