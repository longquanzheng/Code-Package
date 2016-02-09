import java.util.Random;


public class Project2 {
	
	public Task[] taskList;
	public Agent[] projectTeam;
	
	
	//Roll for performance, much like a die in a board game
	public static int randInt(int min, int max){
		Random roll = new Random();
		int randomNum = roll.nextInt(max-min+1)+min;
		return randomNum;
	}
			
	public static void main(String[] args) throws InterruptedException {
		Project2 project = new Project2();
		project.taskList = new Task[10];
		
		//Populate Task List - TASK CONTEXT
		Expertise tmpTaskExp;
		tmpTaskExp = new Expertise(1,2,3,4,5);
		project.taskList[0] = new Task(tmpTaskExp,60,10,TaskType.Additive, 10, 1);
		tmpTaskExp = new Expertise(1,2,3,4,5);
		project.taskList[1] = new Task(tmpTaskExp,60,10,TaskType.Additive, 30, 1);
		tmpTaskExp = new Expertise(1,2,3,4,5);
		project.taskList[2] = new Task(tmpTaskExp,60,10,TaskType.Additive, 30, 1);
		tmpTaskExp = new Expertise(1,2,3,4,5);
		project.taskList[3] = new Task(tmpTaskExp,60,10,TaskType.Additive, 50, 1);
		tmpTaskExp = new Expertise(1,2,3,4,5);
		project.taskList[4] = new Task(tmpTaskExp,60,10,TaskType.Additive, 100, 1);
		tmpTaskExp = new Expertise(1,2,3,4,5);
		project.taskList[5] = new Task(tmpTaskExp,60,10,TaskType.Additive, 15, 1);
		tmpTaskExp = new Expertise(1,2,3,4,5);
		project.taskList[6] = new Task(tmpTaskExp,60,10,TaskType.Additive, 20, 1);
		tmpTaskExp = new Expertise(1,2,3,4,5);
		project.taskList[7] = new Task(tmpTaskExp,60,10,TaskType.Additive, 40, 1);
		tmpTaskExp = new Expertise(1,2,3,4,5);
		project.taskList[8] = new Task(tmpTaskExp,60,10,TaskType.Additive, 5, 1);
		tmpTaskExp = new Expertise(1,2,3,4,5);
		project.taskList[9] = new Task(tmpTaskExp,60,10,TaskType.Additive, 60, 1);
		
		
		int numAgents = 3;
		//**V3 CHANGE** Make this a user input for # of agents
		
		//Populate each agent's vector - AGENT CONTEXT
		//**V3 CHANGE** Make this a user input for ThinkModes and Expertise 
		ThinkMode tmpThinkMode;
		Expertise tmpExpertise;
		project.projectTeam = new Agent[numAgents];
		tmpThinkMode = new ThinkMode(15, 38, 135, 67);
		tmpExpertise = new Expertise(5, 6, 3, 2, 1);
		project.projectTeam[0] = new Agent(tmpThinkMode, tmpExpertise, 4, 1);
		tmpThinkMode = new ThinkMode(100, 60, 58, 12);
		tmpExpertise = new Expertise(10, 0, 0, 6, 8);
		project.projectTeam[1] = new Agent(tmpThinkMode, tmpExpertise, 1, 5);
		tmpThinkMode = new ThinkMode(50, 7, 67, 100);
		tmpExpertise = new Expertise(0, 10, 7, 0, 0);
		project.projectTeam[2] = new Agent(tmpThinkMode, tmpExpertise, 3, 3);
		
		//CREATE INPUTS FOR taskTypeInput, expTeamInput, taskExpInput
		
		//Put all agents' vectors into a matrix
		ThinkMode[] modeProfileList = new ThinkMode[numAgents];
		Expertise[] teamExpertiseList = new Expertise[numAgents];
		for(int i = 0; i < project.projectTeam.length; i++){
			modeProfileList[i] = project.projectTeam[i].modeProfile;
			teamExpertiseList[i] = project.projectTeam[i].expProfile;
		}
		
		//Create new objects for CommCalc and ExpCalc, RETURN coefficients
		CommCalc effComm = new CommCalc();
		ExpCalc effExp = new ExpCalc(); //Make sure I did this right
		
		//for(int iterCounter=0;  iterCounter<)
		//effComm.calcEfficiencyComm();
		//effExp.calcExpertise(taskTypeInput, expTeamInput, taskExpInput);

		//double eff = effComm.calcEfficiencyComm(modeProfileList);
		
		outerwhile: 
			while(true){
			//pick an random number between 0 and NumTasks-1
			int pickNum = randInt(0,project.taskList.length-1);

			//set Count and Index to 0
			int scanCount = 0;
			int scanIndex = pickNum;

			while(scanCount<project.taskList.length){
				if (project.taskList[scanIndex].available() == true){
					int roll = randInt(1,6);
					double effc = effComm.calcEfficiencyComm(modeProfileList);
					boolean effe = effExp.calcExpertise(project.taskList[scanIndex].taskType, teamExpertiseList, project.taskList[scanIndex].taskExp);
					project.taskList[scanIndex].Achieve(roll, effe, effc);
					continue outerwhile;
				}else{
					scanIndex++;
					scanCount++;
					if(scanIndex == project.taskList.length-1){
						scanIndex = 0;
					}
				}
			}
			break;

		}
		System.out.println("Project2 has ended");
		for(int i = 0; i< project.taskList.length; i++){
			System.out.println("Achievement for task" + (i+1) + "is" + project.taskList[i].taskAchievement);
		}
		
	}

}
