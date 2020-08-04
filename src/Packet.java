/**
 * Dan Peterson
 * 109091561
 * daniel.peterson@stonybrook.edu
 * Homework #4
 * CSE 214 Recitation #5
 * Sun Lin
 * @author Dan
 */

/**
 * This class creates an object of the type Packet. This class tracks the total number of Packet objects which
 * have been created and uses that value to create a unique identifier for each packet. The packets are created
 * with a size which is generated randomly within a given range. Each packet is given an arrival time, which is
 * the time within a simulation that the packet was created. Each packet also has a destination time, which is
 * the total number of simulation units it will take for the packet to reach its destination. Since this time is
 * changed as the simulation takes place, the original time is saved in a variable in order to track the total time
 * the packet has existed without being sent. 
 */
public class Packet {

	private static int packetCount = 0;
	private int id;
	private int packetSize;
	private int timeArrive;
	private int timeToDest;
	private int originalTime;
	
	/**
	 * Creates an instance of <code>Packet</code> - Constructor
	 * @param packetCount = Counts the total number of packets which have been created - static int
	 * @param id = Unique identifier given to each packet - String
	 * @param packetSize = Size of a given packet - int
	 * @param timeArrive = The time within the simulation that the packet arrived at the dispatcher - int
	 * @param timeToDest = The number of simulation units that have to pass before the packet can be sent - int
	 * @param originalTime = The original timeToDest value, in case the value changes during the simulation - int
	 */		
	public Packet()
	{
		packetCount = packetCount + 1;
		id = packetCount;
		packetSize = 0;
		timeArrive = 0;
		timeToDest = (int)(packetSize/100);
		originalTime = timeToDest;
	}
	
	/**
	 * Creates an instance of <code>Packet</code> - Constructor
	 * @param minPacketSize = The minimum size that a newly created packet can be - int
	 * @param maxPacketSize = The maximum size that a newly created packet can be - int
	 * @param packetCount = Counts the total number of packets which have been created - static int
	 * @param id = Unique identifier given to each packet - int
	 * @param packetSize = Size of a given packet - int
	 * @param timeArrive = The time within the simulation that the packet arrived at the dispatcher - int
	 * @param timeToDest = The number of simulation units that have to pass before the packet can be sent - int
	 * @param originalTime = The original timeToDest value, in case the value changes during the simulation - int
	 */		
	public Packet(int minPacketSize, int maxPacketSize, int timeArrive)
	{
		packetCount = packetCount + 1;
		id = packetCount;
		packetSize = Simulator.randInt(minPacketSize, maxPacketSize);
		this.timeArrive = timeArrive;
		timeToDest = (int)(packetSize/100);
		originalTime = timeToDest;
	}

	/**
	 * Sets the value of <code>packetCount</code> to the int value passed in.
	 * @param packetCount = Counts the total number of packets which have been created - static int
	 */
	public static void setPacketCount(int packetCount)
	{
		Packet.packetCount = packetCount;
	}
	
	/**
	 * Sets the value of <code>id</code> to the int value passed in.
	 * @param id = Unique identifier given to each packet - int
	 */
	public void setID(int id)
	{
		this.id = id;
	}
	
	/**
	 * Sets the value of <code>packetSize</code> to the int value passed in.
	 * @param packetSize = Size of a given packet - int
	 */
	public void setPacketSize(int packetSize)
	{
		this.packetSize = packetSize;
	}
	
	/**
	 * Sets the value of <code>timeArrive</code> to the int value passed in.
	 * @param timeArrive = The time within the simulation that the packet arrived at the dispatcher - int
	 */
	public void setTimeArrive(int timeArrive)
	{
		this.timeArrive = timeArrive;
	}
	
	/**
	 * Sets the value of <code>timeToDest</code> to the int value passed in.
	 * @param timeToDest = The number of simulation units that have to pass before the packet can be sent - int
	 */
	public void setTimeToDest(int timeToDest)
	{
		this.timeToDest = timeToDest;
	}
	
	/**
	 * Sets the value of <code>originalTime</code> to the int value passed in.
	 * @param originalTime = The original timeToDest value, in case the value changes during the simulation - int
	 */
	public void setOriginalTime(int originalTime)
	{
		this.originalTime = originalTime;
	}
	
	/**
	 * Returns the value of <code>packetCount</code> - int
	 * @return packetCount - int
	 */
	public int getPacketCount()
	{
		return packetCount;
	}
	
	/**
	 * Returns the value of <code>id</code> - int
	 * @return id - int
	 */
	public int getPacketID()
	{
		return id;
	}
	
	/**
	 * Returns the value of <code>packetSize</code> - int
	 * @return packetSize - int
	 */
	public int getPacketSize()
	{
		return packetSize;
	}
	
	/**
	 * Returns the value of <code>timeArrive</code> - int
	 * @return timeArrive - int
	 */
	public int getTimeArrive()
	{
		return timeArrive;
	}
	
	/**
	 * Returns the value of <code>timeToDest</code> - int
	 * @return timeToDest - int
	 */
	public int getTimeToDest()
	{
		return timeToDest;
	}
	
	/**
	 * Returns the value of <code>originalTime</code> - int
	 * @return originalTime- int
	 */
	public int getOriginalTime()
	{
		return originalTime;
	}
	
	/**
	 * Returns the string representation of the current Packet - String
	 * @return msg - String
	 */
	public String toString()
	{
		String msg = "";
		msg = "[" + id + ", " + timeArrive + ", " + timeToDest + "]";
		return msg;
	}
}

