#include <stdio.h>
#include <math.h>
#include "../minunit.h"

int is_even(int i) {
  return ( (i%2) == 0 );
}


unsigned long exponential(int base, int exp) {
  unsigned long result = 1;

  while (exp > 0) {
    if (is_even(exp)) {
      base *= base;
      exp /= 2;
    }
    else {
      result *= base;
      exp -= 1;
    }
  }

  return result;
}


void test_setup() {}
void test_teardown() {}

MU_TEST(test_exponential) {
  int i, j;
  long actual_result, expected_result;
  char test_results[MINUNIT_MESSAGE_LEN];

  for (i=0; i<=10; i++) {
    for (j=0; j<=10; j++) {
      expected_result = (unsigned long) pow(i, j);
      actual_result   = exponential(i, j);
      snprintf(test_results, MINUNIT_MESSAGE_LEN, "%d^%d should be %lu, but was %lu", i, j, expected_result, actual_result);
      mu_assert( (actual_result == expected_result), test_results );
    }
  }
}


MU_TEST_SUITE(test_suite) {
  MU_SUITE_CONFIGURE(&test_setup, &test_teardown);
  MU_RUN_TEST(test_exponential);
}


int main (int argc, char *argv[]) {
  MU_RUN_SUITE(test_suite);
  MU_REPORT();
  return 0;
}

