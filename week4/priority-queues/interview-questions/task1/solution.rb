class MedianStructBaseline
  attr_reader :array

  def initialize
    @array = []
  end

  def insert(n)
    array << n
    array.sort!
  end

  def median
    array[median_i]
  end

  def remove_median
    array.delete_at(median_i)
  end

  private def median_i
    (array.size - 1) / 2
  end
end

class Heap
  attr_reader :arr, :type

  def initialize(type)
    @arr  = []
    @type = type
  end

  def push(value)
    i = arr.size
    arr << value
    parent_i = (i - 1) / 2

    while i > 0 && cmp(arr[i], arr[parent_i])
      swap(i, parent_i)
      i = parent_i
      parent_i = (i - 1) / 2
    end
  end

  def top
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

  private def heapify!(i)
    loop do
      left_child  = 2 * i + 1
      right_child = 2 * i + 2
      top_child   = i

      top_child = left_child  if left_child  < arr.size && cmp(arr[left_child],  arr[top_child])
      top_child = right_child if right_child < arr.size && cmp(arr[right_child], arr[top_child])

      break if top_child == i

      swap(i, top_child)
      i = top_child
    end
  end

  private def cmp(a, b)
    type == :max ? a > b : a < b
  end

  private def swap(i, j)
    arr[i], arr[j] = arr[j], arr[i]
  end
end

class MedianStruct
  attr_reader :min_heap, :max_heap, :median

  def initialize
    @min_heap = Heap.new(:min) # all elements are greater than median
    @max_heap = Heap.new(:max) # all elements are smaller than median
    @median   = nil
  end

  # insert in logarithmic time
  def insert(n)
    if median.nil?
      @median = n
    elsif n < median
      if max_heap.length < min_heap.length
        max_heap.push(n)
      elsif max_heap.top && n < max_heap.top
        min_heap.push(median)
        @median = max_heap.pop
        max_heap.push(n)
      else
        min_heap.push(median)
        @median = n
      end
    elsif n > median
      if min_heap.length <= max_heap.length
        min_heap.push(n)
      elsif n > min_heap.top
        max_heap.push(median)
        @median = min_heap.pop
        min_heap.push(n)
      else
        max_heap.push(median)
        @median = n
      end
    else # n == median
      if max_heap.length <= min_heap.length
        max_heap.push(n)
      else
        min_heap.push(n)
      end
    end
  end

  # find-the-median in constant time
  attr_reader :median

  # remove-the-median in logarithmic time
  def remove_median
    if max_heap.length >= min_heap.length
      @median = max_heap.pop
    else
      @median = min_heap.pop
    end
  end
end


base   = MedianStructBaseline.new
struct = MedianStruct.new

100.times do |x|
  n = rand(100)

  base.insert(n)
  struct.insert(n)

  unless base.median == struct.median
    puts "x#{x} | ERROR: base.median = #{base.median}, struct.median = #{struct.median}"

    p [base.median, base.array]
    p [struct.median, struct.max_heap.arr, struct.min_heap.arr]
    exit
  end
end

100.times do |y|
  base.remove_median
  struct.remove_median

  unless base.median == struct.median
    puts "y#{y} | ERROR: base.median = #{base.median}, struct.median = #{struct.median}"
    exit
  end
end

puts "OK"
