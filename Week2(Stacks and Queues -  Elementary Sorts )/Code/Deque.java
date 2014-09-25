import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

	private Node<Item> head; // The front node of the queue
	private Node<Item> tail; // The end node of the queue
	private int size; // The size of the queue

	private static class Node<Item> {
		private Item item; // The payload element
		private Node<Item> next; // The pointer to the next node
		private Node<Item> previous; // The pointer to the previous node

		/** Returns the payload element. */
		public Item getItem() {
			return item;
		}

		/** Sets the payload element. */
		public void setItem(Item newItem) {
			this.item = newItem;
		}

		/** Returns the pointer to the next node */
		public Node<Item> getNext() {
			return next;
		}

		/** Sets the pointer to the next node */
		public void setNext(Node<Item> newNext) {
			this.next = newNext;
		}

		/** Returns the pointer to the previous node */
		public Node<Item> getPrevious() {
			return previous;
		}

		/** Sets the pointer to the previous node */
		public void setPrevious(Node<Item> newPrevious) {
			this.previous = newPrevious;
		}
	}

	public Deque() {
		head = null;
		tail = null;
		size = 0;
	}

	// is the deque empty?
	public boolean isEmpty() {
		return size == 0;
	}

	// return the number of items on the deque
	public int size() {

		return size;
	}

	// insert the item at the front
	public void addFirst(Item item) {
		if (item == null)
			throw new NullPointerException();
		Node<Item> newNode = new Node<Item>();
		newNode.setItem(item);
		newNode.setNext(head);
		newNode.setPrevious(null);

		if (isEmpty()) {
			head = newNode;
			tail = newNode;
		} else {
			head.setPrevious(newNode);
			head = newNode;
		}

		size++;
	}

	// insert the item at the end
	public void addLast(Item item) {
		if (item == null)
			throw new NullPointerException();
		Node<Item> newNode = new Node<Item>();
		newNode.setItem(item);
		newNode.setNext(null);
		newNode.setPrevious(tail);

		if (isEmpty()) {
			head = newNode;
			tail = newNode;
		} else {
			tail.setNext(newNode);
			tail = newNode;
		}

		size++;
	}

	// delete and return the item at the front
	public Item removeFirst() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		Item item = head.getItem();
		size--;
		if (isEmpty()) {
			head = null;
			tail = null;
		} else {
			head.getNext().setPrevious(null);
			head = head.getNext();
		}
		return item;
	}

	// delete and return the item at the end
	public Item removeLast() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		Item item = tail.getItem();
		size--;
		if (isEmpty()) {
			head = null;
			tail = null;
		} else {
			tail.getPrevious().setNext(null);
			tail = tail.getPrevious();
		}
		return item;
	}

	// return an iterator over items in order from front to end
	public Iterator<Item> iterator() {
		return new ListIterator();
	}

	private class ListIterator implements Iterator<Item> {
		private Node<Item> current = head;

		public boolean hasNext() {
			return current != null;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException();
			Item item = current.getItem();
			current = current.getNext();
			return item;
		}
	}

	public static void main(String[] args) // unit testing
	{
		Deque<Integer> deque = new Deque<Integer>();
		deque.addLast(1);
		deque.addLast(2);
		deque.addLast(3);
		deque.addFirst(-1);
		
	};
}