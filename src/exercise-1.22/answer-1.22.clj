(comment "Clojure solution to SICP exercise 1.22")
(defn divides? [a b]
  (= (rem b a) 0))

(defn square [x] (* x x ))

(defn find-divisor [n test-divisor]
  (cond 
    (> (square test-divisor) n) n
    (divides? test-divisor n) test-divisor
    :else (find-divisor n (+ test-divisor 1))))

(defn smallest-divisor [n]
  (find-divisor n 2))

(defn prime? [n]
  (= n (smallest-divisor n)))

(defn search-for-primes [start]
  (defn f [n]
    (if (prime? n)
      n
      (f (+ n 2))))
  (if (even? start)
    (f (+ start 1))
    (f start)))

(defn benchmark-prime [n] (time (prime? n)))

(comment
primes > 1000:       1009,    1013,    1019
primes > 10000:     10007,   10009,   10037
primes > 100000:   100003,  100019,  100043
primes > 1000000: 1000003, 1000033, 1000037
)

