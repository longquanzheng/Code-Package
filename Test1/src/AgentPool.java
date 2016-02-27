import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//TODO: Fill in agent pool with organization, complete with expertise distribution****
public class AgentPool {
	
	private Agent[] agentList;
	
	public AgentPool(){
		ThinkMode tmpThinkMode;
		Expertise tmpExpertise;
		agentList = new Agent[3];
		tmpThinkMode = new ThinkMode(15, 38, 135, 67);
		tmpExpertise = new Expertise(5, 6, 3, 2, 1);
		agentList[0] = new Agent(tmpThinkMode, tmpExpertise, 1, 1);
		tmpThinkMode = new ThinkMode(100, 60, 58, 12);
		tmpExpertise = new Expertise(10, 0, 0, 6, 8);
		agentList[1] = new Agent(tmpThinkMode, tmpExpertise, 1, 1);
		tmpThinkMode = new ThinkMode(50, 7, 67, 100);
		tmpExpertise = new Expertise(0, 10, 7, 0, 0);
		agentList[2] = new Agent(tmpThinkMode, tmpExpertise, 1, 1);
		//TODO: add all agents in the organization & change the agentlist number
	}
	
	public static void shuffleArray(Agent[] array) {
		  List<Agent> list = new ArrayList<>();
		  for (Agent i : array) {
		    list.add(i);
		  }

		  Collections.shuffle(list);

		  for (int i = 0; i < list.size(); i++) {
		    array[i] = list.get(i);
		  }    
		}
	
	public Agent[] createTeamRandomly(int numAgents){
		//using shuffling
		shuffleArray(agentList);
		Agent[] returnTeam = new Agent[numAgents];
		for(int i=0; i < numAgents; i++){
			returnTeam[i] = agentList[i];
		}
		return returnTeam;
		//using expertise distribution
		//see code online http://stackoverflow.com/questions/9330394/how-to-pick-an-item-by-its-probability
	}
}
