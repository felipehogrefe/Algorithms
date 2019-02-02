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
		Integer x=123,y=321;
		System.out.println(x==y);
			StringBuilder sb = new StringBuilder("string").reverse();
		while(!rq.isEmpty()) {
			System.out.println(rq.dequeue());
		}

	}
}
