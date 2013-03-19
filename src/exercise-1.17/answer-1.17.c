#include <stdio.h>
#include "../minunit.h"

int mydouble(int i) {
  return i + i;
}


int is_even(int i) {
  return ( (i%2) == 0 );
}


int halve(int i) {
  return i / 2;
}


int fast_mult(int a, int b) {
  if (b == 0) {
    return 0;
  }
  else if (is_even(b)) {
    return fast_mult(mydouble(a), halve(b));
  }
  else {
    return a + fast_mult(a, (b-1));
  }
}


void test_setup() {}
void test_teardown() {}

MU_TEST(test_fast_mult) {
  int i, j;
  long actual_result, expected_result;
  char test_results[MINUNIT_MESSAGE_LEN];

  for (i=0; i<=10; i++) {
    for (j=0; j<=10; j++) {
      expected_result = i * j;
      actual_result   = fast_mult(i, j);
      snprintf(test_results, MINUNIT_MESSAGE_LEN, "%d*%d should be %lu, but was %lu", i, j, expected_result, actual_result);
      mu_assert( (actual_result == expected_result), test_results );
    }
  }
}


MU_TEST_SUITE(test_suite) {
  MU_SUITE_CONFIGURE(&test_setup, &test_teardown);
  MU_RUN_TEST(test_fast_mult);
}


int main (int argc, char *argv[]) {
  MU_RUN_SUITE(test_suite);
  MU_REPORT();
  return 0;
}

