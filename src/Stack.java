import java.util.Iterator;

public class Stack<Item> {

	private Node first;

	public Stack() {
		first = null;
	}
	
	public Iterator<Item> iterator(){
		return new ListIterator();
	}

	public void push(Item item) {

		Node newFirst = new Node(item);
		newFirst.next = first;
		first = newFirst;

	}

	public Item pop() {
		Node popped = first;
		first = popped.next;
		return first.item;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public int size() {
		return 0;
	}

	private class Node {
		Item item;
		Node next;

		public Node(Item s) {
			item = s;
			next = null;
		}
	}
	
	private class ListIterator implements Iterator<Item>{
		private Node current = first;
		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public Item next() {
			Item item = current.item;
			current = current.next;
			return item;
		}
		
	}
}
