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
		String id=request.getParameter("selectid");      //学号
		String dormid=request.getParameter("dormid");    //宿舍楼
		String name=request.getParameter("selectname");  //姓名
		String depaId=request.getParameter("depa");      //系别
		String majorId=request.getParameter("major");    //专业
		String gradeId=request.getParameter("grade");    //班级
		String teacherType=(String)request.getSession().getAttribute("teachertype"); //老师所属系别编号，直接查询对应的专业，删除系别条件查询
		
		String action=request.getParameter("action");
		
		
		String value=request.getParameter("pc");  //当前页码
		int pc;    //当前页码
		int ps=7; //每页记录数
		if(value==null || value.trim().isEmpty()){
		    pc=1;
		}else {
			pc=Integer.parseInt(value);
		}

		/**
		 * 处理get请求编码问题，由于查询条件只有姓名可能为中文   所以只处理  姓名
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
				request.getSession().setAttribute("studentselect", student);  //保存查询条件，导出数据用到
				
				Page<Student> message=teacherDao.dormsList(conn,student,pc,ps);
				
				String Url=GetUrlUtil.getUrl(request); //获取get请求地址栏问号后边的内容
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
				//管理员
				Student student=new Student(id,name,dormid,depaId,majorId);
				student.setGradeid(gradeId);
				request.getSession().setAttribute("studentselect", student);  //保存查询条件，导出数据用到
				
				Page<Student> message=adminDao.dormsList(conn,student,pc,ps);
				
				String Url=GetUrlUtil.getUrl(request); //获取get请求地址栏问号后边的内容
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