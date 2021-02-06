class QuickUnionWeighted
  attr_reader :parents, :sizes, :maxes, :length

  def initialize(n)
    @parents = Array.new(n) { |i| i }
    @sizes   = Array.new(n, 1)
    @maxes   = Array.new(n) { |i| i }
    @length  = n
  end

  def connected?(a, b)
    root(a) == root(b)
  end

  def union(a, b)
    a_root = root(a)
    b_root = root(b)
    return if a_root == b_root

    if sizes[a_root] < sizes[b_root]
      parents[a_root] = b_root
      sizes[b_root] += sizes[a_root]
      maxes[b_root] = [maxes[a_root], maxes[b_root]].max
    else
      parents[b_root] = a_root
      sizes[a_root] += sizes[b_root]
      maxes[a_root] = [maxes[a_root], maxes[b_root]].max
    end

    @length -= 1
  end

  def find(i)
    maxes[root(i)]
  end

  private def root(x)
    x = parents[x] until x == parents[x]
    x
  end
end

uf = QuickUnionWeighted.new(10)
uf.union(1, 2)
uf.union(6, 9)
uf.union(2, 6)

[1, 2, 6, 9].each do |i|
  fail "#{i} => #{uf.find(i)}" unless uf.find(i) == 9
end

[0, 3, 4, 5, 7, 8].each do |i|
  fail "#{i} => #{uf.find(i)}" if uf.find(i) == 9
end