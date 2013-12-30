(comment "Clojure solution to SICP exercise 1.16")

(ns sicp-1.16
  (:use [clojure.test]))

(defn exponential [base, exp]
  (defn square [x] (* x x))
  (defn exp-iter [b n result]
    (cond
      (= n 0)   result
      (even? n) (exp-iter (* b b) (/ n 2) result)
      :else     (exp-iter b (- n 1) (* result b))))
  (exp-iter base exp 1))

(deftest test-exponential
  (is (= (exponential 1 1) 1))
  (is (= (exponential 2 2) 4))
  (is (= (exponential 3 3) 27))
  (is (= (exponential 4 4) 256))
  )

(run-tests)

