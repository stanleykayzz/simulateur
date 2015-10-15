package simulateurDeFoule;

public class LinkDouble<V> /*implements ILinkDouble<V> */{

	private V data;
	private LinkDouble previous;
	private LinkDouble next;
	
	
	public V getValue() {
		return this.data;
	}

	
	public void setNext(LinkSimple arg0) {
		this.setNext(arg0);
	}

	public void setValue(V arg0) {
		this.data = arg0;
	}

	public LinkDouble getNext() {
		return (LinkDouble) this.next;
	}

	public LinkDouble getPrevious() {
		return this.previous;
	}

	public void setNext(LinkDouble arg0) {
		this.next = arg0;
		
	}

	public void setPrevious(LinkDouble arg0) {
		this.previous = arg0;
	}

}