package Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import Dao.AdminDao;
import Model.Cart;
import Model.CartItem;
import Model.Order;
import Model.OrderItem;
import Util.DateUtil;
import Util.JdbcUtil;
import Util.ResponseUtil;

public class AddOrderServlet extends HttpServlet {

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

		String people = request.getParameter("people"); // 区分 管理员跟维修人员
		String action = request.getParameter("action"); // 操作方法

		try {
			if (action.equals("saveOrder")) {// 添加物品
				// 保存物品清单到数据库
				saveOrder(request, people);
				JSONObject result = new JSONObject();
				result.put("success", "true");
				result.put("msg", "true");
				ResponseUtil.write(response, result);
			}

		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 生成订单的执行的方法:
	public Order saveOrder(HttpServletRequest request, String people)
			throws Exception {
		Order order = new Order();
		// 由于不是ssh框架，不会自动映射数据库，所以要手动设置oid
		String oid = null;
		while (true) {
			// 获取长度为 6 的随机数，作为物品清单id
			oid = DateUtil.getFixLenthString(6);
			// 验证id是否存在。存在则继续生成
			if (adminDao.findOid(oid)) {
				continue;
			} else {
				// 设置oid
				order.setOid(Integer.parseInt(oid));
				break;
			}
		}

		// 购物车在session中,从session中获得购物车信息.
		Cart cart = (Cart) request.getSession().getAttribute("cart");

		// 设置订单的总金额:订单的总金额应该是购物车中总金额:
		order.setTotal(cart.getTotal());
		// 设置订单的状态
		// 管理员设置为 1 维修人员设置为0
		if (people.equals("service")) {
			order.setState(0); // 0:未接受. 1.已接受
		} else if (people.equals("admin")) {
			order.setState(1); // 0:未接受. 1.已接受
		}
		// 设置延时原因，一开始设置为 ""
		order.setDescs("");
		// 设置订单时间(需要转换时间格式，不然保存到数据库会报错)
		Date date = new Date();
		// SimpleDateFormat temp = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		// String date2 = temp.format(date);
		// Date date3 = temp.parse(date2);

		order.setOtime(DateUtil.formatDate(date, "yyyy-MM-dd"));

		// 设置清单关联的维修员id（工号）:
		String suid = (String) request.getSession().getAttribute("suid");
		order.setSuid(suid);

		// 将物品清单插入到数据库中
		// 现在数据库插入一条oid为随机数的order数据，方便下面插入 物品项 的数据
		adminDao.saveOrder(order);
		// 设置订单项集合:
		for (CartItem cartItem : cart.getCartItems()) {
			// 订单项的信息从购物项获得的.
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(cartItem.getCount()); // 设置每个物品项的数量
			orderItem.setSubtotal(cartItem.getSubtotal());// 设置每个物品项的总价
			orderItem.setPid(Integer.parseInt(cartItem.getProduct().getPid()));// 保存该物品信息
			orderItem.setDormid(Integer.parseInt(cartItem.getDormid()));// 设置该物品项用于的宿舍id
			orderItem.setOid(Integer.parseInt(oid));

			// 由于管理员保存的清单状态是已接受的 所以数量要更新，以下if方法为更新数据
			if (people.equals("admin")) {
				JdbcUtil jdbcUtil = new JdbcUtil();
				Connection conn = jdbcUtil.getConnection();
				// 查找对应物品的库存
				Integer count = adminDao.findCountByPid(conn,
						orderItem.getPid());
				// 库存 的数量要减少
				count = count - orderItem.getCount();
				// 更新库存数量
				adminDao.updateProductTotal(conn, count, orderItem.getPid());
			}

			// 将物品项插入到数据库中
			adminDao.saveOrderItem(orderItem);
		}
		// 清空物品项:
		cart.clearCart();
		// 清除维修工id
		request.getSession().removeAttribute("suid");
		return order;
	}
	/*
	 * // 查询我的订单: public String findByUid() { // 获得用户的id. User existUser =
	 * (User) ServletActionContext.getRequest().getSession()
	 * .getAttribute("existUser"); // 获得用户的id Integer uid = existUser.getUid();
	 * // 根据用户的id查询订单: PageBean<Order> pageBean = orderService.findByUid(uid,
	 * page); // 将PageBean数据带到页面上.
	 * ActionContext.getContext().getValueStack().set("pageBean", pageBean);
	 * return "findByUid"; }
	 * 
	 * // 根据订单id查询订单: public String findByOid() { order =
	 * orderService.findByOid(order.getOid()); return "findByOid"; }
	 */
}
