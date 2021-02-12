require "benchmark"

def fast_solution(array, x)
  return -1 if x < array[0] && x < array[array.size - 1]

  # 1. Find bionic point
  find_bionic_point = lambda do |array, l, r|
    c = (l + r) / 2
    e0 = array[c - 1]
    e1 = array[c]
    e2 = array[c + 1]
    return c if e0 < e1 && e1 > e2

    if e0 < e1 && e1 < e2
      find_bionic_point[array, c + 1, r]
    else
      find_bionic_point[array, l, c - 1]
    end
  end
  k = find_bionic_point.call(array, 0, array.size - 1)
  return -1 if x > array[k]
  return index if x == array[k]

  # 2. Binary search
  bsearch = lambda do |array, l, r, x, dir|
    while l <= r do
      c = l + (r - l) / 2

      case array[c] <=> x
      when 0 then return c
      when dir
        r = c - 1
      else
        l = c + 1
      end
    end
  end

  bsearch.call(array, 0, k - 1, x, 1) || bsearch.call(array, k + 1, array.size - 1, x, -1) || -1
end

def slow_solution(array, x)
  (0...array.size).each do |i|
    return i if array[i] == x
  end
end

file = Dir.glob("data/*").first
numbers = File.open(file).each_line.map(&:to_i).sort

results = {}

[1000, 10_000, 100_000, 1000_000].each do |n|
  part = numbers[0...n]
  search_objects = part.sample(100)

  slow_times = []
  fast_times = []

  8.times do |x|
    k = (1 + x) * part.size / 10
    array = part[0...k] + part[k..-1].reverse
    search_objects.each do |x|
      r0 = nil
      r1 = nil
      slow_times << Benchmark.measure { r0 = slow_solution(array, x) }
      fast_times << Benchmark.measure { r1 = fast_solution(array, x) }
      raise "#{x}: #{r0} != #{r1}" unless r0 == r1
    end
  end
  slow = slow_times.map(&:real).sum
  fast = fast_times.map(&:real).sum
  puts "#{n}\t#{slow}\t| #{fast}"
end

