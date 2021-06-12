class Heap
  attr_reader :arr

  def initialize(values = [])
    @arr = values.dup

    (arr.size / 2 - 1).downto(0).each do |i|
      heapify_down(i)
    end
  end

  def push(value)
    arr << value
    heapify_up(arr.size - 1)
  end

  def max
    arr.first
  end

  def pop
    value = arr[0]
    last  = arr.pop
    unless arr.empty?
      arr[0] = last
      heapify_down(0)
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
    index = rand(arr.size)
    x = arr[index]

    swap(index, arr.size - 1)
    arr.pop

    unless index == arr.size
      if index == 0 || arr[index] <= arr[parent_index(index)]
        heapify_down(index)
      else
        heapify_up(index)
      end
    end

    x
  end

  def valid_heap?
    return true if arr.empty?
    (0..(arr.size / 2 - 1)).each do |parent_i|
      left_i  = left_index(parent_i)
      right_i = right_index(parent_i)
      return false if left_i  < arr.size && arr[left_i]  > arr[parent_i]
      return false if right_i < arr.size && arr[right_i] > arr[parent_i]
    end
    true
  end

  private def heapify_up(index)
    parent_i = parent_index(index)

    return if index == 0 || @arr[index] <= @arr[parent_i]

    swap(index, parent_i)
    heapify_up(parent_i)
  end

  private def heapify_down(index)
    left_i  = left_index(index)
    right_i = right_index(index)
    largest_child = index

    largest_child = left_i  if left_i  < arr.size && arr[left_i]  > arr[largest_child]
    largest_child = right_i if right_i < arr.size && arr[right_i] > arr[largest_child]

    return if largest_child == index

    swap(index, largest_child)
    heapify_down(largest_child)
  end

  private def parent_index(index)
    (index - 1) / 2
  end

  def left_index(index)
    2 * index + 1
  end

  def right_index(index)
    2 * index + 2
  end

  private def swap(i, j)
    return if i == j
    arr[i], arr[j] = arr[j], arr[i]
  end
end

100.times do
  n = rand(3..9)
  a = Array.new(n) { rand(1..99) }.uniq

  h1 = Heap.new(a)
  x  = h1.delRandom
  b  = a - [x]
  h2 = Heap.new(b)

  unless h1.arr.sort == h2.arr.sort && h1.valid_heap? && h2.valid_heap?
    puts "ERROR: h1 = #{h1.arr}, h2 = #{h2.arr} | #{h1.valid_heap?} #{h2.valid_heap?}"
    exit
  end
end

puts "OK"
