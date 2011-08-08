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

(deftest test-deck-consists-of-cards 
  (is (set? deck))
  (is (every? map? deck)))

(deftest test-sum-hand 
  (are [exp sum] (= exp sum)
       31 (sum-hand [(card :hearts :ace) (card :hearts 10) (card :hearts :jack)])
       30.5 (sum-hand [(card :hearts 7)(card :spades 7)(card :diamonds 7)])
       27 (sum-hand [(card :hearts 7) (card :hearts 10) (card :hearts :jack)])
       17 (sum-hand [(card :hearts 7) (card :hearts 10) (card :diamonds 9)])
       9 (sum-hand [(card :hearts 7) (card :spades 8) (card :diamonds 9)])))
