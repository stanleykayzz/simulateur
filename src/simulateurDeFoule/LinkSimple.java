package simulateurDeFoule;

public class LinkSimple<V> /* implements ILinkSimple<V> */{

	private V data;
	@SuppressWarnings("rawtypes")
	private LinkSimple next;

	@SuppressWarnings("rawtypes")
	public LinkSimple getNext() {
		return this.next;
	}

	public V getValue() {
		return this.data;
	}

	@SuppressWarnings("rawtypes")
	public void setNext(LinkSimple arg0) {
		this.next = arg0;
	}

	public void setValue(V arg0) {
		this.data = arg0;
	}

}