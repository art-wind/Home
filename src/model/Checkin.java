package model;

public class Checkin 
{
	public double latitude;
	public double longtitude;
	public int locationId;
	public String time;
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
}
