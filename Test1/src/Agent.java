
public class Agent {

	public ThinkMode modeProfile;
	public Expertise expProfile;
	public int agentFlex;
	//TODO: show that this in, but doesn't go anywhere
	public int	agentLearningSpeed;
	//TODO: show that this in, but doesn't go anywhere

	public Agent(ThinkMode modeProfileInput, Expertise expInput, int flexInput, int learnInput) {
		modeProfile = modeProfileInput;
		expProfile = expInput;
		//Check the adaptInput to verify within the bounds
		if(flexInput <1 || flexInput >5){
			System.err.print("Input must be between 1 and 5");
		}else{
			agentFlex = flexInput;
		}
		//Check the learnInput to verify within the bounds
		if(learnInput <1 || learnInput >5){
			System.err.print("Input must be between 1 and 5");
		}else{
			agentLearningSpeed = learnInput;
		}
	}
}
