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

  left  = start
  right = finish
  i     = start
  while i <= right
    case array2[i] <=> divider1
    when -1
      array2[i], array2[left] = array2[left], array2[i]
      i    += 1
      left += 1
    when 0
      divider2 = array2[i]
      i += 1
    when 1
      array2[i], array2[right] = array2[right], array2[i]
      right -= 1
    end
  end

  left  = start
  right = finish
  i     = start
  while i <= right
    case array1[i] <=> divider2
    when -1
      array1[i], array1[left] = array1[left], array1[i]
      i    += 1
      left += 1
    when 0
      i += 1
    when 1
      array1[i], array1[right] = array1[right], array1[i]
      right -= 1
    end
  end

  sort(array1, array2, start: start,    finish: left - 1)
  sort(array1, array2, start: left + 1, finish: finish  )
end

n = 10

def test(n)
  bolt_pile = (0...n).map { |l| Bolt.new(l) }.shuffle
  nut_pile  = (0...n).map { |l| Nut.new(l)  }.shuffle
  sort(bolt_pile, nut_pile)

  unless bolt_pile.zip(nut_pile).all? { |bolt, nut| bolt == nut }
    p bolt_pile
    p nut_pile
    raise "Not sorted"
  end
end

require "benchmark"

h = {}
[100, 1000, 10000, 100000].each do |n|
  h[n] = 10.times.map { Benchmark.measure { test(n) }.real }.sum / 10
end

min_k = h.keys.min
min_v = h.values.min

h.each do |k, v|
  puts "#{k / min_k}:\t#{(v / min_v).round(2)}"
end
