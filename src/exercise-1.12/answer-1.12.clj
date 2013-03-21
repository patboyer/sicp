(comment "Clojure solution to SICP exercise 1.12")
(ns sicp.chapter1 
  (:use clojure.test))

(defn pascal [row column]
  (cond
    (or (= 0 row) (= 0 column) (= column row)) 1
    (or (< column 0) (> column row)) 0
    :else (+ (pascal (- row 1) (- column 1)) 
             (pascal (- row 1) column))))

(deftest test-pascal 
  (is (= 1 (pascal 0 0)) "node(0,0) should be 1")
  (is (= 1 (pascal 1 0)) "node(1,0) should be 1")
  (is (= 1 (pascal 1 1)) "node(1,1) should be 1")
  (is (= 1 (pascal 2 0)) "node(2,0) should be 1")
  (is (= 2 (pascal 2 1)) "node(2,1) should be 2")
  (is (= 1 (pascal 2 2)) "node(2,2) should be 1")
  (is (= 1 (pascal 3 0)) "node(3,0) should be 1")
  (is (= 3 (pascal 3 1)) "node(3,1) should be 3")
  (is (= 3 (pascal 3 2)) "node(3,2) should be 3")
  (is (= 1 (pascal 3 3)) "node(3,3) should be 1")
  (is (= 1 (pascal 4 0)) "node(4,0) should be 1")
  (is (= 4 (pascal 4 1)) "node(4,1) should be 4")
  (is (= 6 (pascal 4 2)) "node(4,2) should be 6")
  (is (= 4 (pascal 4 3)) "node(4,3) should be 4")
  (is (= 1 (pascal 4 4)) "node(4,4) should be 1")
  (is (= 0 (pascal 4 5)) "node(4,5) should be 0"))

(run-tests)

