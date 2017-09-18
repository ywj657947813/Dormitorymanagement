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
		String stuId=request.getParameter("stuId");   //学号
		String action=request.getParameter("action");  //操作人
		String value=request.getParameter("pc");  //当前页码
		String s_stuId=request.getParameter("selectid");   //查询_学号
		String majorid=request.getParameter("major");   //查询_专业
		String gradeid=request.getParameter("grade");   //查询_班级
		String teacherType=(String)request.getSession().getAttribute("teachertype"); //老师所属系别编号
		
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
				int pc;    //当前页码
				int ps=7; //每页记录数
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
				request.getSession().setAttribute("wrongselect", wrong);  //保存查询条件，导出数据用到
				Page<Wrong> message=teacherDao.wrongList(conn,wrong,pc,ps);
				
				String Url=GetUrlUtil.getUrl(request); //获取get请求地址栏问号后边的内容
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
