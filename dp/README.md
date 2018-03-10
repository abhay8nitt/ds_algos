**Dynamic Programming**
    . Dynamic programming is when you use past knowledge to making solve a future problem easier.
    . Dynamic programming is very similar to recursion. But when subproblems are solved for multiple times,
      dynamic programming utilizes memorization techniques (usually a memory table) to store results of subproblems
      so that same subproblem won’t be solved twice.
    . Do not repeat something you have already done
    . e.g. of standard applications using dynamic programming - diff, Belman Ford for shortest path, Winning and Score Predictor (cricket score prediction) etc
    . The core idea of Dynamic Programming is to avoid repeated work by remembering partial results and this concept
      finds it application in a lot of real life situations
    . If any problem can be converted into mathematics functions and avoid re-computations
    . Trade space for time
    . Overlapping subproblems - solutions of same subproblems are needed again and again, for e.g. Binary Search is not example but fibonacci series is
    . Optimal Substructure - if optimal solution of the given problem can be obtained by using optimal solutions of its subproblems - e.g.
      shortest path but not the longest path problem
    .   f(1) = f(2) = 1
        f(N) = f(N-1) + f(N-2) for N > 2


    . Memoization (Top down - build when needed)-

      int fib(int n)
        {
          if (memo[n] == NIL)
          {
            if (n <= 1)
                memo[n] = n;
            else
                memo[n] = fib(n-1) + fib(n-2);
          }
          return memo[n];
        }
    . Tabulation (Bottom Up - first build and then use, this doesn't involve recursion)
      int fib(int n)
        {
          int table[] = new int[n+1];
          f[0] = 0;
          f[1] = 1;
          for (int i = 2; i <= n; i++)
                table[i] = table[i-1] + table[i-2];
          return table[n];
        }

    . Compare the running time of plain recursion vs memoization vs tabulation
    . Steps to solve
        > Characterize the structure of an optimal solution.
        > Recursively define the value of an optimal solution.
        > Compute the value and memoize it.
        > Construct optimal solution from computed information.


**Examples**:

      (A) 1+1+1+1+1+1+1+1 = 8; (1+1+1+1+1+1+1+1) + 1 =9
      (B) Fibonacci Series
          f(N) = 0 ; N = 0
          f(N) = 1 ; N = 1
          f(N) = f(N-1)+ f(N-2; N >= 2

      (C) Knapsack Problem(0-1) -
          Weight(kg):    1 2 4 2 5
          Value(Rs) :    5 3 5 3 2
          Limit : 10kg
      (D) Given 3 numbers {1, 3, 5}, we need to tell the total number of ways we can form a number 'N'
          e.g. N =6
          1+1+1+1+1+1
          1+1+1+3
          1+1+3+1
          1+3+1+1
          3+1+1+1
          3+3
          1+5
          5+1
          f(N) = 0 ; N < 0
          f(N) = 1;  N = 1
          f(N) = f(N-1)+ f(N-3)+ f(N-5); N > 1
      (C) Binomial Coefficient
          C(n,r) = C(n-1,r) + C(n-1,r-1)
          C(n,0) = C(n, n) = 1

          f(N,R) =  1; N = R or R = 0
          f(N,R) = f(N-1,R) + f(N-1, R-1); otherwise
