class Array
  def swap(i, j)
    return if i == j
    self[i], self[j] = self[j], self[i]
  end
end

### Decimal dominants
# Given an array with `n` keys, design an algorithm to find all values that occur more than `n/10` times. The expected running time of your algorithm should be linear.

n = 1000

a = (n / 2).times.map { rand(0..4) } + (n / 2).times.map { rand(0..99) }
d = 10

expected = a.tally.select { |_, v| v > n / d }.keys

### Solution

def count_dominants(arr, lo, hi, d, result)
  return if lo >= hi

  i  = lo
  lt = lo
  rt = hi
  pivot = arr[lo]

  while i <= rt
    case arr[i] <=> pivot
    when -1
      arr.swap(i, lt)
      i  += 1
      lt += 1
    when 1
      arr.swap(i, rt)
      rt -= 1
    else
      i += 1
    end
  end

  count = i - lt

  result << arr[lt] if count > arr.size / d

  count_dominants(arr, lo,     lt - 1, d, result)
  count_dominants(arr, rt + 1, hi,     d, result)
end

result = []
count_dominants(a, 0, a.size - 1, d, result)

p expected.sort!
p result.sort!
p expected == result
