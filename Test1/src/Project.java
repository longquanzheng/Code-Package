import java.util.Arrays;
import java.util.Random;

public class Project {

	public static final String projectName = "p1";

	public Task[] taskList;
	public Agent[] projectTeam;

	// public double effc;

	// Roll for performance, much like a die in a board game
	public static int randInt(int min, int max) {
		Random roll = new Random();
		int randomNum = roll.nextInt(max - min + 1) + min;
		return randomNum;
	}

	public static void main(String[] args) throws InterruptedException {
		Project project = new Project();
		project.taskList = new Task[10];

		for (int prj_itr_id = 0; prj_itr_id < 200; prj_itr_id++) {
			System.out.println("Project 1 trial " + prj_itr_id);

			// Populate Task List - TASK CONTEXT
			// **TODO** User to change project list here to design tasks in the
			// project
			Expertise tmpTaskExp;
			tmpTaskExp = new Expertise(5, 0, 5, 4, 1);
			project.taskList[0] = new Task(tmpTaskExp, 100, TaskType.Additive, 10);
			tmpTaskExp = new Expertise(0, 0, 0, 3, 3);
			project.taskList[1] = new Task(tmpTaskExp, 100, TaskType.Conjunctive, 30);
			tmpTaskExp = new Expertise(1, 1, 1, 1, 1);
			project.taskList[2] = new Task(tmpTaskExp, 100, TaskType.Disjunctive, 30);
			tmpTaskExp = new Expertise(2, 2, 3, 4, 1);
			project.taskList[3] = new Task(tmpTaskExp, 100, TaskType.Additive, 50);
			tmpTaskExp = new Expertise(1, 2, 3, 4, 5);
			project.taskList[4] = new Task(tmpTaskExp, 100, TaskType.Conjunctive, 10);
			tmpTaskExp = new Expertise(1, 2, 3, 4, 5);
			project.taskList[5] = new Task(tmpTaskExp, 1000, TaskType.Additive, 15);
			tmpTaskExp = new Expertise(1, 2, 3, 4, 5);
			project.taskList[6] = new Task(tmpTaskExp, 1000, TaskType.Additive, 20);
			tmpTaskExp = new Expertise(1, 2, 3, 4, 5);
			project.taskList[7] = new Task(tmpTaskExp, 1000, TaskType.Disjunctive, 40);
			tmpTaskExp = new Expertise(1, 2, 3, 4, 5);
			project.taskList[8] = new Task(tmpTaskExp, 1000, TaskType.Additive, 5);
			tmpTaskExp = new Expertise(1, 2, 3, 4, 5);
			project.taskList[9] = new Task(tmpTaskExp, 100, TaskType.Additive, 60);

			int numAgents = 3;
			// **TODO** This is a user input for # of agents

			// Populate each agent's vector - Randomly pulling from the
			// organization or "pool"
			AgentPool agentPool = new AgentPool();
			project.projectTeam = agentPool.createTeamRandomly(numAgents);

			// Put all agents' information into matrices for 1) Think Mode and
			// 2) Expertise
			ThinkMode[] modeProfileList = new ThinkMode[numAgents];
			Expertise[] teamExpertiseList = new Expertise[numAgents];

			for (int i = 0; i < project.projectTeam.length; i++) {
				modeProfileList[i] = project.projectTeam[i].modeProfile;
				teamExpertiseList[i] = project.projectTeam[i].expProfile;
			}

			System.out.println("Team Matrix");
			System.out.println("A" + "\t" + "B" + "\t" + "C" + "\t" + "D");
			
			
			//TODO: Print the Mode Profile for each member chosen
			//I TRIED BOTH METHODS BELOW...I EITHER GET HASH CODE OR ERROR
			// fix by Q.Long
			for (int i = 0; i < modeProfileList.length; i++) {
				System.out.println(modeProfileList[i]);
			}

			//TODO: Print the Exp Profile for each member chosen
			//I TRIED BOTH METHODS BELOW...I EITHER GET HASH CODE OR ERROR
			for (int i = 0; i < modeProfileList.length; i++) {
				System.out.println(teamExpertiseList[i]);
			}

			// Create new objects for CommCalc and ExpCalc, RETURN coefficients
			CommCalc effComm = new CommCalc();
			ExpCalc effExp = new ExpCalc();
			double effc = effComm.calcEfficiencyComm1(modeProfileList);
			System.out.println("Team Efficiency = " + effc);

			outerwhile: while (true) {
				// pick an random number between 0 and NumTasks-1
				int pickNum = randInt(0, project.taskList.length - 1);

				// set Count and Index to 0
				int scanCount = 0;
				int scanIndex = pickNum;

				while (scanCount < project.taskList.length) {
					if (project.taskList[scanIndex].available() == true) {
						int roll = randInt(1, 6);
						// TWO different algorithms are available: DIST (1) and
						// ANGLE (2)

						boolean effe = effExp.calcExpertise(project.taskList[scanIndex].taskType, teamExpertiseList,
								project.taskList[scanIndex].taskExp);
						project.taskList[scanIndex].Achieve(roll, effe, effc);

						/*
						 * System.out.println((scanIndex + 1) + "," + roll + ","
						 * + project.taskList[scanIndex].iterationsCompleted +
						 * "," + project.taskList[scanIndex].taskAchievement +
						 * effc);
						 */
						// This file saves the play-by-play of each project by
						// iteration of tasks
						// make sure to remove .txt file to not keep appending;
						// it will create a new file automatically
						Output.write2File(projectName, "iteration_achievement" + ".txt", "" + prj_itr_id + "\t"
								+ scanIndex);
						for (int i = 0; i < project.taskList.length; i++) {
							Output.write2File(projectName, "iteration_achievement" + ".txt", "\t" + roll + "\t"
									+ project.taskList[i].iterationsCompleted + "\t"
									+ project.taskList[i].taskAchievement + "\t");
						}
						Output.write2File(projectName, "iteration_achievement" + ".txt", "\n");

						continue outerwhile;
					} else {
						scanIndex++;
						scanCount++;
						if (scanIndex == project.taskList.length) {
							scanIndex = 0;
						}
					}
				}
				break;
			}
			// for each Project Trial, print the Trial ID, Task #, end
			// achievement, min achievement, and number of iterations
			for (int i = 0; i < project.taskList.length; i++) {
				Output.write2File(projectName, "final_achievement" + ".txt", "\t" + prj_itr_id + "\t" + (i + 1) + "\t"
						+ project.taskList[i].minAchievement + "\t" + project.taskList[i].taskAchievement + "\t"
						+ project.taskList[i].iterationsCompleted + "\n");
			}
			// for each Project, print the Team info: effComm, matrix of
			// thinking modes, matrix of expertise
			Output.write2File(projectName, "team_info" + ".txt", "\t" + "EffComm: " + effc + "\n" + "Team Matrix"
					+ "\n" + "\t" + "A" + "\t" + "B" + "\t" + "C" + "\t" + "D" + "\n");
			for (int i = 0; i < project.projectTeam.length; i++) {
				Output.write2File(projectName, "team_info" + ".txt", "\t" + project.projectTeam[i].modeProfile + "\n");
			}
		}// End for loop to run project x number of trials, determined at
			// beginning of loop
			// PROJECT TRIALS HAVE ENDED

	}// end Main
}// end Project Class
