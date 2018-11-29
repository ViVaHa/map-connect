package com.fedex.ims.constants;

public class PeopleDAOSql {
	
	public static final String GET_PEOPLE="SELECT name, email, id from ims where( 3959 * acos( cos( radians(?) ) * "
			+ "cos( radians( latitude ) ) * cos( radians( longitude ) - radians(?) ) + sin( radians(?) ) * sin( radians( latitude ) ) ) ) <?";
}
