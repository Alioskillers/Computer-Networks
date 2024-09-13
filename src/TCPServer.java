import java.io.*; 
import java.net.*; 

public class TCPServer {
	public static void main(String argv[]) throws Exception {  
		ServerSocket welcomeSocket = new ServerSocket(1956); 

			Socket connectionSocket = welcomeSocket.accept(); 
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream())); 
			PrintWriter outToClient = new PrintWriter(connectionSocket.getOutputStream(), true); 
			BufferedReader serverInput= new BufferedReader(new InputStreamReader(System.in));
			
			new Thread(() -> {
	            try {
	                String clientMessage;
	                while ((clientMessage = inFromClient.readLine()) != null) {
	                    System.out.println("Client: " + clientMessage);
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }).start();
	        String serverMessage;
	        while ((serverMessage = serverInput.readLine()) != null) {
	            outToClient.println(serverMessage);
	        }
	        
	        inFromClient.close();
	        outToClient.close();
	        connectionSocket.close();
	        welcomeSocket.close();
			 
		} 
}