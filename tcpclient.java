import java.io.*;
import java.net.*;

class tcpclient{
    public static void main(String args[]) throws Exception{
	//Prompt user for ip address
    	//Wait for ip address
    	//Possibly add error checking for IP address
    	//Assign user IP to socket
    Socket clientSocket = new Socket("127.0.0.1",9876);
	DataOutputStream outToServer = 
	    new DataOutputStream(clientSocket.getOutputStream());
	BufferedReader inFromServer = 
	    new BufferedReader(
               new InputStreamReader(clientSocket.getInputStream()));
	BufferedReader inFromUser = 
            new BufferedReader(new InputStreamReader(System.in));
	//notify user that they are connected to server or show error
	//instructions for the user to communicate to server, ie: enter file name or message.
	// notify user if file does not exist
	System.out.println("Enter a message: ");
	String message = inFromUser.readLine();
	//send file to server ***
	outToServer.writeBytes(message+'\n');
	String serverMessage = inFromServer.readLine();
	//notify on success or failure
	System.out.println("Got from server: "+serverMessage);
	//repeat or end program.
	clientSocket.close();
    }
}
