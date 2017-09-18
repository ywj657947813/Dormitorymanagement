package Servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import Dao.AdminDao;
import Model.Product;
import Util.JdbcUtil;
import Util.ResponseUtil;

public class AddProductServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JdbcUtil jdbcUtil = new JdbcUtil();
	AdminDao adminDao = new AdminDao();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		JdbcUtil jdbcUtil = new JdbcUtil();
		Connection conn = jdbcUtil.getConnection();

		String pId = request.getParameter("pid"); // 物品id
		String pprice = request.getParameter("pprice"); // 价格
		String count = request.getParameter("count"); // 数量
		try {
			// 查找对应物品的库存
			Integer total = adminDao
					.findCountByPid(conn, Integer.parseInt(pId));
			// 更改数据
			total += Integer.parseInt(count);
			Product product = new Product();
			product.setPid(pId);
			product.setPprice(Double.parseDouble(pprice));
			product.setPtotal(total);
			int sum = adminDao.updateProduct(conn, product);

			JSONObject result = new JSONObject();
			if (sum > 0) {
				result.put("success", "true");
				result.put("msg", "true");
			} else {
				result.put("success", "true");
				result.put("msg", "false");
			}
			ResponseUtil.write(response, result);

		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
