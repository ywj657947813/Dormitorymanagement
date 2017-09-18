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
		String oid = request.getParameter("oid"); // 接收清单id

		Connection conn = null;
		try {
			conn = jdbcUtil.getConnection();
			// 获取清单上的物品
			List<OrderItem> message = adminDao.fingByOid(conn, oid);
			// 定义个变量
			boolean flag = true;
			// 保存库存不足的物品
			String product = "";
			for (OrderItem orderItem : message) {
				// 查找对应物品的库存
				int count = adminDao.findCountByPid(conn, orderItem.getPid());
				// 判断 库存 跟 数量 大小
				if (count > orderItem.getCount()) {
					continue;
				} else {
					flag = false;
					product += orderItem.getPname() + " ";
				}
			}
			if (flag) {
				// 每件物品都有足够的库存，则转发这条数据
				JSONObject result = new JSONObject();
				result.put("success", "true");
				result.put("msg", "true");
				ResponseUtil.write(response, result);
			} else {
				JSONObject result = new JSONObject();
				result.put("success", "true");
				// 设置库存不足的物品，在前台显示
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
