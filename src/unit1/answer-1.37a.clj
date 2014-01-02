(comment "Clojure solution to SICP exercise 1.37a")

(ns sicp-1.37a
  (:use [clojure.test]))

(comment "----- Finite Continued Fraction (Recursive) -----")
(defn cont-frac [n d k]
  (defn f [n d k i]
    (if (= k i)
      (/ (n i) (+ 1.0 (/ (n k) (d k))))
      (/ (n i) (+ 1.0 (f n d k (+ i 1))))))
  (f n d k 1))

(defn golden-ratio [n]
  (/ 1.0 (cont-frac (fn [x] 1.0) (fn [x] 1.0) n)))


(defn how-many-iterations? [target]
  (defn try-n [n]
    (if (= (format "%.4f" (golden-ratio n)) target)
       n
       (try-n (inc n))))
  (try-n 1))


(comment "yields => 10")
(prn (how-many-iterations? "1.6180"))

