package Servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.AdminDao;
import Dao.StudentDao;
import Dao.TeacherDao;
import Model.Admin;
import Model.Page;
import Model.Service;
import Model.Student;
import Model.Teacher;
import Util.JdbcUtil;

public class LoginServlet extends HttpServlet {
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
		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		String Identity = request.getParameter("Identity"); // 用户类型
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if (Identity.equals("student")) {
			StudentDao studentDao = new StudentDao();
			boolean islogin;

			islogin = studentDao.CheckLogin(username, password);
			if (islogin) {
				Student student = studentDao.find(username); // 登陆成功，获取学生信息
				session.setAttribute("studentid", username); // 设置学生学号
				session.setAttribute("studentps", password); // 设置学生密码
				session.setAttribute("currentUser", student);
				request.getRequestDispatcher("page.jsp").forward(request,
						response);
			} else {
				request.setAttribute("error", "账号或密码错误！");
				request.setAttribute("username", username); // 账号或密码错误的时候，跳转登陆页面，传回原来的数据
				request.setAttribute("password", password);
				request.getRequestDispatcher("login.jsp").forward(request,
						response);
			}
		} else if (Identity.equals("teacher")) {
			TeacherDao teacherDao = new TeacherDao();
			boolean islogin;

			islogin = teacherDao.CheckLogin(username, password);
			if (islogin) {
				JdbcUtil jdbcUtil = new JdbcUtil();
				Connection conn = null;
				try {
					conn = jdbcUtil.getConnection();
					Teacher teacher = teacherDao.find(username); // 登陆成功，获取学生信息
					session.setAttribute("teacherid", username); // 设置老师工号
					session.setAttribute("teacherps", password); // 设置老师密码
					session.setAttribute("teachertype", teacher.getDepaid()); // 设置老师所属系别
					session.setAttribute("currentUser", teacher);
					request.getRequestDispatcher("Teacherpage.jsp").forward(
							request, response);
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
			} else {
				request.setAttribute("error", "账号或密码错误！");
				request.setAttribute("username", username); // 账号或密码错误的时候，跳转登陆页面，传回原来的数据
				request.setAttribute("password", password);
				request.getRequestDispatcher("login.jsp").forward(request,
						response);
			}
		} else if (Identity.equals("service")) {
			AdminDao adminDao = new AdminDao();
			boolean islogin;

			islogin = adminDao.CheckLogin1(username, password);
			if (islogin) {
				JdbcUtil jdbcUtil = new JdbcUtil();
				Admin admin = adminDao.find1(username); // 登陆成功，获取维修人员类型信息
				session.setAttribute("servicetype", admin.getTypeid()); // 设置维修人员的类型
				session.setAttribute("suid", username); // 设置维修人员的工号
				Connection conn = null;
				try {
					Service service = new Service();
					service.setServicetype(admin.getTypeid());
					conn = jdbcUtil.getConnection();
					// 获取该类型维修员的维修信息
					int pc = 1; // 当前页码
					int ps = 7; // 每页记录数
					Page<Service> message = adminDao.serviceList(conn, service,
							pc, ps);

					// 获取get请求地址栏问号后边的内容
					message.setUrl("selectService?action=service");
					if (message.getBeanList().size() != 0) {

						request.setAttribute("message", message);
						request.getRequestDispatcher("Servicepage.jsp")
								.forward(request, response);

					} else {
						message = null;
						request.setAttribute("message", message);
						request.getRequestDispatcher("Servicepage.jsp")
								.forward(request, response);
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
			} else {
				request.setAttribute("error", "账号或密码错误！");
				request.setAttribute("username", username); // 账号或密码错误的时候，跳转登陆页面，传回原来的数据
				request.setAttribute("password", password);
				request.getRequestDispatcher("login.jsp").forward(request,
						response);
			}
		} else {
			AdminDao adminDao = new AdminDao();
			JdbcUtil jdbcUtil = new JdbcUtil();
			boolean islogin;

			islogin = adminDao.CheckLogin(username, password);
			if (islogin) {
				Admin admin = adminDao.find(username);
				session.setAttribute("name", username);
				session.setAttribute("adminid", username); // 设置管理员账号
				session.setAttribute("adminps", password); // 设置管理员密码
				session.setAttribute("currentUser", admin); // 设置管理员
				Connection conn = null;
				try {
					// Student student=new Student();
					// conn=jdbcUtil.getConnection();
					// List<Student> message=adminDao.dormsList(conn, student);
					// request.setAttribute("message", message);
					request.getRequestDispatcher("Adminpage.jsp").forward(
							request, response);
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
			} else {
				request.setAttribute("error", "账号或密码错误！");
				request.setAttribute("username", username); // 账号或密码错误的时候，跳转登陆页面，传回原来的数据
				request.setAttribute("password", password);
				request.getRequestDispatcher("login.jsp").forward(request,
						response);
			}
		}

	}
}
