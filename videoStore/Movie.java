package videoStore;

public class Movie {
	private String title;
	private String type;
	
	private static final String REGULAR = "regular";
	private static final String CHILDREN = "children";
	private static final String NEW_RELEASE = "new release";
	
	public Movie(String title, String type){
		this.title = title;
		this.type = type;
		System.out.println("New movie " + title + " created.");
	}
	
	public void setType(String type){
		if(type.equals(REGULAR) || type.equals(CHILDREN) || type.equals(NEW_RELEASE)){
			this.type = type;
			System.out.println(title + " type set to " + type + ".");
		}else{
			System.out.println(type + " is not a valid type.");
			System.out.println("Accepted types: regular, children, new release");
		}
		
	}
	
	public String getType(){
		return type;
	}
	
	public String getTitle(){
		return title;
	}
}
