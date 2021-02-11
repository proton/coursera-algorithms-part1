require "benchmark"
require "table_print"

def three_sum(numbers)
  cnt = 0
  (0...numbers.size).each do |i|
    ((i + 1)...numbers.size).each do |j|
      ((j + 1)...numbers.size).each do |k|
        cnt += 1 if numbers[i] + numbers[j] + numbers[k] == 0
      end
    end
  end
  cnt
end

files = Dir.glob("data/*")
results = {}

files.each do |file|
  puts file
  numbers = File.open(file).each_line.map(&:to_i)
  result = nil
  time = Benchmark.measure { result = three_sum(numbers) }
  results[file] = [numbers.size, result, time.real.to_f]
  p [file, results[file]]
end

results.sort_by { |k, v| v[1] }.to_h.each do |file, (n, result, time)|
  puts "#{n} | #{result.to_s.ljust(5)} | #{time}"
end

