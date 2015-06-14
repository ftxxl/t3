
public class Test {
	static String[] test= {"Mal","sehen","ob","das","klappen","tut","Mal","sehen","ob","das","klappen","tut"};
	static Round testround, testround2;
	
	public static void main(String[] args){
		testround = new Round(1, "Testrunde");
		int i=0;
		for(String p : test){
			i++;
			testround.addParticipant(new Participant(p,i));
		}
		testround.sitDown(5);
		System.out.println(testround.toString());
		testround2=new Round(2, "Testrunde 2");
		for(Participant p:testround.getParticipants()){
			if(p.getId()==11){
				p.advance(testround2);
			}
		}
		System.out.println(testround2.toString());
	}
}
