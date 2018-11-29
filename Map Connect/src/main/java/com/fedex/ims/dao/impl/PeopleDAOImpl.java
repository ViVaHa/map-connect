package com.fedex.ims.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.fedex.ims.constants.PeopleDAOSql;
import com.fedex.ims.dao.interfaces.PeopleDAO;
import com.fedex.ims.model.Circle;
import com.fedex.ims.model.SelectedPeople;

@Repository
public class PeopleDAOImpl implements PeopleDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<SelectedPeople> getAllPeople(Circle c){
		// TODO Auto-generated method stub

		String sql = PeopleDAOSql.GET_PEOPLE;
		List<SelectedPeople> selectedPeoples = jdbcTemplate.query(sql,
				new Object[] { c.getLat(), c.getLng(), c.getLat(), c.getRadius() }, new RowMapper<SelectedPeople>() {
					public SelectedPeople mapRow(ResultSet rs, int rowNum) throws SQLException  {
						SelectedPeople selectedPeople = new SelectedPeople();
						selectedPeople.setName(rs.getString(1));
						selectedPeople.setEmail(rs.getString(2));
						selectedPeople.setId(rs.getInt(3));
						return selectedPeople;
					}
				});
		return selectedPeoples;
	}

}
