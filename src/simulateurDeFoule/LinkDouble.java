package simulateurDeFoule;

public class LinkDouble<V> {
	private V value;
	private LinkDouble<V> previous;
	private LinkDouble<V> next;

	public V getValue() {
		return this.value;
	}

	public void setValue(V value) {
		this.value = value;
	}

	public LinkDouble<V> getNext() {
		return this.next;
	}

	public void setNext(LinkSimple<V> nextElement) {
		this.setNext(nextElement);
	}

	public LinkDouble<V> getPrevious() {
		return this.previous;
	}
	
	public void setPrevious(LinkDouble<V> arg0) {
		this.previous = arg0;
	}
}