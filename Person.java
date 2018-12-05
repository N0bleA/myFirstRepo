
import java.util.LinkedList;

public class Person 

{              
       
	private String name;
        private Person next;
	private final LinkedList<Person> friends;	

	
	public Person(String n) 
        {				
            name = n; 
	    friends = new LinkedList<>(); 
        }
	
	public String getName() 
        { 		
		return name; 
        }
	
	public LinkedList<Person> getFriends()
        {
		return friends; 
        }
	
}