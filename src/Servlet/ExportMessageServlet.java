package Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Workbook;

import Dao.AdminDao;
import Dao.TeacherDao;
import Model.Leave;
import Model.Student;
import Model.Wrong;
import Util.ExcelUtil;
import Util.JdbcUtil;
import Util.ResponseUtil;
import Util.StringUtil;

@MultipartConfig
public class ExportMessageServlet extends HttpServlet {

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
		AdminDao adminDao = new AdminDao();
		TeacherDao teacherDao=new TeacherDao();
		JdbcUtil jdbcUtil=new JdbcUtil();
		HttpSession session=request.getSession();
		String fileName=request.getParameter("fileName"); //导出的文件名
		String type=request.getParameter("type"); //导出文件的类型（请假、学生信息、维修、违纪）
		if(StringUtil.isNotEmpty(fileName)){
			fileName= new String(fileName.getBytes("ISO-8859-1"),"UTF-8");
		}
		
		Connection con=null;
		try {
			if(type.equals("leave")){
				Leave leave=(Leave) session.getAttribute("leaveselect"); //获取要导出内容的查询条件
				con=jdbcUtil.getConnection();
				ResultSet rs=adminDao.leaveList2(con, leave);
				Workbook wb=ExcelUtil.fillExcelDataWithTemplate(rs, "leave.xlsx");
				ResponseUtil.export(response, wb, fileName+".xlsx");
	//	方法二	Workbook wb=new XSSFWorkbook();
	//			String headers[]={"学号","姓名","离开日期","结束日期","请假理由","去向","学生电话","家长电话"};
	//			ResultSet rs=adminDao.leaveList2(con, leave);
	//			ExcelUtil.fillExcelData(rs, wb,headers);
			}else if (type.equals("student")) {
				Student student=(Student) session.getAttribute("studentselect"); //获取要导出内容的查询条件
				con=jdbcUtil.getConnection();
				ResultSet rs=adminDao.dormsList2(con, student);
				Workbook wb=ExcelUtil.fillExcelDataWithTemplate1(rs, "student.xlsx");
				ResponseUtil.export(response, wb, fileName+".xlsx");
			}else if (type.equals("wrong")) {
				Wrong wrong=(Wrong) session.getAttribute("wrongselect"); //获取要导出内容的查询条件
				con=jdbcUtil.getConnection();
				ResultSet rs=teacherDao.wrongList1(con, wrong);
				Workbook wb=ExcelUtil.fillExcelDataWithTemplate2(rs, "wrong.xlsx");
				ResponseUtil.export(response, wb, fileName+".xlsx");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				jdbcUtil.close(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	}

}
