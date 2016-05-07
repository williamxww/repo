package com.bow.utils;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

/**
 *  boolean in java and char(1) in the database;
 * @author vivid
 * 2015-1-29
 */
public class BooleanTypeHandler implements TypeHandler<Boolean>{
	
	/**
	 * 获取数据结果集时如何把数据库类型转换为对应的Java类型 
	 * 从结果集根据列名取出值然后转换成boolean
	 */
	public Boolean getResult(ResultSet resultSet, String columnName) throws SQLException {
		return transferType(resultSet.getString(columnName));
	}
	
	/**
	 * 通过字段位置获取字段数据时把数据库类型转换为对应的Java类型 
	 */
	public Boolean getResult(ResultSet resultSet, int columnIndex) throws SQLException {
		return transferType(resultSet.getString(columnIndex));
	}
	
	/**
	 * 调用存储过程后把数据库类型的数据转换为对应的Java类型 
	 */
	public Boolean getResult(CallableStatement callableStatement, int columnIndex)
			throws SQLException {
		return transferType(callableStatement.getString(columnIndex));
	}
	
	/**
	 * 用于将 java 类型 转换为 database类型
	 * @param prep
	 * @param parameterIndex 需要修改的字符在 SQL中的位置
	 * @param javaType
	 * @param dbtype
	 * @throws SQLException
	 */
	public void setParameter(PreparedStatement prep, int parameterIndex, Boolean javaType,
			JdbcType dbtype) throws SQLException {
		if(javaType.equals(Boolean.TRUE)){
			prep.setString(parameterIndex, "1");
		} else {
			prep.setString(parameterIndex, "0");
		}
		
	}
	
	private Boolean transferType(String s){
		if("1".equals(s)){
			return Boolean.TRUE;
		}else{
			return Boolean.FALSE;
		}
	}


}
