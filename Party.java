import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;;

public class Party {
	private ArrayList<Person> roster = new ArrayList<Person>();
	
	public Party(){	
	}
	
	public void getFile() throws IOException{
		//BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		//System.out.println("enter name of file containing party people: ");
		//FileReader readFile = new FileReader(input.readLine());
		
		System.out.println("Using GuestList.txt to read in party people.");
		FileReader readFile = new FileReader("GuestList.txt");
		BufferedReader inFile = new BufferedReader(readFile);
		
		System.out.println("Reading file...\n");
		
		String inputString = inFile.readLine();
		while (inputString != null){
			this.parseLine(inputString);
			inputString = inFile.readLine(); // try to read one line and store it in a string
		}
		
		inFile.close();
	}
	
	public void parseLine(String inputLine){
		StringTokenizer st = new StringTokenizer(inputLine);
		while (st.hasMoreTokens()){
			String first = st.nextToken();
			String last = st.nextToken();
			String company = st.nextToken();
			String rsvp = st.nextToken();
			
			roster.add(new Person(first, last, company, rsvp));
		}
	}
	

	
	public void sort(){
		int minIndex=0;
		Person temp;
		for (int outer=0; outer<this.roster.size()-1; outer++){
			minIndex=outer;
			for (int inner=outer+1; inner<this.roster.size(); inner++){
				if (this.roster.get(inner).name().compareTo(this.roster.get(minIndex).name()) < 0){
					minIndex=inner;
				}
			}
			
			temp=this.roster.remove(minIndex);
			this.roster.set(outer, temp);
			temp=this.roster.remove(outer+1);
			this.roster.set(minIndex, temp);
		}

	}
	
	public int search(String target){
		int left = 0;
		int right = this.roster.size() -1;
		
		while (left <= right){
			int middle = (left + right) / 2;
			if (target.compareTo(this.roster.get(middle).name())<0){
				right = middle - 1;
			}
			else if (target.compareTo(this.roster.get(middle).name())>0){
				left = middle + 1;
			}
			else{
				return middle;
			}
		}
		return -1;
		
	}
	
	public void guestInfo() throws IOException{
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		String firstLast = input.readLine();
		int space = firstLast.indexOf(" ");
		
		int guestIndex = this.search(firstLast.substring(0, space) + firstLast.substring(space+1));
		
		if (guestIndex==-1){
			System.out.println("Guest is not on list.");
		}
		else {
			System.out.println(this.roster.get(guestIndex));
		}
		
		
	}
	
	public void listGuests() {
		for (Person person : this.roster) {
			System.out.println(person);
		}
	}
	
	public void number(){
		int yes = 0;
		int no = 0;
		int maybe = 0;
		
		for (Person guest : roster){
			if (guest.getRsvp().equals("yes")){
				yes++;
			}
			else if(guest.getRsvp().equals("no")){
				no++;
			}
			else {
				maybe++;
			}
		}
		
		System.out.print(yes + " people are attending the party,\n" + no + " people are not attending the party,\n" + maybe + " people have yet to respond.");	
	}
	
	public void addGuest() throws IOException{
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.print("Enter name of guest (first last): ");
		String firstLast = input.readLine();
		int space = firstLast.indexOf(" ");
		
		System.out.print("Enter name of company: ");
		String company = input.readLine();
		
		System.out.print("Enter rsvp of yes, no, or maybe: ");
		String rsvp = input.readLine();
		
		int guestIndex = this.search(firstLast.substring(0, space) + firstLast.substring(space+1));
		
		if (guestIndex==-1){
			this.roster.add(new Person(firstLast.substring(0, space), firstLast.substring(space+1), company, rsvp));
			System.out.println("Guest has been added.");
		}
		else {
			System.out.println("Guest is already in roster\n" + this.roster.get(guestIndex));
		}
		
	}
	
	public void changeRsvp() throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.print("Enter name of guest (first last) to find colleagues: ");
		String firstLast = input.readLine();
		int space = firstLast.indexOf(" ");
		
		int guestIndex = this.search(firstLast.substring(0, space) + firstLast.substring(space+1));
		
		if (guestIndex==-1){
			System.out.println("Guest does not exist.");
		}
		else {
			System.out.print("Enter new rsvp response (yes, no, ?): ");
			String rsvp = input.readLine();
			
			
			while (!rsvp.equals("yes") || !rsvp.equals("no") || rsvp.equals("?")){
				if (this.roster.get(guestIndex).getRsvp().equals(rsvp)) {
					System.out.print("Guest has already Rsvp'ed with " + rsvp);
				}
				else {
					this.roster.get(guestIndex).setRsvp(rsvp);
					System.out.print("Guest has been changed to " + rsvp);
				}
			}
		}
	}
	
	public void colleagues() throws IOException{
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.print("Enter name of guest (first last) to find colleagues: ");
		String firstLast = input.readLine();
		int space = firstLast.indexOf(" ");
		
		int guestIndex = this.search(firstLast.substring(0, space) + firstLast.substring(space+1));
		
		if (guestIndex==-1){
			System.out.println("Guest does not exist.");
		}
		else {
			int colleagues = 0;
			for (Person person : this.roster) {
				if (!this.roster.get(guestIndex).equals(person))	{
					if (this.roster.get(guestIndex).getCompany().equals(person.getCompany())) {
						colleagues++;
						System.out.println(person);
					}
				}
			}
			if (colleagues == 0) {
				System.out.println("Guest has no colleagues.");
			}
		}
	}
	
	public static char errorTrapCommands() throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.print("Enter G, L, N, A, R, C, or Q as per party commands: ");
		char c = Character.toUpperCase(input.readLine().charAt(0));
		
		while (c != 'G' && c != 'L' && c != 'N' && c != 'A' && c != 'R' && c != 'C' && c != 'Q') {
			System.out.print("Enter only G, L, N, A, R, C, or Q as per party commands: ");
			c = Character.toUpperCase(input.readLine().charAt(0));
		}
		return c;
	}
	
	public void main(String args[]) throws IOException{
		Party partyCentral = new Party();
		partyCentral.getFile();
		
		partyCentral.sort();
		
		char command = Party.errorTrapCommands();
		
		while (command!='Q') {
			if (command == 'G') {
				this.guestInfo();
			}
			else if (command == 'L') {
				this.listGuests();
			}
			else if (command == 'N') {
				this.number();
			}
			else if (command == 'A') {
				this.addGuest();
			}
			else if (command == 'R') {
				this.changeRsvp();
			}
			else {
				this.colleagues();
			}
			command = Party.errorTrapCommands();
		}
		
		System.out.println("GoodBye!");
		
	}
}
	
		