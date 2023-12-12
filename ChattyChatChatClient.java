import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
//import ChattyChatChatServer.ServerRunnable;

public class ChattyChatChatClient
{
	String hostname = "localhost";
	Socket server = null;
	int port = 40000;	
		
	public static void main(String[] args)
	{
		String hostname = "localhost";
		Socket server = null;
		int port = 40000;
		
		try
		{
		    server = new Socket(hostname,port);
		
		    BufferedReader in = new BufferedReader( new InputStreamReader(server.getInputStream()));
		    PrintWriter out = new PrintWriter( server.getOutputStream(), true );
		    BufferedReader userIn = new BufferedReader( new InputStreamReader(System.in));
		
		    System.out.println(in.readLine());
		    
		    KeyboardRunnable k = new KeyboardRunnable (userIn,out,server);
            Thread t1 = new Thread(k);
            
            ConsoleRunnable c = new ConsoleRunnable (in, server);
            Thread t2 = new Thread(c);
            
            System.out.println("starting the start of threads...");
            
            t1.start();
            t2.start();		
		}//END try on server
		
		
		catch(IOException e)
		{
		    System.out.println("Error with socket");
		}
		return;  
	}//END main
}//END CCCClient

