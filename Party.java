import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Party {
	private ArrayList<Person> roster = new ArrayList<Person>();
	
	public Party(){	
	}
	
	public void getFile() throws IOException{
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("enter name of file containing party people: ");
		FileReader readFile = new FileReader(input.readLine());
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
		
		int space = firstLast.indexOf(" ")
	}
}
	
	
