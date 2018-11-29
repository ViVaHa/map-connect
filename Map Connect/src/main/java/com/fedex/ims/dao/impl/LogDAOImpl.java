package com.fedex.ims.dao.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.fedex.ims.constants.LogDAOSql;
import com.fedex.ims.dao.interfaces.LogDAO;
import com.fedex.ims.model.Circle;
import com.fedex.ims.model.SelectedPeople;

@Repository
public class LogDAOImpl implements LogDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	// log information to emailaudit table
	@Override
	public int logAllInformation(String subject, String content, String status) {
		String sql = LogDAOSql.INSERT_LOG;
		return jdbcTemplate.update(sql, subject, content, status);
	}

	// log information to usergeneratedlatlng tables
	@Override
	public int logClientInfo(List<Circle> circles) {
		String sql = LogDAOSql.INSERT_CLIENT_LOG;
		for (Circle c : circles) {
			jdbcTemplate.update(sql, c.getLat(), c.getLng(), c.getRadius());
		}
		return 1;
	}

	// log information to useremailaudit table
	@Override
	public int logAllDependentTables(List<Circle> circles, List<SelectedPeople> selectedPeople) {
		String sql = LogDAOSql.GET_EMAIL_AUDIT_ID;
		int emailAuditId = jdbcTemplate.queryForObject(sql, Integer.class);
		System.out.println(emailAuditId);
		for (SelectedPeople pupil : selectedPeople) {
			int id = pupil.getId();
			sql = LogDAOSql.INSERT_EMAIL_LOG;
			jdbcTemplate.update(sql, id, emailAuditId);
		}
		return 1;
	}

}
