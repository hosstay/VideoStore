package videoStore;

import java.util.ArrayList;

public class StatementSystem {
	private static final double defaultRentalCost = 5.00;
	private static final double newMovieMultiplier = 2.00;
	private static final double lateFeePerDay = 2.00;
	private static final int rentalDaysAllowed = 5;
	private static final int frequentRenterPtsPerMovie = 5;
	private static final String NEW_RELEASE = "new release";
	private static final String OUT = "out";
	private static final String PAY = "pay";
	private static final String IN = "in";
	
	private int daysLate = 0;
	private boolean isNew = false;
	private int daysRented = 0;
	private double cost = 0.00;
	private double totalCost = 0.00;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private ArrayList<Movie> movies = new ArrayList();
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private ArrayList<Customer> customers = new ArrayList();
	
	public void addMovie(String title, String type){
		movies.add(new Movie(title, type));
	}
	
	public void addCustomer(String name){
		customers.add(new Customer(name));
	}
	
	public void listMovieNamesAndTypes(){
		for(int i = 0; i < movies.size(); i++){
			System.out.println("name: " + movies.get(i).getTitle() + "   type:" + movies.get(i).getType());
		}
	}
	
	public void listCustomerNames(){
		for(int i = 0; i < customers.size(); i++){
			System.out.println(customers.get(i).getName());
		}
	}
	
	public void setMovieType(String title, String type){
		boolean foundMovie = false;
		
		for(int i = 0; i < movies.size(); i++){
			if(movies.get(i).getTitle().equals(title)){
				foundMovie = true;
				movies.get(i).setType(type);
			}
		}
		
		if(!foundMovie){
			System.out.println("Did not find movie.");
		}
	}
	
	public int getFreqRenterPts(String name){
		boolean foundCustomer = false;
		
		for(int i = 0; i < customers.size(); i++){
			if(customers.get(i).getName().equals(name)){
				foundCustomer = true;
				return customers.get(i).getFreqRenterPts();
			}
		}
		
		if(!foundCustomer){
			System.out.println("Did not find customer.");
		}
		return 0;
	}
	
	public void checkOutMovie(String name, String title){
		boolean foundCustomer = false;
		boolean foundMovie = false;
		
		for(int i = 0; i < customers.size(); i++){
			if(customers.get(i).getName().equals(name)){
				foundCustomer = true;
				for(int j = 0; j < movies.size(); j++){
					if(movies.get(j).getTitle().equals(title)){
						foundMovie = true;
						customers.get(i).checkOutMovie(movies.get(j));
					}
				}
			}
		}
		
		if(!foundCustomer){
			System.out.println("Did not find customer.");
		}else if(!foundMovie){
			System.out.println("Did not find movie.");
		}
	}
	
	public void checkInMovie(String name, String title){
		boolean foundCustomer = false;
		boolean foundMovie = false;
		
		for(int i = 0; i < customers.size(); i++){
			if(customers.get(i).getName().equals(name)){
				foundCustomer = true;
				for(int j = 0; j < movies.size(); j++){
					if(movies.get(j).getTitle().equals(title)){
						foundMovie = true;
						customers.get(i).checkInMovie(movies.get(j));
					}
				}
			}
		}
		
		if(!foundCustomer){
			System.out.println("Did not find customer.");
		}else if(!foundMovie){
			System.out.println("Did not find movie.");
		}
	}
	
	public void daysPass(int d){
		for(int i = 0; i < customers.size(); i++){
			customers.get(i).daysPass(d);
		}
		System.out.println(d + " days have passed.");
	}
	
	@SuppressWarnings("unchecked")
	public void listCustomerRentalHistory(String name){
		@SuppressWarnings("rawtypes")
		ArrayList<Rental> rentals = new ArrayList();
		boolean foundCustomer = false;
		
		for(int i = 0; i < customers.size(); i++){
			if(customers.get(i).getName().equals(name)){
				foundCustomer = true;
				rentals = (ArrayList<Rental>) customers.get(i).getRentals().clone();
			}
		}
		
		if(!foundCustomer){
			System.out.println("Did not find customer.");
		}else{
			if(rentals.size() == 0){
				System.out.println("No rentals have been made by this customer.");
			}else{
				System.out.println("Movies checked out by " + name + ".");
				for(int i = 0; i < rentals.size(); i++){
					if(rentals.get(i).getStatus().equals(OUT)){
						System.out.println("   " + rentals.get(i).getMovie().getTitle());
					}
				}
			}
		}
		
	}
	
	public void printChargeStatement(String name){
		boolean foundCustomer = false;
		
		for(int i = 0; i < customers.size(); i++){
			if(customers.get(i).getName().equals(name)){
				foundCustomer = true;
				printStatement(customers.get(i));
			}
		}
		
		if(!foundCustomer){
			System.out.println("Did not find customer.");
		}
	}
	
	@SuppressWarnings("unchecked")
	private void printStatement(Customer c){
		ArrayList<Rental> rentals;
		totalCost = 0.00;
		
		System.out.println("----------------------------------------------");
		System.out.println("==Video Store==");
		System.out.println("Default rental cost: $" + defaultRentalCost);
		System.out.println("New movie multiplier: x" + newMovieMultiplier);
		System.out.println("Rental days allowed: " + rentalDaysAllowed);
		System.out.println("Late fee cost: $" + lateFeePerDay + " per day");
		System.out.println(frequentRenterPtsPerMovie + " frequent renter points will be given for each movie rented.");
		System.out.println("----------------------");
		System.out.println("Customer: " + c.getName());
		System.out.println("Checked in movies balance:");
		
		rentals = (ArrayList<Rental>) c.getRentals().clone();
		
		for(int i = 0; i < rentals.size(); i++){
			if(rentals.get(i).getStatus().equals(PAY)){
				rentals.get(i).setStatus(IN);
				daysLate = 0;
				isNew = false;
				daysRented = 0;
				cost = 0.00;
				
				System.out.println("   " + rentals.get(i).getMovie().getTitle());
				
				if(rentals.get(i).getMovie().getType().equals(NEW_RELEASE)){
					System.out.println("      New? Yes");
					isNew = true;
				}else{
					System.out.println("      New? No");
					isNew = false;
				}
			
				daysRented = rentals.get(i).getTimeRented();
				System.out.println("      Days rented: " + daysRented);
				
				daysLate = rentals.get(i).getTimeRented() - rentalDaysAllowed;
				if(daysLate < 0){daysLate = 0;}
				System.out.println("      Days late: " + daysLate);
				
				if(isNew){
					cost = ((defaultRentalCost + (lateFeePerDay * daysLate)) * newMovieMultiplier); 
				}else{
					cost = (defaultRentalCost + (lateFeePerDay * daysLate));
				}
				
				System.out.println("      Cost: $" + cost );
				System.out.println("      Renter Points earned for this movie: " +  frequentRenterPtsPerMovie);
				totalCost += cost;
			}
		}
		
		System.out.println("Total check in movies cost: $" + totalCost);
		System.out.println("Total frequent renter points: " + c.getFreqRenterPts());
		System.out.println("----------------------------------------------");
	}
}