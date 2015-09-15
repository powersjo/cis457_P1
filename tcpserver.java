import java.io.*;
import java.net.*;

class tcpserver{
    public static void main(String argv[]) throws Exception{
	ServerSocket listenSocket = new ServerSocket(9876);
	while(true){
	    Socket connectionSocket=listenSocket.accept();
	    /*notify that client is connected
	    part two, once client is connected put client in new thread 
	    and continue to wait for new clients*/
	    BufferedReader inFromClient =
		new BufferedReader(
		  new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = 
		new DataOutputStream(connectionSocket.getOutputStream());
            //wait for file transfer
            //possibly add file size check for consistency
            //notify that file transfer happened successfully or failed
	    String clientMessage = inFromClient.readLine();
	    System.out.println("The client said "+clientMessage);
	    outToClient.writeBytes(clientMessage+'\n');
	    connectionSocket.close();
	}
    }
}
