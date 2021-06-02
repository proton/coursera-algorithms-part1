class LinkedList
  class Node
    attr_accessor :value, :next_node

    def initialize(value:, next_node: nil)
      @value     = value
      @next_node = next_node
    end
  end

  attr_reader :head, :length

  def initialize(array = nil)
    @head   = nil
    @length = 0

    return if array.nil?
    array.each do |value|
      push(value)
    end
  end

  def first
    first_node&.value
  end

  def last
    last_node&.value
  end

  def at(index)
    node_at(index)&.value
  end

  def push(value)
    node = Node.new(value: value)
    if length == 0
      @head = node
    else
      last_node.next_node = node
    end
    @length += 1
    value
  end

  def prepend(value)
    @head = Node.new(value: value, next_node: @head)
    @length += 1
    value
  end

  def pop
    return nil if length == 0

    node = pre_last_node
    last_value = node.next_node.value
    node.next_node = nil
    @length -= 1
    last_value
  end

  def pop_front
    return nil if length == 0

    old_head = head
    @head = head.next_node
    @length -= 1
    old_head.value
  end

  def insert(position, value)
    prev_node = node_at(position - 1)
    next_node = prev_node.next_node
    prev_node.next_node = Node.new(value: value, next_node: next_node)
    @length += 1
    value
  end

  def remove(position)
    return pop_front if position == 0

    prev_node = node_at(position - 1)
    next_node = prev_node.next_node
    prev_node.next_node = next_node.next_node

    @length -= 1

    next_node.value
  end

  def each(&block)
    node = head
    while node
      yield node.value
      node = node.next_node
      break if node == head
    end
  end

  def contains?(search_value)
    each do |value|
      return true if value == search_value
    end
    false
  end

  def to_array
    array = []
    each do |value|
      array << value
    end
    array
  end

  private def first_node
    head
  end

  private def last_node
    node = head
    node = node.next_node while node.next_node
    node
  end

  private def pre_last_node
    node = head
    node = node.next_node while node.next_node&.next_node
    node
  end

  private def node_at(index)
    counter = 0
    node = head
    while node && counter < index
      node = node.next_node
      counter += 1
    end
    node
  end
end

def next_node(node, head)
  node.next_node ||= head
  node = node.next_node
  node.next_node ||= head
  node
end

def shuffle(l)
  lg = Math.log(l.length).round

  chance = 1.0 / l.length

  tmp = []

  head = l.head
  current_node = head

  node1 = head
  node2 = head
  (l.length * 10).times do |i|
    # p [i, l.length * lg]

    # Swap nodes
    if rand < 0.5 && node1 != node2
      node11  = next_node(node1,  head)
      node111 = next_node(node11, head)
      node22  = next_node(node2,  head)
      node222 = next_node(node22, head)

      if node1 == node22
        node2.next_node   = node222
        node222.next_node = node22
        node22.next_node  = node111
      elsif node2 == node11
        node1.next_node   = node111
        node111.next_node = node11
        node11.next_node  = node222
      else
        node1.next_node = node22; node22.next_node = node111
        node2.next_node = node11; node11.next_node = node222
      end
    end

    node1 = next_node(node1, head)
    node2 = next_node(next_node(node2, head), head)
  end
end

### Shuffling a linked list

# Given a singly-linked list containing n items, rearrange the items uniformly at random. Your algorithm should consume a logarithmic (or constant) amount of extra memory and run in time proportional to n*log(n) in the worst case.

n = 10000
l = LinkedList.new((0...n).to_a)

l0 = l.to_array

p l0

shuffle(l)

l1 = l.to_array

p l1

cnt = 0

l0.each_with_index { |x, i| cnt += 1 if l1[i] != x }

p cnt.to_f * 100 / l.length

