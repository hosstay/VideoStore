package videoStore;

import java.util.ArrayList;

public class Customer {
	private String name;
	private int freqRenterPts;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private ArrayList<Rental> rentals = new ArrayList();
	
	private static final String OUT = "out";
	private static final String IN = "in";
	private static final String PAY = "pay";
	private static final int frequentRenterPtsPerMovie = 5;

	public Customer(String name){
		this.name = name;
		freqRenterPts = 0;
		System.out.println("New customer " + this.name + " created.");
	}
	
	public String getName(){
		return name;
	}
	
	public int getFreqRenterPts(){
		return freqRenterPts;
	}
	
	public String getStatus(Movie m){
		for(int i = 0; i < rentals.size(); i++){
			if(rentals.get(i).getMovie().getTitle().equals(m.getTitle())){
				return rentals.get(i).getStatus();
			}
		}
		return "This customer hasn't rented that movie yet.";
	}
	
	public ArrayList<Rental> getRentals(){
		return rentals;
	}
	
	public void checkOutMovie(Movie m){
		boolean added = false;
		
		if(rentals.size() == 0){
			rentals.add(new Rental(m));
			System.out.println("Customer " + name + " checked out " + m.getTitle() +".");
			freqRenterPts += frequentRenterPtsPerMovie;
			added = true;
		}else{
			for(int i = 0; i < rentals.size(); i++){
				if(rentals.get(i).getMovie().getTitle().equals(m.getTitle())){
					if(rentals.get(i).getStatus().equals(IN)){
						rentals.get(i).setTimeRented(0);
						rentals.get(i).setStatus(OUT);
						freqRenterPts += frequentRenterPtsPerMovie;
						added = true;
						
						System.out.println("Customer " + name + " checked out " + m.getTitle() +".");
					}else{
						System.out.println("Customer " + name + " has already checked out " + m.getTitle() +".");
						System.out.println("Customer cannot check out the same movie twice.");
						added = true;
					}
				}
			}
			if(added == false){
				rentals.add(new Rental(m));
				System.out.println("Customer " + name + " checked out " + m.getTitle() +".");
				freqRenterPts += frequentRenterPtsPerMovie;
			}
		}	
	}
	
	public void daysPass(int d){
		for(int i = 0; i < rentals.size(); i++){
			if(rentals.get(i).getStatus().equals(OUT)){
				rentals.get(i).setTimeRented(rentals.get(i).getTimeRented() + d);
			}
		}
	}
	
	public void checkInMovie(Movie m){
		boolean foundMovie = false;
		if(rentals.size() == 0){
			System.out.println("This customer has no rentals.");
			foundMovie = true;
		}else{
			for(int i = 0; i < rentals.size(); i++){
				if(rentals.get(i).getMovie().getTitle().equals(m.getTitle())){
					if(rentals.get(i).getStatus().equals(IN) || rentals.get(i).getStatus().equals(PAY)){
						System.out.println(name + " already checked in " + m.getTitle() + ".");
						foundMovie = true;
					}else{
						rentals.get(i).setStatus(PAY);
						System.out.println(name + " has checked in " + m.getTitle() + ".");
						foundMovie = true;
					}
				}
			}
			
			if(!foundMovie){
				System.out.println("Movie not checked out.");
			}
		}
	}
}

