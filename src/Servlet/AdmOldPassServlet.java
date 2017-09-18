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
		String oldpass = request.getParameter("oldpass"); // ����ľ�����
		String newpass = request.getParameter("newpass"); // �����������
		String oldPassword = (String) session.getAttribute("adminps"); // ����Ա��ʵ����
		String admid = (String) session.getAttribute("adminid"); // ����Ա�˺�
		String oldPassword1 = (String) session.getAttribute("teacherps"); // ��ʦ��ʵ����
		String teaid = (String) session.getAttribute("teacherid"); // ��ʦ�˺�

		String action = request.getParameter("action");
		String action1 = request.getParameter("action1"); // ������Ա����ʦ������Ա��

		if (action.equals("choice")) {// �жϲ���ҳ�� ��������ж������Ƿ���ȷ
			if ("teacher".equals(action1)) {
				// ��ʦ�޸�����
				if (oldpass.equals(oldPassword1)) {
					session.setAttribute("password", newpass);
					request.getRequestDispatcher("Teacherchangepass.jsp")
							.forward(request, response);
				} else {

					request.setAttribute("error", "�����������");
					request.setAttribute("oldpass", oldpass); // ����ľ����룬�������ʱ����תҳ�棬��������벻�����
					request.setAttribute("newpass", newpass); // �����������
					request.getRequestDispatcher("Teacherpass.jsp").forward(
							request, response);
				}
			} else {
				// ����Ա�޸�����
				if (oldpass.equals(oldPassword)) {
					session.setAttribute("password", newpass);
					request.getRequestDispatcher("Adminchangepass.jsp")
							.forward(request, response);
				} else {

					request.setAttribute("error", "�����������");
					request.setAttribute("oldpass", oldpass); // ����ľ����룬�������ʱ����תҳ�棬��������벻�����
					request.setAttribute("newpass", newpass); // �����������
					request.getRequestDispatcher("Adminpass.jsp").forward(
							request, response);
					// response.sendRedirect("Adminpass.jsp");
				}
			}
		} else if (action.equals("change")) { // ������޸�����
			Connection conn = null;
			try {
				conn = jdbcUtil.getConnection();
				if ("teacher".equals(action1)) {
					int saveNums = adminDao.ChangePass1(conn, newpass, teaid);
					if (saveNums > 0) {
						session.setAttribute("teacherps", newpass); // �޸ĳɹ������session�����������
						request.getRequestDispatcher("TeacherchangeOK.jsp")
								.forward(request, response);

					} else {
						request.setAttribute("error", "�޸�ʧ�ܣ�");
						request.getRequestDispatcher("Teacherchangepass.jsp")
								.forward(request, response);
					}
				} else {
					int saveNums = adminDao.ChangePass(conn, newpass, admid);
					if (saveNums > 0) {
						session.setAttribute("adminps", newpass); // �޸ĳɹ������session�����������
						request.getRequestDispatcher("AdminchangeOK.jsp")
								.forward(request, response);
					} else {
						request.setAttribute("error", "�޸�ʧ�ܣ�");
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
