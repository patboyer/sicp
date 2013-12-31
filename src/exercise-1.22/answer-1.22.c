/* C solution to SICP exercise 1.22 */

#include <stdbool.h>
#include <stdlib.h>
#include <time.h>
#include "../minunit.h"

#define NUM_PRIMES 3

int square(int x) {
  return x * x;
}


bool divides (int divisor, int n) {
  return ( (n % divisor) == 0);
}


unsigned long smallest_divisor(int n) {
  int divisor = 2;

  for (;;) {
    if (square(divisor) > n)
      return n;
    else if (divides(divisor, n))
      return divisor;
    else
      divisor++;
  }
}


bool is_prime(int n) {
  return (n == smallest_divisor(n));
}


void test_setup() {}
void test_teardown() {}

MU_TEST(test_square) {
  mu_check(square(0) == 0);
  mu_check(square(1) == 1);
  mu_check(square(2) == 4);
  mu_check(square(3) == 9);
  mu_check(square(4) == 16);
  mu_check(square(5) == 25);
}


MU_TEST(test_divides) {
  mu_check(! divides(3, 4));
  mu_check(  divides(2, 4));
}


MU_TEST(test_smallest_divisor) {
  mu_check(smallest_divisor(4)     == 2);
  mu_check(smallest_divisor(5)     == 5);
  mu_check(smallest_divisor(6)     == 2);
  mu_check(smallest_divisor(9)     == 3);
  mu_check(smallest_divisor(199)   == 199);
  mu_check(smallest_divisor(1999)  == 1999);
  mu_check(smallest_divisor(19999) == 7);
}


MU_TEST(test_is_prime) {
  mu_check(  is_prime(1));
  mu_check(  is_prime(2));
  mu_check(  is_prime(3));
  mu_check(! is_prime(4));
  mu_check(  is_prime(5));
  mu_check(! is_prime(6));
  mu_check(  is_prime(7));
  mu_check(! is_prime(8));
  mu_check(! is_prime(9));
  mu_check(! is_prime(10));
}


MU_TEST_SUITE(test_suite) {
  MU_SUITE_CONFIGURE(&test_setup, &test_teardown);
  MU_RUN_TEST(test_square);
  MU_RUN_TEST(test_divides);
  MU_RUN_TEST(test_smallest_divisor);
  MU_RUN_TEST(test_is_prime);
}


int main (int argc, char *argv[]) {
  MU_RUN_SUITE(test_suite);
  MU_REPORT();

  int primes[] = {
       1009,    1013,    1019,
      10007,   10009,   10037, 
     100003,  100019,  100043, 
    1000003, 1000033, 1000037
  };

  int index = 0;
  int primes_length = sizeof(primes) / sizeof(primes[0]);

  printf("Beginning benchmarks ...\n");
  
  bool result;
  clock_t t;
  int num_runs = 1000;

  while (index < primes_length) {
    t = clock();
    int i;
    int this_prime;

    for (i=1; i<=num_runs; i++) {
      this_prime = primes[index];
      result = is_prime(this_prime);
    }
  
    t = clock() - t;
    printf("%8d: %f seconds\n", this_prime, ((float)t)/CLOCKS_PER_SEC);
    index++;
  }

  return EXIT_SUCCESS;
}

