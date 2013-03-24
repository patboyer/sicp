(comment "Clojure solution to SICP exercise 1.23")

(ns sicp-1.23.test
  (:use [clojure.test]))

(defn divides? [a b]
  (= (rem b a) 0))

(defn square [x] (* x x ))

(defn next-divisor [n]
  (if (= n 2) 
      3
      (+ n 2)))

(defn find-divisor [n test-divisor]
  (cond 
    (> (square test-divisor) n) n
    (divides? test-divisor n) test-divisor
    :else (find-divisor n (next-divisor test-divisor))))

(defn smallest-divisor [n]
  (find-divisor n 2))

(defn prime? [n]
  (= n (smallest-divisor n)))

(defn search-for-primes [start]
  (defn f [n]
    (if (prime? n)
      n
      (f (+ n 2))))
  (if (even? start)
    (f (+ start 1))
    (f start)))

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

(run-tests)
(prn)
(pr "1009:    ")
(time (prime? 1009))
(pr "1013:    ")
(time (prime? 1013))
(pr "1019:    ")
(time (prime? 1019))
(pr "10007:   ")
(time (prime? 10007))
(pr "10009:   ")
(time (prime? 10009))
(pr "10037:   ")
(time (prime? 10037))
(pr "100003:  ")
(time (prime? 100003))
(pr "100019:  ")
(time (prime? 100019))
(pr "100043:  ")
(time (prime? 100043))
(pr "1000003: ")
(time (prime? 1000003))
(pr "1000033: ")
(time (prime? 1000033))
(pr "1000037: ")
(time (prime? 1000037))

