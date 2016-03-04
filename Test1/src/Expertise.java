//This class manages the inputs of Expertise (5 areas denoted as a through e) into an array for Agents and Tasks,
//where Agents have expertise, and Tasks require a minimum expertise


public class Expertise {
	public int[] areaExpertise;
	//each agent has a set of expertise, different areas represented by a - e
	public Expertise(int a, int b, int c, int d, int e){
		areaExpertise = new int[5];
		if(a < 0 || a > 10){
			System.err.println("Value for a must be between 0 and 10");
		}else{	
			areaExpertise[0] = a;
		}
		
		if(b < 0 || b > 10){
			System.err.println("Value for b must be between 0 and 10");
		}else{	
		areaExpertise[1] = b;
		}
		
		if(c < 0 || c > 10){
			System.err.println("Value for c must be between 0 and 10");
		}else{	
		areaExpertise[2] = c;
		}
		
		if(d < 0 || d > 10){
			System.err.println("Value for d must be between 0 and 10");
		}else{	
		areaExpertise[3] = d;
		}
		
		if(e < 0 || e > 10){
			System.err.println("Value for e must be between 0 and 10");
		}else{	
		areaExpertise[4] = e;
		}
		//Expertise must be between 1 and 10
	}
	
	public String toString(){
		return "Expertise is :"+areaExpertise[0]+ "\t"+areaExpertise[1]+ "\t"+areaExpertise[2]+ "\t"+areaExpertise[3];
	}
}
