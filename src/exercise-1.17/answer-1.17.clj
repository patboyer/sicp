(defn fast-mult [a b]
  (defn double [x] (+ x x))
  (defn even? [x] 
    (= (rem x 2) 0))
  (defn halve [x] (/ x 2))
  (cond
    (= b 0)   0
    (even? b) (fast-mult (double a) (halve b))
    :else     (+ a (fast-mult a (- b 1)))))

