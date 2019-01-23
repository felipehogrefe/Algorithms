import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

	private static int INITIAL_SIZE = 1;
	private Object[] stack;
	private int N;

	public RandomizedQueue() {
		stack = (Item[]) new Object[INITIAL_SIZE];
		N = 0;
	}

	public void enqueue(Item item) {
		if (item == null)
			throw new IllegalArgumentException();
		if (N == stack.length)
			resize(2 * stack.length);
		stack[N++] = item;
	}

	public Item dequeue() {
		if (isEmpty())
			throw new NoSuchElementException();
		int index = StdRandom.uniform(N);
		Item selected = (Item) stack[index];

		Object[] newStack = new Object[stack.length];

		int j = 0;
		for (int i = 0; i < N; i++) {
			if (i != index) {
				newStack[j++] = stack[i];
			}
		}

		N--;

		if (N > 0 && N == stack.length / 4)
			resize(stack.length / 2);

		stack = newStack;

		return selected;
	}

	public Item sample() {
		if (isEmpty())
			throw new NoSuchElementException();

		return (Item) stack[StdRandom.uniform(N)];
	}

	public boolean isEmpty() {
		return N == 0;
	}

	public int size() {
		return N;
	}

	private void resize(int newSize) {
		Object[] newStack = (Item[]) new Object[newSize];

		for (int i = 0; i < N; i++) {
			newStack[i] = stack[i];
		}
		stack = newStack;
	}

	public static void main(String[] args) {
		RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();

		for (int i = 0; i < 100; i++) {
			rq.enqueue(i);

		}

	}

	private class ListIterator implements Iterator<Item> {
		int indexes[];
		int current;

		public ListIterator() {
			indexes = new int[N];
			for (int i = 0; i < N; i++) {
				indexes[i] = i;
			}
			StdRandom.shuffle(indexes);

		}

		@Override
		public boolean hasNext() {
			return current < N;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

		@Override
		public Item next() {
			if (current == N)
				throw new NoSuchElementException();
			Item item = (Item) stack[indexes[current]];
			current++;
			return item;
		}
	}

	@Override
	public Iterator<Item> iterator() {
		return new ListIterator();
	}
}