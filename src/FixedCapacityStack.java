
public class FixedCapacityStack<Item> {
	private static int INITIAL_SIZE = 1;
	private Object[] stack;
	private int N;
	
	public FixedCapacityStack() {
		stack = (Item[]) new Object[INITIAL_SIZE];
		N=0;
	}
	
	public boolean isEmpity() {
		return N==0;
	}
	
	public void push(Item item) {
		if(N == stack.length) resize(2*stack.length);
		stack[N++]=item;
	}
	
	public Object pop() {
		Item item = (Item) stack[--N];
		stack[N]=null;
		if(N>0 && N == stack.length/4) resize(stack.length/2);
		return item;
	}
	
	private void resize(int newSize) {
		Object[] newStack = (Item[]) new Object[newSize];
		for(int i = 0 ; i < stack.length; i++) {
			newStack[i] = stack[i];
		}
		stack = newStack;
		
	}
}
