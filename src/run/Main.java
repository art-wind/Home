package run;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import util.ArrayListUtil;
import util.EMUtils;
import util.MathUtil;
import model.Checkin;

public class Main {
	static ArrayList<Checkin>checkins = null;
	static ArrayList<Checkin>home = null;
	static ArrayList<Checkin>work = null;
	static double Ph;
	static double Pw;
	public static void main(String[]args){
		double shreshold = 0.5;
		readCheckIn("./data/Amercia.txt");
		home = new ArrayList<Checkin>();
		work = new ArrayList<Checkin>();
		//Feed
		double tmp;
		for(Checkin c:checkins){
			tmp = Math.random();
			if(tmp>shreshold){
				c.state1 = 0;
				home.add(c);
			}
			else{
				c.state1 = 1;
				work.add(c);
			}
		}
		Ph = (double)home.size() / checkins.size();
		Pw = (double)work.size() / checkins.size();
		double homeMean,workMean;
		double homeCo,workCo;
		homeMean = EMUtils.computeTimeMean(home);
		workMean = EMUtils.computeTimeMean(work);
		homeCo = EMUtils.computeTimeCovariance(homeMean,home);
		workCo = EMUtils.computeTimeCovariance(workMean,work);
		
		
		//M-step
		double homeProbability;
		double workProbability;
		double time;
		double size = checkins.size();
		double total;
		boolean isFirst = true;
		while(1<2){
			total = 0;
			home = new ArrayList<Checkin>();
			work = new ArrayList<Checkin>();
//			ArrayListUtil.clearArray(home);
//			ArrayListUtil.clearArray(work);
			for(int i=0;i<size;i++){
				time = Integer.parseInt(checkins.get(i).time.substring(11, 13)) + (double)Integer.parseInt(checkins.get(i).time.substring(14, 16))/60;
				homeProbability = MathUtil.computeGaussianProbability(time,Ph,homeMean, homeCo, Math.PI/12);
				workProbability = MathUtil.computeGaussianProbability(time,Pw,workMean, workMean, Math.PI/12);
				if(isFirst){
					isFirst = false;
					checkins.get(i).state1 = checkins.get(i).state2;
				}
				if(homeProbability>workProbability){
					checkins.get(i).state2 = 0;
					home.add(checkins.get(i));
				}
				else{
					checkins.get(i).state2 = 1;
					work.add(checkins.get(i));
				}
			}
			homeMean = EMUtils.computeTimeMean(home);
			workMean = EMUtils.computeTimeMean(work);
			homeCo = EMUtils.computeTimeCovariance(homeMean,home);
			workCo = EMUtils.computeTimeCovariance(workMean,work);
			for (int i = 0; i < size; i++) {
				if(checkins.get(i).state1 != checkins.get(i).state2)
					total++;
			}
			//The convergence condition
			if(total/size < 0.2){
				//Output the home and work
				double[]coreHomelocation = EMUtils.computeLocationMean(home);
				double[]coreWorklocation = EMUtils.computeLocationMean(work);
				break;
			}
		}
		
		
		
	}
	public static void readCheckIn(String file)
	{
		BufferedReader br = null;
		try 
		{
			br = new BufferedReader(new FileReader(file));
			String line = null;
			String[] arrs=null;
			int personIndex;
			int reading = 0;
			while ((line = br.readLine()) != null) 
			{
				arrs=line.split("	");
				personIndex = Integer.parseInt(arrs[0]);
				if(personIndex == 2)
				{
					reading = 1;
					checkins.add(new Checkin(Double.parseDouble(arrs[2]),Double.parseDouble(arrs[3]),Integer.parseInt(arrs[4]),arrs[1]));
				}
				else 
				{
					if(reading == 1)
						break;
				}
			}
			br.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
