def baseline(n)
  arr = []

  1.upto(n) do |a|
  1.upto(n) do |b|
  1.upto(n) do |c|
  1.upto(n) do |d|
    next if [a, b, c, d].uniq.size < 4
    r1 = a**3 + b**3
    r2 = c**3 + d**3
    arr << r1 if r1 == r2
  end
  end
  end
  end

  arr.sort.uniq
end

def solution(n)
  # n
  numbers = (1...n).map { |i| i**3 }

  # n^2
  # Instead of using a hash, we could use an array to store the sums.
  h = {}
  numbers.each_with_index do |a, i|
  ((i + 1)...(n - 1)).each do |j|
    b = numbers[j]
    s = a + b
    h[s] ||= []
    h[s] << [a, b]
  end
  end

  h = h.select { |k, v| v.size > 1 }
  h.keys
end

p baseline(30)
p solution(30)
