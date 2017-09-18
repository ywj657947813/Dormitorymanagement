package Servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Dao.AdminDao;
import Model.Student;
import Util.DateUtil;
import Util.ExcelUtil;
import Util.JdbcUtil;
import Util.ResponseUtil;

@MultipartConfig
public class AdmLotInsertStuServlet extends HttpServlet {

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
		JdbcUtil jdbcUtil = new JdbcUtil();

		// System.out.println("+++++++++++++++++++++++++++++");
		// System.out.println(request.getParts().size()); //��ȡ�ļ�����
		// for(Part p:request.getParts()){//�ɴ�ӡ��ǰ̨�������ļ������֣����� name ��������
		// System.out.println(p.getHeader("Content-Disposition"));
		// System.out.println(p.getHeader("Disposition"));
		// }

		Part part = request.getPart("File");
		String fileName = part.getHeader("Content-Disposition");
		fileName = fileName.substring(fileName.lastIndexOf('=') + 2,
				fileName.length() - 1); // ��ȡ�ϴ��ļ�������
		InputStream in = part.getInputStream();
		BufferedInputStream bf = new BufferedInputStream(in);

		File file = new File(fileName); // ������ͬ���ֵĶ�Ӧ�ļ�
		// System.out.println(file.getAbsolutePath()); //��ӡ�ļ���ַ
		BufferedOutputStream bos = new BufferedOutputStream(
				new FileOutputStream(file));

		int len = 0;
		byte[] buf = new byte[1024];
		while ((len = in.read(buf)) != -1) {
			bos.write(buf, 0, len);
		}
		bf.close();
		bos.close();

		Boolean a = true;// ��������������Ϣ
		Workbook wb = null;
		if (ExcelUtil.isExcel2003(new FileInputStream(file))) {
			wb = new HSSFWorkbook(new FileInputStream(file));
		} else {
			wb = new XSSFWorkbook(new FileInputStream(file));
		}
		Sheet sheet = wb.getSheetAt(0); // ��ȡ��һ��sheetҳ

		if (sheet != null) {

			Connection con = null;
			try {
				con = jdbcUtil.getConnection();
				for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
					Row row = sheet.getRow(rowNum); // �ӵڶ��п�ʼ��ȡ���ݣ���һ�������ݿ������ԣ�
					if (row == null) {
						continue;
					}
					Student student = new Student();
					student.setId(ExcelUtil.formatCell(row.getCell(0)));
					student.setPass(ExcelUtil.formatCell(row.getCell(1)));
					if (adminDao.CheckID(con,
							ExcelUtil.formatCell(row.getCell(0)))) {
						adminDao.passUpdate(con, student);
					} else {
						adminDao.passAdd(con, student);
					}
				}
			} catch (Exception e) {
				// �������쳣��ͳһ�׳��˴���
				a = false;// ��sheetҳ��������a=false���򲻻������óɹ�����Ϣ
				String name = sheet.getSheetName(); // ��ȡsheetҳ������
				JSONObject result = new JSONObject();
				result.put("success", "true");
				result.put("msg1", "�ϴ�ʧ�ܣ�����   " + name + "ҳ   �������Ƿ���ȷ��");
				try {
					ResponseUtil.write(response, result);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				// e.printStackTrace();
			} finally {
				jdbcUtil.close(con);
			}

		}
		Sheet sheet2 = wb.getSheetAt(1); // ��ȡ�ڶ���Sheetҳ
		if (sheet2 != null) {

			Connection con = null;
			try {
				con = jdbcUtil.getConnection();
				for (int rowNum = 1; rowNum <= sheet2.getLastRowNum(); rowNum++) {
					Row row = sheet2.getRow(rowNum); // �ӵڶ��п�ʼ��ȡ���ݣ���һ�������ݿ������ԣ�
					if (row == null) {
						continue;
					}
					Student student = new Student();
					student.setId(DateUtil.deleteZero(ExcelUtil.formatCell(row
							.getCell(0))));
					student.setName(ExcelUtil.formatCell(row.getCell(1)));
					student.setDepaid(DateUtil.deleteZero(ExcelUtil
							.formatCell(row.getCell(2))));
					student.setMajorid(DateUtil.deleteZero(ExcelUtil
							.formatCell(row.getCell(3))));
					student.setGradeid(DateUtil.deleteZero(ExcelUtil
							.formatCell(row.getCell(4))));
					student.setSex(ExcelUtil.formatCell(row.getCell(5)));
					student.setDormno(DateUtil.deleteZero(ExcelUtil
							.formatCell(row.getCell(6))));
					student.setRoomno(ExcelUtil.formatCell(row.getCell(7)));
					student.setBedno(DateUtil.deleteZero(ExcelUtil
							.formatCell(row.getCell(8))));
					student.setPhone(DateUtil.deleteZero(ExcelUtil
							.formatCell(row.getCell(9))));

					if (adminDao.CheckID(con, DateUtil.deleteZero(ExcelUtil
							.formatCell(row.getCell(0))))) {
						adminDao.studentUpdate(con, student);
					} else {
						adminDao.studentAdd(con, student);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				// �������쳣��ͳһ�׳��˴���
				a = false;// ��sheetҳ��������a=false���򲻻������óɹ�����Ϣ
				String name = sheet2.getSheetName(); // ��ȡsheetҳ������
				JSONObject result = new JSONObject();
				result.put("success", "true");
				result.put("msg1", "�ϴ�ʧ�ܣ�����   " + name + "ҳ    �������Ƿ���ȷ��");
				try {
					ResponseUtil.write(response, result);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				// e.printStackTrace();
			} finally {
				jdbcUtil.close(con);
			}

		}
		if (a) {
			// �������û�д�����a=true�������ô���Ϣ
			JSONObject result = new JSONObject();
			result.put("success", "true");
			result.put("msg", "true");
			try {
				ResponseUtil.write(response, result);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
