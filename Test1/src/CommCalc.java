//This class contains the method(s) for determining the efficiency of Communication based on the Think Mode profiles
//of the team members. The surrounding theory is: if team members have similar profiles, the efficiency of communication
//will be high, with the reverse true if profiles are significantly different. 

//Multiple methods can be used to determine effComm: Similarity Matrix (Correlation), Euclidean distance, or Clustering
//This experiment will attempt to do all three, and determine any differences in the methods versus results of team performance


public class CommCalc {
	//create method for returning a coefficient of communication efficiency
	public double effComm = 0.4; //initialize the variable that represents efficiency of communication
	//**ADD** constraints, effComm must be between 0 and 1
	public double calcEfficiencyComm(ThinkMode[] modeProfileList) {
		//**ADD** calculation here
		return effComm;
	}
}
