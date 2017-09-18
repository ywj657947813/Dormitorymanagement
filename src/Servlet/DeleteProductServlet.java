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

		String action = request.getParameter("action"); // 调用的方法

		Connection conn = jdbcUtil.getConnection();
		try {
			if (action.equals("product")) {
				// 接收参数
				String pIds = request.getParameter("pIds"); // 要删除物品的id
				// 调用删除物品的方法
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
				// 接收参数
				String oIds = request.getParameter("oIds"); // 要删除清单的id
				// 调用删除清单的方法
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
				// 接收参数
				String otId = request.getParameter("otId"); // 要删除清单中的物品项id
				// 删除清单中物品项的方法
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

	// 删除清单中物品项的方法
	public int deteleOrderItem(Connection con, String otId) throws Exception {
		int a = adminDao.deleteOrderItem(con, otId);
		return a;
	}

	// 删除清单的方法
	public int deteleOrder(Connection con, String oIds) throws Exception {
		int a = adminDao.deleteOrder(con, oIds);
		return a;
	}

	// 删除物品的方法
	public int deteleProduct(Connection con, String pIds) throws Exception {
		int a = adminDao.deleteProduct(con, pIds);
		return a;
	}

}
