import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	private Node first, last;
	private int size;

	public Deque() {
		first = null;
		last = null;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public void addFirst(Item item) {
		checkItem(item);

		Node newNode = new Node();
		newNode.item = item;

		newNode.previous = first;

		if (first != null) {
			first.next = newNode;
		} else {
			last = newNode;
		}

		first = newNode;

		size++;
	}

	public void addLast(Item item) {
		checkItem(item);
		Node newNode = new Node();
		newNode.item = item;

		newNode.next = last;

		if (last != null) {
			last.previous = newNode;
		} else {
			first = newNode;
		}

		last = newNode;

		size++;
	}

	private void checkItem(Item item) {
		if (item == null)
			throw new IllegalArgumentException();
		// TODO Auto-generated method stub

	}

	public Item removeFirst() {
		if (isEmpty()) {
			last = null;
			first = null;
			throw new NoSuchElementException();
		}
		Item item = first.item;
		first = first.previous;
		size--;
		checkNull();
		return item;
	}

	public Item removeLast() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		Item item = last.item;
		last = last.next;
		size--;
		checkNull();
		return item;
	}

	private void checkNull() {
		if (size == 0) {
			last = null;
			first = null;

		}
	}

	public Iterator<Item> iterator() {
		return new ListIterator();
	}

	private class ListIterator implements Iterator<Item> {
		Node current;

		public ListIterator() {
			current = first;
		}

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

		@Override
		public Item next() {
			if (current == null)
				throw new NoSuchElementException();
			Item item = current.item;
			current = current.previous;
			return item;
		}
	}

	public static void main(String[] args) {

	}

	private class Node {
		Node next, previous;
		Item item;

		public Node() {
			next = null;
			previous = null;
		}
	}

}