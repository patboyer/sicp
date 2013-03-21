(comment "Clojure solution to SICP exercise 1.16")
(defn exponential [base, exp]
  (defn square [x] (* x x))
  (defn exp-iter [b n result]
    (cond
      (= n 0)   result
      (even? n) (exp-iter (* b b) (/ n 2) result)
      :else     (exp-iter b (- n 1) (* result b))))
  (exp-iter base exp 1))

