
//This class shows all attributes belonging to the 10 tasks in the project. Comments below define each attribute.
//The Achievement method is also in this class, which calculates how much progress the team will make in a given
//attempt on the task. As one might suspect, Achievement is heavily influenced by the team's ability to communicate
//effectively, as well as the available expertise on the team. The "roll" can be thought of as a die roll, which
//introduces some probability for how much the team might achieve during this attempt.

public class Task {
	public Expertise taskExp;
	public String taskStatus;
	// private long startTime;
	public int iterationsCompleted;
	// public long timeAllowedHours;
	public int iterationsAllowed;
	public double taskAchievement;
	public double minAchievement;
	public TaskType taskType;

	// private static final int secondsPerHour = 1;
	// private int hoursPerIteration;

	public Task(Expertise taskExpInput, int iterationsInput, TaskType taskTypeInput, double minAchievementInput) {
		taskExp = taskExpInput; // Expertise required by the Task
		iterationsCompleted = 0; // How many attempts it took for the team to
									// complete the task
		taskAchievement = 0; // How much the team has achieved toward the task's
								// completion
		taskStatus = "Not Started";
		// timeAllowedHours = timeLimitInput; //Time pressure
		iterationsAllowed = iterationsInput; // Attempt pressure
		taskType = taskTypeInput; // Additive, Conjunctive, Disjunctive - See
									// enumeration for TaskType
		minAchievement = minAchievementInput; // How much achievement is
												// required for the task to be
												// considered "Complete"
		// startTime = System.currentTimeMillis();
		// hoursPerIteration = hoursPerIterationInput;
	}

	/*
	 * public int elapsedHours(){ return (int) ((System.currentTimeMillis() -
	 * startTime)/1000/secondsPerHour); }
	 */

	public boolean available() {
		// if(taskAchievement>= minAchievement ||
		// iterationsCompleted>=iterationsAllowed || elapsedHours() >=
		// timeAllowedHours){
		if (taskAchievement >= minAchievement || iterationsCompleted >= iterationsAllowed) {
			return false;
		} else {
			return true;
		}
	}

	public void Achieve(int roll, boolean effExp, double effComm) throws InterruptedException {
		// Parse Boolean into Integer for future calculation
		int effExpInt = (effExp) ? 1 : 0;
		// One iteration of a task (attempt) takes x hours
		// Thread.sleep(hoursPerIteration * 1000/secondsPerHour);
		double newAchievement = roll * effExpInt * effComm;
		taskAchievement = taskAchievement + newAchievement;
		iterationsCompleted++;
	}
}
