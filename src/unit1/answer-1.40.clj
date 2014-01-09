(comment "Clojure solution to SICP exercise 1.39")

(ns sicp-1.39
  (:use [clojure.test]))

(comment "----- Finite Continued Fraction -----")
(defn cont-frac [n d k]
  (defn f [i]
    (if (> i k)
      (/ (n i) (d i))
      (/ (n i) (+ (d i) (f (+ i 1))))))
  (f 1))


(defn tan-cf [x k]
  (defn f_numer [n] 
    (if (> n 1)
      (- (* x x))
      x))
  (defn f_denom [n] (- (* 2 n) 1))
  (cont-frac f_numer f_denom k))


(prn (Math/tan 1.0))
(prn (tan-cf 1.0 100))
(comment "(Math/tan 1.0)   => 1.5574077246549023")
(comment "(tan-cf 1.0 100) => 1.557407724654902")

