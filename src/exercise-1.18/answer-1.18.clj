(comment "Clojure solution to SICP exercise 1.18")
(defn multiply [a b]
  (defn mydouble [x] (+ x x))
  (defn even? [x]
    (= (rem x 2) 0))
  (defn halve [x] (/ x 2))
  (defn mult-iter [a b result]
    (cond
      (= b 0)   result
      (even? b) (mult-iter (mydouble a) (halve b) result)
      :else     (mult-iter a (- b 1) (+ result a))))
  (mult-iter a b 0))

