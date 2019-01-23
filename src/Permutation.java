import edu.princeton.cs.algs4.StdIn;

public class Permutation {
	public static void main(String[] args) {
		int k = Integer.parseInt(args[0]);

		String s;
		RandomizedQueue<String> rq = new RandomizedQueue<String>();
		
		for(int i =0;i<k;i++) {
			s = StdIn.readString();
			rq.enqueue(s);
		}
			
		while(!rq.isEmpty()) {
			System.out.println(rq.dequeue());
		}

	}
}
