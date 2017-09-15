package com.newt.exploration.batchutil;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.jdbc.core.JdbcTemplate;

import com.newt.exploration.beans.Records;

public class RecordTasklet implements Tasklet {
	private DataSource dataSource;
	private String sql = "SELECT RecordID, RecordName, AuthorName, ReadFlag FROM Records where ReadFlag=0;";

	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		List<Records> recordList = new ArrayList<Records>();
		JdbcTemplate myTemplate = new JdbcTemplate(getDataSource());
		recordList = myTemplate.query(sql, new RecordMapper());
		System.out.println(recordList.size() + " records are unread records!!!");
		for (Records record : recordList) {
			System.out.println(record.getRecordId() +" "+ record.getRecordName()+" "+record.getAuthorName()+" "+record.getReadFlag());
		}
		return RepeatStatus.FINISHED;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
}
