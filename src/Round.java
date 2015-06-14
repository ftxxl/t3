import java.awt.Component;
import java.util.ArrayList;
import java.util.Collections;

public class Round extends Component {
	private ArrayList<Participant> participants;
	private ArrayList<Table> tables;
	private int nr;
	private String topic;

	public Round(int nr) {
		this.nr = nr;
		this.topic = "Runde " + nr;
		this.tables = new ArrayList<Table>();
		this.participants = new ArrayList<Participant>();
	}

	public Round(int nr, String topic) {
		this.nr = nr;
		this.topic = topic;
		this.tables = new ArrayList<Table>();
		this.participants = new ArrayList<Participant>();
	}

	public Round(int nr, ArrayList<Participant> participants) {
		this.nr = nr;
		this.topic = "Runde " + nr;
		this.tables = new ArrayList<Table>();
		this.participants = participants;
	}

	public Round(int nr, String topic, ArrayList<Participant> participants) {
		this.nr = nr;
		this.topic = topic;
		this.tables = new ArrayList<Table>();
		this.participants = participants;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public void setNr(int nr) {
		this.nr = nr;
	}

	public void addParticipant(Participant participant) {
		this.participants.add(participant);
	}

	public void killParticipant(Participant participant) {
		this.participants.remove(participant);
	}

	public String getTopic() {
		return this.topic;
	}

	public int getNr() {
		return this.nr;
	}

	public ArrayList<Participant> getParticipants() {
		return this.participants;
	}

	public ArrayList<Table> getTables() {
		return this.tables;
	}

	public void sitDown(int maxTable) {
		Collections.shuffle(participants);
		this.tables = calcTables(participants, maxTable);
	}

	private ArrayList<Table> calcTables(ArrayList<Participant> participants, int maxTable) {
		int cnt = (participants.size() - 1) / maxTable;
		ArrayList<Table> tables = new ArrayList<Table>();
		for (int i = 1; i < cnt + 2; i++) {
			tables.add(new Table(i));
		}
		int i = 0;
		for (Participant p : participants) {
			tables.get(i % (cnt + 1)).addParticipant(p);
			i++;
		}
		return tables;
	}

	public String toString() {
		StringBuilder build = new StringBuilder();
		build.append(topic + "\n");
		if (this.tables.size() > 0) {
			for (Table t : this.tables) {
				build.append(t.toString() + "\n");
			}
		} else {
			for (Participant p : this.participants) {
				build.append(p.toString() + "\n");
			}
		}
		return this.topic;
	}
}
