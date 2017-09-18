package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.AdminDao;
import Model.Cart;
import Model.CartItem;
import Model.Product;
import Util.JdbcUtil;
import Util.StringUtil;

public class AddCartServlet extends HttpServlet {

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

		String deleteid = request.getParameter("deleteid"); // 删除单件物品用到的id
		String pid = request.getParameter("pid"); // 物品id
		String count = request.getParameter("count"); // 数量
		String suid = request.getParameter("suid"); // 提取人id
		String dormid = request.getParameter("dormId"); // 用于的宿舍id
		String action = request.getParameter("action"); // 操作方法

		String people = request.getParameter("people"); // 区分 管理员跟维修人员
		if (StringUtil.isNotEmpty(suid)) {
			// 保存到session，等会提交物品清单时用到
			request.getSession().setAttribute("suid", suid);
		}

		try {
			if (action.equals("addCart")) {// 添加物品
				Cart cart = addCart(Integer.parseInt(count), pid, dormid,
						request);
				// 物品单上长度大于0，代表有物品存在
				if (cart.getCartItems().size() > 0) {
					// 判断人员类型，跳转不同页面
					if (people.equals("service")) {
						request.getRequestDispatcher("Servicepextract.jsp")
								.forward(request, response);
					} else if (people.equals("admin")) {
						request.getRequestDispatcher("Adminpextract.jsp")
								.forward(request, response);
					}
				}
			}
			if (action.equals("clearCart")) {// 清除物品单所有物品
				clearCart(request);
				if (people.equals("service")) {
					request.getRequestDispatcher("Servicepextract.jsp")
							.forward(request, response);
				} else if (people.equals("admin")) {
					request.getRequestDispatcher("Adminpextract.jsp").forward(
							request, response);
				}

			}
			if (action.equals("removeCart")) {// 移除某件物品
				removeCart(deleteid, request);
				if (people.equals("service")) {
					request.getRequestDispatcher("Servicepextract.jsp")
							.forward(request, response);
				} else if (people.equals("admin")) {
					request.getRequestDispatcher("Adminpextract.jsp").forward(
							request, response);
				}
			}

		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 将购物项添加到购物车:执行的方法
	public Cart addCart(Integer count, String pid, String dormid,
			HttpServletRequest request) throws Exception {
		// 封装一个CartItem对象.
		CartItem cartItem = new CartItem();

		// 将宿舍id 物品id 转换为string 拼接到一起
		String id = dormid + pid;

		cartItem.setId(id);

		// 设置数量:
		cartItem.setCount(count);

		// 根据pid进行查询商品:
		// 设置商品:
		Product product = adminDao.findByPid(pid);
		cartItem.setProduct(product);

		// 设置宿舍名称
		String dname = adminDao.findDname(dormid);
		cartItem.setDname(dname);

		// 设置该物品项用于的宿舍id
		cartItem.setDormid(dormid);

		// 获取session中的物品单，没有则会新创建一个Cart对象，保存到session中
		Cart cart = getCart(request);

		// 将购物项添加到购物车.
		cart.addCart(cartItem, dormid);

		return cart;
	}

	// 清空购物车的执行的方法:
	public void clearCart(HttpServletRequest request) {
		// 获得购物车对象.
		Cart cart = getCart(request);
		// 调用购物车中清空方法.
		cart.clearCart();
	}

	// 从购物车中移除购物项的方法:
	public String removeCart(String deleteid, HttpServletRequest request) {
		// 获得购物车对象
		Cart cart = getCart(request);
		// 调用购物车中移除的方法:
		cart.removeCart(deleteid);
		// 返回页面:
		return "removeCart";
	}

	/**
	 * 获得购物车的方法:从session中获得购物车.
	 * 
	 * @return
	 */
	private Cart getCart(HttpServletRequest request) {
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			request.getSession().setAttribute("cart", cart);
		}
		return cart;
	}

}
