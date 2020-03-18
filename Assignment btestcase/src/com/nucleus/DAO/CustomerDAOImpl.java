package com.nucleus.DAO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.nucleus.connection.ConnectionSetup;
import com.nucleus.customer.Customer;
import com.nucleus.errorlog.ErrorLog;
import com.nucleus.validation.Validation;

public class CustomerDAOImpl implements CustomerDAO {

	Customer customer = new Customer();

	Validation validation = new Validation();
	ErrorLog error = new ErrorLog();
	ConnectionSetup connectionSetup = new ConnectionSetup();
	Connection con = connectionSetup.getConnection();
	PreparedStatement pstmt;

	public void readFromFile(String path, String rejection) {

		int errorcount=0;

		FileReader filereader;
		try {
			filereader = new FileReader(path);
			BufferedReader bufferedReader = new BufferedReader(filereader);

			String line = bufferedReader.readLine();

			while (line != null) {

				String separate[] = line.split("~", -1);
				if (validation.checkpincode(separate[4]))
				{
					customer.setCustomerPinCode(separate[4]);
				
				} 
				else
				{
					errorcount++;
					System.out.println("ERROR  IN CUSTOMER PIN CODE");
                 }
				
				if (validation.checkcode(separate[0])&& separate[0].length() <= 10&&validation.primaryKey(separate[0]))
				{
					customer.setCustomerCode(separate[0]);
				} else
				{
					errorcount++;
					System.out.println("ERROR  IN CUSTOMERCODE");
					

				}
				if (validation.checkaddress(separate[2])&& separate[2].length() <= 100)
				{
					customer.setCustomerAddress1(separate[2]);
				} else
				{
					errorcount++;
					System.out.println("ERROR  IN CUSTOMER ADDRESS");
					
		}
				if (validation.checkprimarycontactperson(separate[7])
						&& separate[7].length() <= 100) {
					customer.setPrimaryContactPerson(separate[7]);
				} else {
					errorcount++;
					System.out.println("ERROR  IN CUSTOMER PRIMARY CONTACT PERSON");
					

				}

				if (validation.checkcreatedate(separate[10])) {
					customer.setCreateDate(separate[10]);
				} else {
					errorcount++;
					System.out.println("ERROR  IN CREATE DATE");
					

				}

				if (validation.checkcreatedby(separate[11])
						&& separate[11].length() <= 30) {
					customer.setCreatedBy(separate[11]);
				} else {
					errorcount++;
					System.out.println("ERROR  IN CREADTED BY");
					

				}

				if (validation.checkRecord(separate[8])) {
					customer.setRecordStatus(separate[8]);
				} else {
					errorcount++;
					System.out.println("ERROR IN RECORD STATUS");
					

				}
				if (validation.checkFlag(separate[9])) {
					customer.setActiveInactiveFlag(separate[9]);
				} else {
					errorcount++;
					System.out.println("ERROR IN FLAG");
					

				}
				if (validation.checkEmailId(separate[5])
						&& separate[5].length() <= 100) {
					customer.setEmailaddress(separate[5]);
				} else {
					errorcount++;
					System.out.println("ERROR IN EMAIL ID");
					
				}
				if (validation.checkName(separate[1])
						&& separate[1].length() <= 30) {
					customer.setCustomerName(separate[1]);
				} 
				else 
				{
				    System.out.println("ERROR IN CUSTOMER NAME");
					errorcount++;
					
				}
				
				customer.setCustomerAddress2(separate[3]);
				customer.setContactNumber(separate[6]);
				customer.setModifiedDate(separate[12]);
				customer.setModifiedBy(separate[13]);
				customer.setAuthorizedDate(separate[14]);
				customer.setAuthorizedBy(separate[15]);
				
				if(errorcount==0)
				{
				InsertDetails(customer);
				line = bufferedReader.readLine();
				
			}
				else
				{
					if(rejection.equals("R"))
					{
						error.SaveToFile(line);
					
						line=bufferedReader.readLine();
						
						errorcount=0;
					}
				else 
				{
					error.CopyFromFile(path);
					PreparedStatement pstmt;
					try {
						pstmt = con.prepareStatement("delete from TANMAYCUSTOMER");
						pstmt.executeUpdate();
						
					} catch (SQLException e) {
						e.printStackTrace();
					}
					System.exit(0);
				}
			}
			}
			bufferedReader.close();
		}
		
			catch (FileNotFoundException e1) {

			e1.printStackTrace();
		} catch (IOException e1) {

			e1.printStackTrace();
		}

		

		

	}

	public void InsertDetails(Customer customer) {

		try {
			pstmt = con
					.prepareStatement("insert into TANMAYCUSTOMER values(tanmay1.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

			pstmt.setString(1, customer.getCustomerCode());
			pstmt.setString(2, customer.getCustomerName());
			pstmt.setString(3, customer.getCustomerAddress1());
			pstmt.setString(4, customer.getCustomerAddress2());
			pstmt.setString(5, customer.getCustomerPinCode());
			pstmt.setString(6, customer.getEmailaddress());
			pstmt.setString(7, customer.getContactNumber());
			pstmt.setString(8, customer.getPrimaryContactPerson());
			pstmt.setString(9, customer.getRecordStatus());
			pstmt.setString(10, customer.getActiveInactiveFlag());
			pstmt.setString(11, customer.getCreateDate());
			pstmt.setString(12, customer.getCreatedBy());
			pstmt.setString(13, customer.getModifiedDate());
			pstmt.setString(14, customer.getModifiedBy());
			pstmt.setString(15, customer.getAuthorizedDate());
			pstmt.setString(16, customer.getAuthorizedBy());
			pstmt.executeUpdate();
			System.out.println("saved");

		} catch (SQLException e)
		{
			e.printStackTrace();
			//if(e.toString()==customer.getCustomerCode());
			//System.out.println("Data already present");
		}

	}

	}


