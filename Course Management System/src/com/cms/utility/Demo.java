package com.cms.utility;

import java.sql.Connection;

public class Demo {

	public static void main(String[] args) {
		
		Connection con = DBUtil.provideConnection();
		
		System.out.println(con);
	}

}
