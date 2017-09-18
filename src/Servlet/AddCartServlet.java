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

		String deleteid = request.getParameter("deleteid"); // ɾ��������Ʒ�õ���id
		String pid = request.getParameter("pid"); // ��Ʒid
		String count = request.getParameter("count"); // ����
		String suid = request.getParameter("suid"); // ��ȡ��id
		String dormid = request.getParameter("dormId"); // ���ڵ�����id
		String action = request.getParameter("action"); // ��������

		String people = request.getParameter("people"); // ���� ����Ա��ά����Ա
		if (StringUtil.isNotEmpty(suid)) {
			// ���浽session���Ȼ��ύ��Ʒ�嵥ʱ�õ�
			request.getSession().setAttribute("suid", suid);
		}

		try {
			if (action.equals("addCart")) {// �����Ʒ
				Cart cart = addCart(Integer.parseInt(count), pid, dormid,
						request);
				// ��Ʒ���ϳ��ȴ���0����������Ʒ����
				if (cart.getCartItems().size() > 0) {
					// �ж���Ա���ͣ���ת��ͬҳ��
					if (people.equals("service")) {
						request.getRequestDispatcher("Servicepextract.jsp")
								.forward(request, response);
					} else if (people.equals("admin")) {
						request.getRequestDispatcher("Adminpextract.jsp")
								.forward(request, response);
					}
				}
			}
			if (action.equals("clearCart")) {// �����Ʒ��������Ʒ
				clearCart(request);
				if (people.equals("service")) {
					request.getRequestDispatcher("Servicepextract.jsp")
							.forward(request, response);
				} else if (people.equals("admin")) {
					request.getRequestDispatcher("Adminpextract.jsp").forward(
							request, response);
				}

			}
			if (action.equals("removeCart")) {// �Ƴ�ĳ����Ʒ
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

	// ����������ӵ����ﳵ:ִ�еķ���
	public Cart addCart(Integer count, String pid, String dormid,
			HttpServletRequest request) throws Exception {
		// ��װһ��CartItem����.
		CartItem cartItem = new CartItem();

		// ������id ��Ʒid ת��Ϊstring ƴ�ӵ�һ��
		String id = dormid + pid;

		cartItem.setId(id);

		// ��������:
		cartItem.setCount(count);

		// ����pid���в�ѯ��Ʒ:
		// ������Ʒ:
		Product product = adminDao.findByPid(pid);
		cartItem.setProduct(product);

		// ������������
		String dname = adminDao.findDname(dormid);
		cartItem.setDname(dname);

		// ���ø���Ʒ�����ڵ�����id
		cartItem.setDormid(dormid);

		// ��ȡsession�е���Ʒ����û������´���һ��Cart���󣬱��浽session��
		Cart cart = getCart(request);

		// ����������ӵ����ﳵ.
		cart.addCart(cartItem, dormid);

		return cart;
	}

	// ��չ��ﳵ��ִ�еķ���:
	public void clearCart(HttpServletRequest request) {
		// ��ù��ﳵ����.
		Cart cart = getCart(request);
		// ���ù��ﳵ����շ���.
		cart.clearCart();
	}

	// �ӹ��ﳵ���Ƴ�������ķ���:
	public String removeCart(String deleteid, HttpServletRequest request) {
		// ��ù��ﳵ����
		Cart cart = getCart(request);
		// ���ù��ﳵ���Ƴ��ķ���:
		cart.removeCart(deleteid);
		// ����ҳ��:
		return "removeCart";
	}

	/**
	 * ��ù��ﳵ�ķ���:��session�л�ù��ﳵ.
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
