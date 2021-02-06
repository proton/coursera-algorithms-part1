class QuickUnionWeighted
  attr_reader :parents, :sizes, :length

  def initialize(n)
    @parents = Array.new(n) { |i| i }
    @sizes   = Array.new(n, 1)
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
    else
      parents[b_root] = a_root
      sizes[a_root] += sizes[b_root]
    end

    @length -= 1
  end

  private def root(x)
    x = parents[x] until x == parents[x]
    x
  end
end

N = 1000

uf = QuickUnionWeighted.new(N)
File.open("log.txt").each do |line|
  time, a, b = line.split(",")
  uf.union(a.to_i, b.to_i)
  if uf.length == 1
    puts time
    break
  end
end