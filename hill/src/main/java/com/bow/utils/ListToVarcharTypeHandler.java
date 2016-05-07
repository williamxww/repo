package com.bow.utils;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

public class ListToVarcharTypeHandler extends BaseTypeHandler<List>{

	@Override
	public List getNullableResult(ResultSet resultSet, String columnName)
			throws SQLException {
		return transferType(resultSet.getString(columnName));
		
	}

	@Override
	public List getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
		
		return transferType(resultSet.getString(columnIndex));
	}

	@Override
	public List getNullableResult(CallableStatement cstmt, int columnIndex)
			throws SQLException {
		
		return transferType(cstmt.getString(columnIndex));
	}

	@Override
	public void setNonNullParameter(PreparedStatement preps, int parameterIndex,
			List srcParam, JdbcType targetParam) throws SQLException {
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<srcParam.size();i++){
			sb.append(srcParam.get(i));
			if(i<srcParam.size()-1){
				sb.append(",");
			}
		}
		preps.setString(parameterIndex, sb.toString());
		
	}
	
	private List transferType(String str){
		List<Long> res = new ArrayList<Long>();
		String[] ary = str.split(",");
		for(String s:ary){
			res.add(Long.valueOf(s));
		}
		return res;
	}

}
