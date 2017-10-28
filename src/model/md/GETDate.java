package model.md;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GETDate {
	  public  String getNowYear() {
		  SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		  return formatter.format(new Date());
		     }

		     public  String getNowMonth() {
		  SimpleDateFormat formatter = new SimpleDateFormat("MM");
		  return formatter.format(new Date());
		     }

		     public  String getNowDay() {
		  SimpleDateFormat formatter = new SimpleDateFormat("dd");
		  return formatter.format(new Date());
		     }
}
