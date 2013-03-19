#!/usr/bin/ruby -w

require 'test/unit'

def double(x)
  x + x
end

def halve(x)
  x /= 2
end

def mult_iter(a, b)
  result = 0

  while (b > 0)
    if b.even?
      a = double(a)
      b = halve(b)
    else
      result += a
      b -= 1
    end
  end

  return result
end


class TestMult < Test::Unit::TestCase
  def test_mult_iter
    (0..10).each do |i|
      (0..10).each do |j|
        assert_equal(i*j, mult_iter(i, j))
      end
    end
  end
end

