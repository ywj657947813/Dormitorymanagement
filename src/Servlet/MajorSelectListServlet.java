package Servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import Util.JsonUtil;
import Util.ResponseUtil;

import Dao.AdminDao;
import Util.JdbcUtil;

public class MajorSelectListServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		JdbcUtil jbJdbcUtil=new JdbcUtil();
		AdminDao adminDao=new AdminDao();
		
		String depaId=request.getParameter("depaId");
		
		String teacherType=(String)request.getSession().getAttribute("teachertype"); //��ʦ����ϵ����,����ʦҳ���õ�
		if(teacherType!=null){
			depaId=teacherType;
		}
		Connection conn=null;
		try{
			conn=jbJdbcUtil.getConnection();
			JSONArray jsonArray=new JSONArray();
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("majorId", "");
			jsonObject.put("majorNa", "��ѡ��...");
			jsonArray.add(jsonObject);
			//����ѯ�������а༶��ӽ�jsonArray
			jsonArray.addAll(JsonUtil.formatRsToJsonArray(adminDao.majorList(conn,depaId)));
			ResponseUtil.write(response, jsonArray);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				jbJdbcUtil.close(conn);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}