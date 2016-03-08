package Logic;
import java.util.Calendar;

public class Game {
	private Regex regex= new Regex();
	private String pattern;
	private String time;
	private String creator="";
	private String number="0";
	private int attempts=0;
	private String[] examples= {"","","","",""};
	private String[] bestplayers= {"","","","",""};
	
	public Game(String pattern,String time,String creator,String number,int attempts, String[] examples,String[] bestplayers){
		this.pattern=pattern;
		this.time=time;
		this.creator=creator;
		this.number=number;
		this.attempts=attempts;
		this.examples =examples;
		this.bestplayers=bestplayers;
	}
	
	public Game(String creator,String pattern){
		this.pattern=pattern;
		time = Calendar.getInstance().getTime().toString();
		this.creator=creator;
		
	}
	
	public void generateExamples(){
		for(int i=0; i<5;i++){
		examples[i]= regex.Generator(pattern);}
	}
	
	public void changeExample(int index){
		examples[index]=regex.Generator(pattern);
	}
	
	public int validateSolution(String solution){
		int percent=0;
		for(int i=0;i<5;i++){
			if(regex.Validate(solution, examples[i]));{
			percent++;
			}
		}
		return percent;
		
	}
	public void addPlayers(String player){
		for(int i=0;i<5;i++){
			if(bestplayers[i]==""){
				break;
			}
			else{
				bestplayers[i]=player;
			}
		}
		
	}
	
	public void plusAttempts(){
		this.attempts++;
	}
	
	public String getCreator() {
		return creator;
	}

	public String getPattern() {
		return pattern;
	}

	public String getTime() {
		return time;
	}

	public String getNumber() {
		return number;
	}

	public int getAttempts() {
		return attempts;
	}

	public String[] getExamples() {
		return examples;
	}

	public String[] getBestplayers() {
		return bestplayers;
	}

}
