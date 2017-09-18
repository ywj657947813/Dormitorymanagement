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

public class CheckProductCountServlet extends HttpServlet {
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
		JdbcUtil jdbcUtil = new JdbcUtil();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		AdminDao adminDao = new AdminDao();
		String oid = request.getParameter("oid"); // �����嵥id

		Connection conn = null;
		try {
			conn = jdbcUtil.getConnection();
			// ��ȡ�嵥�ϵ���Ʒ
			List<OrderItem> message = adminDao.fingByOid(conn, oid);
			// ���������
			boolean flag = true;
			// �����治�����Ʒ
			String product = "";
			for (OrderItem orderItem : message) {
				// ���Ҷ�Ӧ��Ʒ�Ŀ��
				int count = adminDao.findCountByPid(conn, orderItem.getPid());
				// �ж� ��� �� ���� ��С
				if (count > orderItem.getCount()) {
					continue;
				} else {
					flag = false;
					product += orderItem.getPname() + " ";
				}
			}
			if (flag) {
				// ÿ����Ʒ�����㹻�Ŀ�棬��ת����������
				JSONObject result = new JSONObject();
				result.put("success", "true");
				result.put("msg", "true");
				ResponseUtil.write(response, result);
			} else {
				JSONObject result = new JSONObject();
				result.put("success", "true");
				// ���ÿ�治�����Ʒ����ǰ̨��ʾ
				result.put("product", product);
				result.put("msg", "false");
				ResponseUtil.write(response, result);
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
