import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

public class ConsoleRunnable implements Runnable
{
	BufferedReader in;
	Socket server;

	public ConsoleRunnable(BufferedReader i, Socket s) throws IOException
	{
		in=i;
		server = s;
	}

    public void run() 
	{
   	 try 
   	 {	
   		 boolean done = false;
   		 while (!done)
   		 {
   			//System.out.println("inside try");
   			String response = in.readLine();
   			//System.out.println("After in.readLine");
   			
	        System.out.println(response); 
	        
	        if (response.startsWith("/quit"))
   			{
   				System.out.println("Closing this connection in ConsoleRunnable"); 
   				server.close(); 
                System.out.println("Connection closed");
                done = true;
   			}
   		 }
   	 }
   	 catch(IOException e)
       {
           System.out.println("Error with console thread");
           System.out.println("Exception: "+e.toString());
       }
	}//END run
}//END runnable