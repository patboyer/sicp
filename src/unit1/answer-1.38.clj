(comment "Clojure solution to SICP exercise 1.38")

(ns sicp-1.38
  (:use [clojure.test]))

(comment "----- Finite Continued Fraction -----")
(defn cont-frac [n d k]
  (defn f [i]
    (if (> i k)
      (/ (n i) (d i))
      (/ (n i) (+ (d i) (f (+ i 1))))))
  (f 1))


(defn approx-e [n]
  (defn f_numer [x] 1.0)
  (defn f_denom [x] 
    (if (= (mod x 3) 2)
      (/ (* 2 (+ x 1)) 3)
      1.0))
  (+ 2 (cont-frac f_numer f_denom n)))


(prn (approx-e 100))
(comment "=> 2.7182818284590455")

