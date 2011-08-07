(ns schnauz.game
  (:use clojure.contrib.combinatorics))

(def colors 
  #{:clubs :diamonds :hearts :spades})

(def symbols 
  #{7 8 9 10 :jack :queen :king :ace})

(defn card [col value] 
  (assert (contains? colors col))
  (assert (contains? symbols value))
  {:color col :symbol value})

(defn value [{sym :symbol}]
  (if (number? sym)
    sym
    (cond 
      (contains? #{:jack :queen :king} sym) 10
      (= sym :ace) 11)))

(def deck 
  (cartesian-product colors symbols))


