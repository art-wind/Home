package util;

public class MathUtil {
	public static double computeGaussianProbability(double time,double parameter,double mean,double covariance,double coefficient){
		double possibility;
		double max;
		double min;
		double difference;
		if(time >= mean)
		{
			max = time;
			min = mean;
		}
		else
		{
			max = mean;
			min = time;
		}
		if(max - min <= 12)
			difference = Math.pow(max - min, 2);
		else
			difference = Math.pow(24 - max + min, 2);
		possibility = parameter / Math.sqrt(2 * Math.PI * covariance) * Math.pow(Math.E, -1 * Math.pow(coefficient, 2) * difference / 2 / covariance);
		return possibility;
	}
}
