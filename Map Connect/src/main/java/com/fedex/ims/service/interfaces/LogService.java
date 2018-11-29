package com.fedex.ims.service.interfaces;

import java.util.List;

import com.fedex.ims.model.Circle;
import com.fedex.ims.model.SelectedPeople;

public interface LogService {
	public int logAllInfo(String subject, String content, String status);

	public int LogInfoFromClientSide(List<Circle> circles);

	public int logDependentTables(List<Circle> circles, List<SelectedPeople> selectedPeople);
}
