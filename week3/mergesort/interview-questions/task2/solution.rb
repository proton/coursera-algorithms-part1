# In this array: [2, 4, 1, 3, 5], there are three inversions: 2,1; 4,1; 4,3

require 'benchmark'

def base_line_solution(array)
  cnt = 0

  (0...(array.size - 1)).each do |i|
  ((i + 1)...array.size).each do |j|
    cnt += 1 if array[i] > array[j]
  end
  end

  cnt
end

def solution(array)
  count_inversions(array)
end

def count_inversions(array)
  return 0 if array.size <= 1

  cnt = 0

  n = array.size / 2
  pivot = array[n]

  sa1 = []
  sa2 = []
  sa3 = []
  sa4 = []

  (0...n).each do |i|
    x = array[i]
    if x <= pivot
      sa1 << x
    else
      sa2 << x
    end
  end

  ((n + 1)...(array.size)).each do |i|
    x = array[i]
    if x < pivot
      sa3 << x
    else
      sa4 << x
    end
  end

  cnt += sa2.size
  cnt += sa3.size * (1 + sa2.size)

  cnt += count_inversions(array[0...n])
  cnt += count_inversions(array[(n + 1)...array.size])

  cnt += count_inversions_of_in(sa1, sa3)
  cnt += count_inversions_of_in(sa2, sa4)

  cnt
end

def count_inversions_of_in(of_array, in_array)
  return 0 if of_array.empty? || in_array.empty?

  cnt = 0

  # TODO: sort!!!!

  of_array.each do |x|
  in_array.each do |y|
    cnt += 1 if x > y
  end
  end

  cnt
end

# Validation:
n = 1000
a = n.times.map { rand(0..9999) }

x = base_line_solution(a.clone)
y = solution(a.clone)

if x == y
  puts "OK"
  puts
else
  raise "#{x} != #{y}}"
end

ns = [100, 1000, 10000]

as = ns.map do |n|
  a = n.times.map { rand(0..9999) }
  t = Benchmark.measure { solution(a) }
  t.real
end

p as.map { |t| t / as[0] }
