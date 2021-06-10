def kth(array1, array2, k)
  return kth(array2, array1, k)     if array1.size > array2.size
  return array2[k]                  if array1.empty?
  return [array1[0], array2[0]].min if k == 0

  low  = [0, k - array2.size].max
  high = [k, array1.size    ].min

  while low <= high
    partition1 = (low + high) / 2
    partition2 = k - partition1

    max_left_1  = partition1 > 0           ? array1[partition1 - 1] : Float::NEGATIVE_INFINITY
    max_left_2  = partition2 > 0           ? array2[partition2 - 1] : Float::NEGATIVE_INFINITY
    min_right_1 = partition1 < array1.size ? array1[partition1]     : Float::INFINITY
    min_right_2 = partition2 < array2.size ? array2[partition2]     : Float::INFINITY

    if max_left_1 <= min_right_2 && max_left_2 <= min_right_1
      return [min_right_1, min_right_2].min
    elsif max_left_1 > min_right_2
      high = partition1 - 1
    else
      low  = partition1 + 1
    end
  end
end

# Given two sorted arrays `a[]` and `b[]`, of lengths `n1` and `n2` and an integer `0 ≤ k < n1 + n2`
n = 10
n1 = n
n2 = n

a = n1.times.map { rand(10..99) }.sort
b = n2.times.map { rand(10..99) }.sort
k = rand(0...(n1 + n2))

c = (a + b).sort

p kth(a, b, k) == c[k]
