package Servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Util.JdbcUtil;

import Dao.StudentDao;

public class StuOldPassServlet extends HttpServlet {
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
		StudentDao studentDao = new StudentDao();
		HttpSession session=request.getSession();
		String oldpass=request.getParameter("oldpass");   //����ľ�����
		String newpass=request.getParameter("newpass");   //�����������
		String oldPassword=(String) session.getAttribute("studentps"); //ѧ����ʵ����
		String stuid=(String) session.getAttribute("studentid");       //ѧ��ѧ��

		String action=request.getParameter("action");
		
		if(action.equals("choice")){//�жϲ���ҳ��  ��������ж������Ƿ���ȷ
			if(oldpass.equals(oldPassword)){
				request.setAttribute("password",newpass);   //������������ת��ȷ��������ҳ��
			//	response.sendRedirect("stchangepass.jsp");
                request.getRequestDispatcher("stchangepass.jsp").forward(request, response);
			}else{
				request.setAttribute("error", "�����������");
				request.setAttribute("oldpass", oldpass);  //����ľ����룬�������ʱ����תҳ�棬��������벻�����
				request.setAttribute("newpass", newpass);  //�����������
		    //  response.sendRedirect("studentpass.jsp");
                request.getRequestDispatcher("studentpass.jsp").forward(request, response);
			}
		}else if(action.equals("change")){ //������޸�����
			Connection conn=null;
			try{
				conn=jdbcUtil.getConnection();
				
				int saveNums=studentDao.ChangePass(conn, newpass, stuid);
			
			
				if(saveNums>0){
					request.setAttribute("success", "�޸ĳɹ���");
					session.setAttribute("studentps",newpass);  //�޸ĳɹ������session�����������
					request.getRequestDispatcher("stuchangeOK.jsp").forward(request, response);
//					response.sendRedirect("stuchangeOK.jsp");
				}else{
					session.setAttribute("error", "�޸�ʧ�ܣ�");
					response.sendRedirect("stchangepass.jsp");
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

		
}


