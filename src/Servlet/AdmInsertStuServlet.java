package Servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;


import Dao.AdminDao;
import Model.Student;
import Util.JdbcUtil;
import Util.ResponseUtil;

public class AdmInsertStuServlet extends HttpServlet {

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
		JdbcUtil jdbcUtil =new JdbcUtil();
		AdminDao adminDao = new AdminDao();
		String stuId=request.getParameter("stuid");     //ѧ��
		String stuName=request.getParameter("name");    //����
		String depaId=request.getParameter("depa");     //ϵ��
		String majorId=request.getParameter("major");   //רҵ
		String gradeId=request.getParameter("grade");   //�༶
		String sex=request.getParameter("sex");         //�Ա�
		String dormno=request.getParameter("dormno");   //�����
		String roomno=request.getParameter("roomno");   //�����
		String phone=request.getParameter("phone");     //��ϵ�绰
		String bedno=request.getParameter("bedno");     //��λ��
		
		Student student=new Student(stuId,"123",stuName,depaId,majorId,sex,dormno,roomno,phone,bedno);
		student.setGradeid(gradeId);
		Connection conn=null;
		try{
			conn=jdbcUtil.getConnection();
			
			int saveNums1=adminDao.passAdd(conn, student);    //����ѧ����½�˺�
			int saveNums=adminDao.studentAdd(conn, student);  //����ѧ��������Ϣ
			JSONObject result=new JSONObject();
			if(saveNums>0&&saveNums1>0){
				result.put("success", "true");
				result.put("msg", "true");
			}else{
				result.put("success", "true");
				result.put("msg", "false");
			}
			ResponseUtil.write(response, result);
		}catch(Exception e){
			e.printStackTrace();
			
		}finally{
			try {
				jdbcUtil.close(conn);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
