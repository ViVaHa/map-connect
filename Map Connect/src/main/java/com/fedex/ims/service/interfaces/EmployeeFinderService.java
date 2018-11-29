package com.fedex.ims.service.interfaces;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.fedex.ims.model.Circle;
import com.fedex.ims.model.ResponseObject;
import com.fedex.ims.model.SelectedPeople;

public interface EmployeeFinderService {
	public Map<String, List<SelectedPeople>> find(List<Circle> circles) throws SQLException;

	public List<SelectedPeople> removeDuplicates(Map<String, List<SelectedPeople>> mymap);

	public List<ResponseObject> obtainResponseObjects(Map<String, List<SelectedPeople>> mymap);
}
