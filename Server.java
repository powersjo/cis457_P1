import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.File;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.OutputStream;

public class Server {
	private String method = null;
	private String path = null;
	private String protocol = null;	
	private int clientNum;
	
	@SuppressWarnings("resource")
	public Server() throws IOException{
		ServerSocket serverSocket = new ServerSocket(9876);
		
		for (clientNum = 0; true; clientNum++) {
			System.out.println("Connection Started: " + clientNum);
	    	final Socket socket = serverSocket.accept();
	    	new Thread(new Runnable() {
	    		@Override
	    		public void run() {
	    			try {
	    				handleConnection(socket);
	    			} catch (IOException e) {
	    				e.printStackTrace();
	    			}
	    		}
	    	}).start();
	    }	    
	}
	
	public void handleConnection(Socket socket) throws IOException{
		BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	    PrintStream output = new PrintStream(socket.getOutputStream(), true);
	    
	    String line;
	    
	    line = input.readLine();
	    System.out.println(line);
	    if(line != null){
		processRequest(line, socket);
	    }	
	    
	    input.close();
	    output.close();
	    socket.close();
	    clientNum--;
	}
	
    public void processRequest(String fileName, Socket sock) throws IOException{
	    File myFile = new File (fileName);
	    byte [] myByteArray = new byte [(int)myFile.length()];
	    FileInputStream fis = new FileInputStream(myFile);
	    BufferedInputStream bis = new BufferedInputStream(fis);
	    bis.read(myByteArray, 0, myByteArray.length);
	    OutputStream os = sock.getOutputStream();
	    os.write(myByteArray, 0, myByteArray.length);
	    os.flush();
	    System.out.println("File transfered.");
	}
}
