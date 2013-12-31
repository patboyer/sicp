(comment "Clojure solution to SICP exercise 1.31 - recursive method")

(ns sicp-1.31b
  (:use [clojure.test]))


(comment "----- Product (Recursive) -----")
(defn product [term a next b] 
  (if (> a b)
    1
    (* (term a) (product term (next a) next b))))


(comment "----- Factorial -----")
(defn factorial [n]
  (product identity 1 inc n))

(deftest test-factorial
  (is (= (factorial  1)       1))
  (is (= (factorial  2)       2))
  (is (= (factorial  3)       6))
  (is (= (factorial  4)      24))
  (is (= (factorial  5)     120))
  (is (= (factorial  6)     720))
  (is (= (factorial  7)    5040))
  (is (= (factorial  8)   40320))
  (is (= (factorial  9)  362880))
  (is (= (factorial 10) 3628800)))


(comment "----- Wilson Product -----")
(defn wilson-product [n]
  (defn square [j] (* j j))
  (defn term [i]
    (def c (* 4 (square i)))
    (/ c (- c 1)))
  (* 2.0 (product term 1.0 inc n)))

(deftest test-wilson-product
  (is (= (wilson-product    5) 3.0021759545569067))
  (is (= (wilson-product  100) 3.133787490628163))
  (is (= (wilson-product 1000) 3.1408077460304042)))

(run-tests)

