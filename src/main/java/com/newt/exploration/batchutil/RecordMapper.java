package com.newt.exploration.batchutil;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.newt.exploration.beans.Records;

public class RecordMapper implements RowMapper<Records> {

	public Records mapRow(ResultSet rs, int rowNum) throws SQLException {
		Records record = new Records();
		record.setRecordId(rs.getInt("recordId"));
		record.setRecordName(rs.getString("recordName"));
		record.setAuthorName(rs.getString("authorName"));
		record.setReadFlag(rs.getInt("readFlag"));
		return record;
	}
}
