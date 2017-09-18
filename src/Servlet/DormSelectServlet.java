package Servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.AdminDao;
import Dao.TeacherDao;
import Model.Page;
import Model.Student;
import Util.GetUrlUtil;
import Util.JdbcUtil;
import Util.StringUtil;

public class DormSelectServlet extends HttpServlet {

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
		AdminDao adminDao=new AdminDao();
		TeacherDao teacherDao=new TeacherDao();
		JdbcUtil jdbcUtil=new JdbcUtil();
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String id=request.getParameter("selectid");      //ѧ��
		String dormid=request.getParameter("dormid");    //����¥
		String name=request.getParameter("selectname");  //����
		String depaId=request.getParameter("depa");      //ϵ��
		String majorId=request.getParameter("major");    //רҵ
		String gradeId=request.getParameter("grade");    //�༶
		String teacherType=(String)request.getSession().getAttribute("teachertype"); //��ʦ����ϵ���ţ�ֱ�Ӳ�ѯ��Ӧ��רҵ��ɾ��ϵ��������ѯ
		
		String action=request.getParameter("action");
		
		
		String value=request.getParameter("pc");  //��ǰҳ��
		int pc;    //��ǰҳ��
		int ps=7; //ÿҳ��¼��
		if(value==null || value.trim().isEmpty()){
		    pc=1;
		}else {
			pc=Integer.parseInt(value);
		}

		/**
		 * ����get����������⣬���ڲ�ѯ����ֻ����������Ϊ����   ����ֻ����  ����
		 */
		if(StringUtil.isNotEmpty(name)){
			name= new String(name.getBytes("ISO-8859-1"),"UTF-8");
		}
		
		Connection conn=null;
		try {
			conn=jdbcUtil.getConnection();
			if("teacher".equals(action)){
				Student student=new Student(id,name,dormid,teacherType,majorId);
				student.setGradeid(gradeId);
				request.getSession().setAttribute("studentselect", student);  //�����ѯ���������������õ�
				
				Page<Student> message=teacherDao.dormsList(conn,student,pc,ps);
				
				String Url=GetUrlUtil.getUrl(request); //��ȡget�����ַ���ʺź�ߵ�����
				message.setUrl(Url);
				if(message.getBeanList().size()!=0){
					
						request.setAttribute("message", message);
						request.getRequestDispatcher("Teacherpage.jsp").forward(request, response);
					
				}else{
					message=null;
					
						request.setAttribute("message", message);
						request.getRequestDispatcher("Teacherpage.jsp").forward(request, response);
					
				}
			}else if("admin".equals(action)){
				//����Ա
				Student student=new Student(id,name,dormid,depaId,majorId);
				student.setGradeid(gradeId);
				request.getSession().setAttribute("studentselect", student);  //�����ѯ���������������õ�
				
				Page<Student> message=adminDao.dormsList(conn,student,pc,ps);
				
				String Url=GetUrlUtil.getUrl(request); //��ȡget�����ַ���ʺź�ߵ�����
				message.setUrl(Url);
				if(message.getBeanList().size()!=0){
					
						request.setAttribute("message", message);
						request.getRequestDispatcher("Adminpage.jsp").forward(request, response);
					
				}else{
					message=null;
					
						request.setAttribute("message", message);
						request.getRequestDispatcher("Adminpage.jsp").forward(request, response);
					
				}
			}
		//	List<Student> message1=adminDao.dormsList(conn,student);
			
			
			
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