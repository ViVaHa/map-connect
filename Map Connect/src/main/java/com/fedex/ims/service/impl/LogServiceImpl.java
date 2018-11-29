package com.fedex.ims.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fedex.ims.dao.interfaces.LogDAO;
import com.fedex.ims.model.Circle;
import com.fedex.ims.model.SelectedPeople;
import com.fedex.ims.service.interfaces.LogService;

@Service
public class LogServiceImpl implements LogService {

	@Autowired
	LogDAO l;

	// Log information on the database
	@Override
	public int logAllInfo(String subject, String content, String status) {
		// TODO Auto-generated method stub
		return l.logAllInformation(subject, content, status);
	}

	@Override
	public int LogInfoFromClientSide(List<Circle> circles) {
		// TODO Auto-generated method stub
		return l.logClientInfo(circles);
	}

	// Log to table usergeneratedlatlng
	@Override
	public int logDependentTables(List<Circle> circles, List<SelectedPeople> selectedPeople) {
		// TODO Auto-generated method stub
		return l.logAllDependentTables(circles, selectedPeople);
	}

}
