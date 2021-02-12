class Stack
  attr_reader :arr

  def initialize
    @arr = []
  end

  def push(value)
    arr << value
  end

  def pop
    arr.pop
  end

  def empty?
    arr.empty?
  end
end

class Queue
  attr_reader :stack1
  attr_reader :stack2

  def initialize
    @stack1 = Stack.new
    @stack2 = Stack.new
  end

  def enqueue(value)
    stack1.push(value)
  end

  def dequeue
    if stack2.empty?
      until stack1.empty?
        stack2.push(stack1.pop)
      end
    end

    stack2.pop
  end
end

q = Queue.new
q.enqueue 1
q.enqueue 2
q.enqueue 3

p q.dequeue
p q.dequeue
p q.dequeue
