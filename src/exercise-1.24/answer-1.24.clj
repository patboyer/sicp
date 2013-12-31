(comment "Clojure solution to SICP exercise 1.24")

(ns sicp-1.24
  (:use [clojure.test]))

(defn square [x] (* x x))

(defn expmod [base exp m]
  (cond
    (= exp 0) 1
    (even? exp) (rem (square (expmod base (/ exp 2) m)) m)
    :else       (rem (* base (expmod base (- exp 1) m)) m)))

(defn fast-prime? [n times]
  (defn fermat-test [n]
    (defn try-it [a]
      (= (expmod a n n) a))
    (try-it (+ 1 (rand-int (- n 1)))))
  (cond
    (= times 0)     true
    (fermat-test n) (fast-prime? n (- times 1))
    :else           false))

(comment "chose to run Fermat test 100 times")
(defn prime? [n]
  (fast-prime? n 100))

(deftest test-expmod
  (is (= (expmod 1 2 2) 1))
  (is (= (expmod 2 3 3) 2))
  (is (= (expmod 3 4 4) 1))
  (is (= (expmod 4 5 5) 4)))

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

