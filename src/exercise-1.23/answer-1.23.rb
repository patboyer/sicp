#!/usr/bin/ruby -w

=begin
Ruby solution to SICP exercise 1.23
=end

require 'test/unit'

def next_divisor(n)
  (n == 2) ? 3 : n + 2
end

def square(x)
  x * x
end

def divides(divisor, n)
  ( (n % divisor) == 0)
end

def smallest_divisor(n)
  divisor = 2

  loop do
    if square(divisor) > n
      return n
    elsif divides(divisor, n)
      return divisor
    else
      divisor = next_divisor(divisor)
    end
  end
end


class TestDivisor < Test::Unit::TestCase
  def test_next_divisor
    assert_equal(3, next_divisor(2))
    assert_equal(5, next_divisor(3))
    assert_equal(7, next_divisor(5))
    assert_equal(9, next_divisor(7))
  end

  def test_square
    assert_equal(0,  square(0))
    assert_equal(1,  square(1))
    assert_equal(4,  square(2))
    assert_equal(9,  square(3))
    assert_equal(16, square(4))
    assert_equal(25, square(5))
  end

  def test_divides
    assert(! divides(2, 7))
    assert(divides(2, 6))
  end

  def test_smallest_divisor
    assert_equal(199,  smallest_divisor(199))
    assert_equal(1999, smallest_divisor(1999))
    assert_equal(7,    smallest_divisor(19999))
  end
end

