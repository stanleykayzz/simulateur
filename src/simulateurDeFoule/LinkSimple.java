package simulateurDeFoule;

public class LinkSimple<V> {
	private V data;
	private LinkSimple<V> next;

	public LinkSimple<V> getNext() {
		return this.next;
	}

	public V getValue() {
		return this.data;
	}

	public void setNext(LinkSimple<V> element) {
		this.next = element;
	}

	public void setValue(V element) {
		this.data = element;
	}
}