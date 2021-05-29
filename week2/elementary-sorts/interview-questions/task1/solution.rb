a = [[1, 2], [3, 4], [5, 6], [7, 8], [9, 10]]
b = [[3, 2], [7, 8], [3, 4], [5, 1], [9, 10]]

compare = lambda do |a, b|
  if a[0] == b[0]
    a[1] <=> b[1]
  else
    a[0] <=> b[0]
  end
end

# 1. Sort both arrays

a.sort!(&compare)
b.sort!(&compare)

# 2. Count common points

count = 0

i = 0
j = 0

while i < a.length && j < b.length
  case compare.call(a[i], b[j])
  when 0
    count += 1
    i += 1
    j += 1
  when -1
    i += 1
  when +1
    j += 1
  end
end

puts count
