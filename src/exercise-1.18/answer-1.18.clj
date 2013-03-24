(comment "Clojure solution to SICP exercise 1.18")

(ns sicp-1.18
  (:use [clojure.test]))

(defn multiply [a b]
  (defn mydouble [x] (+ x x))
  (defn halve [x] (/ x 2))
  (defn mult-iter [a b result]
    (cond
      (= b 0)   result
      (even? b) (mult-iter (mydouble a) (halve b) result)
      :else     (mult-iter a (- b 1) (+ result a))))
  (mult-iter a b 0))

(deftest test-multiply
  (is (= (multiply 0 1) 0))
  (is (= (multiply 1 2) 2))
  (is (= (multiply 2 3) 6))
  (is (= (multiply 3 4) 12))
  (is (= (multiply 4 5) 20)))

(run-tests)

