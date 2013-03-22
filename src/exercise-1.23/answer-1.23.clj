(comment "Clojure solution to SICP exercise 1.23")
(defn divides? [a b]
  (= (rem b a) 0))

(defn next-divisor [n]
  (if (= n 2) 
      3
      (+ n 2)))

(defn square [x] (* x x ))

(defn find-divisor [n test-divisor]
  (cond 
    (> (square test-divisor) n) n
    (divides? test-divisor n) test-divisor
    :else (find-divisor n (next-divisor test-divisor))))

(defn smallest-divisor [n]
  (find-divisor n 2))

(defn prime? [n]
  (= n (smallest-divisor n)))

(defn timed-prime-test [n]
  (newline)
  (prn n)
  (start-prime-test n (runtime)))

(defn start-prime-test [n start-time]
  (if (prime? n)
      (report-prime (- (runtime) start-time))))

(defn report-prime [elapsed-time]
  (prn " *** ")
  (prn elapsed-time))

(comment
(smallest-divisor 199)   =>  199
(smallest-divisor 1999)  => 1999
(smallest-divisor 19999) =>    7
)

