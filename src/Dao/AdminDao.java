package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Admin;
import Model.Leave;
import Model.Order;
import Model.OrderItem;
import Model.Page;
import Model.Product;
import Model.Service;
import Model.Student;
import Model.Wrong;
import Util.JdbcUtil;
import Util.StringUtil;

public class AdminDao {

	/**
	 * ��ʼ��רҵ������
	 */
	public ResultSet roomList(Connection conn, String Height, String DormId)
			throws Exception {
		StringBuffer sb = new StringBuffer("select * from rooms");
		if (StringUtil.isNotEmpty(Height)) {
			sb.append(" and roomno like '%" + Height + "%'");
		}
		if (StringUtil.isNotEmpty(DormId)) {
			sb.append(" and dormid like '%" + DormId + "%'");
		}
		PreparedStatement pstmt = conn.prepareStatement(sb.toString()
				.replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}

	/**
	 * ��ʼ���༶������
	 */
	public ResultSet gradeList(Connection conn, String major) throws Exception {
		StringBuffer sb = new StringBuffer("select * from grade");
		if (StringUtil.isNotEmpty(major)) {
			sb.append(" and majorId like '%" + major + "%'");
		}
		PreparedStatement pstmt = conn.prepareStatement(sb.toString()
				.replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}

	/**
	 * ��ʼ��רҵ������
	 */
	public ResultSet majorList(Connection conn, String depaId) throws Exception {
		StringBuffer sb = new StringBuffer("select * from major");
		if (StringUtil.isNotEmpty(depaId)) {
			sb.append(" and depaId like '%" + depaId + "%'");
		}
		PreparedStatement pstmt = conn.prepareStatement(sb.toString()
				.replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}

	/**
	 * ��ʼ��ϵ��������
	 */
	public ResultSet depaList(Connection con) throws Exception {
		StringBuffer sb = new StringBuffer("select * from depa");
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}

	/**
	 * ��ʼ�������������
	 */
	public ResultSet dormNoList(Connection con) throws Exception {
		StringBuffer sb = new StringBuffer("select * from dorms");
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}

	/**
	 * ����Ա�޸����״̬
	 */
	public int ChangeLeave(Connection conn, String id, String state)
			throws Exception {
		String sql = "update leavemsg set state=? where id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, state);
		pstmt.setString(2, id);

		return pstmt.executeUpdate();
	}

	/**
	 * �޸ı�����Ϣ��״̬
	 */
	public int changeServiceState(Connection conn, String state, String Id)
			throws Exception {
		String sql = "update service set state=? where id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, state);
		pstmt.setString(2, Id);

		return pstmt.executeUpdate();
	}

	/**
	 * ɾ��ѧ���˺�(����������)
	 */
	public int deleteStu1(Connection conn, String stuIds) throws Exception {
		String sql = "delete from admin where id in(" + stuIds + ")";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		return pstmt.executeUpdate();
	}

	/**
	 * ����Աɾ��ѧ�������Ϣ
	 */
	public int deleteLeave(Connection conn, String id) throws Exception {
		String sql = "delete from leavemsg where id in(" + id + ")";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		return pstmt.executeUpdate();
	}

	/**
	 * ����Աɾ��������Ϣ
	 */
	public int deleteService(Connection conn, String id) throws Exception {
		String sql = "delete from service where id in(" + id + ")";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		return pstmt.executeUpdate();
	}

	/**
	 * ѧ����ѯ�����Ϣ
	 */
	public List<Leave> leaveList(Connection conn, String id) throws Exception {

		StringBuffer sb = new StringBuffer("select * from leavemsg ");
		if (StringUtil.isNotEmpty(id)) {
			sb.append(" and stuid like '%" + id + "%' and state not in ('"
					+ "�ѽ���" + "') ");
		}
		List<Leave> message = new ArrayList<Leave>();
		PreparedStatement stmt = conn.prepareStatement(sb.toString()
				.replaceFirst("and", "where"));
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Leave leave = new Leave();
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
		return message;

	}

	/**
	 * ����Ա��ѯ�����Ϣ
	 */
	public Page<Leave> leaveList1(Connection conn, Leave leave1, int pc, int ps)
			throws Exception {
		Page<Leave> page = new Page<Leave>();
		page.setPc(pc);
		page.setPs(ps);
		int tr = leaveCount(conn, leave1, pc, ps);
		page.setTr(tr);
		StringBuffer sb = new StringBuffer(
				"select * from leavemsg l,student s where l.stuid=s.id ");
		if (StringUtil.isNotEmpty(leave1.getStuid())) {
			sb.append(" and l.stuid=" + leave1.getStuid() + "");
		}
		if (StringUtil.isNotEmpty(leave1.getDepaId())) {
			sb.append(" and s.depaid=" + leave1.getDepaId() + "");
		}
		if (StringUtil.isNotEmpty(leave1.getState())) {
			sb.append(" and l.state='" + leave1.getState() + "'");
		}
		if (StringUtil.isNotEmpty(leave1.getMajorId())) {
			sb.append(" and s.majorid='" + leave1.getMajorId() + "'");
		}
		sb.append(" limit ?,?");
		List<Leave> message = new ArrayList<Leave>();
		PreparedStatement stmt = conn.prepareStatement(sb.toString());
		stmt.setInt(1, (pc - 1) * ps);
		stmt.setInt(2, ps);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Leave leave = new Leave();
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
	 * ���������Ϣ��¼��
	 */
	public int leaveCount(Connection conn, Leave leave, int pc, int ps)
			throws Exception {
		StringBuffer sb = new StringBuffer(
				"select count(*) as total from leavemsg l,student s where l.stuid=s.id ");
		if (StringUtil.isNotEmpty(leave.getStuid())) {
			sb.append(" and l.stuid=" + leave.getStuid() + "");
		}
		if (StringUtil.isNotEmpty(leave.getDepaId())) {
			sb.append(" and s.depaid=" + leave.getDepaId() + "");
		}
		if (StringUtil.isNotEmpty(leave.getState())) {
			sb.append(" and l.state='" + leave.getState() + "'");
		}
		if (StringUtil.isNotEmpty(leave.getMajorId())) {
			sb.append(" and s.majorid='" + leave.getMajorId() + "'");
		}
		PreparedStatement stmt = conn.prepareStatement(sb.toString());
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			return rs.getInt("total");
		} else {
			return 0;
		}
	}

	/**
	 * 
	 * @param conn
	 * @param id
	 * @return excel��ѯ���ݵ���
	 * @throws Exception
	 */
	public ResultSet leaveList2(Connection conn, Leave leave1) throws Exception {

		StringBuffer sb = new StringBuffer(
				"select s.id,s.name,d.depana,m.majorna,g.gradena,l.firsttime,l.lasttime,l.leavereason,l.address,l.studentphone,l.parentsphone "
						+ "from leavemsg l,student s,depa d,major m,grade g where l.stuid=s.id and d.depaid=s.depaid and m.majorid=s.majorid and g.gradeid=s.gradeid");
		if (StringUtil.isNotEmpty(leave1.getStuid())) {
			sb.append(" and l.stuid=" + leave1.getStuid() + "");
		}
		if (StringUtil.isNotEmpty(leave1.getDepaId())) {
			sb.append(" and s.depaid=" + leave1.getDepaId() + "");
		}
		if (StringUtil.isNotEmpty(leave1.getState())) {
			sb.append(" and l.state='" + leave1.getState() + "'");
		}
		if (StringUtil.isNotEmpty(leave1.getMajorId())) {
			sb.append(" and s.majorid='" + leave1.getMajorId() + "'");
		}
		PreparedStatement stmt = conn.prepareStatement(sb.toString());
		ResultSet rs = stmt.executeQuery();

		return rs;

	}

	/**
	 * ����Ա��ѯ���ᱨ����Ϣ(����ҳ)
	 */
	public List<Service> serviceList(Connection conn, Service service)
			throws Exception {

		StringBuffer sb = new StringBuffer(
				"select * from service s,dorms d where s.dormno=d.id");
		if (StringUtil.isNotEmpty(service.getDormno())) {
			sb.append(" and dormno like '%" + service.getDormno() + "%'");
		}
		if (StringUtil.isNotEmpty(service.getState())) {
			sb.append(" and state like '%" + service.getState() + "%'");
		}
		if (StringUtil.isNotEmpty(service.getServicetype())) {
			sb.append(" and servicetype like '%" + service.getServicetype()
					+ "%'");
		}
		sb.append(" order by time");
		List<Service> message = new ArrayList<Service>();
		PreparedStatement stmt = conn.prepareStatement(sb.toString());
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Service service1 = new Service();
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
	 * ��ѯά����Ϣ����ҳ��
	 */

	public Page<Service> serviceList(Connection conn, Service service, int pc,
			int ps) throws Exception {
		Page<Service> page = new Page<Service>();
		page.setPc(pc);
		page.setPs(ps);
		int tr = serviceCount(conn, service, pc, ps);
		page.setTr(tr);
		StringBuffer sb = new StringBuffer(
				"select * from service s,dorms d where s.dormno=d.id");
		if (StringUtil.isNotEmpty(service.getDormno())) {
			sb.append(" and dormno like '%" + service.getDormno() + "%'");
		}
		if (StringUtil.isNotEmpty(service.getState())) {
			sb.append(" and state like '%" + service.getState() + "%'");
		}
		if (StringUtil.isNotEmpty(service.getServicetype())) {
			sb.append(" and servicetype like '%" + service.getServicetype()
					+ "%'");
		}
		sb.append(" order by time limit ?,?");
		PreparedStatement pstmt = conn.prepareStatement(sb.toString());
		pstmt.setInt(1, (pc - 1) * ps);
		pstmt.setInt(2, ps);
		ResultSet rs = pstmt.executeQuery();
		List<Service> message = new ArrayList<Service>();
		while (rs.next()) {
			Service service1 = new Service();
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
		page.setBeanList(message);
		return page;
	}

	/**
	 * ����ά����Ϣ��¼��
	 */
	public int serviceCount(Connection conn, Service service, int pc, int ps)
			throws Exception {
		StringBuffer sb = new StringBuffer(
				"select count(*) as total from service s,dorms d where s.dormno=d.id");
		if (StringUtil.isNotEmpty(service.getDormno())) {
			sb.append(" and dormno like '%" + service.getDormno() + "%'");
		}
		if (StringUtil.isNotEmpty(service.getState())) {
			sb.append(" and state like '%" + service.getState() + "%'");
		}
		if (StringUtil.isNotEmpty(service.getServicetype())) {
			sb.append(" and servicetype like '%" + service.getServicetype()
					+ "%'");
		}
		PreparedStatement stmt = conn.prepareStatement(sb.toString());
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			return rs.getInt("total");
		} else {
			return 0;
		}
	}

	/**
	 * ����Ա��ѯ������Ϣ������ҳ��
	 */
	public List<Student> dormsList(Connection conn, Student student)
			throws Exception {
		StringBuffer sb = new StringBuffer(
				"select * from student s,dorms d,depa dp,major m where s.dormno=d.id and s.depaid=dp.depaid and s.majorid=m.majorid");
		if (StringUtil.isNotEmpty(student.getId())) {
			sb.append(" and s.id like '%" + student.getId() + "%'");
		}
		if (StringUtil.isNotEmpty(student.getName())) {
			sb.append(" and name like '%" + student.getName() + "%'");
		}
		if (StringUtil.isNotEmpty(student.getDormno())) {
			sb.append(" and dormno like '%" + student.getDormno() + "%'");
		}
		if (StringUtil.isNotEmpty(student.getDepaid())) {
			sb.append(" and s.depaid like '%" + student.getDepaid() + "%'");
		}
		if (StringUtil.isNotEmpty(student.getMajorid())) {
			sb.append(" and s.majorid like '%" + student.getMajorid() + "%'");
		}
		PreparedStatement stmt = conn.prepareStatement(sb.toString());
		ResultSet rs = stmt.executeQuery();
		List<Student> message = new ArrayList<Student>();
		while (rs.next()) {
			Student student2 = new Student();
			student2.setId(rs.getString("s.id"));
			student2.setName(rs.getString("s.name"));
			student2.setDepaName(rs.getString("dp.depana"));
			student2.setMajorName(rs.getString("m.majorNa"));
			student2.setDormName(rs.getString("d.dormna"));
			student2.setRoomno(rs.getString("s.roomno"));
			student2.setBedno(rs.getString("s.bedno"));
			message.add(student2);
		}
		return message;
	}

	/**
	 * ����Ա��ѯѧ����Ϣ����ҳ��
	 */

	public Page<Student> dormsList(Connection conn, Student student, int pc,
			int ps) throws Exception {
		Page<Student> page = new Page<Student>();
		page.setPc(pc);
		page.setPs(ps);
		int tr = studentCount(conn, student, pc, ps);
		page.setTr(tr);
		StringBuffer sb = new StringBuffer(
				"select * from student s,dorms d,depa dp,major m,grade g where s.gradeid=g.gradeid and s.dormno=d.id and s.depaid=dp.depaid and s.majorid=m.majorid ");
		if (StringUtil.isNotEmpty(student.getId())) {
			sb.append(" and s.id like '%" + student.getId() + "%'");
		}
		if (StringUtil.isNotEmpty(student.getName())) {
			sb.append(" and s.name like '%" + student.getName() + "%'");
		}
		if (StringUtil.isNotEmpty(student.getDormno())) {
			sb.append(" and dormno like '%" + student.getDormno() + "%'");
		}
		if (StringUtil.isNotEmpty(student.getDepaid())) {
			sb.append(" and s.depaid like '%" + student.getDepaid() + "%'");
		}
		if (StringUtil.isNotEmpty(student.getMajorid())) {
			sb.append(" and s.majorid like '%" + student.getMajorid() + "%'");
		}
		if (StringUtil.isNotEmpty(student.getGradeid())) {
			sb.append(" and s.gradeid like '%" + student.getGradeid() + "%'");
		}
		sb.append(" order by s.id limit ?,?");
		PreparedStatement pstmt = conn.prepareStatement(sb.toString());
		pstmt.setInt(1, (pc - 1) * ps);
		pstmt.setInt(2, ps);
		ResultSet rs = pstmt.executeQuery();
		List<Student> message = new ArrayList<Student>();
		while (rs.next()) {
			Student student2 = new Student();
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
	 * ����ѧ����Ϣ��¼��
	 */
	public int studentCount(Connection conn, Student student, int pc, int ps)
			throws Exception {
		StringBuffer sb = new StringBuffer(
				"select count(*) as total from student s,dorms d,depa dp,major m,grade g where s.gradeid=g.gradeid and s.dormno=d.id and s.depaid=dp.depaid and s.majorid=m.majorid");
		if (StringUtil.isNotEmpty(student.getId())) {
			sb.append(" and s.id like '%" + student.getId() + "%'");
		}
		if (StringUtil.isNotEmpty(student.getName())) {
			sb.append(" and name like '%" + student.getName() + "%'");
		}
		if (StringUtil.isNotEmpty(student.getDormno())) {
			sb.append(" and dormno like '%" + student.getDormno() + "%'");
		}
		if (StringUtil.isNotEmpty(student.getDepaid())) {
			sb.append(" and s.depaid like '%" + student.getDepaid() + "%'");
		}
		if (StringUtil.isNotEmpty(student.getMajorid())) {
			sb.append(" and s.majorid like '%" + student.getMajorid() + "%'");
		}
		if (StringUtil.isNotEmpty(student.getGradeid())) {
			sb.append(" and s.gradeid like '%" + student.getGradeid() + "%'");
		}
		PreparedStatement stmt = conn.prepareStatement(sb.toString());
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			return rs.getInt("total");
		} else {
			return 0;
		}
	}

	/**
	 * ����Ա��ѯ������Ϣ,Excel���������õ�
	 */
	public ResultSet dormsList2(Connection conn, Student student)
			throws Exception {
		StringBuffer sb = new StringBuffer(
				"select s.id,s.name,dp.depana,m.majorNa,g.gradena,d.dormna,s.roomno,s.bedno,s.phone from "
						+ "student s,dorms d,depa dp,major m,grade g "
						+ "where s.gradeid=g.gradeid and s.dormno=d.id and s.depaid=dp.depaid and s.majorid=m.majorid");
		if (StringUtil.isNotEmpty(student.getId())) {
			sb.append(" and s.id like '%" + student.getId() + "%'");
		}
		if (StringUtil.isNotEmpty(student.getName())) {
			sb.append(" and name like '%" + student.getName() + "%'");
		}
		if (StringUtil.isNotEmpty(student.getDormno())) {
			sb.append(" and dormno like '%" + student.getDormno() + "%'");
		}
		if (StringUtil.isNotEmpty(student.getDepaid())) {
			sb.append(" and s.depaid like '%" + student.getDepaid() + "%'");
		}
		if (StringUtil.isNotEmpty(student.getMajorid())) {
			sb.append(" and s.majorid like '%" + student.getMajorid() + "%'");
		}
		if (StringUtil.isNotEmpty(student.getGradeid())) {
			sb.append(" and s.gradeid like '%" + student.getGradeid() + "%'");
		}
		sb.append(" order by s.id");
		PreparedStatement stmt = conn.prepareStatement(sb.toString());
		ResultSet rs = stmt.executeQuery();

		return rs;
	}

	/**
	 * ����ѧ����¼�˺š���ʼ����
	 */
	public int passAdd(Connection conn, Student student) throws Exception {
		String sql = "insert into admin values(?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, student.getId());
		pstmt.setString(2, student.getPass());

		return pstmt.executeUpdate();
	}

	/**
	 * ����ѧ����¼�˺š���ʼ����
	 */
	public int passUpdate(Connection conn, Student student) throws Exception {
		String sql = "update admin set pass=? where id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, student.getPass());
		pstmt.setString(2, student.getId());

		return pstmt.executeUpdate();
	}

	/**
	 * ����ѧ����Ϣ
	 */
	public int studentAdd(Connection conn, Student student) throws Exception {
		String sql = "insert into student values(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, student.getId());
		pstmt.setString(2, student.getName());
		pstmt.setString(3, student.getDepaid());
		pstmt.setString(4, student.getMajorid());
		pstmt.setString(5, student.getGradeid());
		pstmt.setString(6, student.getSex());
		pstmt.setString(7, student.getDormno());
		pstmt.setString(8, student.getRoomno());
		pstmt.setString(9, student.getBedno());
		pstmt.setString(10, student.getPhone());
		return pstmt.executeUpdate();
	}

	/**
	 * ����ѧ����Ϣ
	 */
	public int studentUpdate(Connection conn, Student student) throws Exception {
		String sql = "update student set name=?,depaid=?,majorid=?,sex=?,dormno=?,roomno=?,bedno=?,phone=? where id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, student.getName());
		pstmt.setString(2, student.getDepaid());
		pstmt.setString(3, student.getMajorid());
		pstmt.setString(4, student.getSex());
		pstmt.setString(5, student.getDormno());
		pstmt.setString(6, student.getRoomno());
		pstmt.setString(7, student.getBedno());
		pstmt.setString(8, student.getPhone());
		pstmt.setString(9, student.getId());
		return pstmt.executeUpdate();
	}

	/**
	 * ����Υ����Ϣ
	 */
	public int wrongAdd(Connection conn, Wrong wrong) throws Exception {
		String sql = "insert into wrongmsg values(null,?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, wrong.getStuid());
		pstmt.setString(2, wrong.getName());
		pstmt.setString(3, wrong.getWrongday());
		pstmt.setString(4, wrong.getWrongreason());
		pstmt.setString(5, wrong.getResult());

		return pstmt.executeUpdate();
	}

	/**
	 * ����Ա��Ϣ��ѯ
	 */
	public Admin find(String classnumber) {
		JdbcUtil jdbcUtil = new JdbcUtil();
		Admin admin = null;
		String sql = "select * from admin where id=?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn = jdbcUtil.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, classnumber);
			rs = stmt.executeQuery();

			if (rs.next()) {
				admin = new Admin();
				admin.setId(rs.getString("id"));
				admin.setPass(rs.getString("pass"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				jdbcUtil.close(conn);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return admin;
	}

	/**
	 * ά����Ա��Ϣ��ѯ
	 */
	public Admin find1(String classnumber) {
		JdbcUtil jdbcUtil = new JdbcUtil();
		Admin admin = null;
		String sql = "select * from l_service where id=?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn = jdbcUtil.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, classnumber);
			rs = stmt.executeQuery();

			if (rs.next()) {
				admin = new Admin();
				admin.setId(rs.getString("id"));
				admin.setPass(rs.getString("pass"));
				admin.setTypeid(rs.getString("typeid"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				jdbcUtil.close(conn);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return admin;
	}

	/**
	 * ����Ա�޸�����
	 * 
	 * @param pwd
	 * @return
	 * @throws Exception
	 */
	public int ChangePass(Connection conn, String pwd, String id)
			throws Exception {
		String sql = "update admin set pass=? where id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, pwd);
		pstmt.setString(2, id);

		return pstmt.executeUpdate();
	}

	/**
	 * ��ʦ�޸�����
	 * 
	 * @param pwd
	 * @return
	 * @throws Exception
	 */
	public int ChangePass1(Connection conn, String pwd, String id)
			throws Exception {
		String sql = "update l_teacher set pass=? where id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, pwd);
		pstmt.setString(2, id);

		return pstmt.executeUpdate();
	}

	/**
	 * ����Ա��¼��֤
	 * 
	 * @param name
	 * @param pwd
	 * @return
	 */
	public boolean CheckLogin(String name, String pwd) {

		JdbcUtil jdbcUtil = new JdbcUtil();
		boolean islogin = false;
		String sql = "select * from admin where id =? and pass =?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn = jdbcUtil.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setString(2, pwd);
			rs = stmt.executeQuery();
			if (rs.next()) {
				islogin = true;
			} else {
				islogin = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {

				jdbcUtil.close(conn);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return islogin;
	}

	/**
	 * ά����Ա��¼��֤
	 * 
	 * @param name
	 * @param pwd
	 * @return
	 */
	public boolean CheckLogin1(String name, String pwd) {

		JdbcUtil jdbcUtil = new JdbcUtil();
		boolean islogin = false;
		String sql = "select * from l_service where id =? and pass =?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn = jdbcUtil.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setString(2, pwd);
			rs = stmt.executeQuery();
			if (rs.next()) {
				islogin = true;
			} else {
				islogin = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {

				jdbcUtil.close(conn);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return islogin;
	}

	/**
	 * ��ѯ�Ƿ����ѧ��,�������������
	 */
	public boolean CheckID(Connection conn, String id) throws Exception {
		String sql = "select * from student where id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		boolean a = true;
		pstmt.setString(1, id);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			a = true;
		} else {
			a = false;
		}

		return a;
	}

	/**
	 * ��ѯ��Ʒ��Ϣ
	 * 
	 */
	public Page<Product> productList(Connection conn, Product product, int pc,
			int ps) throws Exception {
		Page<Product> page = new Page<Product>();
		page.setPc(pc);
		page.setPs(ps);
		int tr = productCount(conn, product, pc, ps);
		page.setTr(tr);
		StringBuffer sb = new StringBuffer("select * from product");
		if (StringUtil.isNotEmpty(product.getPname())) {
			sb.append(" and pname like '%" + product.getPname() + "%'");
		}
		sb.append(" order by pid limit ?,?");
		PreparedStatement pstmt = conn.prepareStatement(sb.toString()
				.replaceFirst("and", "where"));
		pstmt.setInt(1, (pc - 1) * ps);
		pstmt.setInt(2, ps);
		ResultSet rs = pstmt.executeQuery();
		List<Product> message = new ArrayList<Product>();
		while (rs.next()) {
			Product product2 = new Product();
			product2.setPid(rs.getString("pid"));
			product2.setPname(rs.getString("pname"));
			product2.setPprice(Double.parseDouble(rs.getString("pprice")));
			product2.setPtotal(Integer.parseInt(rs.getString("ptotal")));
			message.add(product2);
		}
		page.setBeanList(message);
		return page;
	}

	/**
	 * ������Ʒ��Ϣ��¼��
	 */
	public int productCount(Connection conn, Product product, int pc, int ps)
			throws Exception {
		StringBuffer sb = new StringBuffer(
				"select count(*) as total from product");
		if (StringUtil.isNotEmpty(product.getPname())) {
			sb.append(" and pname like '%" + product.getPname() + "%'");
		}
		PreparedStatement stmt = conn.prepareStatement(sb.toString()
				.replaceFirst("and", "where"));
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			return rs.getInt("total");
		} else {
			return 0;
		}
	}

	/**
	 * ��ʼ����Ʒ������
	 * 
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public ResultSet productsList(Connection conn, String pid) throws Exception {
		StringBuffer sb = new StringBuffer("select * from product");
		if (StringUtil.isNotEmpty(pid)) {
			sb.append(" and pid = " + pid + "");
		}
		PreparedStatement pstmt = conn.prepareStatement(sb.toString()
				.replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}

	/**
	 * ����pid���Ҷ�Ӧ����Ʒ
	 * 
	 * @param pid
	 * @return
	 */
	public Product findByPid(String pid) {
		JdbcUtil jdbcUtil = new JdbcUtil();
		Product product = null;
		String sql = "select * from product where pid=?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn = jdbcUtil.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, Integer.parseInt(pid));
			rs = stmt.executeQuery();

			if (rs.next()) {
				product = new Product();
				product.setPid(String.valueOf(rs.getInt("pid")));
				product.setPname(rs.getString("pname"));
				product.setPprice(rs.getDouble("pprice"));
				product.setPtotal(rs.getInt("ptotal"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				jdbcUtil.close(conn);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return product;
	}

	/**
	 * ��ѯ�������� ��ȡ��Ʒ�õ�
	 * 
	 * @param dormid
	 * @return
	 */
	public String findDname(String dormid) {
		JdbcUtil jdbcUtil = new JdbcUtil();
		String name = null;
		String sql = "select * from dorms where id=?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn = jdbcUtil.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, Integer.parseInt(dormid));
			rs = stmt.executeQuery();

			if (rs.next()) {
				name = rs.getString("dormna");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				jdbcUtil.close(conn);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return name;
	}

	/**
	 * �����Ʒ�嵥
	 * 
	 * @param order
	 * @return
	 */
	public int saveOrder(Order order) {
		JdbcUtil jdbcUtil = new JdbcUtil();
		int a = 0;
		Connection conn = jdbcUtil.getConnection();
		try {
			String sql = "insert into orders values(?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, order.getOid());
			pstmt.setString(2, order.getOtime());
			pstmt.setInt(3, order.getState());
			pstmt.setString(4, order.getSuid());
			pstmt.setString(5, order.getDescs());
			a = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				jdbcUtil.close(conn);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return a;
	}

	/**
	 * �����Ʒ��
	 * 
	 * @param orderItem
	 * @return
	 */
	public int saveOrderItem(OrderItem orderItem) {
		JdbcUtil jdbcUtil = new JdbcUtil();
		int a = 0;
		Connection conn = jdbcUtil.getConnection();
		try {
			String sql = "insert into orderitem values(null,?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, orderItem.getCount());
			pstmt.setDouble(2, orderItem.getSubtotal());
			pstmt.setInt(3, orderItem.getPid());
			pstmt.setInt(4, orderItem.getOid());
			pstmt.setInt(5, orderItem.getDormid());
			a = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				jdbcUtil.close(conn);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return a;
	}

	/**
	 * ��֤��Ʒ�嵥id�Ƿ����
	 * 
	 * @param oid
	 * @return
	 */
	public boolean findOid(String oid) {
		JdbcUtil jdbcUtil = new JdbcUtil();
		boolean a = true;
		Connection conn = jdbcUtil.getConnection();
		try {
			String sql = "select * from orders where oid=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, oid);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				a = true;
			} else {
				a = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				jdbcUtil.close(conn);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return a;
	}

	/**
	 * ��ѯά��Ա�����Ƿ����
	 */
	public boolean ChecksuId(Connection conn, String suid) throws Exception {
		String sql = "select * from l_service where id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		boolean a = true;
		pstmt.setString(1, suid);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			a = true;
		} else {
			a = false;
		}
		return a;
	}

	/**
	 * ��ѯ��Ʒ�嵥��Ϣ
	 * 
	 */
	public Page<Order> orderList(Connection conn, Order order, int pc, int ps)
			throws Exception {
		Page<Order> page = new Page<Order>();
		page.setPc(pc);
		page.setPs(ps);
		int tr = orderCount(conn, order, pc, ps);
		page.setTr(tr);
		StringBuffer sb = new StringBuffer("select * from orders");
		if (StringUtil.isNotEmpty(String.valueOf(order.getOid()))) {
			sb.append(" and oid = " + order.getOid() + "");
		}
		if (StringUtil.isNotEmpty(String.valueOf(order.getSuid()))) {
			sb.append(" and suid = " + order.getSuid() + "");
		}
		if (StringUtil.isNotEmpty(String.valueOf(order.getState()))) {
			sb.append(" and state = " + order.getState() + "");
		}
		sb.append(" order by otime desc limit ?,?");
		PreparedStatement pstmt = conn.prepareStatement(sb.toString()
				.replaceFirst("and", "where"));
		pstmt.setInt(1, (pc - 1) * ps);
		pstmt.setInt(2, ps);
		ResultSet rs = pstmt.executeQuery();
		List<Order> message = new ArrayList<Order>();
		while (rs.next()) {
			Order order2 = new Order();
			order2.setOid(Integer.parseInt(rs.getString("oid")));
			order2.setOtime(rs.getString("otime"));
			order2.setState(Integer.parseInt(rs.getString("state")));
			order2.setSuid(rs.getString("suid"));
			order2.setDescs(rs.getString("descs"));
			message.add(order2);
		}
		page.setBeanList(message);
		return page;
	}

	/**
	 * ������Ʒ�嵥��¼��
	 */
	public int orderCount(Connection conn, Order order, int pc, int ps)
			throws Exception {
		StringBuffer sb = new StringBuffer("select count(*) total from orders");
		if (StringUtil.isNotEmpty(String.valueOf(order.getOid()))) {
			sb.append(" and oid = " + order.getOid() + "");
		}
		if (StringUtil.isNotEmpty(String.valueOf(order.getSuid()))) {
			sb.append(" and suid = " + order.getSuid() + "");
		}
		if (StringUtil.isNotEmpty(String.valueOf(order.getState()))) {
			sb.append(" and state = " + order.getState() + "");
		}
		PreparedStatement stmt = conn.prepareStatement(sb.toString()
				.replaceFirst("and", "where"));
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			return rs.getInt("total");
		} else {
			return 0;
		}
	}

	/**
	 * ά��Ա��ѯ�Լ���������Ϣ
	 */
	public Page<Order> orderSList(Connection conn, String suid)
			throws Exception {
		Page<Order> page = new Page<Order>();
		StringBuffer sb = new StringBuffer(
				"select * from orders where state in(0,1,3)");
		if (StringUtil.isNotEmpty(suid)) {
			sb.append(" and suid = " + suid + "");
		}
		PreparedStatement pstmt = conn.prepareStatement(sb.toString());
		ResultSet rs = pstmt.executeQuery();
		List<Order> message = new ArrayList<Order>();
		while (rs.next()) {
			Order order2 = new Order();
			order2.setOid(Integer.parseInt(rs.getString("oid")));
			order2.setOtime(rs.getString("otime"));
			order2.setState(Integer.parseInt(rs.getString("state")));
			order2.setSuid(rs.getString("suid"));
			order2.setDescs(rs.getString("descs"));
			message.add(order2);
		}
		page.setBeanList(message);
		return page;
	}

	/**
	 * ��ѯ��Ʒ�嵥�����Ʒ����Ϣ
	 * 
	 */
	public Page<OrderItem> orderItemList(Connection conn, OrderItem orderItem,
			int pc, int ps) throws Exception {
		Page<OrderItem> page = new Page<OrderItem>();
		page.setPc(pc);
		page.setPs(ps);
		int tr = orderItemCount(conn, orderItem, pc, ps);
		page.setTr(tr);
		StringBuffer sb = new StringBuffer(
				"select * from orderitem oi,product p,dorms d where oi.pid=p.pid and oi.dormid=d.id");
		if (StringUtil.isNotEmpty(String.valueOf(orderItem.getOid()))) {
			sb.append(" and oid = " + orderItem.getOid() + "");
		}
		sb.append(" order by otid limit ?,?");
		PreparedStatement pstmt = conn.prepareStatement(sb.toString());
		pstmt.setInt(1, (pc - 1) * ps);
		pstmt.setInt(2, ps);
		ResultSet rs = pstmt.executeQuery();
		List<OrderItem> message = new ArrayList<OrderItem>();
		while (rs.next()) {
			OrderItem orderItem2 = new OrderItem();
			orderItem2.setOtid(Integer.parseInt(rs.getString("otid")));
			orderItem2.setOid(Integer.parseInt(rs.getString("oid")));
			orderItem2.setDname(rs.getString("d.dormna"));
			orderItem2.setPname(rs.getString("p.pname"));
			orderItem2.setCount(Integer.parseInt(rs.getString("count")));
			orderItem2
					.setSubtotal(Double.parseDouble(rs.getString("subtotal")));
			message.add(orderItem2);
		}
		page.setBeanList(message);
		return page;
	}

	/**
	 * ������Ʒ�嵥�����Ʒ���¼��
	 */
	public int orderItemCount(Connection conn, OrderItem orderItem, int pc,
			int ps) throws Exception {
		StringBuffer sb = new StringBuffer(
				"select count(*) total from orderitem");
		if (StringUtil.isNotEmpty(String.valueOf(orderItem.getOid()))) {
			sb.append(" and oid = " + orderItem.getOid() + "");
		}
		PreparedStatement stmt = conn.prepareStatement(sb.toString()
				.replaceFirst("and", "where"));
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			return rs.getInt("total");
		} else {
			return 0;
		}
	}

	/**
	 * ɾ����Ʒ�ķ���
	 */
	public int deleteProduct(Connection con, String pIds) throws Exception {
		String sql = "delete from product where pid in(" + pIds + ")";
		PreparedStatement pstmt = con.prepareStatement(sql);
		return pstmt.executeUpdate();
	}

	/**
	 * ɾ���嵥�ķ���
	 * 
	 */
	public int deleteOrder(Connection con, String oIds) throws Exception {
		String sql = "delete from orders where oid in(" + oIds + ")";
		PreparedStatement pstmt = con.prepareStatement(sql);
		return pstmt.executeUpdate();
	}

	/**
	 * ɾ���嵥����Ʒ��ķ���
	 * 
	 * @throws Exception
	 * 
	 */
	public int deleteOrderItem(Connection con, String otId) throws Exception {
		String sql = "delete from orderitem where otid in(" + otId + ")";
		PreparedStatement pstmt = con.prepareStatement(sql);
		return pstmt.executeUpdate();
	}

	/**
	 * ����oid���Ҷ�Ӧ����Ʒ��
	 */
	public List<OrderItem> fingByOid(Connection conn, String oid)
			throws Exception {
		StringBuffer sb = new StringBuffer(
				"select * from orderitem oi,product p where oi.pid=p.pid and oi.oid=?");
		PreparedStatement pstmt = conn.prepareStatement(sb.toString());
		pstmt.setString(1, oid);
		ResultSet rs = pstmt.executeQuery();
		List<OrderItem> message = new ArrayList<OrderItem>();
		while (rs.next()) {
			OrderItem orderItem2 = new OrderItem();
			orderItem2.setPname(rs.getString("pname"));
			orderItem2.setPid(Integer.parseInt(rs.getString("pid")));
			orderItem2.setCount(Integer.parseInt(rs.getString("count")));
			message.add(orderItem2);
		}
		return message;
	}

	/**
	 * ����pid���ҿ��
	 * 
	 * @throws Exception
	 */
	public int findCountByPid(Connection conn, Integer pid) throws Exception {
		String sql = "select * from product where pid=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, pid);
		ResultSet rs = pstmt.executeQuery();
		int count = 0;
		if (rs.next()) {
			count = Integer.parseInt(rs.getString("ptotal"));
		}
		return count;
	}

	/**
	 * ���¿������
	 */
	public void updateProductTotal(Connection conn, Integer count, Integer pid)
			throws Exception {
		String sql = "update product set ptotal =? where pid=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, count);
		pstmt.setInt(2, pid);
		pstmt.executeUpdate();
	}

	/**
	 * ������Ʒ��Ϣ
	 */
	public int updateProduct(Connection conn, Product product) throws Exception {
		String sql = "update product set ptotal =? ,pprice=? where pid=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, product.getPtotal());
		pstmt.setDouble(2, product.getPprice());
		pstmt.setString(3, product.getPid());
		return pstmt.executeUpdate();
	}

	/**
	 * �޸�order״̬
	 */
	public void updateOrderState(Connection conn, Integer state, String oid,
			String desc) throws Exception {
		StringBuffer sb = new StringBuffer("update orders set state =?");
		if (desc != null) {
			sb.append(" ,descs = '" + desc + "'");
		}
		sb.append("  where oid=?");
		PreparedStatement pstmt = conn.prepareStatement(sb.toString());
		pstmt.setInt(1, state);
		pstmt.setInt(2, Integer.parseInt(oid));
		pstmt.executeUpdate();
	}
}
