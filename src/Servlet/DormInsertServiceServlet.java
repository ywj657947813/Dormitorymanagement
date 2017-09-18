package Servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import Dao.StudentDao;
import Model.Service;
import Util.JdbcUtil;

public class DormInsertServiceServlet extends HttpServlet {
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
		StudentDao studentDao=new StudentDao();
		JdbcUtil jdbcUtil=new JdbcUtil();
		HttpSession session=request.getSession();
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String stuid=(String) session.getAttribute("studentid");   //��ȡ��ǰ����ѧ����ѧ��
		String Dormmun=request.getParameter("Dormmun");         //����¥
		String RoomId=request.getParameter("RoomId");           //����
		String name=request.getParameter("Dname");              //����
		String phone=request.getParameter("phone");             //��ϵ��ʽ
		String servicething=request.getParameter("thing");      //������Ʒ����   
		String servicereason=request.getParameter("reason");    //��������
		String Time=request.getParameter("NowTime");             //����ʱ��
		String State="��ά��";            //״̬
		
		Service service=new Service(stuid,Dormmun,RoomId,servicething,servicereason,name,phone,Time,State);
		Connection conn=null;
		try{
			conn=jdbcUtil.getConnection();
			
			int saveNums=studentDao.serviceAdd(conn, service);
		
		
			if(saveNums>0){
				request.setAttribute("success", "true");  
	            request.getRequestDispatcher("service.jsp").forward(request, response);
			}else{
				
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
