//This class contains the method(s) for determining the efficiency of Communication based on the Think Mode profiles
//of the team members. The surrounding theory is: if team members have similar profiles, the efficiency of communication
//will be high, with the reverse true if profiles are significantly different. 

//Multiple methods can be used to determine effComm: Similarity Matrix (Correlation), Euclidean distance, or Clustering
//This experiment will attempt to do all three, and determine any differences in the methods versus results of team performance

public class CommCalc {
	// create method for returning a coefficient of communication efficiency
	// public double effComm = 0.4;

	private static final int A = 0;
	private static final int B = 1;
	private static final int C = 2;
	private static final int D = 3;

	// FIRST ALGORITHM: DISTANCE TO CENTROID
	public double calcEfficiencyComm1(ThinkMode[] modeProfileList) {
		ThinkMode teamAverage = new ThinkMode(0, 0, 0, 0);
		// set maxDist equal to the lowest possible number, do not interfere
		// with calculation
		double maxDist = Double.NEGATIVE_INFINITY;
		int maxIdx = -1;

		// Take the averages of all team members in each think mode
		for (int i = 0; i < modeProfileList.length; i++) {
			teamAverage.thinkMode[A] += modeProfileList[i].thinkMode[A];
		}
		teamAverage.thinkMode[A] /= modeProfileList.length;

		for (int i = 0; i < modeProfileList.length; i++) {
			teamAverage.thinkMode[B] = teamAverage.thinkMode[B] + modeProfileList[i].thinkMode[B];
		}
		teamAverage.thinkMode[B] /= modeProfileList.length;

		for (int i = 0; i < modeProfileList.length; i++) {
			teamAverage.thinkMode[C] = teamAverage.thinkMode[C] + modeProfileList[i].thinkMode[C];
		}
		teamAverage.thinkMode[C] /= modeProfileList.length;

		for (int i = 0; i < modeProfileList.length; i++) {
			teamAverage.thinkMode[D] = teamAverage.thinkMode[D] + modeProfileList[i].thinkMode[D];
		}
		teamAverage.thinkMode[D] /= modeProfileList.length;
		// find the outlier agent who is farthest away from the average
		for (int i = 0; i < modeProfileList.length; i++) {
			double dist = dist(teamAverage.thinkMode, modeProfileList[i].thinkMode);
			if (dist > maxDist) {
				maxDist = dist;
				maxIdx = i;
			}
		}
		// calculate average without the outlier
		ThinkMode teamAverageWithoutOutlier = new ThinkMode(0, 0, 0, 0);

		for (int i = 0; i < modeProfileList.length; i++) {
			if (i == maxIdx) {
				continue;
			}
			teamAverageWithoutOutlier.thinkMode[A] += modeProfileList[i].thinkMode[A];
		}
		teamAverageWithoutOutlier.thinkMode[A] /= modeProfileList.length;

		for (int i = 0; i < modeProfileList.length; i++) {
			if (i == maxIdx) {
				continue;
			}
			teamAverageWithoutOutlier.thinkMode[B] = teamAverageWithoutOutlier.thinkMode[B]
					+ modeProfileList[i].thinkMode[B];
		}
		teamAverageWithoutOutlier.thinkMode[B] /= modeProfileList.length;

		for (int i = 0; i < modeProfileList.length; i++) {
			if (i == maxIdx) {
				continue;
			}
			teamAverageWithoutOutlier.thinkMode[C] = teamAverageWithoutOutlier.thinkMode[C]
					+ modeProfileList[i].thinkMode[C];
		}
		teamAverageWithoutOutlier.thinkMode[C] /= modeProfileList.length;

		for (int i = 0; i < modeProfileList.length; i++) {
			if (i == maxIdx) {
				continue;
			}
			teamAverageWithoutOutlier.thinkMode[D] = teamAverageWithoutOutlier.thinkMode[D]
					+ modeProfileList[i].thinkMode[D];
		}
		teamAverageWithoutOutlier.thinkMode[D] /= modeProfileList.length;

		// Calculate the distance between teamAverage and
		// teamAverageWithoutOutlier
		// number divided (150) may change
		double averageDiff = dist(teamAverage.thinkMode, teamAverageWithoutOutlier.thinkMode);
		double effComm = 1 - (averageDiff / 150);
		// Safety limits to make sure it's not out of bounds
		effComm = Math.max(effComm, 0);
		effComm = Math.min(effComm, 1);

		return effComm;
	}

	// Calculate distance
	private double dist(int[] thinkMode, int[] thinkMode2) {
		double dist = Math.pow(thinkMode[A] - thinkMode2[A], 2) + Math.pow(thinkMode[B] - thinkMode2[B], 2)
				+ Math.pow(thinkMode[C] - thinkMode2[C], 2) + Math.pow(thinkMode[D] - thinkMode2[D], 2);
		dist = Math.sqrt(dist);
		return dist;
	}

