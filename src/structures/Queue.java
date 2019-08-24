package structures;

import java.util.NoSuchElementException;

/**************************************************************************************
 * NOTE: before starting to code, check support/structures/UnboundedQueueInterface.java
 * for detailed explanation of each interface method, including the parameters, return
 * values, assumptions, and requirements
 ***************************************************************************************/
public class Queue<T> implements UnboundedQueueInterface<T> {
	private int front, rear;
	protected T[] queue;
	protected int numElement;
	protected T[] reverseQueue;
	//protected T[] copyQueue;
	private int size;
	@SuppressWarnings("unchecked")
	public Queue() {		
            // TODO 1
		queue = (T[]) new Object[10];
		size = queue.length;
		reverseQueue = (T[]) new Object[10];
		numElement = 0;
		front = 0;
		rear = 0;
    }
	
	//copy a new array
	@SuppressWarnings("unchecked")
	public Queue(Queue<T> other) {
            // TODO 2
		front = 0; rear =0;
		int newnum = other.size();
		numElement = other.size();
		T[] copyQueue = (T[]) new Object[newnum];
		//T[] queue2= other.queue;
		
		for (int i = 0; i < newnum; i++) {
			T j = other.dequeue();
			copyQueue[i] = j;
			other.enqueue(j);
		}
		rear = numElement;
		queue = copyQueue;
	
	}
	
	@Override
	public boolean isEmpty() {
            // TODO 3
        return (numElement==0);
	}

	@Override
	public int size() {
            // TODO 4
        return numElement;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void enqueue(T element) {
            // TODO 5
		//int a = queue.length;
		if (numElement == queue.length || rear == queue.length)
		{
			if (queue.length==0) queue= (T[]) new Object[10];
			T[] newqueue = (T[]) new Object[queue.length*2];
			for (int i = 0; i< queue.length; i++)
				newqueue[i] = queue[i];
			queue = newqueue;
		}
		queue[rear] = element;
		rear = (rear + 1);
		
		
		numElement ++;
	}

	@Override
	public T dequeue() throws NoSuchElementException {
            // TODO 6
		if (isEmpty()) throw new NoSuchElementException();
		
		else {
			T old = queue[front];
			queue[front] = null;
			numElement--;
			if (numElement != 0) front = (front + 1);
			else {front =0; rear=0;}
			
			return old;
		}
	}

	@Override
	public T peek() throws NoSuchElementException {
            // TODO 7
		if (isEmpty()) throw new NoSuchElementException();
		else {
            return queue[front];
		}
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public UnboundedQueueInterface<T> reversed() {
            // TODO 8
		int newnum = queue.length;
		//int newEle = numElement;
		int j = 0;
		Queue<T> reverseQ = new Queue<T>();
		reverseQueue = (T[]) new Object[newnum];
		int a = rear -1 ;
		for (int i = a; i > a - numElement; i--) {
			reverseQueue[j] = queue[i];
			reverseQ.enqueue(reverseQueue[j]);
			j++;
		}
		
            return reverseQ;
	}
}

class Node<T> {
	public T data;
	public Node<T> next;
	public Node(T data) { this.data=data;}
	public Node(T data, Node<T> next) {
		this.data = data; this.next=next;
	}
}

