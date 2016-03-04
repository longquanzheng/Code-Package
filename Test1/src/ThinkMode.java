
//This class manages inputs for profile values of each agent regarding their Think Mode profiles

public class ThinkMode {
	public int[] thinkMode;
// each agent will be have a profile of integers in A, B, C, D
	public ThinkMode(int A, int B, int C, int D) {
		thinkMode = new int[4];
		if(A < 0 || A > 150){
			System.err.println("Value for A must be between 0 and 150");
		}else{	
			thinkMode[0] = A;
		}
		if(B < 0 || B > 150){
			System.err.println("Value for B must be between 0 and 150");
		}else{	
			thinkMode[1] = B;
		}		
		if(C < 0 || C > 150){
			System.err.println("Value for C must be between 0 and 150");
		}else{	
			thinkMode[2] = C;
		}		
		if(D < 0 || D > 150){
			System.err.println("Value for D must be between 0 and 150");
		}else{	
			thinkMode[3] = D;
		}
		//Constraint: These integers must be between 0 and 150
	}
	
	public String toString(){
		return "ThinkMode is :"+thinkMode[0]+ "\t"+thinkMode[1]+ "\t"+thinkMode[2]+ "\t"+thinkMode[3];
	}
	
}
