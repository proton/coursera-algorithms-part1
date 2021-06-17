# Design an algorithm that takes a sequence of `n` document words and a sequence of `m` query words and find the shortest interval in which the `m` query words appear in the document in the order given. The length of an interval is the number of words in that interval.

def algorithm(document_words, query_words)
  next_word = query_words.shift
  start_i = nil
  document_words.each_with_index do |word, i|
    if word == next_word
      start_i = i if start_i.nil?
      next_word = query_words.shift
      return i - start_i if next_word.nil?
    end
  end
  return nil
end
