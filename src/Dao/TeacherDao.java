package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Leave;
import Model.Page;
import Model.Student;
import Model.Teacher;
import Model.Wrong;
import Util.JdbcUtil;
import Util.StringUtil;


public class TeacherDao {
	
	/**
	 * 老师查询学生信息（分页）
	 */
	
	public Page<Student> dormsList(Connection conn,Student student,int pc,int ps)throws Exception{
		Page<Student> page=new Page<Student>();
		page.setPc(pc);
		page.setPs(ps);
		int tr=studentCount(conn, student, pc, ps);
		page.setTr(tr);
		StringBuffer sb=new StringBuffer("select * from student s,dorms d,depa dp,major m,grade g where s.gradeid=g.gradeid and s.dormno=d.id and s.depaid=dp.depaid and s.majorid=m.majorid ");
		if(StringUtil.isNotEmpty(student.getId())){
			sb.append(" and s.id like '%"+student.getId()+"%'");
		}
		if(StringUtil.isNotEmpty(student.getName())){
			sb.append(" and s.name like '%"+student.getName()+"%'");
		}
		if(StringUtil.isNotEmpty(student.getDormno())){
			sb.append(" and dormno like '%"+student.getDormno()+"%'");
		}
		if(StringUtil.isNotEmpty(student.getDepaid())){
			sb.append(" and s.depaid like '%"+student.getDepaid()+"%'");
		}
		if(StringUtil.isNotEmpty(student.getMajorid())){
			sb.append(" and s.majorid like '%"+student.getMajorid()+"%'");
		}
		if(StringUtil.isNotEmpty(student.getGradeid())){
			sb.append(" and s.gradeid like '%"+student.getGradeid()+"%'");
		}
		    sb.append(" order by s.id limit ?,?");
		PreparedStatement pstmt=conn.prepareStatement(sb.toString());
		pstmt.setInt(1, (pc-1)*ps);
		pstmt.setInt(2, ps);
		ResultSet rs=pstmt.executeQuery();
		List<Student> message=new ArrayList<Student>();
		while(rs.next()){
			Student student2=new Student();
			student2.setId(rs.getString("s.id"));
			student2.setName(rs.getString("s.name"));
			student2.setDepaName(rs.getString("dp.depana"));
			student2.setMajorName(rs.getString("m.majorNa"));
			student2.setGradeName(rs.getString("g.gradeNa"));
			student2.setDormName(rs.getString("d.dormna"));
			student2.setRoomno(rs.getString("s.roomno"));
			student2.setBedno(rs.getString("s.bedno"));
			message.add(student2);
		}
		page.setBeanList(message);
	    return page;
     }
	/**
	 * 计算学生信息记录数
	 */
	public int studentCount(Connection conn,Student student, int pc, int ps)throws Exception{
		StringBuffer sb=new StringBuffer("select count(*) as total from student s,dorms d,depa dp,major m,grade g where s.gradeid=g.gradeid and s.dormno=d.id and s.depaid=dp.depaid and s.majorid=m.majorid");
		if(StringUtil.isNotEmpty(student.getId())){
			sb.append(" and s.id like '%"+student.getId()+"%'");
		}
		if(StringUtil.isNotEmpty(student.getName())){
			sb.append(" and name like '%"+student.getName()+"%'");
		}
		if(StringUtil.isNotEmpty(student.getDormno())){
			sb.append(" and dormno like '%"+student.getDormno()+"%'");
		}
		if(StringUtil.isNotEmpty(student.getDepaid())){
			sb.append(" and s.depaid like '%"+student.getDepaid()+"%'");
		}
		if(StringUtil.isNotEmpty(student.getMajorid())){
			sb.append(" and s.majorid like '%"+student.getMajorid()+"%'");
		}
		if(StringUtil.isNotEmpty(student.getGradeid())){
			sb.append(" and s.gradeid like '%"+student.getGradeid()+"%'");
		}
		PreparedStatement stmt=conn.prepareStatement(sb.toString());
		ResultSet rs=stmt.executeQuery();
		if(rs.next()){
			return rs.getInt("total");
		}else{
			return 0;
		}
	}
	/**
	 * 老师查询违纪信息,Excel导出数据用到
	 */
	public  ResultSet wrongList1(Connection conn,Wrong wrong1)throws Exception{
			StringBuffer sb=new StringBuffer("select s.id,s.name,d.depana,m.majorNa,g.gradena,w.wrongday,w.wrongreason,w.result from " +
					                         "student s,wrongmsg w,major m,depa d,grade g " +
					                         "where s.majorid=m.majorid and w.stuid=s.id and d.depaid=s.depaid and s.gradeid=g.gradeid");
			if(StringUtil.isNotEmpty(wrong1.getStuid())){
				sb.append(" and w.stuid="+wrong1.getStuid()+"");
			}
			if(StringUtil.isNotEmpty(wrong1.getDepaid())){
				sb.append(" and s.depaid="+wrong1.getDepaid()+"");
			}
			if(StringUtil.isNotEmpty(wrong1.getMajorid())){
				sb.append(" and s.majorid="+wrong1.getMajorid()+"");
			}
			sb.append(" order by s.id,w.wrongday");
			PreparedStatement stmt=conn.prepareStatement(sb.toString());
			ResultSet rs=stmt.executeQuery();
			
		    return rs;
	}
	/**
	 * 老师查违纪信息
	 */
	public Page<Wrong> wrongList(Connection conn,Wrong wrong1,int pc,int ps)throws Exception{	
		Page<Wrong> page=new Page<Wrong>();
		page.setPc(pc);
		page.setPs(ps);
		int tr=wrongCount(conn, wrong1, pc, ps);
		page.setTr(tr);
		StringBuffer sb=new StringBuffer("select * from wrongmsg w,student s,major m where w.stuid=s.id and s.majorid=m.majorid");
		if(StringUtil.isNotEmpty(wrong1.getStuid())){
			sb.append(" and w.stuid="+wrong1.getStuid()+"");
		}
		if(StringUtil.isNotEmpty(wrong1.getDepaid())){
			sb.append(" and s.depaid="+wrong1.getDepaid()+"");
		}
		if(StringUtil.isNotEmpty(wrong1.getMajorid())){
			sb.append(" and s.majorid="+wrong1.getMajorid()+"");
		}
		if(StringUtil.isNotEmpty(wrong1.getGradeid())){
			sb.append(" and s.gradeid='"+wrong1.getGradeid()+"'");
		}
		    sb.append(" order by s.id,w.wrongday limit ?,? ");
		List<Wrong> message=new ArrayList<Wrong>();
		PreparedStatement stmt=conn.prepareStatement(sb.toString());
		stmt.setInt(1, (pc-1)*ps);
		stmt.setInt(2, ps);
		ResultSet rs=stmt.executeQuery();
		while(rs.next()){
			Wrong wrong=new Wrong();
			wrong.setStuid(rs.getString("s.id"));
			wrong.setName(rs.getString("s.name"));
			wrong.setMajorna(rs.getString("m.majorna"));
			wrong.setWrongreason(rs.getString("w.wrongreason"));
			wrong.setWrongday(rs.getString("wrongday"));
			wrong.setResult(rs.getString("result"));
			message.add(wrong);
			}
		page.setBeanList(message);
		return page;
		
	}
	/**
	 * 计算违纪信息记录数
	 */
	public int wrongCount(Connection conn,Wrong wrong1, int pc, int ps)throws Exception{
		StringBuffer sb=new StringBuffer("select count(*) as total from wrongmsg w,student s where w.stuid=s.id ");
		if(StringUtil.isNotEmpty(wrong1.getStuid())){
			sb.append(" and w.stuid="+wrong1.getStuid()+"");
		}
		if(StringUtil.isNotEmpty(wrong1.getDepaid())){
			sb.append(" and s.depaid="+wrong1.getDepaid()+"");
		}
		if(StringUtil.isNotEmpty(wrong1.getMajorid())){
			sb.append(" and s.majorid="+wrong1.getMajorid()+"");
		}
		if(StringUtil.isNotEmpty(wrong1.getGradeid())){
			sb.append(" and s.gradeid='"+wrong1.getGradeid()+"'");
		}
		PreparedStatement stmt=conn.prepareStatement(sb.toString());
		ResultSet rs=stmt.executeQuery();
		if(rs.next()){
			return rs.getInt("total");
		}else{
			return 0;
		}
	}
	
