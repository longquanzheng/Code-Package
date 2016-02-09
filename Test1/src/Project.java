import java.util.Random;
//import java.math.BigDecimal;

public class Project {
	
	public Task[] taskList;
	public Agent[] projectTeam;
	
	
	//Roll for performance, much like a die in a board game
	public static int randInt(int min, int max){
		Random roll = new Random();
		int randomNum = roll.nextInt(max-min+1)+min;
		return randomNum;
	}
			
	public static void main(String[] args) throws InterruptedException {
		//System.out.println("Project1 has started.");
		Project project = new Project();
		project.taskList = new Task[10];
	//project.achievementList = new Task[10];
	for(int j = 0; j<10; j++){
		System.out.println("Project 1 trial "+j);
			System.out.println("Task, Die roll, Iters, Score");
			
			
		//Populate Task List - TASK CONTEXT
		System.out.println("Building Task List");
		Expertise tmpTaskExp;
		tmpTaskExp = new Expertise(5,0, 5, 4, 1);
		project.taskList[0] = new Task(tmpTaskExp, 100,100,TaskType.Additive, 10, 1);
		tmpTaskExp = new Expertise(0, 0, 0, 3, 3);
		project.taskList[1] = new Task(tmpTaskExp,100,100,TaskType.Conjunctive, 30, 5);
		tmpTaskExp = new Expertise(1, 1, 1, 1, 1);
		project.taskList[2] = new Task(tmpTaskExp,100,100,TaskType.Disjunctive, 30, 1);
		tmpTaskExp = new Expertise(2, 2, 3, 4, 1);
		project.taskList[3] = new Task(tmpTaskExp,100,100,TaskType.Additive, 50, 1);
		tmpTaskExp = new Expertise(1,2,3,4,5);
		project.taskList[4] = new Task(tmpTaskExp,100,100,TaskType.Conjunctive, 10, 1);
		tmpTaskExp = new Expertise(1,2,3,4,5);
		project.taskList[5] = new Task(tmpTaskExp,1000,1000,TaskType.Additive, 15, 1);
		tmpTaskExp = new Expertise(1,2,3,4,5);
		project.taskList[6] = new Task(tmpTaskExp,1000,1000,TaskType.Additive, 20, 1);
		tmpTaskExp = new Expertise(1,2,3,4,5);
		project.taskList[7] = new Task(tmpTaskExp,6000,1000,TaskType.Disjunctive, 40, 1);
		tmpTaskExp = new Expertise(1,2,3,4,5);
		project.taskList[8] = new Task(tmpTaskExp,6000,1000,TaskType.Additive, 5, 1);
		tmpTaskExp = new Expertise(1,2,3,4,5);
		project.taskList[9] = new Task(tmpTaskExp,300,100,TaskType.Additive, 60, 1);
		
		
		int numAgents = 3;
		//**V3 CHANGE** Make this a user input for # of agents
		
		//Populate each agent's vector - AGENT CONTEXT
		//**V3 CHANGE** Make this a user input for ThinkModes and Expertise 
		System.out.println("Building Project Team");
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
		ExpCalc effExp = new ExpCalc(); 
		

		
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
					//double toBeTruncated = new Double("3.5789055");
					//double truncatedDouble = new BigDecimal(taskAchievement).setScale(3, BigDecimal.ROUND_HALF_UP)
					System.out.println((scanIndex+1) +","+ roll+ "," +project.taskList[scanIndex].iterationsCompleted +"," + project.taskList[scanIndex].taskAchievement);
					continue outerwhile;
				}else{
					//System.out.println("Task "+(scanIndex+1) + " is not available because "+ project.taskList[scanIndex].taskStatus);
					scanIndex++;
					scanCount++;
					if(scanIndex == project.taskList.length){//I removed -1 from this equation because 10 would never finish
						scanIndex = 0;
					}
				}
			}
			break;
			}
		}//End for loop to run project 10x
		
		System.out.println("Project1 has ended");
		/*System.out.println("Task, Ach, Iters, ItersAllowed");
		for(int i = 0; i< project.taskList.length; i++){
			System.out.println((i+1) +"	"+ project.taskList[i].taskAchievement + "    "+ project.taskList[i].iterationsCompleted +"	" + project.taskList[i].iterationsAllowed);
		}*/
		
	}

}
