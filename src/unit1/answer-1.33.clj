(comment "Clojure solution to SICP exercise 1.33")

(ns sicp-1.33
  (:use [clojure.test]))


(comment "----- Accumulate-filter -----")
(defn accumulate-filter [term a next b accumulator init filter] 
  (if (> a b)
    init
    (accumulator 
      (if (filter a) 
        (term a) 
        init)
      (accumulate-filter term (next a) next b accumulator init filter))))


(comment "----- Square -----")
(defn square [x] (* x x))


(comment "----- Prime? (from exercise 1.22) -----")
(defn prime? [n]
  (defn divides? [a b]
    (= (rem b a) 0))
  (defn find-divisor [n test-divisor]
    (cond 
      (> (square test-divisor) n) n
      (divides? test-divisor n) test-divisor
      :else (find-divisor n (+ test-divisor 1))))
  (defn smallest-divisor [n]
    (find-divisor n 2))
  (= n (smallest-divisor n)))


(comment "----- Sum of Squares (Primes) -----")
(defn sum-prime-squares [a b]
  (accumulate-filter square a inc b + 0 prime?))


(comment "----- Sum of Squares (Primes) -----")
(comment "using Euclid's algorithm for GCD")
(defn product-relative-primes [a n]
  (defn gcd [x,y]
    (if (= 0 y)
      x
      (gcd y, (mod x y))))
  (defn relative-prime? [i]
    (= (gcd i n) 1))
  (accumulate-filter identity a inc (- n 1) * 1 relative-prime?))


(comment "----- Tests -----")
(deftest test-primes
  (is (not (prime? 1000)))
  (is (prime? 1009))
  (is (prime? 1013))
  (is (prime? 1019))

  (is (not (prime? 10000)))
  (is (prime? 10007))
  (is (prime? 10009))
  (is (prime? 10037))

  (is (not (prime? 100000)))
  (is (prime? 100003))
  (is (prime? 100019))
  (is (prime? 100043))

  (is (not (prime? 1000000)))
  (is (prime? 1000003))
  (is (prime? 1000033))
  (is (prime? 1000037)))

(deftest test-sum-of-square-primes
  (is (= (sum-prime-squares 3  6)  34))
  (is (= (sum-prime-squares 4  8)  74))
  (is (= (sum-prime-squares 5 10)  74))
  (is (= (sum-prime-squares 6 12) 170))
  (is (= (sum-prime-squares 7 14) 339))
  (is (= (sum-prime-squares 8 16) 290)))

(deftest test-relative-primes
  (is (= (product-relative-primes  1 10)               189))
  (is (= (product-relative-primes 10 30)          30808063))
  (is (= (product-relative-primes 25 50) 4692993764001543)))

(run-tests)

