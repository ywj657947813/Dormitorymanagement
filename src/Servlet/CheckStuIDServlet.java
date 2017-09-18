package Servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import Dao.AdminDao;
import Dao.StudentDao;
import Util.JdbcUtil;
import Util.ResponseUtil;

public class CheckStuIDServlet extends HttpServlet {
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
		StudentDao studentDao = new StudentDao();
		JdbcUtil jdbcUtil = new JdbcUtil();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String id = request.getParameter("stuID"); // ѧ��ѧ��
		String name = request.getParameter("name"); // ѧ������
		String DormId = request.getParameter("DormId"); // ����¥
		String RoomId = request.getParameter("RoomId"); // �����
		String BedId = request.getParameter("BedId"); // ����
		String action = request.getParameter("action"); // ����

		String action1 = request.getParameter("action1"); // ����Ա Υ����Ϣҳ���õ�

		Connection conn = null;
		try {
			conn = jdbcUtil.getConnection();
			if (action.equals("xuehao")) {

				boolean saveNums = studentDao.CheckID(conn, id);
				if (saveNums) { // ѧ�Ŵ���
					if ("action1".equals(action1)) { // Υ��ҳ�棬��ѧ����ȷʱ�����жϸ������Ƿ�һ��
						boolean saveNums1 = studentDao
								.CheckName(conn, id, name);
						if (saveNums1) { // ѧ�š�����һ��
							JSONObject result = new JSONObject();
							result.put("success", "true");
							result.put("msg", "true1");
							ResponseUtil.write(response, result);
						} else { // ѧ�š�������һ��
							JSONObject result = new JSONObject();
							result.put("success", "true");
							result.put("msg", "false1");
							ResponseUtil.write(response, result);
						}
					} else { // ����ѧ����Ϣ����ѧ���Ƿ���ڣ�true��

						JSONObject result = new JSONObject();
						result.put("success", "true");
						result.put("msg", "true");
						ResponseUtil.write(response, result);
					}

				} else { // Υ����Ϣҳ�桢����ѧ����Ϣҳ�����ѧ���Ƿ���ڣ�false��
					JSONObject result = new JSONObject();
					result.put("success", "true");
					result.put("msg", "false");
					ResponseUtil.write(response, result);
				}
			} else if (action.equals("chuangwei")) {
				boolean saveNums = studentDao.CheckBed(conn, DormId, RoomId,
						BedId);
				if (saveNums) {

					JSONObject result = new JSONObject();
					result.put("success", "true");
					result.put("msg", "true");
					ResponseUtil.write(response, result);
				} else {
					JSONObject result = new JSONObject();
					result.put("success", "true");
					result.put("msg", "false");
					ResponseUtil.write(response, result);
				}
			} else if (action.equals("serviceId")) {
				// ��ѯά����Ա�����Ƿ����
				AdminDao adminDao = new AdminDao();
				String suid = request.getParameter("suid"); // ά����Ա����
				boolean saveNums = adminDao.ChecksuId(conn, suid);
				if (saveNums) {
					JSONObject result = new JSONObject();
					result.put("success", "true");
					result.put("msg", "true");
					ResponseUtil.write(response, result);
				} else {
					JSONObject result = new JSONObject();
					result.put("success", "true");
					result.put("msg", "false");
					ResponseUtil.write(response, result);
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
