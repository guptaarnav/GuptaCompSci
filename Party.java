/**arnav gupta
 * Program allows the creation of a Party guest list through parsing of a file and 
 * allows commands to be run on the guests to view or change various informations. 
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;;

public class Party {
	private ArrayList<Person> roster = new ArrayList<Person>();
	
	/**
	 * empty constructor for Party.
	 */
	public Party(){	
	}
	
	/**
	 * parses through GuestList.txt to read in Guests and Guest info.
	 * Inputs each guest as an object in the ArrayList this.roster 
	 * @throws IOException
	 */
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
	
	/**
	 * helper function for getFile() that creates each individual object for the ArrayList.
	 * @param inputLine
	 */
	private void parseLine(String inputLine){
		StringTokenizer st = new StringTokenizer(inputLine);
		while (st.hasMoreTokens()){
			String first = st.nextToken();
			String last = st.nextToken();
			String company = st.nextToken();
			String rsvp = st.nextToken();
			
			roster.add(new Person(first, last, company, rsvp));
		}
	}
	

	
	/**
	 * Uses selection sort to sort the ArrayList this.roster by Last_First
	 */
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
	
	/** Uses binary search to find a person by Last_First
	 * @param target name of Person as Last_First
	 * @return index of target Person in this.roster
	 */
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
	
	/**
	 * Prints out a guest by finding it with search() and 
	 * either printing out the guest or that the guest does not exist.
	 * @throws IOException
	 */
	public void guestInfo() throws IOException{
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.print("Enter first name of guest to find: ");
		String first = input.readLine();
		
		System.out.print("Enter last name of guest to find: ");
		String last = input.readLine();
		
		int guestIndex = this.search(last + " " + first);
		
		if (guestIndex==-1){
			System.out.println("Guest is not on list.");
		}
		else {
			System.out.println(this.roster.get(guestIndex));
		}
		
		
	}
	
	/**
	 * Prints out each guest in the roster.
	 */
	public void listGuests() {
		for (Person guest : this.roster) {
			System.out.println(guest);
		}
	}
	
	/**
	 * Prints out information on the number of yes, no, and maybe responses.
	 */
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
	
	/**
	 * Adds a guest alphabetically if they already don't exist,
	 * else informs the user if they exist.
	 * @throws IOException
	 */
	public void addGuest() throws IOException{
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.print("Enter first name of guest: ");
		String first = input.readLine();
		
		System.out.print("Enter last name of guest: ");
		String last = input.readLine();
		
		System.out.print("Enter name of company: ");
		String company = input.readLine();
		
		System.out.print("Enter rsvp of yes, no, or maybe: ");
		String rsvp = input.readLine();
		
		int guestIndex = this.search(last + " " + first);
		
		if (guestIndex==-1){
			for (int i=0; i<this.roster.size(); i++){
				if (this.roster.get(i).name().compareTo(last + " " + first) > 0){
					this.roster.add(i, new Person(first, last, company, rsvp));
					System.out.println("Guest has been added.");
					return;
				}
			}
			this.roster.add(new Person(first, last, company, rsvp));
			System.out.println("Guest has been added.");
		}
		else {
			System.out.println("Guest is already in roster\n" + this.roster.get(guestIndex));
		}
		
	}
	
	/**
	 * changes the rsvp content of a guest by finding the guest
	 * and set the rsvp.
	 * @throws IOException
	 */
	public void changeRsvp() throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.print("Enter first name of guest to find: ");
		String first = input.readLine();
		
		System.out.print("Enter last name of guest to find: ");
		String last = input.readLine();
		
		int guestIndex = this.search(last + " " + first);
		
		
		if (guestIndex==-1){
			System.out.println("Guest does not exist.");
		}
		else {
			System.out.print("Enter new rsvp response (yes, no, ?): ");
			String rsvp = input.readLine();
			
			
			while (!rsvp.equals("yes") || !rsvp.equals("no") || rsvp.equals("?")){
				System.out.print("Enter a correct new rsvp response (yes, no, ?): ");
				rsvp = input.readLine();
			}
		
			if (this.roster.get(guestIndex).getRsvp().equals(rsvp)) {
				System.out.print("Guest has already Rsvp'ed with " + rsvp);
			}
			else {
				this.roster.get(guestIndex).setRsvp(rsvp);
				System.out.print("Guest has been changed to " + rsvp);
			
			}
		}
		
	}
	
	/**prompts the user for a guest and then prints out
	 * the colleagues of the guest based on the company.
	 * @throws IOException
	 */
	public void colleagues() throws IOException{
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.print("Enter first name of guest to find: ");
		String first = input.readLine();
		
		System.out.print("Enter last name of guest to find: ");
		String last = input.readLine();
		
		int guestIndex = this.search(last + " " + first);
		
		if (guestIndex==-1){
			System.out.println("Guest does not exist.");
		}
		else {
			int colleagues = 0;
			for (Person person : this.roster) {
				if (!this.roster.get(guestIndex).equals(person)){
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
	
	/**
	 * error traps given command and loops until correct command is given.
	 * @return correct command.
	 * @throws IOException
	 */
	public static char errorTrapCommands() throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.print("Enter (G)uest information, (L)ist guests, (N)umber, (A)dd guest, (R)svp change, (C)olleagues print, or (Q)uit as per party commands: ");
		char c = Character.toUpperCase(input.readLine().charAt(0));
		
		while (c != 'G' && c != 'L' && c != 'N' && c != 'A' && c != 'R' && c != 'C' && c != 'Q') {
			System.out.print("Enter only G, L, N, A, R, C, or Q as per party commands: ");
			c = Character.toUpperCase(input.readLine().charAt(0));
		}
		return c;
	}
	
	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String args[]) throws IOException{
		Party partyCentral = new Party();
		partyCentral.getFile();
		
		partyCentral.sort();
		
		char command = Party.errorTrapCommands();
		
		//loops command entry and execution
		while (command!='Q') {
			if (command == 'G') {
				partyCentral.guestInfo();
			}
			else if (command == 'L') {
				partyCentral.listGuests();
			}
			else if (command == 'N') {
				partyCentral.number();
			}
			else if (command == 'A') {
				partyCentral.addGuest();
			}
			else if (command == 'R') {
				partyCentral.changeRsvp();
			}
			else {
				partyCentral.colleagues();
			}
			command = Party.errorTrapCommands();
		}
		
		System.out.println("GoodBye!");
		
	}
}
	