	/**
	 * 老师查询请假信息
	 */
	public Page<Leave> leaveList1(Connection conn,Leave leave1,int pc,int ps)throws Exception{	
		Page<Leave> page=new Page<Leave>();
		page.setPc(pc);
		page.setPs(ps);
		int tr=leaveCount(conn, leave1, pc, ps);
		page.setTr(tr);
		StringBuffer sb=new StringBuffer("select * from leavemsg l,student s where l.stuid=s.id ");
		if(StringUtil.isNotEmpty(leave1.getStuid())){
			sb.append(" and l.stuid="+leave1.getStuid()+"");
		}
		if(StringUtil.isNotEmpty(leave1.getDepaId())){
			sb.append(" and s.depaid="+leave1.getDepaId()+"");
		}
		if(StringUtil.isNotEmpty(leave1.getState())){
			sb.append(" and l.state='"+leave1.getState()+"'");
		}
		if(StringUtil.isNotEmpty(leave1.getMajorId())){
			sb.append(" and s.majorid='"+leave1.getMajorId()+"'");
		}
		if(StringUtil.isNotEmpty(leave1.getGradeId())){
			sb.append(" and s.gradeid='"+leave1.getGradeId()+"'");
		}
		    sb.append(" limit ?,?");
		List<Leave> message=new ArrayList<Leave>();
		PreparedStatement stmt=conn.prepareStatement(sb.toString());
		stmt.setInt(1, (pc-1)*ps);
		stmt.setInt(2, ps);
		ResultSet rs=stmt.executeQuery();
		while(rs.next()){
			Leave leave=new Leave();
			leave.setId(rs.getString("id"));
			leave.setStuid(rs.getString("stuid"));
			leave.setName(rs.getString("name"));
			leave.setFirsttime(rs.getString("firsttime"));
			leave.setLasttime(rs.getString("lasttime"));
			leave.setAddress(rs.getString("address"));
			leave.setLeavereason(rs.getString("leavereason"));
			leave.setStudentphone(rs.getString("studentphone"));
			leave.setParentsphone(rs.getString("parentsphone"));
			leave.setState(rs.getString("state"));
			message.add(leave);
			}
		page.setBeanList(message);
		return page;
		
	}
	/**
	 * 计算请假信息记录数
	 */
	public int leaveCount(Connection conn,Leave leave, int pc, int ps)throws Exception{
		StringBuffer sb=new StringBuffer("select count(*) as total from leavemsg l,student s where l.stuid=s.id ");
		if(StringUtil.isNotEmpty(leave.getStuid())){
			sb.append(" and l.stuid="+leave.getStuid()+"");
		}
		if(StringUtil.isNotEmpty(leave.getDepaId())){
			sb.append(" and s.depaid="+leave.getDepaId()+"");
		}
		if(StringUtil.isNotEmpty(leave.getState())){
			sb.append(" and l.state='"+leave.getState()+"'");
		}
		if(StringUtil.isNotEmpty(leave.getMajorId())){
			sb.append(" and s.majorid='"+leave.getMajorId()+"'");
		}
		if(StringUtil.isNotEmpty(leave.getGradeId())){
			sb.append(" and s.gradeid='"+leave.getGradeId()+"'");
		}
		PreparedStatement stmt=conn.prepareStatement(sb.toString());
		ResultSet rs=stmt.executeQuery();
		if(rs.next()){
			return rs.getInt("total");
		}else{
			return 0;
		}
	}
	/**
	 * 老师信息查询
	 */
	public Teacher find(String classnumber){
		JdbcUtil jdbcUtil=new JdbcUtil();
		Teacher teacher=null;
		String sql = "select * from l_teacher where id=?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn = jdbcUtil.getConnection();
		try {
		    stmt=conn.prepareStatement(sql);
			stmt.setString(1, classnumber);
			rs=stmt.executeQuery();
			
			if(rs.next()){
				teacher=new Teacher();
				teacher.setId(rs.getString("id"));
				teacher.setPass(rs.getString("pass"));
				teacher.setDepaid(rs.getString("depaid"));
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
		return teacher;
}
	

	/**
	 * 老师登录验证
	 * @param name
	 * @param pwd
	 * @return
	 */
	public boolean CheckLogin(String name, String pwd){
		
		JdbcUtil jdbcUtil=new JdbcUtil();
		boolean islogin = false;
		String sql = "select * from l_teacher where id =? and pass =?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn = jdbcUtil.getConnection();
		try {
			stmt=conn.prepareStatement(sql);
			stmt.setString(1, name);
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
}
