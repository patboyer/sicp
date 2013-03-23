/* C solution to SICP exercise 1.22 */

#include <stdbool.h>
#include <stdlib.h>
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

  return EXIT_SUCCESS;
}

