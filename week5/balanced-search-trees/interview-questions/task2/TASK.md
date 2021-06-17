### Document search

Design an algorithm that takes a sequence of `n` document words and a sequence of `m` query words and find the shortest interval in which the `m` query words appear in the document in the order given. The length of an interval is the number of words in that interval.

### Solution

Create a symbol table of words and their positions in the document. Then, for each query word, find the shortest interval containing all query words.
