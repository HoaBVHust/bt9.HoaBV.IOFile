package vn.topica.itlab.iofile;

import java.util.ArrayList;

public class ListDevice extends ArrayList<Device> {

	@Override
	public String toString() {
		String string="";
		for (Device device : this) {
			string = string + device.toString()+"\n";
		}
		return string;
	}
}
