package videoStore;

import java.util.Scanner;

public class RunPrompt {
	public static void main(String[] args) {
		int choice = 0;
		String input1 = "";
		String input2 = "";
		boolean loop = true;
		StatementSystem system = new StatementSystem();
		Scanner scanner = new Scanner(System.in);
		
		while(loop){
			System.out.println("==Menu==");
			System.out.println("0) Exit.");
			System.out.println("1) Add a movie to the database.");
			System.out.println("2) Add a customer to the database.");
			System.out.println("3) Have customer check out a movie.");
			System.out.println("4) Have customer check in a movie.");
			System.out.println("5) Have days pass.");
			System.out.println("6) Set movie type.");
			System.out.println("7) Print statement for customer.");
			System.out.println("8) List movie names and types.");
			System.out.println("9) List customer names.");
			System.out.println("10) List customer rental history.");
			System.out.println("11) Populate the database with 5 movies.");
			System.out.println("12) Populate the database with 2 customers.");
			
			choice = Integer.parseInt(scanner.nextLine());
			
			switch (choice) {
	        case 0:
	            loop = false;
	            break;
	        case 1:
	        	System.out.println("What is the movie title? ");
	        	input1 = scanner.nextLine();
	        	System.out.println("What is the type name? ");
	        	input2 = scanner.nextLine();
	        	system.addMovie(input1, input2);
	        	break;
	        case 2:
	        	System.out.println("What is the customer's name? ");
	        	input1 = scanner.nextLine();
	        	system.addCustomer(input1);
	        	break;
	        case 3:
	        	System.out.println("What is the customer name? ");
	        	input1 = scanner.nextLine();
	        	System.out.println("What is the movie title? ");
	        	input2 = scanner.nextLine();
	        	system.checkOutMovie(input1, input2);
	        	do{
	        		System.out.println("Any other movie titles to check out? Type \"no\" to exit this prompt.");
	        		input2 = scanner.nextLine();
	        		if(!input2.equals("no")){
	        			system.checkOutMovie(input1, input2);
	        		}
	        	}while(!input2.equals("no"));
	        	break;
	        case 4:
	        	System.out.println("What is the customer name? ");
	        	input1 = scanner.nextLine();
	        	System.out.println("What is the movie title? ");
	        	input2 = scanner.nextLine();
	        	system.checkInMovie(input1, input2);
	        	do{
	        		System.out.println("Any other movie titles to check in? Type \"no\" to exit this prompt.");
	        		input2 = scanner.nextLine();
	        		if(!input2.equals("no")){
	        			system.checkInMovie(input1, input2);
	        		}
	        	}while(!input2.equals("no"));
	        	break;
	        case 5:
	        	System.out.println("How many days will pass? ");
	        	input1 = scanner.nextLine();
	        	system.daysPass(Integer.parseInt(input1));
	        	break;
	        case 6:
	        	System.out.println("What is the movie title? ");
	        	input1 = scanner.nextLine();
	        	System.out.println("What is the type name? ");
	        	System.out.println("Accepted types: regular, children, new release");
	        	input2 = scanner.nextLine();
	        	system.setMovieType(input1, input2);
	        	break;
	        case 7:
	        	System.out.println("What is the customer's name? ");
	        	input1 = scanner.nextLine();
	        	system.printChargeStatement(input1);
	        	break;
	        case 8:
	        	system.listMovieNamesAndTypes();
	        	break;
	        case 9:
	        	system.listCustomerNames();
	        	break;
	        case 10:
	        	System.out.println("What is the customer's name? ");
	        	input1 = scanner.nextLine();
	        	system.listCustomerRentalHistory(input1);
	        	break;
	        case 11:
	        	system.addMovie("indiana jones", "regular");
	        	system.addMovie("avengers", "new release");
	        	system.addMovie("toy story", "children");
	        	system.addMovie("rush", "normal");
	        	system.addMovie("new mutants", "new release");
	        	break;
	        case 12:
	        	system.addCustomer("bob");
	        	system.addCustomer("steve");
	        	break;
	        }
		}
		scanner.close();
	}
}
