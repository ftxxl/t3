import java.util.ArrayList;


public class Table {
	private ArrayList<Participant> participants;
	private int nr;
	
	public Table(int nr){
		this.nr=nr;
		this.participants=new ArrayList<Participant>();
	}
	
	public Table(int nr, ArrayList<Participant> participants){
		this.nr=nr;
		this.participants=participants;
	}
	public void addParticipant(Participant p){
		this.participants.add(p);
	}
	public ArrayList<Participant> getParticipants(){
		return this.participants;
	}
	public int getNr(){
		return this.nr;
	}
	
	public String toString(){
		StringBuilder build = new StringBuilder();
		build.append("Table "+this.nr+": ");
		for(Participant p: this.participants){
			build.append(p.toString()+", ");
		}
		return build.toString();
	}
}
