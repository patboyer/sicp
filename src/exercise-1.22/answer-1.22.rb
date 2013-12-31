#!/usr/bin/ruby -w

=begin
Ruby solution to SICP exercise 1.22
=end

require 'benchmark'
require 'test/unit'

class Integer
  def divides(n)
  ( (n % self) == 0)
  end

  def prime?
    (self == self.smallest_divisor)
  end

  def smallest_divisor
    divisor = 2

    loop do
      if divisor.square > self
        return self
      elsif divisor.divides(self)
        return divisor
      else
        divisor += 1
      end
    end
  end

  def square
    self * self
  end
end

def find_primes(n)
  # finds the first 3 primes >= to the given number
  result = []

  while result.length < 3
    if n.prime?
      result << n
      n += 1
    elsif n.even?
      n += 1
    else
      n += 2
    end
  end

  return result
end

class TestDivisor < Test::Unit::TestCase
  def test_square
    assert_equal(0,  0.square)
    assert_equal(1,  1.square)
    assert_equal(4,  2.square)
    assert_equal(9,  3.square)
    assert_equal(16, 4.square)
    assert_equal(25, 5.square)
  end

  def test_divides
    assert(! 2.divides(7))
    assert(  2.divides(6))
  end

  def test_smallest_divisor
    assert_equal(199,  199.smallest_divisor)
    assert_equal(1999, 1999.smallest_divisor)
    assert_equal(7,    19999.smallest_divisor)
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
  end

  def test_find_primes
    assert(! find_primes(1000).include?(1000))
    assert(  find_primes(1000).include?(1009))
    assert(  find_primes(1000).include?(1013))
    assert(  find_primes(1000).include?(1019))

    assert(! find_primes(10000).include?(10000))
    assert(  find_primes(10000).include?(10007))
    assert(  find_primes(10000).include?(10009))
    assert(  find_primes(10000).include?(10037))
    
    assert(! find_primes(100000).include?(100000))
    assert(  find_primes(100000).include?(100003))
    assert(  find_primes(100000).include?(100019))
    assert(  find_primes(100000).include?(100043))

    assert(! find_primes(1000000).include?(1000000))
    assert(  find_primes(1000000).include?(1000003))
    assert(  find_primes(1000000).include?(1000033))
    assert(  find_primes(1000000).include?(1000037))
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

