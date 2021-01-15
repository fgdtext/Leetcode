// UVa11582 Colossal Fibonacci Numbers!
// Rujia Liu
#include<iostream>
#include<cstring>
#include<cstdio>
using namespace std;

const int maxn = 1000 + 5;
typedef unsigned long long ULL;

int f[maxn][maxn*6], period[maxn];

//快速幂取余数(二分快速幂)
int pow_mod(ULL a, ULL b, int n) {
  if(!b) return 1;
  int k = pow_mod(a, b/2, n);
  k = k * k % n;
  if(b % 2) k = k * a % n;
  return k;
}
// f(a^b)是指第a^b项，也就是在数列中的第a^b个数。不是fib数的值。 a^b %n    ===> (a%n)^b%n
// 求 f(a^b)%n
int solve(ULL a, ULL b, int n) {
  if(a == 0 || n == 1) return 0; // attention!

  int p = pow_mod(a % period[n], b, period[n]);
  return f[n][p];
}

// 定理： 斐波那契数列，转换为对n的余数数列后，  最多 n^2项 就会出现重复， 余数数列是一个循环数列。

int main() {
  // 保存的目的是为了多组输入，不需要重复该过程。
  for(int n = 2; n <= 1000; n++) {
    f[n][0] = 0; f[n][1] = 1;
    // f[n][i] 表示 第i项对n 的余数
   // f(i+2) % n = (f(i+1)%n + f(i) %n) %n
    for(int i = 2; ; i++) {
      f[n][i] = (f[n][i-1] + f[n][i-2]) % n;
      if(f[n][i-1] == 0 && f[n][i] == 1) {
        // 余数为n时， 每i-1个斐波那契数的余数就会重复。
        //余数为n, 则 斐波那契数的余数数列，每i-1个就会重复
        period[n] = i - 1;
        break;
      }
    }
  }
  ULL a, b;
  int n, T;
  cin >> T;
  while(T--) {
    // 输入 n <= 1000
    cin >> a >> b >> n;
    cout << solve(a, b, n) << "\n";
  }
  return 0;
}
