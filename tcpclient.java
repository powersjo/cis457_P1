import java.io.*;
import java.net.*;
import java.util.Scanner;

class tcpclient{
    public static void main(String args[]) throws Exception{
	//Prompt user for ip address
    	//Wait for ip address
    	String ip_address, port;
    	Scanner input = new Scanner(System.in);
    	System.out.print("Enter an IP address, loopback address is 127.0.0.1");
    	ip_address = input.next();
    	System.out.print("Enter a port, default port is 9876");
    	port = input.next();
    	//Possibly add error checking for IP address
    	if(checkIP(ip_address) == true && checkPort(port) == true){
    	} else {
    		System.out.print("Not a valid ip address or port.");
    		System.exit(0);
		}
		Socket clientSocket = new Socket(ip_address,Integer.parseInt(port));	
    	//Assign user IP to socket
	DataOutputStream outToServer = 
	    new DataOutputStream(clientSocket.getOutputStream());
	BufferedReader inFromServer = 
	    new BufferedReader(
               new InputStreamReader(clientSocket.getInputStream()));
	BufferedReader inFromUser = 
            new BufferedReader(new InputStreamReader(System.in));
	//notify user that they are connected to server or show error
	System.out.println("Connected to server...");
	//instructions for the user to communicate to server, ie: enter file name.
	// notify user if file does not exist
	System.out.println("Enter a file name: ");
	String message = inFromUser.readLine();
	//send file to server ***
	outToServer.writeBytes(message+'\n');
	String serverMessage = inFromServer.readLine();
	//notify on success or failure
	System.out.println("Got from server: "+serverMessage);
	//repeat or end program.
	clientSocket.close();
    }
    /*
    Check to make sure the input is a valid ipv4 address. 
    Valid ip range is 0.0.0.1 to 255.255.255.254
    */
    private boolean checkIP(String ip){
    	String[] tokens = ip.split(".");
    	if (tokens.length > 4) return false;
    	int token;
    	for (int i = 0; i < 4; i++){
    		try{token = Integer.parseInt(tokens[i]);}
    		catch(NumberFormatException e){return false;}
    		if (i != 3) {
    			if(token < 0 || token > 255) return false;
    		}
    		else if (token <1 || token > 254) return false;
    	}
    	return true; //or true
    }
    /*
    Check to make sure the input is a valid port. 
    */
    private boolean checkPort(String port){
    	int input;
    	try{input = Integer.parseInt(port);}
    	catch(NumberFormatException e){return false;}
    	if(input < 0 || input > 65535) return false;
    	return true;
    	return true; //or true
    }
}
