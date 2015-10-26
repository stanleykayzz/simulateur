package simulateurDeFoule;

public class LinkDouble<V> /* implements ILinkDouble<V> */{

	private V data;
	@SuppressWarnings("rawtypes")
	private LinkDouble previous;
	@SuppressWarnings("rawtypes")
	private LinkDouble next;

	public V getValue() {
		return this.data;
	}

	@SuppressWarnings("rawtypes")
	public void setNext(LinkSimple arg0) {
		this.setNext(arg0);
	}

	public void setValue(V arg0) {
		this.data = arg0;
	}

	@SuppressWarnings("rawtypes")
	public LinkDouble getNext() {
		return (LinkDouble) this.next;
	}

	@SuppressWarnings("rawtypes")
	public LinkDouble getPrevious() {
		return this.previous;
	}

	@SuppressWarnings("rawtypes")
	public void setNext(LinkDouble arg0) {
		this.next = arg0;

	}

	@SuppressWarnings("rawtypes")
	public void setPrevious(LinkDouble arg0) {
		this.previous = arg0;
	}

}