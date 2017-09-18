package Servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import Dao.AdminDao;
import Model.Wrong;
import Util.JdbcUtil;

public class AdmInsertWrongServlet extends HttpServlet {

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
		String stuId=request.getParameter("id");        //ѧ��
		String stuName=request.getParameter("name");    //����
		String day=request.getParameter("day");         //Υ������
		String reason=request.getParameter("reason");   //Υ��ԭ��
		String result=request.getParameter("result");   //����ʽ
		
		Wrong wrong=new Wrong(stuId,stuName,day,reason,result);
		Connection conn=null;
		try{
			conn=jdbcUtil.getConnection();
			
			int saveNums=adminDao.wrongAdd(conn, wrong);  //����ѧ��������Ϣ
		
			if(saveNums>0){
				request.setAttribute("success", "true");
				
				request.getRequestDispatcher("Adminwrong.jsp").forward(request, response);
				
			
			}else{
                request.setAttribute("error", "false");
				
				request.getRequestDispatcher("Adminwrong.jsp").forward(request, response);
				
			}
			
			
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
