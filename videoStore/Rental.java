package videoStore;

public class Rental {
	private Movie movieRented;
	private int timeRented;
	private String status;
	
	private static final String OUT = "out";
	
	public Rental(Movie m)
	{
		movieRented = m;
		timeRented = 0;
		status = OUT;
	}
	
	public Movie getMovie(){
		return movieRented;
	}
	
	public int getTimeRented(){
		return timeRented;
	}
	
	public String getStatus(){
		return status;
	}
	
	public void setTimeRented(int t){
		timeRented = t;
	}
	
	public void setStatus(String s){
		status = s;
	}
	
}
