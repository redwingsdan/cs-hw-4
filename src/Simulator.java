/**
 * Dan Peterson
 * 109091561
 * daniel.peterson@stonybrook.edu
 * Homework #4
 * CSE 214 Recitation #5
 * Sun Lin
 * @author Dan
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Simulator {
	
	public static final int MAX_PACKETS = 3;
	LinkedList<Router> dispatcher = new LinkedList<Router>();

	/**
	 * Takes an ArrayList of a user-determined number of routers as well as user-determined values for the
	 * arrival probability of a packet, the maximum buffer size for a router, the minimum and maximum sizes
	 * of the packets, the bandwidth for sending packets, and the duration of the simulation. Once the
	 * simulation is complete, a table summarizing the total number of packets sent, dropped, and the average
	 * time taken to send each packet is displayed. The average time taken for each packet is returned. Between
	 * zero and three packets can arrive at a given simulation unit.
	 * @param list = ArrayList of type Router. Holds the intermediate routers for the simulation - ArrayList(Router)
	 * @param arrivalProb = The probability that a packet will arrive at a given time unit within the simuation - double
	 * @param maxBufferSize = The maximum size of an intermediate buffer. Determines the number of packets each router can hold - int
	 * @param minPacketSize = The minimum size of a newly created packet - int
	 * @param maxPacketSize = The maximum size of a newly created packet - int
	 * @param bandwidth = The maximum number of packets which can be sent to their destination at a given simulation time - int
	 * @param duration = The total amount of cycles that the simulation will take before ending - int
	 * @return average = The average time that each packet took before reaching its destination - double
	 */
	public static double simulate(ArrayList<Router> list, double arrivalProb, int maxBufferSize, int minPacketSize, int maxPacketSize, int bandwidth, int duration)
	{
		double average = 0.0;
		int totalServiceTime = 0;
		int totalPacketsArrived = 0;
		int packetsDropped = 0;
		int numPackets = 0;
		int routerNum = 0;
		int iterator = 0;
		int packetNum = 0;
		int totalPacketsServed = 0;
		String msg = "";
		
		for(int i = 1; i <= duration; i++)
		{
			System.out.println("\nTIME: " + i);
			numPackets = 0;
			msg = "";
			for(int num = 0; num < MAX_PACKETS; num++)
			{
				packetNum = randInt(0,100);		
				if(packetNum < arrivalProb*100)
				{
					numPackets++;
				}
			}			
			if(numPackets == 0)
			{
				System.out.println("No Packets arrived.");
			}
			for(int j = 1; j <= numPackets; j++)
			{
				Packet p = new Packet(minPacketSize, maxPacketSize, i);
				totalPacketsArrived++;
				System.out.println("Packet " + totalPacketsArrived + " arrives at dispatcher with size " + p.getPacketSize() + ".");
			
				try 
				{
					routerNum = Router.sendPacketTo(list);
					list.get(routerNum-1).setLength();
					if((list.get(routerNum-1).getLength() - 1) == maxBufferSize)
					{
						msg = msg + "Network congested, packet " + totalPacketsArrived + " is dropped.\n";
						packetsDropped++;
					}
					else
					{
						list.get(routerNum-1).setData(p);					
						msg = msg + "Packet " + totalPacketsArrived + " sent to Router " + routerNum + ".\n";
					}
				} 
				catch(Exception e)
				{
					msg = msg + "Network congested, packet " + totalPacketsArrived + " is dropped.\n";
					packetsDropped++;
				}
			}
			System.out.print(msg);
			String msg1 = "";
			int bandCount = 0;
			for(int x = 1; x <= list.size(); x++)
			{
				msg1 = msg1 + "\nR" + x + ": ";
				for(iterator = 0; iterator < list.get(x-1).size(); iterator++)
				{
					try
					{					
						if(list.get(x-1).getData(iterator).getTimeToDest() == 0)
						{
							if(bandCount != bandwidth)
							{
								System.out.println("Packet " + list.get(x-1).getData(iterator).getPacketID() + " has successfully reached its destination: +" + list.get(x-1).getData(iterator).getOriginalTime());
								totalServiceTime += list.get(x-1).getData(iterator).getOriginalTime();
								list.get(x-1).getData(iterator).setID(-1);
								list.get(x-1).getData(iterator).setTimeToDest(-1);
								list.get(x-1).setLength2();
								totalPacketsServed++;
								bandCount++;
								list.get(x-1).dequeue();
							}
						}
						if(list.get(x-1).getData(iterator).getPacketID() != -1)
						{
							msg1 = msg1 + list.get(x-1).getData(iterator).toString();
							if((list.get(x-1).getData(0) == list.get(x-1).getData(iterator)) && list.get(x-1).getData(0).getTimeToDest() != 0)
								{
									list.get(x-1).getData(iterator).setTimeToDest(list.get(x-1).getData(iterator).getTimeToDest() - 1);
									if(list.get(x-1).getData(iterator).getTimeToDest() == 0)
									{
		//								list.get(x-1).getData(iterator).setID(-2);
									}
								}
							msg1 = msg1 + ", ";
						}
					}
					catch(NullPointerException e)
					{
						System.out.print("");
					}
					catch(IndexOutOfBoundsException e)
					{
						System.out.print("");
					}
					catch(Exception e)
					{
						msg1 = msg1 + "Network congested, packet " + totalPacketsArrived + " is dropped.";
						packetsDropped++;
					}
				}
			}
			System.out.print(msg1);
			System.out.println();
		}
		average = totalServiceTime / (double)totalPacketsServed;
		average = (int)(average * 100.0) / 100.0;
		System.out.println("\nSimulation Ending...");
		System.out.println("Total service time: " + totalServiceTime);
		System.out.println("Total packets served: " + totalPacketsServed);
		System.out.println("Average service time per packet: " + average);
		System.out.println("Total packets dropped: " + packetsDropped);
		return average;
	}
	
	/**
	 * Creates a randomly generated integer number greater than minVal and less than maxVal.
	 * @param minVal = The minimum value for a randomly generated integer number - int
	 * @param maxVal = The maximum value for a randomly generated integer number - int
	 * @return random = Randomly generated value, greater than minVal and less than maxVal - int
	 */
	static int randInt(int minVal, int maxVal)
	{
		int random;
		random = (int)(Math.random()*(maxVal-minVal) + minVal);
		return random;
	}
	public static void main(String [] args)
	{		
		//ArrayList<Router> list = new ArrayList<Router>();
		double arrivalProb = 0.0;
		int maxBufferSize = 0;
		int minPacketSize = 0;
		int maxPacketSize = 0;
		int bandwidth = 0;
		int duration = 0;
		double average;
		boolean flag = false;
		
		Scanner input = new Scanner(System.in);
		Scanner input2 = new Scanner(System.in);
		System.out.println("Starting simulator...");
		
		while(flag == false)
		{
			ArrayList<Router> list = new ArrayList<Router>();
			Packet.setPacketCount(0);
			System.out.print("Enter the number of Intermediate routers: ");
			while(!input.hasNextInt())
			{
				System.out.println("Invalid input, try again.");
				System.out.print("Enter the number of Intermediate routers: ");
				input.nextLine();
			}
			int number = input.nextInt();
			
			System.out.print("Enter the arrival probability of a packet: ");
			while(!input.hasNextDouble())
			{
				System.out.println("Invalid input, try again.");
				System.out.print("Enter the arrival probability of a packet: ");
				input.nextLine();
			}
			arrivalProb = input.nextDouble();
			
			System.out.print("Enter the maximum buffer size of a router: ");
			while(!input.hasNextInt())
			{
				System.out.println("Invalid input, try again.");
				System.out.print("Enter the maximum buffer size of a router: ");
				input.nextLine();
			}
			maxBufferSize = input.nextInt();
			
			System.out.print("Enter the minimum size of a packet: ");
			while(!input.hasNextInt())
			{
				System.out.println("Invalid input, try again.");
				System.out.print("Enter the minimum size of a packet: ");
				input.nextLine();
			}
			minPacketSize = input.nextInt();
			
			System.out.print("Enter the maximum size of a packet: ");
			while(!input.hasNextInt())
			{
				System.out.println("Invalid input, try again.");
				System.out.print("Enter the maximum size of a packet: ");
				input.nextLine();
			}
			maxPacketSize = input.nextInt();
			
			System.out.print("Enter the bandwidth size: ");
			while(!input.hasNextInt())
			{
				System.out.println("Invalid input, try again.");
				System.out.print("Enter the bandwidth size: ");
				input.nextLine();
			}
			bandwidth = input.nextInt();
			
			System.out.print("Enter the simulation duration: ");
			while(!input.hasNextInt())
			{
				System.out.println("Invalid input, try again.");
				System.out.print("Enter the simulation duration: ");
				input.nextLine();
			}
			duration = input.nextInt();
			
			for(int i = 0; i < number; i++)
			{
				Router intRouter = new Router(maxBufferSize);
				list.add(intRouter);
			}
			
			if(number <= 0 || arrivalProb > 1 || arrivalProb <= 0 || maxBufferSize <= 0 || minPacketSize <= 0 || maxPacketSize < minPacketSize || bandwidth <= 0 || duration <= 0)
			{
				System.out.println("One or more of the inputs was invalid. Please enter new values.");
				System.out.println();
				continue;
			}
			average = simulate(list, arrivalProb, maxBufferSize, minPacketSize, maxPacketSize, bandwidth, duration);
			System.out.print("Do you want to try another simulation? (y/n): ");
			boolean secondFlag = false;
			while(secondFlag == false)
			{
				String value = input2.next();
				if(value.toLowerCase().equals("n"))
				{
					flag = true;
					secondFlag = true;
				}
				else if(value.toLowerCase().equals("y"))
				{
					secondFlag = true;
				}
				else
				{
					System.out.print("\nInvalid Input.\nDo you want to try another simulation? (y/n): ");
				}
			}	
		}
		System.out.println("\nProgram terminating successfully...");
		input.close();
		input2.close();
	}
}
