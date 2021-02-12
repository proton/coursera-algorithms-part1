### Search in a bitonic array

An array is bitonic if it is comprised of an increasing sequence of integers followed immediately by a decreasing sequence of integers. Write a program that, given a bitonic array of nn distinct integer values, determines whether a given integer is in the array.

- Standard version: Use ~ 3 lg(n) compares in the worst case.
- Signing bonus: Use 2 lg(n) compares in the worst case (and prove that no algorithm can guarantee to perform fewer than 2 lg(n) compares in the worst case).

#### Speed:

```
N       | Slow ~ N             | Fast ~ 3log(N)
------------------------------------------------------
1000    | 0.019207003992050886 | 0.004104000981897116
10000   | 0.15662500401958823  | 0.004657002165913582
100000  | 1.4367660004645586   | 0.006195003166794777
1000000 | 15.570394994225353   | 0.009836995974183083
```
