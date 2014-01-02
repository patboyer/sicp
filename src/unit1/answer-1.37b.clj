(comment "Clojure solution to SICP exercise 1.37b")

(ns sicp-1.37b
  (:use [clojure.test]))

(comment "----- Finite Continued Fraction (Iterative) -----")
(defn cont-frac-iter [n d k]
  (defn f [n d k i result]
    (if (= i 0)
      result 
      (f n d k (- i 1) (/ (n 1) (+ (d i) result)))))
  (f n d k (- k 1) (/ (n k) (d k))))


(defn golden-ratio [n]
  (/ 1.0 (cont-frac-iter (fn [x] 1.0) (fn [x] 1.0) n)))


(defn how-many-iterations? [target]
  (defn try-n [n]
    (if (= (format "%.4f" (golden-ratio n)) target)
       n
       (try-n (inc n))))
  (try-n 1))


(comment "yields => 10")
(prn (how-many-iterations? "1.6180"))

