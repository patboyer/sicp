#!/usr/bin/ruby -w

require 'test/unit'

def double(x)
  x + x
end

def halve(x)
  x /= 2
end

def fast_mult(a, b)
  if b == 0
    0
  elsif b.even?
    fast_mult(double(a), halve(b))
  else
    a + fast_mult(a, (b-1))
  end
end


class TestFastMult < Test::Unit::TestCase
  def test_fast_mult
    (0..10).each do |i|
      (0..10).each do |j|
        assert_equal(i*j, fast_mult(i, j))
      end
    end
  end
end

