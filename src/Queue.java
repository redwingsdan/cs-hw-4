/**
 * Dan Peterson
 * 109091561
 * daniel.peterson@stonybrook.edu
 * Homework #4
 * CSE 214 Recitation #5
 * Sun Lin
 * @author Dan
 */

import java.util.Collection;
import java.util.Iterator;

/**
 * The Queue interface defines the functions which are utilized by subclasses of Queue. Even if the functions
 * are not used, they must be defined in order for subclasses of Queue to be defined.
 */
public interface Queue {

	void enqueue(Packet item);
	
	Packet dequeue();
	
	Packet peek();
		
	int size();
	
	boolean isEmpty();
	
	public boolean addAll(Collection<?> arg0);
	
	public void clear();

	public boolean contains(Object arg0);

	public boolean containsAll(Collection<?> arg0);

	public Iterator<?> iterator();

	public boolean remove(Object arg0);

	public boolean removeAll(Collection<?> arg0);

	public boolean retainAll(Collection<?> arg0);

	public Object[] toArray();

	public Object[] toArray(Object[] arg0);

	public void add(Packet p);

	public Object element();

	public boolean offer(Object arg0);

	public Object poll();

	public Object remove();

	public boolean add(Object arg0);
}
