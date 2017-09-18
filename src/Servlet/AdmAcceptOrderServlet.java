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
		String oid = request.getParameter("oid"); // 接收清单id
		String method = request.getParameter("method"); // 方法
		JdbcUtil jdbcUtil = new JdbcUtil();
		Connection conn = jdbcUtil.getConnection();

		try {
			if (method.equals("accept")) {
				// 获取清单上的物品
				List<OrderItem> message = adminDao.fingByOid(conn, oid);
				// 循环更新物品库存数量
				for (OrderItem orderItem : message) {
					// 查找对应物品的库存
					Integer count = adminDao.findCountByPid(conn,
							orderItem.getPid());
					// 库存 的数量要减少
					count = count - orderItem.getCount();
					// 更新库存数量
					adminDao.updateProductTotal(conn, count, orderItem.getPid());
				}
				// 修改order状态位 1，信息被接受，延时原因清空
				adminDao.updateOrderState(conn, 1, oid, "");

				JSONObject result = new JSONObject();
				result.put("success", "true");
				result.put("msg", "true");
				ResponseUtil.write(response, result);
			} else if (method.equals("quxiao")) {
				// 清单被接受，但长时间不领取，将状态修改为 3 ，库存回调
				// 获取清单上的物品
				String desc = request.getParameter("desc"); // 延时的原因
				List<OrderItem> message = adminDao.fingByOid(conn, oid);
				// 循环更新物品库存数量
				for (OrderItem orderItem : message) {
					// 查找对应物品的库存
					Integer count = adminDao.findCountByPid(conn,
							orderItem.getPid());
					// 库存 的数量要减少
					count = count + orderItem.getCount();
					// 更新库存数量
					adminDao.updateProductTotal(conn, count, orderItem.getPid());
				}
				// 修改order状态位 3,延时原因
				adminDao.updateOrderState(conn, 3, oid, desc);

				JSONObject result = new JSONObject();
				result.put("success", "true");
				result.put("msg", "true");
				ResponseUtil.write(response, result);
			} else if (method.equals("finish")) {
				// 清单信息结束 ，修改状态为 2
				adminDao.updateOrderState(conn, 2, oid, null);
				JSONObject result = new JSONObject();
				result.put("success", "true");
				result.put("msg", "true");
				ResponseUtil.write(response, result);
			} else if (method.equals("delay")) {
				// 清单信息延时 ，修改状态为 3
				String desc = request.getParameter("desc"); // 延时的原因

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
