package utility;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	//DataProvider 1
	@DataProvider(name ="logindata1")
	public String[][] getlogin_data() throws IOException {
		String path = ".\\testData\\login_testdata.xlsx"; //read xl file from the project
		Excel_Utility xlutil = new Excel_Utility(path); //pass the file location to the xl handle class
		
		int totalrows = xlutil.getRowCount("Sheet1");
		int totalcolum = xlutil.getCellCount("Sheet1",1);
		
		
		String logindata[][] = new String[totalrows][totalcolum]; //create two dimensional array for store excel data
		
		for (int r=1;r<=totalrows;r++)//1(xl heading avoid) , read data from xl and store in two dimensional array
		{
			for(int c=0;c<totalcolum;c++)//0 r rows, c column
			{
				logindata[r-1][c]=xlutil.getCellData("Sheet1", r, c);
			}
		}
		return logindata; //return two dimensional array with data
	
	}
	    //DataProvider 2
	
		//DataProvider 3
		
		//DataProvider 4
	
}
