


import java.util.Scanner;
import java.io.*;
import java.util.Hashtable;

public class Face 
        
{    
    public static int tablesize = 167;
    
    Hashtable People = new Hashtable(tablesize); 
	
    Hashtable Friends = new Hashtable(tablesize);
	   
      
      public int KeyLogger(String name)
      {
      int inputlength = name.length();
      int key = 0;
        
      for(int j =2; j < inputlength ; j++)
     {
      
      
      char kar1 = name.charAt(0);
      char kar2 = name.charAt(1);   
      char kar = name.charAt(j);        
      
      key = (int) kar + (kar1*3) + (kar2*7);      
      
      }
                int hash = (key % tablesize);      
                return hash;
      }
                        
      
	public  Integer twoPersonKey(String name1, String name2)
        {
		int key = 0;               
                int key2 = KeyLogger(name1);
                int key3 = KeyLogger(name2);
                
		if (People.get(key2) != null && People.get(key3) != null) 
                {
			if (name1.compareTo(name2) < 1) 
                        {
                             String keyy = name1 + "_" + name2;
                             key = KeyLogger(keyy);
			}
                        
                        else if (name1.compareTo(name2) >= 1) 
                        {       
                                String keyy = name2 + "_" + name1;
                                key = KeyLogger(keyy);
			} 
		}
		return  key;
	}
                      		
	
	
	public void commandI(String name) 
        {       
                
                Person p = new Person(name);
                int key = KeyLogger(name);
           
		
                if(People.get(key) != null)
                {
                    System.out.println(name + " cannot be created");
                    return;
                }
                
                /* 
                if (People.get(key) != null)                                                                                                                                                                           
                {                                                                                                                                             
                        if(People.containsKey(p))                              
                {                                                                       // The part that solves collision !!!!!!!!!!!!!!!!
                        System.out.println(name + " cannot be created");
			return;                       
                }
                      key = key +2;  
                }
                */
                
		People.put(key, p);          
              
	}
	
	
	public void commandF(String name1, String name2) 
        { 
                int key2 = KeyLogger(name1);
                int key3 = KeyLogger(name2);
                
		Person p1 = (Person) People.get(key2); 	
		Person p2 = (Person) People.get(key3);
                
		if (p1 == null || p2 == null) 
                { 	
                        System.out.print (name1 + " ");
			System.out.println (name2 + " " + "cannot be friend."); 
			return; 
                }
                
		Integer key = twoPersonKey(name1, name2);
                
		if (Friends.get(key) != null ) 
                { 
                    System.out.println("Friends already."); 
                    return; 
                } 
                
		p1.getFriends().addFirst(p2); 
		p2.getFriends().addFirst(p1); 
                
		Friends.put(key,true);             
                
	}
        
        	public void commandD(String name) 
        { 
                int key = KeyLogger(name);
                Person p = (Person) People.get(key);
                
		if (People.get(key) == null)
                {  
			System.out.println(name + " not in the list"); 
                } 
                
                People.remove(key);
                
                
	}	
	
	public void commandE(String name1, String name2) 
        {       
                int key2 = KeyLogger(name1);
                int key3 = KeyLogger(name2);
            
		Person p1 = (Person) People.get(key2); 	
		Person p2 = (Person) People.get(key3);
                
		if (p1 == null || p2 == null ) 
                { 
			System.out.println ("Person does not exist."); 
			return; 
                }
                
		Integer key = twoPersonKey(name1, name2);
                
		if (Friends.get(key) == null || Friends.get(key).equals(false))
                { 
			System.out.println ("Persons not friends before."); 
			return; 
                }
		p1.getFriends().remove(p2); 
		p2.getFriends().remove(p1);
		Friends.remove(key); 
	}
	
	
	public void commandL(String name) 
        {       
                int key = KeyLogger(name);  
		Person p = (Person) People.get(key);
                
                
		if (p == null) 
                { 					
			System.out.println(name + " not in the list."); 
			return; 
                }
                
                if(p.getFriends().peek() == null)
                {
                    System.out.println(name + " has no friend");
                }
		
		for (Person list: p.getFriends()) 
                { 
			System.out.print(list.getName() + " ");
                      
		}
                
                
		
	}
        

	
	public void commandS(String name1, String name2) 
        { 
		 
		Integer key = twoPersonKey(name1, name2); 
                
                if((key.equals(0)))
                {
                    System.out.println(name1+ " or "+ name2 +" "+ " not in the list");
                    return;                    
                }
                
		if (Friends.get(key) == null) 
                { 
			Friends.put(key, false);
		}
		
		if (Friends.get(key).equals(true)) 
                {
			System.out.println("Yes");
		} 
                
                else 
                {
			System.out.println("No");
		}
	}
        
        public  void commandR() 
        {
          
 
            BufferedWriter bw = null;
        
            try 
            {            
            InputStream in = System.in;
            bw = new BufferedWriter(new FileWriter("filename.txt"));
            StringBuilder builder = new StringBuilder();
            int letter;
            
            
            while ((letter = in.read()) != -1) 
            {           
                
                
                bw.write((char)letter);                  
                builder.append((char) letter);
                bw.flush();
                
                
                if (builder.toString().endsWith("X")) 
                {
                    System.exit(0);
                }
            }           
            }
            catch (IOException e) 
            {
                
            } 
            
        }
    
              
	
	public static void main(String[] args) 
        {
                Face hash = new Face();
		Scanner input = new Scanner(System.in);
		String line;
                
	
	while (true) 
        {
            line = input.nextLine(); 
            Scanner sc = new Scanner(line);  
            String userCommand = sc.next();
	       
            if (userCommand.equals("X")) 
                break;
            if (userCommand.equals("R"))
            {
                hash.commandR();
                break;
            }                         
                String name1 = sc.next();
                
		switch (userCommand.charAt(0))
                {
		case 'I': hash.commandI(name1);
                //System.out.println(hash.People);
		break;
		case 'F': hash.commandF(name1,sc.next());
                //System.out.println(allFriends);
	        break;
		case 'E': hash.commandE(name1,sc.next()); 
		break;
                case 'S': hash.commandS(name1,sc.next()); 
       		break;
                case 'L': hash.commandL(name1);
	        break;
                case 'D': hash.commandD(name1);
	        break;


	
        }
	
        }
}
}

   
