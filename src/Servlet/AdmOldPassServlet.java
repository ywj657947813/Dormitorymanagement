package Servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.AdminDao;
import Util.JdbcUtil;

public class AdmOldPassServlet extends HttpServlet {
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

		JdbcUtil jdbcUtil = new JdbcUtil();
		AdminDao adminDao = new AdminDao();
		HttpSession session = request.getSession();
		String oldpass = request.getParameter("oldpass"); // 输入的旧密码
		String newpass = request.getParameter("newpass"); // 输入的新密码
		String oldPassword = (String) session.getAttribute("adminps"); // 管理员真实密码
		String admid = (String) session.getAttribute("adminid"); // 管理员账号
		String oldPassword1 = (String) session.getAttribute("teacherps"); // 老师真实密码
		String teaid = (String) session.getAttribute("teacherid"); // 老师账号

		String action = request.getParameter("action");
		String action1 = request.getParameter("action1"); // 操作人员（老师，管理员）

		if (action.equals("choice")) {// 判断操作页面 ，这个是判断密码是否正确
			if ("teacher".equals(action1)) {
				// 老师修改密码
				if (oldpass.equals(oldPassword1)) {
					session.setAttribute("password", newpass);
					request.getRequestDispatcher("Teacherchangepass.jsp")
							.forward(request, response);
				} else {

					request.setAttribute("error", "密码输入错误！");
					request.setAttribute("oldpass", oldpass); // 输入的旧密码，当错误的时候跳转页面，输入的密码不会清空
					request.setAttribute("newpass", newpass); // 输入的新密码
					request.getRequestDispatcher("Teacherpass.jsp").forward(
							request, response);
				}
			} else {
				// 管理员修改密码
				if (oldpass.equals(oldPassword)) {
					session.setAttribute("password", newpass);
					request.getRequestDispatcher("Adminchangepass.jsp")
							.forward(request, response);
				} else {

					request.setAttribute("error", "密码输入错误！");
					request.setAttribute("oldpass", oldpass); // 输入的旧密码，当错误的时候跳转页面，输入的密码不会清空
					request.setAttribute("newpass", newpass); // 输入的新密码
					request.getRequestDispatcher("Adminpass.jsp").forward(
							request, response);
					// response.sendRedirect("Adminpass.jsp");
				}
			}
		} else if (action.equals("change")) { // 这个是修改密码
			Connection conn = null;
			try {
				conn = jdbcUtil.getConnection();
				if ("teacher".equals(action1)) {
					int saveNums = adminDao.ChangePass1(conn, newpass, teaid);
					if (saveNums > 0) {
						session.setAttribute("teacherps", newpass); // 修改成功后更新session里的密码数据
						request.getRequestDispatcher("TeacherchangeOK.jsp")
								.forward(request, response);

					} else {
						request.setAttribute("error", "修改失败！");
						request.getRequestDispatcher("Teacherchangepass.jsp")
								.forward(request, response);
					}
				} else {
					int saveNums = adminDao.ChangePass(conn, newpass, admid);
					if (saveNums > 0) {
						session.setAttribute("adminps", newpass); // 修改成功后更新session里的密码数据
						request.getRequestDispatcher("AdminchangeOK.jsp")
								.forward(request, response);
					} else {
						request.setAttribute("error", "修改失败！");
						request.getRequestDispatcher("Adminchangepass.jsp")
								.forward(request, response);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();

			} finally {
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
