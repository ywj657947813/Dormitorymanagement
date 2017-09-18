package Servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.AdminDao;
import Model.Page;
import Model.Product;
import Util.GetUrlUtil;
import Util.JdbcUtil;
import Util.StringUtil;

public class AdmSelectProductServlet extends HttpServlet {
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

		String pname = request.getParameter("pname"); // ��Ʒ����
		String action = request.getParameter("action"); // ������

		String value = request.getParameter("pc"); // ��ǰҳ��

		Connection conn = null;
		try {
			conn = jdbcUtil.getConnection();
			if ("admin".equals(action)) {
				// ����Ա��ѯ
				int pc; // ��ǰҳ��
				int ps = 7; // ÿҳ��¼��
				if (value == null || value.trim().isEmpty()) {
					pc = 1;
				} else {
					pc = Integer.parseInt(value);
				}

				/**
				 * ����get����������⣬���ڲ�ѯ����ֻ��״̬��ά�����Ϳ���Ϊ���� ����ֻ���� ����
				 */
				if (StringUtil.isNotEmpty(pname)) {
					pname = new String(pname.getBytes("ISO-8859-1"), "UTF-8");
				}

				Product product = new Product();
				product.setPname(pname);
				Page<Product> message = adminDao.productList(conn, product, pc,
						ps);

				String Url = GetUrlUtil.getUrl(request); // ��ȡget�����ַ���ʺź�ߵ�����
				message.setUrl(Url);

				if (message.getBeanList().size() != 0) {

					request.setAttribute("message", message);
					request.getRequestDispatcher("Adminproduct.jsp").forward(
							request, response);
				} else {
					message = null;
					request.setAttribute("message", message);
					request.getRequestDispatcher("Adminproduct.jsp").forward(
							request, response);
				}
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
