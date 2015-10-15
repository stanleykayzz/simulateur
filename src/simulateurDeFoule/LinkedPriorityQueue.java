package simulateurDeFoule;

import java.util.Comparator;

public class LinkedPriorityQueue<T> /*implements IPriorityQueue<T>*/ {

	private LinkSimple<T> node, queue;
	private Comparator<T> cmp;

	public LinkedPriorityQueue(Comparator<T> comparator) {
		this.cmp = comparator;
	}

	public void add(T value) {
		LinkSimple<T> newNode = new LinkSimple<T>();
		newNode.setValue(value);
		if (node == null) {
			this.node = newNode;
			this.queue = newNode;
		} else {

			LinkSimple<T> next = this.node;
			LinkSimple<T> prev = new LinkSimple<T>();
			while (next != null && cmp.compare(value, next.getValue()) > 0) {
				prev = next;
				next = next.getNext();
			}

			if (next == null) {
				queue.setNext(newNode);
				queue = newNode;
			} else {
				if (prev.getValue() != null) {
					prev.setNext(newNode);
				} else {
					this.node = newNode;
				}
				newNode.setNext(next);
			}
		}
	}

	public T peek() {
		if (node != null)
			return node.getValue();
		else
			return null;
	}

	public T remove() {

		if (node != null) {
			T value = this.peek();

			LinkSimple<T> next = this.node.getNext();
			this.node = next;

			return value;
		} else {
			return null;
		}
	}
	
	  /*public static void main(String[] args) {
		  LinkedPriorityQueue<Integer> s = new LinkedPriorityQueue<Integer>(new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					return o2 != null ? o1.compareTo(o2) : o1.compareTo(0);
				}
			});
			
			s.add(15);
			s.add(11);
			s.add(18);
			s.add(1);
			s.add(6);
			s.add(13);
			s.add(25);
			s.add(14);
			
			Integer i = null;
			while (null != (i = s.remove())) {
				System.out.println(i);
			}	
	  }*/
}