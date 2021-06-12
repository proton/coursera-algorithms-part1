### Taxicab numbers

# A `taxicab` number is an integer that can be expressed as the sum of two cubes of positive integers in two different ways: `a^3 + b^3 = c^3 + d^3`.
# For example, `1729` is the smallest taxicab number: `9^3 + 10^3 = 1^3 + 12^3`.
# Design an algorithm to find all taxicab numbers with `a`, `b`, `c`, and `d` less than `n`.

# - Version 1: Use time proportional to `(n^2)*log(n)` and space proportional to `n^2`.
# - Version 2: Use time proportional to `(n^2)*log(n)` and space proportional to `n`.

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

p baseline(30)
