package vn.topica.itlab.iofile;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	ListDevice listDevice =new ListDevice();
        try {
        	/*
        	 * 1.1 read file, sort by warrantyYrear increase and write file
        	 */
        	//read file
        	FileInputStream fileInputStream = new FileInputStream("input1.txt");
        	Scanner scanner = new Scanner(fileInputStream);
        	String string;
        	while(scanner.hasNext()) {
    			string=scanner.nextLine();
    			System.out.println(string);
    			listDevice.add(new Device(string));
    		}
        	scanner.close();
        	fileInputStream.close();
        	//sort
        	listDevice.sort(Device.warrantyYearIncrease);
        	//write file
        	FileOutputStream fileOutputStream = new FileOutputStream("output1.txt");
        	fileOutputStream.write(listDevice.toString().getBytes());
        	
        	/*
        	 * 1.2 Standard owner and 
        	 * output list device that is sorted by warrantyYrear decrease
        	 */
        	for (Device device : listDevice) {
    			device.StandardOwner();
    		}
            listDevice.sort(Device.warrantyYearDecrease);
            fileOutputStream.write("###\n".getBytes());
            fileOutputStream.write(listDevice.toString().getBytes());
            
            /*
             * 1.3 Select device that have code is "TOPICA" 
             * and inputDate from 31/10/2018 to 31/10/2019
             */
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date startDate=null,endDate=null;
            try {
				startDate =simpleDateFormat.parse("31/10/2018");
				endDate = simpleDateFormat.parse("31/10/2019");
			} catch (ParseException e) {
				e.printStackTrace();
			}
            ListDevice listDevice3 =new ListDevice();          
            for (Device device : listDevice) {
				if(device.code.contains("TOPICA")
					&& device.inputDate.after(startDate)
					&& device.inputDate.before(endDate)) {
					listDevice3.add(device);
				}	
			}
            listDevice3.sort(Device.inputDateAndWanrranty);
            fileOutputStream.write("###\n".getBytes());
            fileOutputStream.write(listDevice3.toString().getBytes());
            
            /*
             * 1.4 find words that appear most often in the owner
             */
            ArrayList<String> listAllWords = new ArrayList<String>();
            for (Device device : listDevice) {
				for (String word : ProcessString.stringToWords(device.owner)) {
					listAllWords.add(word);
				}
			}
            ArrayList<String> listWordMax = new ArrayList<String>();
            listWordMax=ProcessString.findWordAppearMost(listAllWords);
            fileOutputStream.write("###\n".getBytes());
            for (String word : listWordMax) {
				fileOutputStream.write((ProcessString.toUpperFirstChar(word)+"\n").getBytes());
			}
            
            fileOutputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}   
    }
}
