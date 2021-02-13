import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
  private class Node<Item> {
    public Item value;
    public Node<Item> nextNode;
    public Node<Item> prevNode;

    Node(Item value) {
      this.value = value;
    }
  }

  private class DequeIterator<Item> implements Iterator<Item> {
    private Node<Item> current;

    public DequeIterator(Node<Item> head) {
      current = head;
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasNext() {
      return current != null;
    }

    @Override
    public Item next() {
      if (!hasNext()) throw new NoSuchElementException();

      Item item = current.value;
      current = current.nextNode;

      return item;
    }
  }

  private Node<Item> head;
  private Node<Item> tail;
  private int size = 0;

  // construct an empty deque
  public Deque() {
    //
  }

  // is the deque empty?
  public boolean isEmpty() {
    return size == 0;
  }

  // return the number of items on the deque
  public int size() {
    return size;
  }

  // add the item to the front
  public void addFirst(Item item) {
    if (item == null) throw new IllegalArgumentException();

    ++size;
    Node<Item> node = new Node<Item>(item);
    if (isEmpty()) {
      head = node;
      tail = node;
    } else {
      node.nextNode = head;
      head.prevNode = node;
      head = node;
    }
  }

  // add the item to the back
  public void addLast(Item item) {
    if (item == null) throw new IllegalArgumentException();

    ++size;
    Node<Item> node = new Node<Item>(item);
    if (isEmpty()) {
      head = node;
      tail = node;
    } else {
      node.prevNode = tail;
      tail.nextNode = node;
      tail = node;
    }
  }

  // remove and return the item from the front
  public Item removeFirst() {
    if (isEmpty()) throw new NoSuchElementException();

    --size;
    Node<Item> node = head;
    head = head.nextNode;
    if (head != null) {
      head.prevNode = null;
    }

    return node.value;
  }

  // remove and return the item from the back
  public Item removeLast() {
    if (isEmpty()) throw new NoSuchElementException();

    --size;
    Node<Item> node = tail;
    tail = head.prevNode;
    if (tail != null) {
      tail.nextNode = null;
    }

    return node.value;
  }

  // return an iterator over items in order from front to back
  public Iterator<Item> iterator() {
    return new DequeIterator<Item>(head);
  }

  // unit testing (required)
  public static void main(String[] args) {
    //
  }
}
