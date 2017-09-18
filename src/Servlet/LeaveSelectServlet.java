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
		String stuId=request.getParameter("id");      //学号      (学生、管理员)
		String action=request.getParameter("action"); //操作人员
		String teacherType=(String)request.getSession().getAttribute("teachertype"); //老师所属系别编号
		String value=request.getParameter("pc");  //当前页码
		
		String depa=request.getParameter("depa");    //系别
		String major=request.getParameter("major");  //专业
		String grade=request.getParameter("grade");  //班级
		String state=request.getParameter("state");  //信息状态
		Connection conn=null;
		try{
			conn=jdbcUtil.getConnection();
			
		
		
			if("student".equals(action)){ //学生查询请加信息
				List<Leave> leave=adminDao.leaveList(conn,stuId);
				if(leave.size()!=0){
					request.setAttribute("leave",leave);  
					request.getRequestDispatcher("selectleave.jsp").forward(request, response);
				}else {
					leave=null;    //设置为空，前台方便显示
					request.setAttribute("leave",leave);  
					request.getRequestDispatcher("selectleave.jsp").forward(request, response);
				}
			}else if("teacher".equals(action)){
				int pc;    //当前页码
				int ps=7; //每页记录数
				if(value==null || value.trim().isEmpty()){
				    pc=1;
				}else {
					pc=Integer.parseInt(value);
				}
				/**
				 * 处理get请求编码问题，由于查询条件只有  信息状态  可能为中文， 所以只处理  信息状态
				 */
				if(StringUtil.isNotEmpty(state)){
					state= new String(state.getBytes("ISO-8859-1"),"UTF-8");
				}
				Leave leave1=new Leave(stuId,state,teacherType);
				leave1.setMajorId(major);
				leave1.setGradeId(grade);
				request.getSession().setAttribute("leaveselect", leave1);  //保存查询条件，导出数据用到
				Page<Leave> message=teacherDao.leaveList1(conn,leave1,pc,ps);
				
				String Url=GetUrlUtil.getUrl(request); //获取get请求地址栏问号后边的内容
				message.setUrl(Url);
				if(message.getBeanList().size()!=0){
					request.setAttribute("leave",message);  
					request.getRequestDispatcher("Teacherleave.jsp").forward(request, response);
				}else {
					message=null;    //设置为空，前台方便显示
					request.setAttribute("leave",message);  
					request.getRequestDispatcher("Teacherleave.jsp").forward(request, response);
				}
			}else if("admin".equals(action)){        //管理员查询请假信息
				int pc;   //当前页码
				int ps=7; //每页记录数
				if(value==null || value.trim().isEmpty()){
				    pc=1;
				}else {
					pc=Integer.parseInt(value);
				}
				/**
				 * 处理get请求编码问题，由于查询条件只有  信息状态  可能为中文， 所以只处理  信息状态
				 */
				if(StringUtil.isNotEmpty(state)){
					state= new String(state.getBytes("ISO-8859-1"),"UTF-8");
				}
				Leave leave1=new Leave(stuId,state,depa);
				Page<Leave> message=adminDao.leaveList1(conn,leave1,pc,ps);
				
				String Url=GetUrlUtil.getUrl(request); //获取get请求地址栏问号后边的内容
				message.setUrl(Url);
				if(message.getBeanList().size()!=0){
					request.setAttribute("leave",message);  
					request.getRequestDispatcher("Adminleave.jsp").forward(request, response);
				}else {
					message=null;    //设置为空，前台方便显示
					request.setAttribute("leave",message);  
					request.getRequestDispatcher("Adminleave.jsp").forward(request, response);
				}
//				Leave leave1=new Leave(stuId,state,depa);
//				List<Leave> leave=adminDao.leaveList1(conn,leave1);
//				if(leave.size()!=0){
//					request.setAttribute("leave",leave);  
//					request.getRequestDispatcher("Adminleave.jsp").forward(request, response);
//				}else {
//					leave=null;     //设置为空，前台方便显示
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
