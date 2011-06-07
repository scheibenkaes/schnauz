(ns schnauz.game
  (:use clojure.contrib.combinatorics))

(def colors 
  [:clubs :diamonds :hearts :spades])

(def symbols 
  [7 8 9 10 :jack :queen :king :ace])

(def deck 
  (cartesian-product colors symbols))

