#!/usr/bin/ruby -w

require 'test/unit'

def exponential(base, exp)
  result = 1

  while exp > 0
    if exp.even?
      base *= base
      exp  /= 2
    else
      result *= base
      exp -= 1
    end
  end

  return result
end


class TestExponential < Test::Unit::TestCase
  def test_exponential
    (0..10).each do |i|
      (0..10).each do |j|
        assert_equal(i**j, exponential(i, j))
      end
    end
  end
end

