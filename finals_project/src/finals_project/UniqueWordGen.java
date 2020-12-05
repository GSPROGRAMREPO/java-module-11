package finals_project;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class UniqueWordGen {
	
	public static List<UniqueWord> unique_word_list = new ArrayList<UniqueWord>();
	
	public static boolean isThereAPoemFile() {
		File myObj = new File("Poem.txt");
		
	    if (myObj.exists()) {return true;} else {
	      System.out.println("The file does not exist.");
	      return false;
	    }
	}
	
	public static String readFileAsString() {
	    String text = "";
	    try {
	      text = new String(Files.readAllBytes(Paths.get("Poem.txt")));
	    } catch (IOException e) {
	      e.printStackTrace();
	    }

	    return text;
	  }

	static int countOccurences(String str, String word)  
	{ 
	    // split the string by spaces in a 
	    String a[] = str.split(" "); 
	    
	    // search for pattern in a 
	    int count = 0; 
	    for (int i = 0; i < a.length; i++)  
	    { 
	    	if (word.equals(a[i])) 
	    		count++; 
	    }
	    
	    //This will remove weird cases where a single occurrence sometimes returns a 0 for occurrence.
	    if(count > 100) {return 0;}
	    if(count == 0) {return 1;}
	  
	    return count; 
	}
	
	
	
	public static void generateWords() {
		
		boolean poem_exists = isThereAPoemFile();
		
		if(poem_exists) {
			
		    String poem_text_dirty = readFileAsString();
		    
		    //Takes out everything that is not a letter and places it inside of a String Array
		    
		    poem_text_dirty = poem_text_dirty.replaceAll("\\p{Punct}"," "); 
		    poem_text_dirty = poem_text_dirty.replaceAll("[\\t\\n\\r]+"," ");
		    String[] poem_text_arr = poem_text_dirty.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
		    
		    //Converts the poem_text_arr into a list than creates a String Set from the list
		    List<String> entire_poem = Arrays.asList(poem_text_arr);
		    Set<String> word_set = new HashSet<String>(entire_poem);
		    
		    //Iterates through the entire set counting the occurrences then creating a UniqueWord object
		    //to add to the list of UniqueWord Objects
		    for(String temp : word_set) {
		    	
		    	unique_word_list.add(new UniqueWord(temp, countOccurences(poem_text_dirty, temp)));
		    }
		    
		    Collections.sort(unique_word_list);
		    /*
		    for(UniqueWord item: unique_word_list) {
		    	System.out.println(item);
		    }
		    */
		    	
		}
	    
	  }
	
}
