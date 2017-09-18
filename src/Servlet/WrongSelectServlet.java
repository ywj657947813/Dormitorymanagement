package Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.StudentDao;
import Dao.TeacherDao;
import Model.Page;
import Model.Wrong;
import Util.GetUrlUtil;
import Util.JdbcUtil;

public class WrongSelectServlet extends HttpServlet  {
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
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
      
		StudentDao studentDao=new StudentDao();
		JdbcUtil jdbcUtil =new JdbcUtil();
		String stuId=request.getParameter("stuId");   //ѧ��
		String action=request.getParameter("action");  //������
		String value=request.getParameter("pc");  //��ǰҳ��
		String s_stuId=request.getParameter("selectid");   //��ѯ_ѧ��
		String majorid=request.getParameter("major");   //��ѯ_רҵ
		String gradeid=request.getParameter("grade");   //��ѯ_�༶
		String teacherType=(String)request.getSession().getAttribute("teachertype"); //��ʦ����ϵ����
		
		Connection conn=null;
		try{
			conn=jdbcUtil.getConnection();
			if(action.equals("student")){
				List<Wrong> wrong=studentDao.wrongList(conn,stuId);
				if(wrong.size()!=0){
					request.setAttribute("wrong",wrong);  
		            request.getRequestDispatcher("studentwrong.jsp").forward(request, response);
				}else{
					wrong=null;
					request.setAttribute("wrong", wrong);  
		            request.getRequestDispatcher("studentwrong.jsp").forward(request, response);
				}
			}else if (action.equals("teacher")) {
				int pc;    //��ǰҳ��
				int ps=7; //ÿҳ��¼��
				if(value==null || value.trim().isEmpty()){
				    pc=1;
				}else {
					pc=Integer.parseInt(value);
				}
				TeacherDao teacherDao=new TeacherDao();
				Wrong wrong=new Wrong();
				wrong.setMajorid(majorid);
				wrong.setGradeid(gradeid);
				wrong.setStuid(s_stuId);
				wrong.setDepaid(teacherType);
				request.getSession().setAttribute("wrongselect", wrong);  //�����ѯ���������������õ�
				Page<Wrong> message=teacherDao.wrongList(conn,wrong,pc,ps);
				
				String Url=GetUrlUtil.getUrl(request); //��ȡget�����ַ���ʺź�ߵ�����
				message.setUrl(Url);
				
				if(message.getBeanList().size()!=0){
					request.setAttribute("message",message);  
		            request.getRequestDispatcher("Teacherselectwrong.jsp").forward(request, response);
				}else{
					message=null;
					request.setAttribute("message", message);  
		            request.getRequestDispatcher("Teacherselectwrong.jsp").forward(request, response);
				}
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
