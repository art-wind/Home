package util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import model.Checkin;

public class EMUtils {
	
	public static double computeTimeMean(ArrayList<Checkin> c){
		class CheckinTimeComparator implements Comparator<Checkin>{
			public int compare(Checkin o1, Checkin o2) {
				// TODO Auto-generated method stub
				Checkin ci1 = (Checkin)o1;
				Checkin ci2 = (Checkin)o2;
				double time1 = ci1.getTimeByHour();
				double time2 = ci2.getTimeByHour();
				if(time1<time2){
					return -1;
				}
				else if(time1>time2){
					return 1;
				}
				else{
					return 0;
				}
			}
		}
		Collections.sort(c, new CheckinTimeComparator());
		
		double mean = 0;
		int count = 0;
		for(Checkin checkin:c){
			double time = checkin.getTimeByHour();
			if(count == 0){
				mean = time;
				count ++;
			}
			else{
				count ++;
				if(time - mean > 12){
					double interval = (mean + 24 - time); 
					mean = mean - interval / count;
					
				}
				else{
					double interval = (time - mean); 
					mean = mean + interval / count;
				}
				if(mean < 0){
					mean += 24;
				}
				else if(mean > 24){
					mean -= 24;
				}
			}
		}
		return mean;
	}
	public static double computeTimeCovariance(double mean,ArrayList<Checkin> c){
		int size = c.size();
		double variance = 0;
		double temptime;
		double max;
		double min;
		for (int i = 0; i < size; i++) 
		{
			temptime = Integer.parseInt(c.get(i).time.substring(11, 13)) + (double)Integer.parseInt(c.get(i).time.substring(14, 16))/60;
			if(temptime >= mean)
			{
				max = temptime;
				min = mean;
			}
			else
			{
				max = mean;
				min = temptime;
			}
			if(max - min <= 12)
				variance += Math.pow(max - min, 2);
			else
				variance += Math.pow(24 - max + min, 2);
		}
		variance /= size-1;
		return variance;
	}
	
	
	public static double[] computeLocationMean(ArrayList<Checkin> c){
		int size = c.size();
		double latitude = 0;
		double longtitude = 0;
		for (int i = 0; i < size; i++) 
		{
			latitude += c.get(i).latitude;
			longtitude += c.get(i).longtitude;
		}
		return new double[]{latitude/size,longtitude/size};
	}
//	public static double[][] computeLocationCovariance(ArrayList<Checkin> c){
//		return new double[][]{ {0,0} , {0,0}};
//	}
}
