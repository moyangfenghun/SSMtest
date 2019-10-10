package ssm.test.utils;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.sql.RowSet;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * JSONObject转换工具
 * 
 * @author 86189 2019-01-04
 */
public class JsonConversionUtils {
	/**
	 * sql结果集转换为JSONArray
	 * 如果某个字段值为Null JSONObject.get()将会返回null
	 * @param RowSets
	 * @return JSONArray 参数为空时返回null
	 * @throws SQLException
	 */
	public static JSONArray ResultToJson(RowSet rowset) throws SQLException {
		JSONArray jsonarray = new JSONArray();
		if (rowset != null) {
			ResultSetMetaData rsmd = rowset.getMetaData();
			while (rowset.next()) {
				JSONObject oneRow = new JSONObject();
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					oneRow.put(rsmd.getColumnLabel(i), rowset.getString(rsmd.getColumnLabel(i)));
				}
				jsonarray.add(oneRow);
			}
			return jsonarray;
		}
		return null;
	}

	/**
	 * sql结果集的字段名获取,建议名即as后指定的名字如果没有则返回原字段名
	 * key:TypeName获取数据库类型名,key:ColumnLabel获取字段别名
	 * 
	 * 
	 * @return JSONArray 参数为空时返回NUll
	 * @throws SQLException
	 */
	public static JSONArray getColumnlist(RowSet rowset) throws SQLException {
		JSONArray jsonarray=new JSONArray();
		if (rowset != null) {
			ResultSetMetaData rsmd = rowset.getMetaData();
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				JSONObject JsonObj=new JSONObject();
				JsonObj.put("TypeName",rsmd.getColumnTypeName(i));
				JsonObj.put("ColumnLabel",rsmd.getColumnLabel(i));
				jsonarray.add(JsonObj);
			}
			return jsonarray;
		}
		return null;
	}
	/**
	 * 不为null返回'str'
	 */
	public static String Isnull(String str){
		return str==null? null:"'"+str+"'";
	}
}
