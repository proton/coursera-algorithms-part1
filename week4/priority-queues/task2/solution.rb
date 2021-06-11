class Heap
  attr_reader :arr

  def initialize(values = [])
    @arr = values.dup

    (arr.size / 2 - 1).downto(0).each do |i|
      heapify!(i)
    end
  end

  def push(value)
    i = arr.size
    arr << value
    parent_i = (i - 1) / 2

    while i > 0 && arr[parent_i] < arr[i]
      swap(i, parent_i)
      i = parent_i
      parent_i = (i - 1) / 2
    end
  end

  def max
    arr.first
  end

  def pop
    value = arr[0]
    last  = arr.pop
    unless arr.empty?
      arr[0] = last
      heapify!(0)
    end
    value
  end

  def length
    arr.size
  end

  def sample
    arr.sample
  end

  # logarithmic time
  def delRandom
    i = rand(arr.size)
    x = arr[i]

    swap(i, arr.size - 1)
    arr.pop

    (0...arr.size).each do |k|
      heapify!(k)
    end
    (0...arr.size).each do |k|
      heapify!(k)
    end
    (0...arr.size).each do |k|
      heapify!(k)
    end

    x
  end

  def valid_heap?
    return true if arr.empty?
    (0..(arr.size / 2 - 1)).each do |parent_idx|
      left_child_idx  = 2 * parent_idx + 1
      right_child_idx = 2 * parent_idx + 2
      return false if left_child_idx  < arr.size && arr[left_child_idx]  > arr[parent_idx]
      return false if right_child_idx < arr.size && arr[right_child_idx] > arr[parent_idx]
    end
    true
  end

  private def heapify!(i)
    loop do
      left_child  = 2 * i + 1
      right_child = 2 * i + 2
      largest_child = i

      largest_child = left_child  if left_child  < arr.size && arr[left_child]  > arr[largest_child]
      largest_child = right_child if right_child < arr.size && arr[right_child] > arr[largest_child]

      break if largest_child == i

      swap(i, largest_child)
      i = largest_child
    end
  end

  private def swap(i, j)
    arr[i], arr[j] = arr[j], arr[i]
  end
end

n = rand(3..9)
a = Array.new(n) { rand(1..99) }.uniq

h1 = Heap.new(a)
x  = h1.delRandom
b  = a - [x]
h2 = Heap.new(b)

p h1.arr.sort == h2.arr.sort && h1.valid_heap? && h2.valid_heap?
