
public class Participant {
	private int id;
	private String name;
	
	public Participant(String name,int id){
		this.id=id;
		this.name=name;
	}
	
	public void setName(String name){
		this.name=name;
	}
	
	public int getId(){
		return this.id;
	}
	public String getName(){
		return this.name;
	}
	public String toString(){
		return name;
	}
	public void advance(Round round){
		round.addParticipant(this);
	}
}
