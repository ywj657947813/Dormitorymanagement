package Util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtil {

	public static JSONArray formatRsToJsonArray(ResultSet rs) throws Exception {
		ResultSetMetaData md = rs.getMetaData();
		int num = md.getColumnCount();
		JSONArray array = new JSONArray();
		while (rs.next()) {
			JSONObject mapOfColValues = new JSONObject();
			for (int i = 1; i <= num; i++) {
				Object o = rs.getObject(i);// 获取对象类型
				// instanceof判断对象Object o是什么类型

				if (o instanceof Date) {
					// 如果Date的话
					mapOfColValues.put(md.getColumnName(i),
							DateUtil.formatDate((Date) o, "yyyy-MM-dd"));
				} else {
					mapOfColValues.put(md.getColumnName(i), rs.getObject(i));
				}
			}
			array.add(mapOfColValues);
		}
		return array;
	}
}
