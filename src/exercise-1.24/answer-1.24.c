/* C solution to SICP exercise 1.24 */

#include <stdbool.h>
#include <stdlib.h>
#include <time.h>
#include "../minunit.h"

#define NUM_FERMAT_ATTEMPTS 100
#define NUM_PRIMES 3

bool is_even(int i) {
  return ( (i%2) == 0);
}


int square(int x) {
  return x * x;
}


int expmod(int base, int exp, int m) {
  if (exp == 0) {
    return 1;
  }
  else if (is_even(exp)) {
    return ((square(expmod(base, (exp/2), m))) % m);
  }
  else {
    return ((base * (expmod(base, (exp-1), m))) % m);
  }
}


bool fermat_test(int n) {
  int a = rand() % n;
  return (a == expmod(a, n, n));
}


bool is_prime(int n, int times) {
  if (times == 0) {
    return true;
  }
  else if (fermat_test(n)) {
    return is_prime(n, times-1);
  }
  else {
    return false;
  }
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


MU_TEST(test_is_even) {
  mu_check(  is_even(0));
  mu_check(! is_even(1));
  mu_check(  is_even(2));
  mu_check(! is_even(3));
}


MU_TEST(test_expmod) {
  mu_check(expmod(1, 2, 2) == 1);
  mu_check(expmod(2, 3, 3) == 2);
  mu_check(expmod(3, 4, 4) == 1);
  mu_check(expmod(4, 5, 5) == 4);
}


MU_TEST(test_is_prime) {
  mu_check(  is_prime(1,  NUM_FERMAT_ATTEMPTS));
  mu_check(  is_prime(2,  NUM_FERMAT_ATTEMPTS));
  mu_check(  is_prime(3,  NUM_FERMAT_ATTEMPTS));
  mu_check(! is_prime(4,  NUM_FERMAT_ATTEMPTS));
  mu_check(  is_prime(5,  NUM_FERMAT_ATTEMPTS));
  mu_check(! is_prime(6,  NUM_FERMAT_ATTEMPTS));
  mu_check(  is_prime(7,  NUM_FERMAT_ATTEMPTS));
  mu_check(! is_prime(8,  NUM_FERMAT_ATTEMPTS));
  mu_check(! is_prime(9,  NUM_FERMAT_ATTEMPTS));
  mu_check(! is_prime(10, NUM_FERMAT_ATTEMPTS));
}


MU_TEST_SUITE(test_suite) {
  MU_SUITE_CONFIGURE(&test_setup, &test_teardown);
  MU_RUN_TEST(test_square);
  MU_RUN_TEST(test_is_even);
  MU_RUN_TEST(test_expmod);
  MU_RUN_TEST(test_is_prime);
}


int main (int argc, char *argv[]) {
  MU_RUN_SUITE(test_suite);
  MU_REPORT();

  srand(time(NULL));

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
  int num_runs = 10000;

  while (index < primes_length) {
    t = clock();
    int i;
    int this_prime;

    for (i=1; i<=num_runs; i++) {
      this_prime = primes[index];
      result = is_prime(this_prime, NUM_FERMAT_ATTEMPTS);
    }
  
    t = clock() - t;
    printf("%8d: %f seconds\n", this_prime, ((float)t)/CLOCKS_PER_SEC);
    index++;
  }

  return EXIT_SUCCESS;
}

