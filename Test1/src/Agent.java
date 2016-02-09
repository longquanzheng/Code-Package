
public class Agent {

	public ThinkMode modeProfile;
	public Expertise expProfile;
	public int agentAdapt;
	//**ADD** How does adaptability change the equation?
	public int	agentLearningSpeed;
	//**ADD** How does learning speed change the equation?

	public Agent(ThinkMode modeProfileInput, Expertise expInput, int adaptInput, int learnInput) {
		modeProfile = modeProfileInput;
		expProfile = expInput;
		//Check the adaptInput to verify within the bounds
		if(adaptInput <1 || adaptInput >5){
			System.err.print("Input must be between 1 and 5");
		}else{
			agentAdapt = adaptInput;
		}
		//Check the learnInput to verify within the bounds
		if(learnInput <1 || learnInput >5){
			System.err.print("Input must be between 1 and 5");
		}else{
			agentLearningSpeed = learnInput;
		}
	}
}
