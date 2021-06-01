# a = 100.times.map { rand(0..99) }
a = [2, 4, 1, 3, 5]

# In this array: [2, 4, 1, 3, 5], there are three inversions: 2,1; 4,1; 4,3

cnt = 0

(0...(a.size - 1)).each do |i|
((i + 1)...a.size).each do |j|
  cnt += 1 if a[i] > a[j]
end
end

p cnt
