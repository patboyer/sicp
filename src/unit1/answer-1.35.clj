(comment "Clojure solution to SICP exercise 1.35")

(ns sicp-1.35
  (:use [clojure.test]))

(comment "----- Fixed Point -----")
(defn fixed-point [f first-guess]
  (defn close-enough? [v1 v2]
    (< (Math/abs (- v1 v2)) 0.00001))
  (defn try-guess [guess]
    (let [next-guess (f guess)]
      (if (close-enough? guess next-guess)
      next-guess
      (try-guess next-guess))))
  (try-guess first-guess))


(defn golden-ratio [n]
  (+ 1 (/ 1 n)))


(comment "----- Tests -----")
(deftest test-fixed-point
  (is (= (fixed-point (fn [x] (Math/cos x))                  1.0) 0.7390822985224024))
  (is (= (fixed-point (fn [x] (+ (Math/sin x) (Math/cos x))) 1.0) 1.2587315962971173))
  (is (= (fixed-point golden-ratio                           1.0) 1.6180327868852458)))

(run-tests)

