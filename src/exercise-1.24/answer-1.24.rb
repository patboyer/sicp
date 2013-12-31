#!/usr/bin/ruby -w

=begin
Ruby solution to SICP exercise 1.24
=end

require 'benchmark'
require 'test/unit'

def expmod(base, exp, m)
  if exp == 0
    return 1
  elsif exp.even?
    return (expmod(base, exp/2, m)**2) % m
  else
    return (base * expmod(base, exp-1, m) ) % m
  end
end

class Integer
  def fermat_test?
    a = 1 + rand(self-1)
    return (expmod(a, self, self) == a)
  end

  def prime?
    return true if self == 1

    100.times do 
      if (! self.fermat_test?)
        return false
      end
    end

    return true
  end
end

class TestPrime < Test::Unit::TestCase
  def test_expmod
    assert_equal(1, expmod(1, 2, 2))
    assert_equal(2, expmod(2, 3, 3))
    assert_equal(1, expmod(3, 4, 4))
    assert_equal(4, expmod(4, 5, 5))
  end

  def test_prime
    assert(  1.prime?)
    assert(  2.prime?)
    assert(  3.prime?)
    assert(! 4.prime?)
    assert(  5.prime?)
    assert(! 6.prime?)
    assert(  7.prime?)
    assert(! 8.prime?)
    assert(! 9.prime?)
    assert(! 10.prime?)

    assert(! 1000.prime?)
    assert(  1009.prime?)
    assert(  1013.prime?)
    assert(  1019.prime?)

    assert(! 10000.prime?)
    assert(  10007.prime?)
    assert(  10009.prime?)
    assert(  10037.prime?)

    assert(! 100000.prime?)
    assert(  100003.prime?)
    assert(  100019.prime?)
    assert(  100043.prime?)

    assert(! 1000000.prime?)
    assert(  1000003.prime?)
    assert(  1000033.prime?)
    assert(  1000037.prime?)
  end
end

Benchmark.bm do |x|
  primes = [
       1009,    1013,    1019,
      10007,   10009,   10037, 
     100003,  100019,  100043, 
    1000003, 1000033, 1000037
  ]

  primes.each do |i|
    x.report(i.to_s.ljust(9)) do
      1000.times { i.prime? }
    end
  end
end
