package com.fedex.ims.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.fedex.ims.model.Circle;
import com.fedex.ims.model.SelectedPeople;

public interface PeopleDAO {
	public List<SelectedPeople> getAllPeople(Circle c) throws SQLException;
}
