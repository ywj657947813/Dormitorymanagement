package Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.AdminDao;
import Dao.StudentDao;
import Model.Page;
import Model.Service;
import Util.GetUrlUtil;
import Util.JdbcUtil;
import Util.StringUtil;

public class AdmSelectServiceServlet extends HttpServlet {
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
		AdminDao adminDao = new AdminDao();
		StudentDao studentDao = new StudentDao();
		JdbcUtil jdbcUtil = new JdbcUtil();

		String state = request.getParameter("state"); // ά��״̬
		String type = request.getParameter("type"); // ά������
		String dormno = request.getParameter("dormId"); // �����
		String action = request.getParameter("action"); // ������
		String stuId = request.getParameter("stuId"); // ѧ��

		String typeid = (String) request.getSession().getAttribute(
				"servicetype"); // ��ȡά����Ա����
		String value = request.getParameter("pc"); // ��ǰҳ��

		Connection conn = null;
		try {
			conn = jdbcUtil.getConnection();
			if ("student".equals(action)) {
				List<Service> service1 = studentDao.serviceList(conn, stuId,
						"�����");

				if (service1.size() != 0) {

					request.setAttribute("service", service1);
					request.getRequestDispatcher("stuservice.jsp").forward(
							request, response);
				} else {
					service1 = null;
					request.setAttribute("service", service1);
					request.getRequestDispatcher("stuservice.jsp").forward(
							request, response);
				}
			} else if ("service".equals(action)) {
				int pc; // ��ǰҳ��
				int ps = 7; // ÿҳ��¼��
				if (value == null || value.trim().isEmpty()) {
					pc = 1;
				} else {
					pc = Integer.parseInt(value);
				}

				/**
				 * ����get����������⣬���ڲ�ѯ����ֻ��״̬��ά�����Ϳ���Ϊ���� ����ֻ���� ����
				 */
				if (StringUtil.isNotEmpty(state)) {
					state = new String(state.getBytes("ISO-8859-1"), "UTF-8");
				}
				// if(StringUtil.isNotEmpty(typeid)){
				// typeid= new String(typeid.getBytes("ISO-8859-1"),"UTF-8");
				// }
				Service service = new Service(dormno, state, typeid);
				// List<Service> service1=adminDao.serviceList(conn,service);
				Page<Service> message = adminDao.serviceList(conn, service, pc,
						ps);

				String Url = GetUrlUtil.getUrl(request); // ��ȡget�����ַ���ʺź�ߵ�����
				message.setUrl(Url);
				if (message.getBeanList().size() != 0) {

					request.setAttribute("message", message);
					request.getRequestDispatcher("Servicepage.jsp").forward(
							request, response);

				} else {
					message = null;
					request.setAttribute("message", message);
					request.getRequestDispatcher("Servicepage.jsp").forward(
							request, response);
				}

			} else if ("admin".equals(action)) {
				// ����Ա��ѯ
				int pc; // ��ǰҳ��
				int ps = 7; // ÿҳ��¼��
				if (value == null || value.trim().isEmpty()) {
					pc = 1;
				} else {
					pc = Integer.parseInt(value);
				}

				/**
				 * ����get����������⣬���ڲ�ѯ����ֻ��״̬��ά�����Ϳ���Ϊ���� ����ֻ���� ����
				 */
				if (StringUtil.isNotEmpty(state)) {
					state = new String(state.getBytes("ISO-8859-1"), "UTF-8");
				}
				if (StringUtil.isNotEmpty(typeid)) {
					typeid = new String(typeid.getBytes("ISO-8859-1"), "UTF-8");
				}
				Service service = new Service(dormno, state, type);
				Page<Service> message = adminDao.serviceList(conn, service, pc,
						ps);

				String Url = GetUrlUtil.getUrl(request); // ��ȡget�����ַ���ʺź�ߵ�����
				message.setUrl(Url);

				if (message.getBeanList().size() != 0) {

					request.setAttribute("service", message);
					request.getRequestDispatcher("Adminservice.jsp").forward(
							request, response);
				} else {
					message = null;
					request.setAttribute("service", message);
					request.getRequestDispatcher("Adminservice.jsp").forward(
							request, response);
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
