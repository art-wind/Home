package model;

public class Checkin 
{
	public double latitude;
	public double longtitude;
	public int locationId;
	public String time;
	public int state1;
	public int state2;
	
	public Checkin(double latitude, double longtitude, int locationId, String time) 
	{
		this.latitude = latitude;
		this.longtitude = longtitude;
		this.locationId = locationId;
		this.time = time;
	}
	
	public double getTimeByHour(){
		double tmpTime = Integer.parseInt(time.substring(11, 13)) + (double)Integer.parseInt(time.substring(14, 16))/60;
		return tmpTime;
	}
	
//	public boolean setHomeState() {
//		if("home".equals(this.state)){
//			this.state = "home";
//			return true;
//		}
//		else{
//			this.state = "home";
//			return false;
//		}
//		
//	}
//	public boolean setWorkState() {
//		if("work".equals(this.state)){
//			this.state = "work";
//			return true;
//		}
//		else{
//			this.state = "work";
//			return false;
//		}
//	}
	
	
}
