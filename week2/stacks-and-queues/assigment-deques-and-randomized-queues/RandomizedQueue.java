import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
  private class RandomizedQueueIterator<Item> implements Iterator<Item> {
    private Item[] array;
    private int index;
    private int left;
    private int size;

    public RandomizedQueueIterator(Item[] array, int left, int size) {
      this.array = array;
      this.index = 0;
      this.left = left;
      this.size = size;
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasNext() {
      return index < size;
    }

    @Override
    public Item next() {
      if (!hasNext()) throw new NoSuchElementException();

      Item item = array[(left + index) % size];
      ++index;

      return item;
    }
  }

  private Item[] array;
  private int size = 0;
  private int capacity = 0;
  private int left = 0;

  // construct an empty randomized queue
  public RandomizedQueue() {
    //
  }

  // is the randomized queue empty?
  public boolean isEmpty() {
    return size == 0;
  }

  // return the number of items on the randomized queue
  public int size() {
    return size;
  }

  // add the item
  public void enqueue(Item item) {
    if (item == null) throw new IllegalArgumentException();

    if (capacity == size) {
      growArray();
    }

    ++size;
    write(size, item);
  }

  // remove and return a random item
  public Item dequeue() {
    if (isEmpty()) throw new NoSuchElementException();

    // TODO: if index == 0 => ++left ?

    int index = StdRandom.uniformInt(size);
    Item item = at(index);
    for (int i = index; i < size; ++i) {
      write(i, at(i + 1));
    }
    --size;

    if (capacity / 2 > size) {
      shrinkArray();
    }

    return item;
  }

  // return a random item (but do not remove it)
  public Item sample() {
    if (isEmpty()) throw new NoSuchElementException();

    int index = StdRandom.uniformInt(size);
    return at(index);
  }

  // return an independent iterator over items in random order
  public Iterator<Item> iterator() {
    return new RandomizedQueueIterator<>(array, left, size);
  }

  private Item at(int index) {
    return array[(left + index) % size];
  }

  private void write(int index, Item item) {
    array[(left + index) % size] = item;
  }

  private void growArray() {
    int newSize = capacity == 0 ? 2 : capacity * 2;
    resizeArray(newSize);
  }

  private void shrinkArray() {
    int newSize = capacity == 2 ? 0 : capacity / 2;
    resizeArray(newSize);
  }

  @SuppressWarnings("unchecked")
  private void resizeArray(int newSize) {
    Item[] newArray = (Item[]) new Object[newSize];
    for (int i = 0; i < size; ++i) {
      newArray[i] = at(i);
    }
    left = 0;
    capacity = newSize;
    array = newArray;
  }

  // unit testing (required)
  public static void main(String[] args) {
    RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
    assert queue.isEmpty() == true;
    assert queue.size() == 0;

    queue.enqueue(1);
    queue.enqueue(2);
    queue.enqueue(3);
    queue.enqueue(4);

    assert queue.size() == 4;

    Iterator<Integer> it = queue.iterator();
    for (int i = 0; i < 4; ++i) {
      assert it.hasNext() == true;
      it.next();
    }
    assert it.hasNext() == false;

    queue.dequeue();
    assert queue.size() == 3;
    queue.dequeue();
    assert queue.size() == 2;
    queue.dequeue();
    assert queue.size() == 1;
    queue.dequeue();
    assert queue.size() == 0;
  }
}
