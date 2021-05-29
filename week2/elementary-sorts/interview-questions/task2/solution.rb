a = 100.times.map { rand(100) }
b = a.shuffle

# 1. Sort both arrays
a.sort!
b.sort!

# 2. Check if arrays are equal
are_equal = true
a.each_with_index do |x, i|
  if x != b[i]
    are_equal = false
    break
  end
end

puts are_equal
