(ns schnauz.test.game
  (:use schnauz.game)
  (:use clojure.test))

(deftest deck-consits-of-32-cards 
  (is (= 32 (count deck))))

(deftest test-value-of-cards 
  (are [x y] (= x y)
       11 (value (card :hearts :ace))
       10 (value (card :hearts :king))
       10 (value (card :hearts :queen))
       10 (value (card :hearts :jack))
       10 (value (card :hearts 10))
       9 (value (card :hearts 9))
       8 (value (card :hearts 8))
       7 (value (card :hearts 7))))
