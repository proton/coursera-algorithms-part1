N = 1000
INITIAL_TIME = Time.now - N * 10

content = N.times.map do |n|
  [INITIAL_TIME + n * 10, n, (n + 1) % N].join(",")
end.join("\n")

File.write("log.txt", content)