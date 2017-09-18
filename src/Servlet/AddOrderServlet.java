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

		String people = request.getParameter("people"); // ���� ����Ա��ά����Ա
		String action = request.getParameter("action"); // ��������

		try {
			if (action.equals("saveOrder")) {// �����Ʒ
				// ������Ʒ�嵥�����ݿ�
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

	// ���ɶ�����ִ�еķ���:
	public Order saveOrder(HttpServletRequest request, String people)
			throws Exception {
		Order order = new Order();
		// ���ڲ���ssh��ܣ������Զ�ӳ�����ݿ⣬����Ҫ�ֶ�����oid
		String oid = null;
		while (true) {
			// ��ȡ����Ϊ 6 �����������Ϊ��Ʒ�嵥id
			oid = DateUtil.getFixLenthString(6);
			// ��֤id�Ƿ���ڡ��������������
			if (adminDao.findOid(oid)) {
				continue;
			} else {
				// ����oid
				order.setOid(Integer.parseInt(oid));
				break;
			}
		}

		// ���ﳵ��session��,��session�л�ù��ﳵ��Ϣ.
		Cart cart = (Cart) request.getSession().getAttribute("cart");

		// ���ö������ܽ��:�������ܽ��Ӧ���ǹ��ﳵ���ܽ��:
		order.setTotal(cart.getTotal());
		// ���ö�����״̬
		// ����Ա����Ϊ 1 ά����Ա����Ϊ0
		if (people.equals("service")) {
			order.setState(0); // 0:δ����. 1.�ѽ���
		} else if (people.equals("admin")) {
			order.setState(1); // 0:δ����. 1.�ѽ���
		}
		// ������ʱԭ��һ��ʼ����Ϊ ""
		order.setDescs("");
		// ���ö���ʱ��(��Ҫת��ʱ���ʽ����Ȼ���浽���ݿ�ᱨ��)
		Date date = new Date();
		// SimpleDateFormat temp = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		// String date2 = temp.format(date);
		// Date date3 = temp.parse(date2);

		order.setOtime(DateUtil.formatDate(date, "yyyy-MM-dd"));

		// �����嵥������ά��Աid�����ţ�:
		String suid = (String) request.getSession().getAttribute("suid");
		order.setSuid(suid);

		// ����Ʒ�嵥���뵽���ݿ���
		// �������ݿ����һ��oidΪ�������order���ݣ������������ ��Ʒ�� ������
		adminDao.saveOrder(order);
		// ���ö������:
		for (CartItem cartItem : cart.getCartItems()) {
			// ���������Ϣ�ӹ������õ�.
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(cartItem.getCount()); // ����ÿ����Ʒ�������
			orderItem.setSubtotal(cartItem.getSubtotal());// ����ÿ����Ʒ����ܼ�
			orderItem.setPid(Integer.parseInt(cartItem.getProduct().getPid()));// �������Ʒ��Ϣ
			orderItem.setDormid(Integer.parseInt(cartItem.getDormid()));// ���ø���Ʒ�����ڵ�����id
			orderItem.setOid(Integer.parseInt(oid));

			// ���ڹ���Ա������嵥״̬���ѽ��ܵ� ��������Ҫ���£�����if����Ϊ��������
			if (people.equals("admin")) {
				JdbcUtil jdbcUtil = new JdbcUtil();
				Connection conn = jdbcUtil.getConnection();
				// ���Ҷ�Ӧ��Ʒ�Ŀ��
				Integer count = adminDao.findCountByPid(conn,
						orderItem.getPid());
				// ��� ������Ҫ����
				count = count - orderItem.getCount();
				// ���¿������
				adminDao.updateProductTotal(conn, count, orderItem.getPid());
			}

			// ����Ʒ����뵽���ݿ���
			adminDao.saveOrderItem(orderItem);
		}
		// �����Ʒ��:
		cart.clearCart();
		// ���ά�޹�id
		request.getSession().removeAttribute("suid");
		return order;
	}
	/*
	 * // ��ѯ�ҵĶ���: public String findByUid() { // ����û���id. User existUser =
	 * (User) ServletActionContext.getRequest().getSession()
	 * .getAttribute("existUser"); // ����û���id Integer uid = existUser.getUid();
	 * // �����û���id��ѯ����: PageBean<Order> pageBean = orderService.findByUid(uid,
	 * page); // ��PageBean���ݴ���ҳ����.
	 * ActionContext.getContext().getValueStack().set("pageBean", pageBean);
	 * return "findByUid"; }
	 * 
	 * // ���ݶ���id��ѯ����: public String findByOid() { order =
	 * orderService.findByOid(order.getOid()); return "findByOid"; }
	 */
}
