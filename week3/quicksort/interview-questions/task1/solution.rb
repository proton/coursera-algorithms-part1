class Obj
  attr_reader :size

  def initialize(size)
    @size = size
  end

  def <(other)
    (self <=> other) < 0
  end

  def >(other)
    (self <=> other) > 0
  end

  def >=(other)
    (self <=> other) >= 0
  end

  def <=(other)
    (self <=> other) <= 0
  end

  def ==(other)
    (self <=> other) == 0
  end

  def <=>(other)
    raise "Can compare only nuts and bolts" unless comparable?(other)
    size <=> other.size
  end

  def comparable?(other)
    other.is_a?(other_class)
  end

  def inspect
    "#{self.class.to_s[0]}#{size}"
  end
end

class Nut < Obj
  def other_class
    Bolt
  end
end

class Bolt < Obj
  def other_class
    Nut
  end
end

def sort(array1, array2, start: nil, finish: nil)
  start  ||= 0
  finish ||= array1.size - 1

  return if start >= finish

  divider1 = array1[(finish - start) / 2 + start]
  divider2 = nil

  # p divider1.size

  # p [start, finish, divider1, divider2]

  # p array1
  # p array2
  # p "==="

  left  = start
  right = finish
  while left <= right
    left  += 1 while array2[left]  < divider1
    right -= 1 while array2[right] > divider1

    unless divider2
      divider2 = array2[left]  if array2[left]  == divider1
      divider2 = array2[right] if array2[right] == divider1
    end

    if left <= right
      # p [left, right, array2[left], array2[right]]
      array2[left], array2[right] = array2[right], array2[left]
      left  += 1
      right -= 1
    end
  end
  left1  = left
  right1 = right

  # p array1[start..finish]
  # p array2[start..finish]
  # p [left, right]
  # p array2[left]
  # p [array2[start..right], divider1, array2[left..finish], :!, start, right, left, finish]

  # p [start, finish, divider1, divider2, array1, array2]

  left  = start
  right = finish
  while left <= right
    left  += 1 while array1[left]  < divider2
    right -= 1 while array1[right] > divider2

    if left <= right
      array1[left], array1[right] = array1[right], array1[left]
      left  += 1
      right -= 1
    end
  end
  left2  = left
  right2 = right

  # p [array1[start..right], divider2, array1[left..finish], :!, start, right, left, finish]
  # p array1[start..finish]
  # p array2[start..finish]
  # p [left, right]
  # p array1[left]

  # return if start == finish - 1

  right = [right1, right2].max
  left  = [left1,  left2 ].min

  sort(array1, array2, start: start, finish: right)
  sort(array1, array2, start: left,  finish: finish)
end

n = 10

def test(n)
  bolt_pile = (0...n).map { |l| Bolt.new(l) }.shuffle
  nut_pile  = (0...n).map { |l| Nut.new(l)  }.shuffle
  sort(bolt_pile, nut_pile)
  bolt_pile.zip(nut_pile).all? { |bolt, nut| bolt == nut }
end

require "benchmark"
puts Benchmark.measure { "a"*1_000_000_000 }

h = {}
[100, 1000, 10000, 1000000].each do |n|
  b = Benchmark.measure { raise unless test(n) }
  h[n] = b.real
end
p h
