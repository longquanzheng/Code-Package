//This class calculates the Efficiency with respect to Expertise, based on the requirements of the task and the
//expertise available on the team. There are 3 types of tasks: Additive, Conjunctive, and Disjunctive, which 
//have different rules for determining if the team has enough expertise to make progress on the task

public class ExpCalc {
	
	
	boolean calcExpertise(TaskType taskTypeInput, Expertise[] expTeamInput, Expertise taskExpInput){
	//boolean result for comparison
		boolean effExp = false;
		if(taskTypeInput == TaskType.Additive) {
		//  add all agents' exp in a - e = sumTeam;
			int j;
			for(j = 0; j < expTeamInput[0].areaExpertise.length; j++){
				int sumTeam = 0;
				for(int i = 0; i < expTeamInput.length; i++){
					sumTeam = sumTeam + expTeamInput[i].areaExpertise[j];
				}
				if(sumTeam >= taskExpInput.areaExpertise[j]){
					continue;
				}else{
					break;
				}
			}
			if (j < expTeamInput[0].areaExpertise.length){
				effExp = false;
			}else{
				effExp = true;
			}
		//	if(sumTeam >= Task Exp){effExp = true}else{effExp = false};
		//}elseif(taskTypeInput == TaskType.Conjunctive){
		//		**min of all agents' exp in a - e must be > TaskExp**
		}else if (taskTypeInput == TaskType.Conjunctive){
			int j;
			for(j = 0; j < expTeamInput[0].areaExpertise.length; j++){
				int i;
				for(i = 0; i < expTeamInput.length; i++){
					if(expTeamInput[i].areaExpertise[j] < taskExpInput.areaExpertise[j]){
						break;
					}else{
						continue;
					}
				}
				if(i < expTeamInput.length){
					break;
				}else{
					continue;
				}
			}
			if (j < expTeamInput[0].areaExpertise.length){
				effExp = false;
			}else{
				effExp = true;
			}
		//	if(minTeam >= Task Exp){effExp = true}else{effExp = 0};
		//  }else{
		//  max of one agent's exp in a - e must be > TaskExp**}
		//  if(maxTeam >= Tasp Exp){effExp = 1}else{effExp = 0};
		}else if (taskTypeInput == TaskType.Disjunctive){
			int j;
			for(j = 0; j < expTeamInput[0].areaExpertise.length; j++){
				int i;
				int  agentsAble = 0;
				for(i = 0; i < expTeamInput.length; i++){
					if(expTeamInput[i].areaExpertise[j] >= taskExpInput.areaExpertise[j]){
						agentsAble++;
					}
				}
				if(agentsAble > 0){
					continue;
				}else{
					break;
				}
			}
			if (j < expTeamInput[0].areaExpertise.length){
				effExp = false;
			}else{
				effExp = true;
			}
		}
		return effExp;
	}
}
