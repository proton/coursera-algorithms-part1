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

    Node<Item> node = new Node<Item>(item);
    if (isEmpty()) {
      head = node;
      tail = node;
    } else {
      node.nextNode = head;
      head.prevNode = node;
      head = node;
    }
    ++size;
  }

  // add the item to the back
  public void addLast(Item item) {
    if (item == null) throw new IllegalArgumentException();

    Node<Item> node = new Node<Item>(item);
    if (isEmpty()) {
      head = node;
      tail = node;
    } else {
      node.prevNode = tail;
      tail.nextNode = node;
      tail = node;
    }
    ++size;
  }

  // remove and return the item from the front
  public Item removeFirst() {
    if (isEmpty()) throw new NoSuchElementException();

    --size;
    Node<Item> node = head;
    head = head.nextNode;
    if (head != null) {
      head.prevNode = null;
    } else {
      tail = null;
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
    } else {
      head = null;
    }

    return node.value;
  }

  // return an iterator over items in order from front to back
  public Iterator<Item> iterator() {
    return new DequeIterator<Item>(head);
  }

  // unit testing (required)
  public static void main(String[] args) {
    Deque<Integer> deque = new Deque<Integer>();
    assert deque.isEmpty();
    assert deque.size() == 0;

    deque.addFirst(1);
    deque.addFirst(2);
    deque.addLast(3);
    deque.addLast(4);

    assert deque.size() == 4;
    assert !deque.isEmpty();

    int[] array = new int[]{ 2, 1, 3, 4 };
    Iterator<Integer> it = deque.iterator();
    for (int i = 0; i < array.length; ++i) {
      assert it.hasNext();
      assert it.next() == array[i];
    }
    assert !it.hasNext();

    assert deque.removeFirst() == 2;
    assert deque.removeLast() == 4;
    assert deque.size() == 2;
  }
}
