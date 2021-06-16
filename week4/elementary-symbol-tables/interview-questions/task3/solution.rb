class TreeNode
  attr_accessor :value, :left, :right

  def initialize(value = 0, left = nil, right = nil)
    @value = value
    @left = left
    @right = right
  end

  def to_a
    arr = []
    arr += left.to_a if left
    arr << value
    arr += right.to_a if right
    arr
  end
end

def build_tree(array)
  root = TreeNode.new(array[0])
  queue = [root]
  i = 1

  while i < array.size
    current = queue.shift

    if array[i]
      current.left = TreeNode.new(array[i])
      queue << current.left
    end
    i += 1

    if array[i]
      current.right = TreeNode.new(array[i])
      queue << current.right
    end
    i += 1
  end

  root
end

# Morris traversal

tree = build_tree([4, 2, 6, 1, 3, 5, 7])

 # Use Morris Tree Traversal.
def morris_traversal(current_root)
  while current_root
    if current_root.left
      current = current_root.left
      current = current.right while current.right
      current.right = current_root

      temp = current_root
      current_root = current_root.left
      temp.left = nil
    else
      yield current_root.value
      current_root = current_root.right
    end
  end
end

arr = []
morris_traversal(tree) { |value| arr << value }
fail unless arr == arr.sort
p arr
