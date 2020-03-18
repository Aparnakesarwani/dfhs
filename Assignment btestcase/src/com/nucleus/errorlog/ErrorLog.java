package com.nucleus.errorlog;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

	public class ErrorLog {
		public void SaveToFile(String line)
		{
			
			
			FileWriter filewriter = null;
			PrintWriter printwriter = null;
			try {
				filewriter = new FileWriter("Error1.txt",true);
				printwriter= new PrintWriter(filewriter);
				printwriter.write(line +"\n");
				printwriter.flush();
				System.out.println("file created");
			} catch (IOException e) {

				e.printStackTrace();
			}
			
			finally
			{
				try
				{
					filewriter.close();
					printwriter.close();
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
				
			}
		}
		public void CopyFromFile(String path)
		{
			FileReader fileReader = null;
			BufferedReader bufferReader = null;
			try {
				fileReader = new FileReader(path);
				bufferReader = new BufferedReader(fileReader);
				try {
					String l = bufferReader.readLine();
					while(l != null)
					{
						SaveToFile(l);
						l = bufferReader.readLine();
						
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			finally
			{
				try {
					fileReader.close();
					bufferReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}



