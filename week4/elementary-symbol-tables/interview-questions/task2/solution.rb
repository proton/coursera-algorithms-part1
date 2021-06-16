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

# Given a binary tree where each Node contains a key, determine whether it is a binary search tree. Use extra space proportional to the height of the tree.

bsts = []
bsts << build_tree([4, 2, 6, 1, 3, 5, 7])
bsts << build_tree([1, nil, 2])
bsts << build_tree([3, 2, nil, 1])
bsts << build_tree([5, 2, 8, 1, 3, nil, 9, nil, nil, nil, 4])

non_bsts = []
non_bsts << build_tree([3, 4, 1, nil, 2])
non_bsts << build_tree([1, 2, 3, 4, 5, 6, 7])
non_bsts << build_tree([4, 2, 6, 1, 3, 7, 5])

def is_bst?(root)
  if root.left
    return false if root.left.value > root.value
    return false unless is_bst?(root.left)
  end
  if root.right
    return false if root.right.value < root.value
    return false unless is_bst?(root.right)
  end
  true
end

bsts.each do |tree|
  fail unless is_bst?(tree)
end

non_bsts.each do |tree|
  fail if is_bst?(tree)
end
