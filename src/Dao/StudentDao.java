package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import Model.Leave;
import Model.Service;
import Model.Student;
import Model.Wrong;
import Util.JdbcUtil;


public class StudentDao {
	
	/**
	 * 学生删除请假信息
	 */
	public int deleteLeave(Connection conn,String id)throws Exception{
		String sql="delete from leavemsg where id in("+id+")";
		PreparedStatement pstmt=conn.prepareStatement(sql);
	
		return pstmt.executeUpdate();
	}
	/**
	 * 学生删除报修信息
	 */
	public int deleteService(Connection conn,String id)throws Exception{
		String sql="delete from service where id in("+id+")";
		PreparedStatement pstmt=conn.prepareStatement(sql);
	
		return pstmt.executeUpdate();
	}
	/**
	 * 学生查询报修信息(只显示未完成的)
	 */
	public List<Service> serviceList(Connection conn,String stuId,String state)throws Exception{	
		//select * from service s,dorms d where s.dormno=d.id
		StringBuffer sb=new StringBuffer("select * from service s,dorms d where s.dormno=d.id and s.stuid=? and state not in ('"+state+"')");
		
		    sb.append(" order by time");
		List<Service> message=new ArrayList<Service>();
		PreparedStatement stmt=conn.prepareStatement(sb.toString());
		stmt.setString(1, stuId);
		ResultSet rs=stmt.executeQuery();
		while(rs.next()){
			Service service1=new Service();
			service1.setId(rs.getString("id"));
			service1.setStuid(rs.getString("stuid"));
			service1.setName(rs.getString("name"));
			service1.setDormno(rs.getString("dormno"));
			service1.setDormName(rs.getString("d.dormna"));
			service1.setRoomno(rs.getString("s.roomno"));
			service1.setServicetype(rs.getString("servicetype"));
			service1.setServicereason(rs.getString("servicereason"));
			service1.setPhone(rs.getString("phone"));
			service1.setTime(rs.getString("time"));
			service1.setState(rs.getString("state"));
			message.add(service1);
			}
		return message;
		
	}
	/**
	 * 学生查询学生违纪信息
	 */
	public List<Wrong> wrongList(Connection conn,String id)throws Exception{	
		
		StringBuffer sb=new StringBuffer("select * from wrongmsg where stuid=?");
			
		List<Wrong> message=new ArrayList<Wrong>();
		PreparedStatement stmt=conn.prepareStatement(sb.toString());
		                  stmt.setString(1, id);
		ResultSet rs=stmt.executeQuery();
		while(rs.next()){
			Wrong wrong=new Wrong();
			wrong.setStuid(rs.getString("stuid"));
			wrong.setName(rs.getString("name"));
			wrong.setWrongday(rs.getString("wrongday"));
			wrong.setWrongreason(rs.getString("wrongreason"));
			wrong.setResult(rs.getString("result"));
			message.add(wrong);
			}
		
			
		return message;
		
	}
	/**
	 * 学生报修宿舍信息
	 * @param con
	 * @param student
	 * @return
	 * @throws Exception
	 */
	public int serviceAdd(Connection con,Service service)throws Exception{
		String sql="insert into service values(null,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, service.getStuid());
		pstmt.setString(2, service.getDormno());
		pstmt.setString(3, service.getRoomno());
		pstmt.setString(4, service.getServicetype());
		pstmt.setString(5, service.getServicereason());
		pstmt.setString(6, service.getName());
		pstmt.setString(7, service.getPhone());
		pstmt.setString(8,service.getTime());
		pstmt.setString(9, service.getState());

		return pstmt.executeUpdate();
	}
	/**
	 * 信息查询
	 */
	public Student find(String classnumber){
		JdbcUtil jdbcUtil=new JdbcUtil();
		Student student=null;
		String sql = "select * from student s,depa d,major m,dorms do  where do.id=s.dormno and s.majorid=m.majorid and m.depaid=d.depaid and s.id=?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn = jdbcUtil.getConnection();
		try {
		    stmt=conn.prepareStatement(sql);
			stmt.setString(1, classnumber);
			rs=stmt.executeQuery();
			
			if(rs.next()){
				student=new Student();
				student.setId(rs.getString("s.id"));
				student.setDepaName(rs.getString("d.depaNa"));
				student.setMajorName(rs.getString("m.majorNa"));
				student.setName(rs.getString("s.name"));
				student.setSex(rs.getString("s.sex"));
				student.setDormName(rs.getString("do.dormna"));
				student.setDormno(rs.getString("s.dormno"));
				student.setRoomno(rs.getString("s.roomno"));
				student.setPhone(rs.getString("s.phone"));
				student.setBedno(rs.getString("s.bedno"));
			}
	   } catch (SQLException e) {
		e.printStackTrace();
	   }finally{
		try {
			jdbcUtil.close(conn);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
		return student;
}
	/**
	 * 登录验证
	 * @param name
	 * @param classnumber
	 * @param pwd
	 * @return
	 */
	public boolean CheckLogin(String classnumber, String pwd){
		boolean islogin = false;
		JdbcUtil jdbcUtil=new JdbcUtil();
		String sql = "select * from admin where id=? and pass =?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn = jdbcUtil.getConnection();
		try {
		    stmt=conn.prepareStatement(sql);
			stmt.setString(1, classnumber);
			stmt.setString(2, pwd);
		
			rs=stmt.executeQuery();
			
			if(rs.next()){
				
				islogin = true;
			}
			else{
				islogin = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				
				jdbcUtil.close(conn);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return islogin;
	}

	/**
	 * 学生修改电话号码
	 * @param pwd
	 * @return
	 * @throws Exception 
	 */
	public int ChangePhone(Connection conn,String phone,String id) throws Exception{
		String sql="update student set phone=? where id=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		
		pstmt.setString(1, phone);
		pstmt.setString(2, id);
	
		return pstmt.executeUpdate();
	}
	/**
	 * 学生修改请假状态
	 * @param pwd
	 * @return
	 * @throws Exception 
	 */
	public int ChangeLeave(Connection conn,String id) throws Exception{
		String sql="update leavemsg set state=? where id=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		
		pstmt.setString(1, "已结束");
		pstmt.setString(2, id);
	
		return pstmt.executeUpdate();
	}
	/**
	 * 学生修改密码
	 * @param pwd
	 * @return
	 * @throws Exception 
	 */
	public int ChangePass(Connection conn,String pwd,String id) throws Exception{
		String sql="update admin set pass=? where id=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		
		pstmt.setString(1, pwd);
		pstmt.setString(2, id);
	
		return pstmt.executeUpdate();
	}
	/**
	 * 学生请假申请
	 */
	public int LeaveApply(Connection conn,Leave leave,String id) throws Exception{
		String sql="insert into leavemsg values(null,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		
		pstmt.setString(1, leave.getStuid());
		pstmt.setString(2, leave.getName());
		pstmt.setString(3, leave.getFirsttime());
		pstmt.setString(4, leave.getLasttime());
		pstmt.setString(5, leave.getLeavereason());
		pstmt.setString(6, leave.getAddress());
		pstmt.setString(7, leave.getStudentphone());
		pstmt.setString(8, leave.getParentsphone());
		pstmt.setString(9, leave.getState());
		return pstmt.executeUpdate();
	}
	
	/**
	 * 查询该床位是否存在学生使用
	 */
	public boolean CheckBed(Connection conn,String dormId,String roomId,String bedId) throws Exception{
		String sql="select * from student where dormno=? and roomno=? and bedno=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		boolean a=true;
		pstmt.setString(1, dormId);
		pstmt.setString(2, roomId);
		pstmt.setString(3, bedId);
		ResultSet rs =pstmt.executeQuery();
		if(rs.next()){
			 a=true;
		}else {
			 a=false;
		}
		
		return  a;
	}
	/**
	 * 查询学号跟姓名是否一致
	 */
	public boolean CheckName(Connection conn,String id,String name) throws Exception{
		String sql="select * from student where id=? and name=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		boolean a=true;
		pstmt.setString(1, id);
		pstmt.setString(2, name);
		ResultSet rs =pstmt.executeQuery();
		if(rs.next()){
			 a=true;
		}else {
			 a=false;
		}
		
		return  a;
	}
	/**
	 * 查询是否存在学号
	 */
	public boolean CheckID(Connection conn,String id) throws Exception{
		String sql="select * from admin where id=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		boolean a=true;
		pstmt.setString(1, id);
		ResultSet rs =pstmt.executeQuery();
		if(rs.next()){
			 a=true;
		}else {
			 a=false;
		}
		
		return  a;
	}
}

