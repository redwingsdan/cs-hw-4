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
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
import java.util.LinkedList;

/**
 * The Router class is a subclass of the Queue class. This class is used to represent the Routers which will
 * be used to store the packets. Each Router has a list within it to store a user-determined number of packets.
 * There is a value for the total buffer size of the routers, which determines the maximum number of packets
 * which can be stored at one time. There is a length value which determines the current number of packets
 * stored in the Router. And the Queue class has methods which add and remove Routers and Packets to the Queue
 * and lists.
 */
public class Router implements Queue{
	
		private Packet data1;
		private int size;
		private int length;
		private LinkedList<Packet> data2 = new LinkedList<Packet>();
		
		/**
		 * Creates an instance of <code>Router</code> with a <code>size</code> of type int passed in - Constructor
		 * Creates a LinkedList of type Packet in order to store the packets which are passed to the Router.
		 * @param data1 = Stores an object of type Packet in the Router - Packet
		 * @param size = Sets the total size of the LinkedList which holds the packets - int
		 * @param length = Counts the number of packets which are currently in the Router - int
		 */	
		public Router(int size)
		{
			data1 = null;
			this.size = size;
			length = 0;
		}
		
		/**
		 * Increments the value of <code>length</code> by 1.
		 * @param length = Counts the number of packets which are currently in the Router - int
		 */
		public void setLength()
		{
			this.length = this.length + 1;
		}
		
		/**
		 * Decrements the value of <code>length</code> by 1.
		 * @param length = Counts the number of packets which are currently in the Router - int
		 */
		public void setLength2()
		{
			this.length = this.length - 1;
		}
		
		/**
		 * Returns the value of <code>length</code> - int
		 * @return length - int
		 */
		public int getLength()
		{
			return this.length;
		}
		
		/**
		 * Adds the passed in value of type Packet to the LinkedList.
		 * @param p = The object of type Packet which is passed in - Packet
		 */
		public void setData(Packet p)
		{
			data2.add(p);
		}
		
		/**
		 * Returns the Packet at index i of the LinkedList - Packet
		 * @param i = The index of the element which will be returned - int
		 * @return data2.get(i) = The Packet which is at the index i of the LinkedList - Packet
		 */
		public Packet getData(int i)
		{
			return data2.get(i);
		}
		
		/**
		 * Returns the Packet at the head of the LinkedList - Packet
		 * @return data2.remove() = The Packet which is at the head of the LinkedList - Packet
		 */
		public Packet dequeue()
		{
			return data2.remove();
		}
		/**
		 * Returns the top element of the LinkedList - Packet
		 * @return data1 - Packet
		 */
		public Packet peek()
		{
			return data1;
		}
		
		/**
		 * Returns the size of the Router Object(number of elements it contains) - int
		 * @return size - int
		 */
		public int size()
		{
			return size;
		}
		
		/**
		 * Sets the Packet object passed in to data1.
		 * @param p - Packet
		 */
		public void add(Packet p) {
			this.data1 = p;
		}
		
		/**
		 * Returns the string representation of the current Packet - String
		 * @return msg - String
		 */
		public String toString()
		{
			String msg = "";
			
			return msg;
		}
		
		/**
		 * Determines the intermediate router which the incoming packet will be sent to. The Router is chosen by determining
		 * which Router currently has the most open space in its buffer. If they all have the same amount of open space, then
		 * the first Router in the list is chosen to be the destination for the packet. If all of the Routers are full and 
		 * the packet cannot be added without overflow, then the packet will be dropped and will not be stored in the list.
		 * @param routers = ArrayList of routers which contains all of the intermediate routers which can accept packets - ArrayList
		 * @return num = The router number which the incoming packet will be sent to - int
		 * @throws Exception = Indicates that all routers are full and the incoming packet must be dropped.
		 */
		public static int sendPacketTo(ArrayList<Router> routers) throws Exception
		{
			int size = 0;
			int oldsize = routers.get(0).getLength();;
			int num = -1;
			int count = 0;
			int fullCount = 0;
			for(int i = 0; i < routers.size(); i++)
			{
				size = routers.get(i).getLength();	
				if(oldsize == size)
				{
					count++;
				}
				if(oldsize > size)
				{
					num = i + 1;
					oldsize = size;
				}
			}
			if(count == routers.size())
			{
				num = 1;
			}
			if(count == routers.size())
			{
				for(int j = 0; j < routers.size(); j++)	
					{
						if(routers.get(j).getLength() == routers.get(j).size())
						{
							fullCount++;
						}
					}		
				num = 1;
			}
			if(num == -1)
			{
				num = 1;
			}
			if(fullCount == routers.size())
			{
				throw new Exception("Network congested, packet is dropped.");
			}
			return num;
		}

		/**
		 * Below are all of the functions which are utilized by the Queue class. These functions are not
		 * used, but must be included in order for the Router class to extend the Queue class. 
		 */
		@Override
		public boolean addAll(Collection arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void clear() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Object[] toArray() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object[] toArray(Object[] arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean add(Object arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Object element() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean offer(Object arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Object poll() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object remove() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean contains(Object arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean containsAll(Collection arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Iterator iterator() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean remove(Object arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean removeAll(Collection arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean retainAll(Collection arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isEmpty() {
			// TODO Auto-generated method stub
			return false;
		}


}
