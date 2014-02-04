(comment "Clojure solution to SICP exercise 1.42")

(ns sicp-1.42
  (:use [clojure.test]))

(def square [x] (* x x))

(def compose [f] 
  )

(deftest test-compose
  (is (= (compose square inc) 49))

(run-tests)

