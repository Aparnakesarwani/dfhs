package com.nucleus.rejection;


	import java.util.Scanner;

	import com.nucleus.DAO.*;

	public class Rejection {

		public static void main(String[] args) {
			String path=null;
			Scanner sc= new Scanner(System.in);
			CustomerDAOImpl customerdaoimp= new CustomerDAOImpl();
			System.out.println("enter the file location");
			String location= sc.nextLine();
			System.out.println("enter the file name");
			String filename=sc.nextLine();
			
				if(filename.endsWith(".txt"))
				{
					path=location.concat("\\" +filename);	
				}	
				else
				{
					System.out.println("enter the file in correct format");
				}
				System.out.println("Enter the rejection level");
				System.out.println("R.For Record level Rejection");
				System.out.println("F.For File level Rejection");
				String rejection= sc.next();
			customerdaoimp.readFromFile(path,rejection);
			sc.close();
			
		}

	}
		


