package Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import Dao.AdminDao;
import Dao.TeacherDao;
import Model.Leave;
import Model.Page;
import Util.GetUrlUtil;
import Util.JdbcUtil;
import Util.StringUtil;

public class LeaveSelectServlet extends HttpServlet {
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
		
		AdminDao adminDao=new AdminDao();
		TeacherDao teacherDao=new TeacherDao();
		JdbcUtil jdbcUtil=new JdbcUtil();
		String stuId=request.getParameter("id");      //ѧ��      (ѧ��������Ա)
		String action=request.getParameter("action"); //������Ա
		String teacherType=(String)request.getSession().getAttribute("teachertype"); //��ʦ����ϵ����
		String value=request.getParameter("pc");  //��ǰҳ��
		
		String depa=request.getParameter("depa");    //ϵ��
		String major=request.getParameter("major");  //רҵ
		String grade=request.getParameter("grade");  //�༶
		String state=request.getParameter("state");  //��Ϣ״̬
		Connection conn=null;
		try{
			conn=jdbcUtil.getConnection();
			
		
		
			if("student".equals(action)){ //ѧ����ѯ�����Ϣ
				List<Leave> leave=adminDao.leaveList(conn,stuId);
				if(leave.size()!=0){
					request.setAttribute("leave",leave);  
					request.getRequestDispatcher("selectleave.jsp").forward(request, response);
				}else {
					leave=null;    //����Ϊ�գ�ǰ̨������ʾ
					request.setAttribute("leave",leave);  
					request.getRequestDispatcher("selectleave.jsp").forward(request, response);
				}
			}else if("teacher".equals(action)){
				int pc;    //��ǰҳ��
				int ps=7; //ÿҳ��¼��
				if(value==null || value.trim().isEmpty()){
				    pc=1;
				}else {
					pc=Integer.parseInt(value);
				}
				/**
				 * ����get����������⣬���ڲ�ѯ����ֻ��  ��Ϣ״̬  ����Ϊ���ģ� ����ֻ����  ��Ϣ״̬
				 */
				if(StringUtil.isNotEmpty(state)){
					state= new String(state.getBytes("ISO-8859-1"),"UTF-8");
				}
				Leave leave1=new Leave(stuId,state,teacherType);
				leave1.setMajorId(major);
				leave1.setGradeId(grade);
				request.getSession().setAttribute("leaveselect", leave1);  //�����ѯ���������������õ�
				Page<Leave> message=teacherDao.leaveList1(conn,leave1,pc,ps);
				
				String Url=GetUrlUtil.getUrl(request); //��ȡget�����ַ���ʺź�ߵ�����
				message.setUrl(Url);
				if(message.getBeanList().size()!=0){
					request.setAttribute("leave",message);  
					request.getRequestDispatcher("Teacherleave.jsp").forward(request, response);
				}else {
					message=null;    //����Ϊ�գ�ǰ̨������ʾ
					request.setAttribute("leave",message);  
					request.getRequestDispatcher("Teacherleave.jsp").forward(request, response);
				}
			}else if("admin".equals(action)){        //����Ա��ѯ�����Ϣ
				int pc;   //��ǰҳ��
				int ps=7; //ÿҳ��¼��
				if(value==null || value.trim().isEmpty()){
				    pc=1;
				}else {
					pc=Integer.parseInt(value);
				}
				/**
				 * ����get����������⣬���ڲ�ѯ����ֻ��  ��Ϣ״̬  ����Ϊ���ģ� ����ֻ����  ��Ϣ״̬
				 */
				if(StringUtil.isNotEmpty(state)){
					state= new String(state.getBytes("ISO-8859-1"),"UTF-8");
				}
				Leave leave1=new Leave(stuId,state,depa);
				Page<Leave> message=adminDao.leaveList1(conn,leave1,pc,ps);
				
				String Url=GetUrlUtil.getUrl(request); //��ȡget�����ַ���ʺź�ߵ�����
				message.setUrl(Url);
				if(message.getBeanList().size()!=0){
					request.setAttribute("leave",message);  
					request.getRequestDispatcher("Adminleave.jsp").forward(request, response);
				}else {
					message=null;    //����Ϊ�գ�ǰ̨������ʾ
					request.setAttribute("leave",message);  
					request.getRequestDispatcher("Adminleave.jsp").forward(request, response);
				}
//				Leave leave1=new Leave(stuId,state,depa);
//				List<Leave> leave=adminDao.leaveList1(conn,leave1);
//				if(leave.size()!=0){
//					request.setAttribute("leave",leave);  
//					request.getRequestDispatcher("Adminleave.jsp").forward(request, response);
//				}else {
//					leave=null;     //����Ϊ�գ�ǰ̨������ʾ
//					request.setAttribute("leave",leave);  
//					request.getRequestDispatcher("Adminleave.jsp").forward(request, response);
//				}
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
