import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class KeyboardRunnable implements Runnable
{
		BufferedReader userIn;
		PrintWriter out;
		Socket server;
		    		
   	public KeyboardRunnable(BufferedReader ui, PrintWriter o, Socket s) throws IOException
   	{
   		out = o;
   		userIn = ui;
   		server = s;
   	}

    public void run() 
   	{
      	 try 
      	 {
      		 boolean done = false;
  			 //String output = userIn.readLine();

      		 while (!done)
      		 {
      			 //out.println("inside while");
      			 String output = userIn.readLine();
      			
      			 out.println(output);
      			 
      			 if(output.startsWith("/quit"))
      			 {
      			 	System.out.println("Closing this connection in Keyboard Runnable"); 
      			 	server.close(); 
      				System.out.println("Connection closed"); 
      			 	done = true;
      			 }
      		 }
      		 
      	 }
      	 catch(IOException e)
	        {
	            System.out.println("Error with keyboard thread");
	            System.out.println("Exception: "+e.toString());
	        }
   	}//END run
}//END runnable