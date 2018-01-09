
public class Person {
	private String last;
	private String first;
	private String company;
	private String rsvp;
	
	public Person(String first, String last, String company, String rsvp){
		this.first = first;
		this.last = last;
		this.company = company;
		this.rsvp = rsvp;
	}
	
	
	public String toString(){
		String output = "";
		output += (this.last + ", " + this.first + ", " + this.company + ", " + this.getRsvp());
		return output;
	}

	/**
	 * @return the last
	 */
	public String getLast() {
		return last;
	}

	/**
	 * @return the first
	 */
	public String getFirst() {
		return first;
	}

	/**
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * @return the rsvp
	 */
	public String getRsvp() {
		if (!this.rsvp.equals("yes") && !this.rsvp.equals("no")){
			return "maybe";
		}
		else{
			return this.rsvp;
		}
	}
	
	public String name(){
		return this.last +" "+ this.first;
	}
}
