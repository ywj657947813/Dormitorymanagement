package Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import Dao.AdminDao;
import Model.OrderItem;
import Util.JdbcUtil;
import Util.ResponseUtil;

public class AdmAcceptOrderServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		AdminDao adminDao = new AdminDao();
		String oid = request.getParameter("oid"); // �����嵥id
		String method = request.getParameter("method"); // ����
		JdbcUtil jdbcUtil = new JdbcUtil();
		Connection conn = jdbcUtil.getConnection();

		try {
			if (method.equals("accept")) {
				// ��ȡ�嵥�ϵ���Ʒ
				List<OrderItem> message = adminDao.fingByOid(conn, oid);
				// ѭ��������Ʒ�������
				for (OrderItem orderItem : message) {
					// ���Ҷ�Ӧ��Ʒ�Ŀ��
					Integer count = adminDao.findCountByPid(conn,
							orderItem.getPid());
					// ��� ������Ҫ����
					count = count - orderItem.getCount();
					// ���¿������
					adminDao.updateProductTotal(conn, count, orderItem.getPid());
				}
				// �޸�order״̬λ 1����Ϣ�����ܣ���ʱԭ�����
				adminDao.updateOrderState(conn, 1, oid, "");

				JSONObject result = new JSONObject();
				result.put("success", "true");
				result.put("msg", "true");
				ResponseUtil.write(response, result);
			} else if (method.equals("quxiao")) {
				// �嵥�����ܣ�����ʱ�䲻��ȡ����״̬�޸�Ϊ 3 �����ص�
				// ��ȡ�嵥�ϵ���Ʒ
				String desc = request.getParameter("desc"); // ��ʱ��ԭ��
				List<OrderItem> message = adminDao.fingByOid(conn, oid);
				// ѭ��������Ʒ�������
				for (OrderItem orderItem : message) {
					// ���Ҷ�Ӧ��Ʒ�Ŀ��
					Integer count = adminDao.findCountByPid(conn,
							orderItem.getPid());
					// ��� ������Ҫ����
					count = count + orderItem.getCount();
					// ���¿������
					adminDao.updateProductTotal(conn, count, orderItem.getPid());
				}
				// �޸�order״̬λ 3,��ʱԭ��
				adminDao.updateOrderState(conn, 3, oid, desc);

				JSONObject result = new JSONObject();
				result.put("success", "true");
				result.put("msg", "true");
				ResponseUtil.write(response, result);
			} else if (method.equals("finish")) {
				// �嵥��Ϣ���� ���޸�״̬Ϊ 2
				adminDao.updateOrderState(conn, 2, oid, null);
				JSONObject result = new JSONObject();
				result.put("success", "true");
				result.put("msg", "true");
				ResponseUtil.write(response, result);
			} else if (method.equals("delay")) {
				// �嵥��Ϣ��ʱ ���޸�״̬Ϊ 3
				String desc = request.getParameter("desc"); // ��ʱ��ԭ��

				adminDao.updateOrderState(conn, 3, oid, desc);
				JSONObject result = new JSONObject();
				result.put("success", "true");
				result.put("msg", "true");
				ResponseUtil.write(response, result);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
