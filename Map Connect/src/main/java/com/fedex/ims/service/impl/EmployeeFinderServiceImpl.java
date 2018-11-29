package com.fedex.ims.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fedex.ims.model.Circle;
import com.fedex.ims.model.ResponseObject;
import com.fedex.ims.model.SelectedPeople;
import com.fedex.ims.service.interfaces.EmployeeFinderService;
import com.fedex.ims.service.interfaces.PeopleService;

@Service
public class EmployeeFinderServiceImpl implements EmployeeFinderService {
	@Autowired
	PeopleService peopleService;

	@Override
	public Map<String, List<SelectedPeople>> find(List<Circle> circles) throws SQLException {
		// TODO Auto-generated method stub
		// Obtain list of people from database in each circle
		Map<String, List<SelectedPeople>> mymap = new HashMap<>();
		for (Circle c : circles) {
			List<SelectedPeople> selectedPeople = peopleService.obtainPeople(c);
			mymap.put(c.getId(), selectedPeople);
		}
		return mymap;
	}

	// Remove redundancies
	@Override
	public List<SelectedPeople> removeDuplicates(Map<String, List<SelectedPeople>> myMap) {
		// TODO Auto-generated method stub
		Set<SelectedPeople> set = new HashSet<>();
		for (String id : myMap.keySet()) {
			List<SelectedPeople> selectedPeople = myMap.get(id);
			set.addAll(selectedPeople);
		}
		List<SelectedPeople> finalList = new ArrayList<>();
		finalList.addAll(set);
		return finalList;
	}

	// Provides response objects
	@Override
	public List<ResponseObject> obtainResponseObjects(Map<String, List<SelectedPeople>> mymap) {
		// TODO Auto-generated method stub
		List<ResponseObject> responseObjects = new ArrayList<>();
		for (String key : mymap.keySet()) {
			ResponseObject r = new ResponseObject();
			r.setId(key);
			r.setNumberOfPeople(mymap.get(key).size());
			responseObjects.add(r);
		}
		return responseObjects;
	}
}
