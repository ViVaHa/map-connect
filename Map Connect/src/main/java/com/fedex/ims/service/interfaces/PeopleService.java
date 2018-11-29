package com.fedex.ims.service.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.fedex.ims.model.Circle;
import com.fedex.ims.model.SelectedPeople;

public interface PeopleService {
	public List<SelectedPeople> obtainPeople(Circle c) throws SQLException;
}
