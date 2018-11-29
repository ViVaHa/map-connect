package com.fedex.ims.dao.interfaces;

import java.util.List;

import com.fedex.ims.model.Circle;
import com.fedex.ims.model.SelectedPeople;

public interface LogDAO {
	public int logAllInformation(String subject,String content, String status);
	public int logClientInfo(List<Circle>circles);
	public int logAllDependentTables(List<Circle>circles,List<SelectedPeople>selectedPeople);
}
