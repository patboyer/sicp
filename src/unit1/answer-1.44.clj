(comment "Clojure solution to SICP exercise 1.43")

(ns sicp-1.43
  (:use [clojure.test]))

(defn square [x] (* x x))

(comment "----------------------------------------")
(defn compose [f g] 
  (fn [x] (f (g x))))

(deftest test-compose
  (is (= ((compose square inc) 0)  1))
  (is (= ((compose square inc) 1)  4))
  (is (= ((compose square inc) 5) 36))
  (is (= ((compose square inc) 6) 49))
  (is (= ((compose square inc) 7) 64))
  
  (is (= ((compose inc square) 0)  1))
  (is (= ((compose inc square) 1)  2))
  (is (= ((compose inc square) 5) 26))
  (is (= ((compose inc square) 6) 37))
  (is (= ((compose inc square) 7) 50)))

(comment "----------------------------------------")
(defn repeated [f n]
  (cond
    (< n 1) (throw (Throwable. "Error: n must be positive."))
    (= n 1) (fn [x] (f x))
    :else   (compose f (repeated f (- n 1)))))

(deftest test-repeated
  (is (= ((repeated square 2) 5) 625))
  )

(run-tests)

