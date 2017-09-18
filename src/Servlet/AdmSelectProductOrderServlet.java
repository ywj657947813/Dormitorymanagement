package Servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.AdminDao;
import Model.Order;
import Model.OrderItem;
import Model.Page;
import Util.GetUrlUtil;
import Util.JdbcUtil;
import Util.StringUtil;

/**
 * ��Ʒ�嵥��ѯ
 * 
 * @author Yu
 * 
 */
public class AdmSelectProductOrderServlet extends HttpServlet {
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
		JdbcUtil jdbcUtil = new JdbcUtil();

		String suid = request.getParameter("suid"); // ����
		String oid = request.getParameter("oid"); // ��Ʒ�嵥���
		String state = request.getParameter("state"); // �嵥״̬
		String action = request.getParameter("action"); // ������
		String action1 = request.getParameter("action1"); // ��������

		String value = request.getParameter("pc"); // ��ǰҳ��

		Connection conn = null;
		try {
			conn = jdbcUtil.getConnection();
			int pc; // ��ǰҳ��
			int ps = 7; // ÿҳ��¼��
			if (value == null || value.trim().isEmpty()) {
				pc = 1;
			} else {
				pc = Integer.parseInt(value);
			}

			// ��ʾ�嵥����Ʒ����
			if ("orderitem".equals(action1)) {
				OrderItem orderItem = new OrderItem();
				// ����ѯ�嵥�����ʱ���ڱ��������ʾ����,�嵥���
				if (StringUtil.isNotEmpty(suid)) {
					request.setAttribute("suid1", suid);
					request.setAttribute("oid1", oid);
					request.setAttribute("state1", state);
				}
				if (StringUtil.isNotEmpty(oid)) {
					orderItem.setOid(Integer.parseInt(oid));
				}
				Page<OrderItem> message = adminDao.orderItemList(conn,
						orderItem, pc, ps);

				String Url = GetUrlUtil.getUrl(request); // ��ȡget�����ַ���ʺź�ߵ�����
				message.setUrl(Url);

				if (message.getBeanList().size() != 0) {
					// �жϹ���Ա ά��Ա
					if (action.equals("admin")) {
						request.setAttribute("message", message);
						request.getRequestDispatcher(
								"Adminproductorderitem.jsp").forward(request,
								response);
					} else if (action.equals("service")) {
						request.setAttribute("message", message);
						request.getRequestDispatcher("ServiceOrderItem.jsp")
								.forward(request, response);
					}
				} else {
					message = null;
					// �жϹ���Ա ά��Ա
					if (action.equals("admin")) {
						request.setAttribute("message", message);
						request.getRequestDispatcher(
								"Adminproductorderitem.jsp").forward(request,
								response);
					} else if (action.equals("service")) {
						request.setAttribute("message", message);
						request.getRequestDispatcher("ServiceOrderItem.jsp")
								.forward(request, response);
					}
				}
			}

			// ��ʾ�嵥����
			else {
				Order order = new Order();
				if (StringUtil.isNotEmpty(suid)) {
					order.setSuid(suid);
				}
				if (StringUtil.isNotEmpty(oid)) {
					order.setOid(Integer.parseInt(oid));
				}
				if (StringUtil.isNotEmpty(state)) {
					order.setState(Integer.parseInt(state));
				}
				Page<Order> message = null;
				if (action.equals("admin")) {
					message = adminDao.orderList(conn, order, pc, ps);
				} else if (action.equals("service")) {
					// ά��Ա��ѯ�Լ���������Ϣ��ֻ����ʾδ���� �� ��ʱ�ļ�¼
					message = adminDao.orderSList(conn, suid);
				}

				String Url = GetUrlUtil.getUrl(request); // ��ȡget�����ַ���ʺź�ߵ�����
				message.setUrl(Url);
				if (message.getBeanList().size() != 0) {
					// �жϹ���Ա ά��Ա
					if (action.equals("admin")) {
						request.setAttribute("message", message);
						request.getRequestDispatcher("Adminproductorder.jsp")
								.forward(request, response);
					} else if (action.equals("service")) {
						request.setAttribute("message", message);
						request.getRequestDispatcher("ServiceOrderMsg.jsp")
								.forward(request, response);
					}
				} else {
					message = null;
					// �жϹ���Ա ά��Ա
					if (action.equals("admin")) {
						request.setAttribute("message", message);
						request.getRequestDispatcher("Adminproductorder.jsp")
								.forward(request, response);
					} else if (action.equals("service")) {
						request.setAttribute("message", message);
						request.getRequestDispatcher("ServiceOrderMsg.jsp")
								.forward(request, response);
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				jdbcUtil.close(conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
