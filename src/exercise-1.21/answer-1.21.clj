(comment "Clojure solution to SICP exercise 1.21")

(ns sicp-1.21
  (:use [clojure.test]))

(defn divides? [a b]
  (= (rem b a) 0))

(defn square [x] (* x x ))

(defn find-divisor [n test-divisor]
  (cond 
    (> (square test-divisor) n) n
    (divides? test-divisor n) test-divisor
    :else (find-divisor n (+ test-divisor 1))))

(defn smallest-divisor [n]
  (find-divisor n 2))

(comment
(smallest-divisor 199)   =>  199
(smallest-divisor 1999)  => 1999
(smallest-divisor 19999) =>    7
)

(deftest test-find-divisor
  (is (= (smallest-divisor 199)   199))
  (is (= (smallest-divisor 1999)  1999))
  (is (= (smallest-divisor 19999) 7)))

(run-tests)

