package com.nucleus.validation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import com.nucleus.connection.ConnectionSetup;
import com.nucleus.customer.Customer;

public class Validation {
	Customer customer = new Customer();
	ConnectionSetup connectionSetup = new ConnectionSetup();
	Connection con = connectionSetup.getConnection();
	
   
	public boolean checkName(String CustomerName) {
		if (CustomerName.matches("[a-zA-Z0-9 ]+") && !CustomerName.isEmpty()) {
			return true;
		} else
			return false;
	}

	/*public boolean checkuniquekey(String CustomerCode )
	{
		HashSet<String> hs=new HashSet<String>();
		if(hs.add(CustomerCode))
		{
		return true;
		}
		else
		{
			return false;
		}
	}*/
	public boolean checkcode(String CustomerCode) 
	{
		    //HashSet hs=new HashSet();
		if (!CustomerCode.isEmpty())
		{
			return true;
		
	     }
		else
	        {
	   	    return false;
             	}
	}
	public boolean primaryKey(String customerCode)
	{
		try {
			PreparedStatement pstmt = con.prepareStatement("select * from tanmaycustomer");
			ResultSet resultSet = pstmt.executeQuery();
			while(resultSet.next())
			{
				if((resultSet.getString(2).equals(customerCode)))
						{
					        return false;
						}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}





	public boolean checkaddress(String CustomerAddress1) {
		if (!CustomerAddress1.isEmpty()) {
			return true;
		} else
			return false;

	}

	public boolean checkprimarycontactperson(String PrimaryContactPerson) {
		if (!PrimaryContactPerson.isEmpty()) {
			return true;
		} else
			return false;

	}

	public boolean checkcreatedate(String CreateDate) {
		if (!CreateDate.isEmpty()) {
			return true;
		} else
			return false;

	}

	public boolean checkcreatedby(String CreatedBy) {
		if (!CreatedBy.isEmpty()) {
			return true;
		} else
			return false;

	}

	public boolean checkEmailId(String Emailaddress) {
		if (Emailaddress.contains("@") && (Emailaddress.endsWith(".com"))
				|| Emailaddress.endsWith("co.in")
				|| Emailaddress.endsWith("COM")) {
			return true;
		} else if (Emailaddress.equals(null)) {
			return false;
		} else
			return false;

	}

	public boolean checkpincode(String CustomerPinCode) {

		if (CustomerPinCode.length() == 6) {
			return true;
		} else if (CustomerPinCode.equals(null)) {
			return false;
		} else
			return false;
	}

	public boolean checkRecord(String RecordStatus) {

		if (RecordStatus.length() == 1&& (RecordStatus.equals("N") || RecordStatus.equals("M")
						|| RecordStatus.equals("D") || RecordStatus.equals("A") || RecordStatus
							.equals("R"))) {
			return true;
		} else if (RecordStatus.equals(null)) {
			return false;
		} else
			return false;
	}

	public boolean checkFlag(String ActiveInactiveFlag) {

		if (ActiveInactiveFlag.length() == 1
				&& (ActiveInactiveFlag.equals("A") || ActiveInactiveFlag
						.equals("I"))) {
			return true;
		} else if (ActiveInactiveFlag.equals(null)) {
			return false;
		} else
			return false;
	}

	


		

	}


