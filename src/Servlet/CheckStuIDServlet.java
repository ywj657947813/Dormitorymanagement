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
		String id = request.getParameter("stuID"); // 学生学号
		String name = request.getParameter("name"); // 学生姓名
		String DormId = request.getParameter("DormId"); // 宿舍楼
		String RoomId = request.getParameter("RoomId"); // 房间号
		String BedId = request.getParameter("BedId"); // 床号
		String action = request.getParameter("action"); // 操作

		String action1 = request.getParameter("action1"); // 管理员 违纪信息页面用到

		Connection conn = null;
		try {
			conn = jdbcUtil.getConnection();
			if (action.equals("xuehao")) {

				boolean saveNums = studentDao.CheckID(conn, id);
				if (saveNums) { // 学号存在
					if ("action1".equals(action1)) { // 违纪页面，当学号正确时，在判断跟名字是否一致
						boolean saveNums1 = studentDao
								.CheckName(conn, id, name);
						if (saveNums1) { // 学号、姓名一致
							JSONObject result = new JSONObject();
							result.put("success", "true");
							result.put("msg", "true1");
							ResponseUtil.write(response, result);
						} else { // 学号、姓名不一致
							JSONObject result = new JSONObject();
							result.put("success", "true");
							result.put("msg", "false1");
							ResponseUtil.write(response, result);
						}
					} else { // 插入学生信息检验学号是否存在（true）

						JSONObject result = new JSONObject();
						result.put("success", "true");
						result.put("msg", "true");
						ResponseUtil.write(response, result);
					}

				} else { // 违纪信息页面、插入学生信息页面检验学号是否存在（false）
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
				// 查询维修人员工号是否存在
				AdminDao adminDao = new AdminDao();
				String suid = request.getParameter("suid"); // 维修人员工号
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
