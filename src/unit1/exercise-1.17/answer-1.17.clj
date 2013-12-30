(comment "Clojure solution to SICP exercise 1.17")

(ns sicp-1.17
  (:use [clojure.test]))

(defn fast-mult [a b]
  (defn double [x] (+ x x))
  (defn even? [x] 
    (= (rem x 2) 0))
  (defn halve [x] (/ x 2))
  (cond
    (= b 0)   0
    (even? b) (fast-mult (double a) (halve b))
    :else     (+ a (fast-mult a (- b 1)))))

(deftest test_mult
  (is (= (fast-mult 0 1) 0))
  (is (= (fast-mult 1 2) 2))
  (is (= (fast-mult 2 3) 6))
  (is (= (fast-mult 3 4) 12))
  (is (= (fast-mult 4 5) 20)))

(run-tests)

