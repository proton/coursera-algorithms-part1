# def merge_sort(array, start: nil, finish: nil, buffer: nil)
#   return if start == finish

#   mid = (finish - start) / 2 + start

#   merge_sort(array, start: start,   finish: mid,    buffer: buffer)
#   merge_sort(array, start: mid + 1, finish: finish, buffer: buffer)

#   i = 0
#   j = 0
#   b = 0
#   loop do
#     remaining = (j + mid + 1)..finish if i == mid + 1 - start
#     remaining = (i + start)..mid      if j == finish - mid
#     if remaining
#       remaining.each do |k|
#         buffer[b] = array[k]
#         b += 1
#       end
#       break
#     end
#     x = array[i + start]
#     y = array[j + mid + 1]
#     if x < y
#       buffer[b] = x
#       i += 1
#     else
#       buffer[b] = y
#       j += 1
#     end
#     b += 1
#   end
#   (0...b).each do |k|
#     array[start + k] = buffer[k]
#   end

#   array
# end

# n = 10
# a = n.times.map { rand(0..99) }.sort + n.times.map { rand(0..99) }.sort
# b = Array.new(n)

# p a

# k = 0
# i = 0
# j = n

# while k < n do
#   if a[i] <= a[j]
#     b[k] = a[i]
#     a[i] = nil
#     i += 1
#   else
#     b[k] = a[j]
#     a[j] = nil
#     j += 1
#   end
#   k += 1
# end

# i = 2 * n - 1
# k = 2 * n - 1
# while k >= n do
#   if a[i]
#     a[k] = a[i] unless k == i
#     k -= 1
#   end
#   i -= 1
# end

# b.each_with_index do |e, i|
#   a[i] = e
# end

# merge_sort(a, start: n, finish: 2*n - 1, buffer: b)

# p b
# p a
# p a.sort
# p a == a.sort
