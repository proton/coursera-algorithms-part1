class MyArray
  attr_reader :array

  def initialize
    @array = 3000.times.map { rand(1..3) }
  end

  def size
    array.size
  end

  def swap(i, j)
    # p [:swap, i, j, "#{array[i]} <-> #{array[j]}"]
    @swap_cnt ||= 0
    @swap_cnt += 1
    # raise 'Too many swap calls' if @swap_cnt > size

    array[i], array[j] = array[j], array[i]
    # self.print
  end

  def color(i)
    #p [:color, i, array[i]]
    @color_cnt ||= 0
    @color_cnt += 1
    #raise 'Too many color calls' if @color_cnt > size

    array[i]
  end

  def sorted?
    if array == array.sort
      true
    else
      puts "==="
      p array
      p array.sort
      false
    end
  end

  def print
    p array
  end

  def print_stats
    puts "Elements: #{size}"
    puts "Swap count: #{@swap_cnt} (#{(100 * @swap_cnt / size).round(2)}%})"
    puts "Color check count: #{@color_cnt} (#{(100 * @color_cnt / size).round(2)}%})"
  end
end

array = MyArray.new

i, j = 0, array.size - 1

i += 1 while array.color(i) == 1
x = i

j -= 1 while array.color(j) == 3
y = j

while x < y && i <= j
  ki = array.color(i)
  case ki
  when 1
    array.swap(i, x) unless i == x
    x += 1
    i = [i, x].max
  when 2
    i += 1
  when 3
    array.swap(i, y) unless i == y
    y -= 1
    j = [j, y].min
  end

  kj = array.color(j)
  case kj
  when 1
    array.swap(j, x) unless j == x
    x += 1
    i = [i, x].max
  when 2
    j -= 1
  when 3
    array.swap(j, y) unless j == y
    y -= 1
    j = [j, y].min
  end
end

puts array.sorted?
array.print_stats
