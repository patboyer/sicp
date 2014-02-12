(comment "Clojure solution to SICP exercise 1.46")

(ns sicp-1.46
  (:use [clojure.test]))

(defn square [x] (* x x))
(defn average [a b] (/ (+ a b) 2))

(defn iterative-improve [good-enough? improve-guess]
  (fn [x] 
    (defn f [n]
      (cond (good-enough? n)
        n
        :else (f (improve-guess n))))
    (f x)))

(defn sqrt [x]
  ((iterative-improve 
    (fn [n] (< (Math/abs (- (square n) x)) 0.001))
    (fn [n] (average n (/ x n))))
  1.0))

(sqrt 55)
(comment => 5.000023178253949)

(defn fixed-point [f]
  ((iterative-improve 
    (fn [n] (< (Math/abs (- (f n) n)) 0.001))
    (fn [n] (f n)))
  1.0))

(fixed-point (fn [x] (Math/cos x)))
(comment => 0.7395672022122561)

