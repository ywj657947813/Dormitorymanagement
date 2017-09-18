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
		String stuId=request.getParameter("stuid");     //学号
		String stuName=request.getParameter("name");    //姓名
		String depaId=request.getParameter("depa");     //系别
		String majorId=request.getParameter("major");   //专业
		String gradeId=request.getParameter("grade");   //班级
		String sex=request.getParameter("sex");         //性别
		String dormno=request.getParameter("dormno");   //宿舍号
		String roomno=request.getParameter("roomno");   //房间号
		String phone=request.getParameter("phone");     //联系电话
		String bedno=request.getParameter("bedno");     //床位号
		
		Student student=new Student(stuId,"123",stuName,depaId,majorId,sex,dormno,roomno,phone,bedno);
		student.setGradeid(gradeId);
		Connection conn=null;
		try{
			conn=jdbcUtil.getConnection();
			
			int saveNums1=adminDao.passAdd(conn, student);    //输入学生登陆账号
			int saveNums=adminDao.studentAdd(conn, student);  //输入学生基本信息
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
