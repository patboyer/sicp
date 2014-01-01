(comment "Clojure solution to SICP exercise 1.36")

(ns sicp-1.36
  (:use [clojure.test]))

(comment "----- Fixed Point -----")
(defn fixed-point [f first-guess]
  (defn close-enough? [v1 v2]
    (< (Math/abs (- v1 v2)) 0.00001))
  (defn try-guess [guess]
    (prn guess)
    (let [next-guess (f guess)]
      (if (close-enough? guess next-guess)
      next-guess
      (try-guess next-guess))))
  (prn "----- Fixed Point -----")
  (try-guess first-guess))


(comment "----- Fixed Point (Damping)-----")
(defn fixed-point-damping [f first-guess]
  (defn average [a b] (/ (+ a b) 2))
  (defn close-enough? [v1 v2]
    (< (Math/abs (- v1 v2)) 0.00001))
  (defn try-guess [guess]
    (prn guess)
    (let [next-guess (f guess)]
      (if (close-enough? guess next-guess)
      next-guess
      (try-guess (average guess next-guess)))))
  (prn "----- Fixed Point (with damping) -----")
  (try-guess first-guess))


(defn f [x] (/ (Math/log 1000) (Math/log x)))


(fixed-point         f 2.0)
(prn "")
(fixed-point-damping f 2.0)

