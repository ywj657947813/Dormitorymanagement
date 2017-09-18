package Servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import Dao.AdminDao;
import Util.JdbcUtil;
import Util.ResponseUtil;

public class DeleteProductServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AdminDao adminDao = new AdminDao();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		JdbcUtil jdbcUtil = new JdbcUtil();

		String action = request.getParameter("action"); // ���õķ���

		Connection conn = jdbcUtil.getConnection();
		try {
			if (action.equals("product")) {
				// ���ղ���
				String pIds = request.getParameter("pIds"); // Ҫɾ����Ʒ��id
				// ����ɾ����Ʒ�ķ���
				int sum = deteleProduct(conn, pIds);
				JSONObject result = new JSONObject();
				if (sum > 0) {
					result.put("success", "true");
					result.put("msg", "true");
				} else {
					result.put("success", "true");
					result.put("msg", "false");
				}
				ResponseUtil.write(response, result);
			} else if (action.equals("order")) {
				// ���ղ���
				String oIds = request.getParameter("oIds"); // Ҫɾ���嵥��id
				// ����ɾ���嵥�ķ���
				int sum = deteleOrder(conn, oIds);
				JSONObject result = new JSONObject();
				if (sum > 0) {
					result.put("success", "true");
					result.put("msg", "true");
				} else {
					result.put("success", "true");
					result.put("msg", "false");
				}
				ResponseUtil.write(response, result);
			} else if (action.equals("orderitem")) {
				// ���ղ���
				String otId = request.getParameter("otId"); // Ҫɾ���嵥�е���Ʒ��id
				// ɾ���嵥����Ʒ��ķ���
				int sum = deteleOrderItem(conn, otId);
				JSONObject result = new JSONObject();
				if (sum > 0) {
					result.put("success", "true");
					result.put("msg", "true");
				} else {
					result.put("success", "true");
					result.put("msg", "false");
				}
				ResponseUtil.write(response, result);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
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

	// ɾ���嵥����Ʒ��ķ���
	public int deteleOrderItem(Connection con, String otId) throws Exception {
		int a = adminDao.deleteOrderItem(con, otId);
		return a;
	}

	// ɾ���嵥�ķ���
	public int deteleOrder(Connection con, String oIds) throws Exception {
		int a = adminDao.deleteOrder(con, oIds);
		return a;
	}

	// ɾ����Ʒ�ķ���
	public int deteleProduct(Connection con, String pIds) throws Exception {
		int a = adminDao.deleteProduct(con, pIds);
		return a;
	}

}
