(comment "Clojure solution to SICP exercise 1.30")

(ns sicp-1.30
  (:use [clojure.test]))

(defn cube [n] (* n n n))

(defn sum [term a next b] 
  (defn iter [a result]
    (if (> a b)
      result
      (iter (next a) (+ result (term a)))))
  (iter a 0))

(defn simple_integral [f a b dx]
  (defn nextx [x] (+ x dx))
  (* dx (sum cube (+ a (/ dx 2.0)) nextx b)))

(defn simpson_integral [f a b n]
  (def h (/ (- b a) n))
  (defn term [k]
    (* (f (+ a (* k h)))
       (cond 
         (= 0 k)   1
         (= n k)   1
         (even? k) 2
         :else     4)))
  (* (/ h 3) (sum term 0 inc n)))

(deftest test-simple-integral
  (is (= (simple_integral cube 0 1 0.01)  0.24998750000000042))
  (is (= (simple_integral cube 0 1 0.001) 0.24998750000000073)))

(deftest test-simpson-integral
  (is (= (simpson_integral cube 0.0 1.0 100)  0.25000000000000006))
  (is (= (simpson_integral cube 0.0 1.0 1000) 0.25000000000000006)))

(run-tests)

