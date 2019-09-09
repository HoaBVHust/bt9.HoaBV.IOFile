package vn.topica.itlab.iofile;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;

public class Device {
	String code;
	String name;
	String owner;
	Date inputDate;
	Integer warrantyYear;
	
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	public Device(String string) {
		String[] strings = string.split(",");
		for (int i = 0; i < strings.length; i++) {
			this.code=strings[0];
			this.name=strings[1];
			this.owner=strings[2];
			try {
				this.inputDate=simpleDateFormat.parse(strings[3]);
			} catch (ParseException e) {
				this.inputDate=new Date();
				e.printStackTrace();
			}
			this.warrantyYear=Integer.parseInt(strings[4]);
		}
	}
	public static Comparator<Device> warrantyYearIncrease = new Comparator<Device>() {
		public int compare(Device o1, Device o2) {
			return o1.warrantyYear.compareTo(o2.warrantyYear);
		};
	};
	public static Comparator<Device> warrantyYearDecrease = new Comparator<Device>() {
		public int compare(Device o1, Device o2) {
			return o2.warrantyYear.compareTo(o1.warrantyYear);
		};
	};
	public static Comparator<Device> inputDateAndWanrranty = new Comparator<Device>() {
		public int compare(Device o1, Device o2) {
			if(o1.inputDate.compareTo(o2.inputDate)==0)
				return o1.warrantyYear.compareTo(o2.warrantyYear);
			else
				return o1.inputDate.compareTo(o2.inputDate);
		};
	};
	//standard owner
	public void StandardOwner() {	
		String[] words = ProcessString.stringToWords(owner);
		for (int i = 0; i < words.length; i++) {
			words[i]=ProcessString.toUpperFirstChar(words[i]);
		}
		owner=String.join(" ", words);
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return code + "," + name + "," + owner + "," + simpleDateFormat.format(inputDate) + "," + warrantyYear;
	}
}
