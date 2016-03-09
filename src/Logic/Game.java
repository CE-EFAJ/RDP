package Logic;
import java.util.Calendar;
import java.util.List;

public class Game {
	
	private String pattern;
	private String time;
	private String creator="";
	private String number="0";
	private int attempts=0;
	private String[] examples= {"","","","",""};
	private String[] bestplayers= {"","","","",""};
	private Regex regex= new Regex();
	
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
		this.time = Calendar.getInstance().getTime().toString();
		this.creator=creator;
		generateExamples();
		this.number=generateNumber();
		
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
	
	public String generateNumber(){
		JsonFile genNumber=new JsonFile("genNumber.txt");
		genNumber.OpenFile("read");
		List<String> number=genNumber.Read();
		if(number.isEmpty()){
			genNumber.CloseFile();
			genNumber.OpenFile("write");
			genNumber.Write("0");
			genNumber.CloseFile();
			return "0";
		}
		else{
			
			String num=number.get(0);
			genNumber.CloseFile();
			int next = (Integer.parseInt(num)) + 1;
			num=Integer.toString(next);
			genNumber.OpenFile("write");
			genNumber.Write(num);
			genNumber.CloseFile();
			
			return num;
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
