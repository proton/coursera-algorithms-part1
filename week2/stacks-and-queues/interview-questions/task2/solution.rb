class Stack
  attr_reader :arr, :maxes

  def initialize
    @arr = []
    @maxes = []
  end

  def push(value)
    arr << value
    if maxes.empty?
      maxes << value
    else
      maxes << [value, maxes.last].max
    end
  end

  def pop
    maxes.pop
    arr.pop
  end

  def empty?
    arr.empty?
  end

  def max
    maxes.last
  end
end

stack = Stack.new
stack.push 3
stack.push 2
stack.push 5

p stack.max

p stack.pop

p stack.max

p stack.pop

p stack.max

p stack.pop
