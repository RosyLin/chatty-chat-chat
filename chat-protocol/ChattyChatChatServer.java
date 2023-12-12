import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ChattyChatChatServer
{
	private static ArrayList<String> allUsers = new ArrayList<>();
	private static ArrayList<PrintWriter> pw = new ArrayList<>();

	public static class ServerRunnable implements Runnable
	{
	    int clientNumber;
	    Socket client;
	    PrintWriter out;
	    BufferedReader in;
	    String username;
    	int nickCount = 0;
	
	    public ServerRunnable(int n, Socket s) throws IOException //constructor of ServerRunnable
	    {
	        clientNumber = n;
	        client = s;
	        out = new PrintWriter(s.getOutputStream(),true);
	        in = new BufferedReader(new InputStreamReader(s.getInputStream()));
	        username = "null";
	    }
	    
	    public void run() //handles the client as a thread
	    {
	        try
	        {
	            out.println("Type /quit to disconnect");
	        	int nickCount = 1;
	        	pw.add(out);
	        	allUsers.add("null");
	        	
	            boolean done = false;
	            while (!done)
	            {
	                String input = in.readLine();
	                
	                if(input == null||input.startsWith("/quit"))
	                {
	                    done = true;
	                }
	                	                
	                else
	                {
	                	String array[] = input.split(" ",3);
	                    //divide the array into strings by space
	                	
	                    if (array[0].equals("/nick")||array[0].equals("/dm"))
	                    {	
	                		if (array[0].equals("/nick")) //setting username
	                    	{
	                			
	                			nickCount = nickCount+1;;
	                			if(nickCount > 1)
	                			{
	                				//out.println("the nickCount is greater than 1!");
	                				allUsers.remove(username);
	                				out.println("The removed username is: "+username);
	                				pw.remove(out);
	                			}
	                			
	                    		username = array[1];
	                    		allUsers.add(username);
	                    		out.println("Adding client " + clientNumber+ " to all user directory...");
	                    		
	                    		for(PrintWriter p:pw)
	                    		{
	                    			p.println("The username for client "+clientNumber+ " is now: "+ username); //printing new name to all clients
	                    			
	                    		}
	                    		pw.add(out); //add new printwriter to arraylist
	                    	}//END /nick if
	                		
	                    	if (array[0].equals("/dm")) //sending dm to target
	                    	{                    			                    		
	                    		String targetName = array[1];
	                    		String dm = array[2];
	                    		out.println("Sending dm to: "+ targetName);
	                    		
	                    		
	                    		for (int i=0; i<allUsers.size();i++) //check targetName in allUser directory, send dm if found
	                    		{
	                    			//out.println(allUsers.get(i)+" inside for loop");
	                    			if (targetName.equals(allUsers.get(i)))
	                    			{                    				
	                    				(pw.get(i)).println("dm from client "+clientNumber + " " + username + ": "+ dm);
	                    			}

	                    		}	                    		
	                    	}//END /dm if
	                	}//END /nick and /dm if
	                    
	                    
	                    
	                   	else
	                   	{
	                   		//send message to every client
	                   		for(PrintWriter p:pw)
	                   		{
	                   			p.println("Broadcast from "+username+ ": "+input);
	                   		}
	                   	}//END else  
	                    
	                }//END if!=null
 	            }// END while(!done)
	        }//END try
	        
	        catch(IOException e)
	        {
	            System.out.println("Error with client " + clientNumber);
	            System.out.println("Exception: "+e.toString());
	        } 
	        finally
	        {
	            System.out.println("Closing connection to client "+clientNumber);
	            try { client.close();}
	            catch(Exception e){ }
	        }
	    }//END run()
	}//END ServerRunnable

    public static void main(String[] args)
    {
        int port = 40000;
        ServerSocket listener = null;
        Socket client = null;
        int clientNumber = 0;
                
        try
        {
            listener = new ServerSocket(port);
            System.out.println("ServerSocket established on port "+port);
            
            while(true)
            {
                try
                {
                    System.out.println("Listening for connection...");
                    client = listener.accept();
                    System.out.println("Connection established to client "+ clientNumber+": "+client);
                    
                    ServerRunnable r = new ServerRunnable (clientNumber,client);
                    Thread t = new Thread(r);
                    t.start();
                }//END try on connection
                catch(IOException e)
                {
                    System.out.println("Error connecting to client " + clientNumber);
                    System.out.println("Exception: "+e.toString());
                }
                clientNumber++;
            }//END while
        }//END try on listener
        
        catch(IOException e)
        {
            System.out.println("Error with listener");
            System.out.println("Exception: "+e.toString());
        } 
        
        finally
        {
            if(listener != null)
            {
                try{listener.close();}
                catch(Exception e){ }
            }
        }
        return;
    }//END main
}//END CCCServer
