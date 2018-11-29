package com.fedex.ims.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fedex.ims.dao.interfaces.PeopleDAO;
import com.fedex.ims.model.Circle;
import com.fedex.ims.model.SelectedPeople;
import com.fedex.ims.service.interfaces.PeopleService;

@Service
public class PeopleServiceImpl implements PeopleService {

	@Autowired
	PeopleDAO p;

	// Call to DAO implementation to fetch records from database
	@Override
	public List<SelectedPeople> obtainPeople(Circle c) throws SQLException {
		// TODO Auto-generated method stub
		return p.getAllPeople(c);
	}

}
