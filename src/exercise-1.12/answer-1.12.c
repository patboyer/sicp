#include <stdio.h>
#include "../minunit.h"

int calculate_pascal_node(int row, int col) {
  int newcol = col - 1;
  int newrow = row - 1;

  if ( (col < 0) || (col > row) ) {
    return 0;
  }
  else if ( (col == 0) || (col == row) ) {
    return 1;
  }
  else {
    return ( calculate_pascal_node(newrow, newcol) + 
             calculate_pascal_node(newrow, col) );
  }
}

void test_setup() {}
void test_teardown() {}

MU_TEST(test_calculate_pascal_node) {
  mu_assert( (calculate_pascal_node(0, 0) == 1), "node(0,0) should be 1");
  mu_assert( (calculate_pascal_node(1, 0) == 1), "node(1,0) should be 1");
  mu_assert( (calculate_pascal_node(1, 1) == 1), "node(1,1) should be 1");
  mu_assert( (calculate_pascal_node(2, 0) == 1), "node(2,0) should be 1");
  mu_assert( (calculate_pascal_node(2, 1) == 2), "node(2,1) should be 2");
  mu_assert( (calculate_pascal_node(2, 2) == 1), "node(2,2) should be 1");
  mu_assert( (calculate_pascal_node(3, 0) == 1), "node(3,0) should be 1");
  mu_assert( (calculate_pascal_node(3, 1) == 3), "node(3,1) should be 3");
  mu_assert( (calculate_pascal_node(3, 2) == 3), "node(3,2) should be 3");
  mu_assert( (calculate_pascal_node(3, 3) == 1), "node(3,3) should be 1");
  mu_assert( (calculate_pascal_node(4, 0) == 1), "node(4,0) should be 1");
  mu_assert( (calculate_pascal_node(4, 1) == 4), "node(4,1) should be 4");
  mu_assert( (calculate_pascal_node(4, 2) == 6), "node(4,2) should be 6");
  mu_assert( (calculate_pascal_node(4, 3) == 4), "node(4,3) should be 4");
  mu_assert( (calculate_pascal_node(4, 4) == 1), "node(4,4) should be 1");

  mu_assert( (calculate_pascal_node(4, 5) == 0), "node(4,5) should be 0");
}


MU_TEST_SUITE(test_suite) {
  MU_SUITE_CONFIGURE(&test_setup, &test_teardown);
  MU_RUN_TEST(test_calculate_pascal_node);
}


int main (int argc, char *argv[]) {
  MU_RUN_SUITE(test_suite);
  MU_REPORT();
  return 0;
}

