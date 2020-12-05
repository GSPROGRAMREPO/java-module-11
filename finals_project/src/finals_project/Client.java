package finals_project;


import java.io.*;  
import java.net.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Client extends Application{
	
	public static String sendToServer(String word) {
		
		try {
			//Set up Input / Output
			Socket s = new Socket("localhost",6666);
			DataOutputStream dout=new DataOutputStream(s.getOutputStream());
			DataInputStream fromServer = new DataInputStream(s.getInputStream());
			
			//Send Input
			dout.writeUTF(word);  
			dout.flush();
			
			//Recieve output
			String results = (String)fromServer.readUTF();
			
			//Close the Connection
			dout.close();  
			s.close();
			return results;
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null; 
	}
	
	public static void main(String[] args) {  		
		//Launch launches the GUI
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		initUI(arg0);
		
	}
	
	private void initUI(Stage stage) {

		//Create Items for GUI
        Button input_button = new Button();
        TextField input_text = new TextField();
        Button close_conn_button = new Button();
        TextArea ta = new TextArea();
        Label titleText = new Label("Search for a word that might be in the Poem");
        
        
        //Set up Items for GUI
        ta.setPrefHeight(50);
        ta.setPrefWidth(300);
        
        
        close_conn_button.setText("Close Server");
        close_conn_button.setOnAction((ActionEvent event) -> {        
        	sendToServer("close_server");
        });
        
        input_button.setText("Search");
        input_button.setOnAction((ActionEvent event) -> {  
        	
        	String userWord = input_text.getText();
			String results = sendToServer(userWord);
			ta.appendText(results);
			
        });
        
        
        BorderPane bp = new BorderPane();
        BorderPane bp1 = new BorderPane();
        BorderPane bp2 = new BorderPane();
        bp1.setTop(titleText);
        bp1.setRight(input_text);
        bp1.setLeft(input_button);
        bp1.setBottom(ta);
        bp2.setBottom(close_conn_button);
        bp.setTop(bp1);
        bp.setBottom(bp2);

        //Create and Show Scene
        Scene scene = new Scene(bp, 300, 200);
        stage.setTitle("Poem Search");
        stage.setScene(scene);
        stage.show();
    }
}  