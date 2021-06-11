# Design a data type that supports:

# - insert in logarithmic time
# - find-the-median in constant time
# - remove-the-median in logarithmic time

# If the number of keys in the data type is even, find/remove the lower median.

class MedianStruct
  attr_reader :array

  def initialize(array)
    @array = array.sort
  end

  def insert(n)
    array << n
    array.sort!
  end

  def median
    array[array.size / 2]
  end

  def remove_median
    array.delete_at(array.size / 2)
  end
end
