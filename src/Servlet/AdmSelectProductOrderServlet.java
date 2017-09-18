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
 * 物品清单查询
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

		String suid = request.getParameter("suid"); // 工号
		String oid = request.getParameter("oid"); // 物品清单编号
		String state = request.getParameter("state"); // 清单状态
		String action = request.getParameter("action"); // 操作人
		String action1 = request.getParameter("action1"); // 操作方法

		String value = request.getParameter("pc"); // 当前页码

		Connection conn = null;
		try {
			conn = jdbcUtil.getConnection();
			int pc; // 当前页码
			int ps = 7; // 每页记录数
			if (value == null || value.trim().isEmpty()) {
				pc = 1;
			} else {
				pc = Integer.parseInt(value);
			}

			// 显示清单里物品详情
			if ("orderitem".equals(action1)) {
				OrderItem orderItem = new OrderItem();
				// 当查询清单详情的时候，在表格上面显示工号,清单编号
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

				String Url = GetUrlUtil.getUrl(request); // 获取get请求地址栏问号后边的内容
				message.setUrl(Url);

				if (message.getBeanList().size() != 0) {
					// 判断管理员 维修员
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
					// 判断管理员 维修员
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

			// 显示清单详情
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
					// 维修员查询自己的申领信息，只需显示未接受 和 延时的记录
					message = adminDao.orderSList(conn, suid);
				}

				String Url = GetUrlUtil.getUrl(request); // 获取get请求地址栏问号后边的内容
				message.setUrl(Url);
				if (message.getBeanList().size() != 0) {
					// 判断管理员 维修员
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
					// 判断管理员 维修员
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