	// SECOND ALGORITHM: ANGLE BETWEEN TWO VECTORS
	public double calcEfficiencyComm2(ThinkMode[] modeProfileList) {
		ThinkMode teamAverage = new ThinkMode(0, 0, 0, 0);
		// set maxDist equal to the lowest possible number, do not interfere
		// with calculation
		double maxAngle = Double.NEGATIVE_INFINITY;
		int maxIdx = -1;

		// Take the averages of all team members in each think mode
		for (int i = 0; i < modeProfileList.length; i++) {
			teamAverage.thinkMode[A] += modeProfileList[i].thinkMode[A];
		}
		teamAverage.thinkMode[A] /= modeProfileList.length;

		for (int i = 0; i < modeProfileList.length; i++) {
			teamAverage.thinkMode[B] = teamAverage.thinkMode[B] + modeProfileList[i].thinkMode[B];
		}
		teamAverage.thinkMode[B] /= modeProfileList.length;

		for (int i = 0; i < modeProfileList.length; i++) {
			teamAverage.thinkMode[C] = teamAverage.thinkMode[C] + modeProfileList[i].thinkMode[C];
		}
		teamAverage.thinkMode[C] /= modeProfileList.length;

		for (int i = 0; i < modeProfileList.length; i++) {
			teamAverage.thinkMode[D] = teamAverage.thinkMode[D] + modeProfileList[i].thinkMode[D];
		}
		teamAverage.thinkMode[D] /= modeProfileList.length;
		// find the outlier agent who is farthest away from the average
		for (int i = 0; i < modeProfileList.length; i++) {
			double angle = angle(teamAverage.thinkMode, modeProfileList[i].thinkMode);
			if (angle > maxAngle) {
				maxAngle = angle;
				maxIdx = i;
			}
		}
		// calculate average without the outlier
		ThinkMode teamAverageWithoutOutlier = new ThinkMode(0, 0, 0, 0);

		for (int i = 0; i < modeProfileList.length; i++) {
			if (i == maxIdx) {
				continue;
			}
			teamAverageWithoutOutlier.thinkMode[A] += modeProfileList[i].thinkMode[A];
		}
		teamAverageWithoutOutlier.thinkMode[A] /= modeProfileList.length;

		for (int i = 0; i < modeProfileList.length; i++) {
			if (i == maxIdx) {
				continue;
			}
			teamAverageWithoutOutlier.thinkMode[B] = teamAverageWithoutOutlier.thinkMode[B]
					+ modeProfileList[i].thinkMode[B];
		}
		teamAverageWithoutOutlier.thinkMode[B] /= modeProfileList.length;

		for (int i = 0; i < modeProfileList.length; i++) {
			if (i == maxIdx) {
				continue;
			}
			teamAverageWithoutOutlier.thinkMode[C] = teamAverageWithoutOutlier.thinkMode[C]
					+ modeProfileList[i].thinkMode[C];
		}
		teamAverageWithoutOutlier.thinkMode[C] /= modeProfileList.length;

		for (int i = 0; i < modeProfileList.length; i++) {
			if (i == maxIdx) {
				continue;
			}
			teamAverageWithoutOutlier.thinkMode[D] = teamAverageWithoutOutlier.thinkMode[D]
					+ modeProfileList[i].thinkMode[D];
		}
		teamAverageWithoutOutlier.thinkMode[D] /= modeProfileList.length;

		// Calculate the angle between teamAverage and
		// teamAverageWithoutOutlier
		double averageDiff = angle(teamAverage.thinkMode, teamAverageWithoutOutlier.thinkMode);
		double effComm = 1 - (Math.abs(averageDiff)/90);
		// Safety limits to make sure it's not out of bounds
		effComm = Math.max(effComm, 0);
		effComm = Math.min(effComm, 1);

		return effComm;
	}

	private double angle(int[] tm1, int[] tm2) {
		double temp1 = tm1[A]*tm2[A] +tm1[B]*tm2[B]+ tm1[C]*tm2[C] + tm1[D]*tm2[D];
		double temp2 = Math.sqrt(Math.pow(tm1[A],2)+Math.pow(tm1[B],2)+Math.pow(tm1[C],2)+Math.pow(tm1[D],2));
		double temp3 = Math.sqrt(Math.pow(tm2[A],2)+Math.pow(tm2[B],2)+Math.pow(tm2[C],2)+Math.pow(tm2[D],2));
		double angle = ((Math.acos(temp1 / (temp2*temp3)))/Math.PI)*180;
		return angle;
	}
}
