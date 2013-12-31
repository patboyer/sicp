#!/usr/bin/ruby -w

=begin
Ruby solution to SICP exercise 1.29
=end

require 'test/unit'

class Integral
  def initialize(f)
    @f = f
    @a = nil
    @b = nil
    @h = nil
  end

  def summation(f, a, f_next, b)
    return (a > b) ? 0 : f.call(a) + summation(f, f_next.call(a), f_next, b)
  end

  def simple_approx(a, b, dx)
    f_next = Proc.new { |x| x + dx }
    return dx * summation(@f, (a + dx/2), f_next, b)
  end

  def simpson_approx(a, b, n)
    @h = (b - a) / n
    @a = a
    @n = n
    f_next = Proc.new { |x| x + 1 }
    f_term = Proc.new do |k|
      i = case 
           when (k == 0)        then 1
           when (k == @n)       then 1
           when ( (k % 2) == 0) then 2
           else 4
         end

      i * @f.call(@a + (k * @h))
    end

    return @h * summation(f_term, 0, f_next, n) / 3
  end
end

class TestIntegral < Test::Unit::TestCase
  def setup
    f_cube    = Proc.new { |n| n * n * n }
    @integral = Integral.new(f_cube)
  end

  def test_simple_approx
    assert_equal(0.24998750000000042, @integral.simple_approx(0.0, 1.0, 0.01))
    assert_equal(0.249999875000001,   @integral.simple_approx(0.0, 1.0, 0.001))
  end

  def test_simpson_approx
    assert_equal(0.24999999999999992, @integral.simpson_approx(0.0, 1.0, 100))
    assert_equal(0.2500000000000003,  @integral.simpson_approx(0.0, 1.0, 1000))
  end
end

