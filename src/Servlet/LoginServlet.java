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

		String Identity = request.getParameter("Identity"); // �û�����
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if (Identity.equals("student")) {
			StudentDao studentDao = new StudentDao();
			boolean islogin;

			islogin = studentDao.CheckLogin(username, password);
			if (islogin) {
				Student student = studentDao.find(username); // ��½�ɹ�����ȡѧ����Ϣ
				session.setAttribute("studentid", username); // ����ѧ��ѧ��
				session.setAttribute("studentps", password); // ����ѧ������
				session.setAttribute("currentUser", student);
				request.getRequestDispatcher("page.jsp").forward(request,
						response);
			} else {
				request.setAttribute("error", "�˺Ż��������");
				request.setAttribute("username", username); // �˺Ż���������ʱ����ת��½ҳ�棬����ԭ��������
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
					Teacher teacher = teacherDao.find(username); // ��½�ɹ�����ȡѧ����Ϣ
					session.setAttribute("teacherid", username); // ������ʦ����
					session.setAttribute("teacherps", password); // ������ʦ����
					session.setAttribute("teachertype", teacher.getDepaid()); // ������ʦ����ϵ��
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
				request.setAttribute("error", "�˺Ż��������");
				request.setAttribute("username", username); // �˺Ż���������ʱ����ת��½ҳ�棬����ԭ��������
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
				Admin admin = adminDao.find1(username); // ��½�ɹ�����ȡά����Ա������Ϣ
				session.setAttribute("servicetype", admin.getTypeid()); // ����ά����Ա������
				session.setAttribute("suid", username); // ����ά����Ա�Ĺ���
				Connection conn = null;
				try {
					Service service = new Service();
					service.setServicetype(admin.getTypeid());
					conn = jdbcUtil.getConnection();
					// ��ȡ������ά��Ա��ά����Ϣ
					int pc = 1; // ��ǰҳ��
					int ps = 7; // ÿҳ��¼��
					Page<Service> message = adminDao.serviceList(conn, service,
							pc, ps);

					// ��ȡget�����ַ���ʺź�ߵ�����
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
				request.setAttribute("error", "�˺Ż��������");
				request.setAttribute("username", username); // �˺Ż���������ʱ����ת��½ҳ�棬����ԭ��������
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
				session.setAttribute("adminid", username); // ���ù���Ա�˺�
				session.setAttribute("adminps", password); // ���ù���Ա����
				session.setAttribute("currentUser", admin); // ���ù���Ա
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
				request.setAttribute("error", "�˺Ż��������");
				request.setAttribute("username", username); // �˺Ż���������ʱ����ת��½ҳ�棬����ԭ��������
				request.setAttribute("password", password);
				request.getRequestDispatcher("login.jsp").forward(request,
						response);
			}
		}

	}
}
