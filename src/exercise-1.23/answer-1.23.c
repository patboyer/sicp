/* C solution to SICP exercise 1.23 */

#include "../minunit.h"

int next_divisor(n) {
  return (n == 2) ? 3 : n + 2;
}


int square(int x) {
  return x * x;
}


int divides (int divisor, int n) {
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
      divisor = next_divisor(divisor);
  }
}


void test_setup() {}
void test_teardown() {}

MU_TEST(test_next_divisor) {
  mu_check(next_divisor(2) == 3);
  mu_check(next_divisor(3) == 5);
  mu_check(next_divisor(5) == 7);
  mu_check(next_divisor(7) == 9);
}


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
  mu_check(divides(2, 4));
}


MU_TEST(test_smallest_divisor) {
  mu_check(smallest_divisor(199)   == 199);
  mu_check(smallest_divisor(1999)  == 1999);
  mu_check(smallest_divisor(19999) == 7);
}


MU_TEST_SUITE(test_suite) {
  MU_SUITE_CONFIGURE(&test_setup, &test_teardown);
  MU_RUN_TEST(test_next_divisor);
  MU_RUN_TEST(test_square);
  MU_RUN_TEST(test_divides);
  MU_RUN_TEST(test_smallest_divisor);
}


int main (int argc, char *argv[]) {
  MU_RUN_SUITE(test_suite);
  MU_REPORT();
  return 0;
}

