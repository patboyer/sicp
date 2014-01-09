(comment "Clojure solution to SICP exercise 1.41")

(ns sicp-1.41)

(defn double [f]
  (fn [x] (f (f x))))

(prn ((double inc) 1))
(prn ((double inc) 7))
(comment "
1 => 3
7 => 9
")

(prn (((double (double double)) inc) 5))
(prn (((double (double double)) inc) 9))
(comment "
5 => 21
9 => 25
")

