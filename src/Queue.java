
public class Queue<Item> {
	private Node first, last;
	public Queue(){
		first = null;
		last = null;
	}
	
	public void enqueue(Item item) {
	
		Node oldLast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		if(isEmpty()) {
			first = last;
		}else {
			oldLast.next = last;
		}
	}
	
	public Item dequeue() {
		Item value = first.item;
		first = first.next;
		if(isEmpty()) last = null;
		return value;
	}
	
	public boolean isEmpty() {
		return first == null;
	}
	
	public int size() {
		return 0;
	}
	
	private class Node{
		Item item;
		Node next;
	}
}
