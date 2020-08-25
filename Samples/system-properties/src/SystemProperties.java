import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import com.sun.jna.platform.win32.Advapi32Util;
import static com.sun.jna.platform.win32.WinReg.HKEY_LOCAL_MACHINE;

public class SystemProperties{
  
   public static void main(String[] args){
  
	    InetAddress ip;
	    try {
	  
	        ip = InetAddress.getLocalHost();
	        System.out.println("Current host name : "        + ip.getHostName());
	        System.out.println("Current IP address : "       + ip.getHostAddress());
	        System.out.println("Operating system Name : "    + Advapi32Util.registryGetStringValue
	                (HKEY_LOCAL_MACHINE,"SOFTWARE\\Microsoft\\Windows NT\\CurrentVersion\\", "ProductName"));
	        System.out.println("Operating system version : " + System.getProperty("os.version"));
	        System.out.println(System.getenv("PROCESSOR_ARCHITECTURE"));
	        System.out.println("Processor: "                 + Advapi32Util.registryGetStringValue
	                (HKEY_LOCAL_MACHINE,"HARDWARE\\DESCRIPTION\\System\\CentralProcessor\\0\\", "ProcessorNameString"));
	        System.out.println("Identifier: "                + Advapi32Util.registryGetStringValue
	                (HKEY_LOCAL_MACHINE, "HARDWARE\\DESCRIPTION\\System\\CentralProcessor\\0\\", "Identifier"));
	        
	        //System.out.println(System.getenv("PROCESSOR_IDENTIFIER"));
	        //System.out.println(System.getenv("PROCESSOR_ARCHITECTURE"));
	        //System.out.println(System.getenv("PROCESSOR_ARCHITEW6432"));
	        //System.out.println("Cores: " + System.getenv("NUMBER_OF_PROCESSORS"));
	        
		        /* Total number of processors or cores available to the JVM */
		    System.out.println("Available processors (cores): "  + Runtime.getRuntime().availableProcessors());
		 
		    /* Total amount of free memory available to the JVM */
		    System.out.println("Free memory (bytes): "           + Runtime.getRuntime().freeMemory());
		 
		    /* This will return Long.MAX_VALUE if there is no preset limit */
		    long maxMemory = Runtime.getRuntime().maxMemory();
		    
		    /* Maximum amount of memory the JVM will attempt to use */
		    System.out.println("Maximum memory (bytes): "        + (maxMemory == Long.MAX_VALUE ? "no limit" : maxMemory));
		 
		    /* Total memory currently in use by the JVM */
		    System.out.println("Total memory (bytes): "          + Runtime.getRuntime().totalMemory());
		         
		         
		   NetworkInterface network = NetworkInterface.getByInetAddress(ip);
		  
		   byte[] mac = network.getHardwareAddress();
		  
		   System.out.print("Current MAC address : ");
		  
		   StringBuilder sb = new StringBuilder();
		   for (int i = 0; i < mac.length; i++) {
		     sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));     
		   }
		   System.out.println(sb.toString());
	   
	   } catch (UnknownHostException e) {
	  
	        e.printStackTrace();
	  
	    } catch (SocketException e){
	  
	        e.printStackTrace();
	  
	    }
	    catch (Exception e){
	  
	        e.printStackTrace();
	  
	    }
  
   }
  
}