package finals_project;

import java.io.*;  
import java.net.*; 

public class Server {
	public static UniqueWordGen wordList = new UniqueWordGen();
	public static int occurences = 0;
	
	public static int compareWordToList(String userWord) {
		
		if(userWord.equalsIgnoreCase("close_server")){
			return 0;}
		
		
		for(UniqueWord item: UniqueWordGen.unique_word_list) {
			if(userWord.equalsIgnoreCase(item.getWord())){
				occurences = item.getOccurences();
				return 2;}
	    }
		return 1;
	}
	
	public static void main(String[] args){
		
		UniqueWordGen.generateWords();
		
		boolean serverOn = true;
		try{
			
			ServerSocket ss=new ServerSocket(6666);  
			
			do{

				//Establish Connections and data Streams
				Socket socket=ss.accept();//establishes connection
				DataInputStream clientInput=new DataInputStream(socket.getInputStream());  
				String userWord=(String)clientInput.readUTF();
				DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());
	
				
				if(compareWordToList(userWord) == 2) {
					
					String message = "";
					message = "The word you entered is: "+ userWord + ". It occurs " + occurences 
							+ " times.\r\n";
					outputToClient.writeUTF(message);
					
					System.out.println("The word you entered is: "+ userWord + ". It occurs " + occurences 
							+ " times. \r\n");
					
				}else if(compareWordToList(userWord) == 1){
					
					String message = "There is no word in the poem that matches.\r\n";
					outputToClient.writeUTF(message);
					
				}else if(compareWordToList(userWord) == 0) {
					
					serverOn = false;
				}
				
				
			}while(serverOn);
			ss.close();
		}catch(Exception e){System.out.println(e);}  
	}  
}  
