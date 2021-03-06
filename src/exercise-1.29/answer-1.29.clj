(comment "Clojure solution to SICP exercise 1.29")

(ns sicp-1.29
  (:use [clojure.test]))

(defn cube [n] (* n n n))

(defn sum [term a next b] 
  (if (> a b)
    0
    (+ (term a) (sum term (next a) next b))))

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
  (is (= (simple_integral cube 0 1 0.001) 0.249999875000001)))

(deftest test-simpson-integral
  (is (= (simpson_integral cube 0.0 1.0 100)  0.24999999999999992))
  (is (= (simpson_integral cube 0.0 1.0 1000) 0.2500000000000003)))

(run-tests)

