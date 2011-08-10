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

(defn sum-hand [cards] 
  "Add the values of the given hand."
  (let [groups (group-by :color cards)
        all-same-symbols? (apply = (map :symbol cards))]
    (if all-same-symbols?
      30.5
      (->> (vals groups) (map #(map value %)) (map #(apply + %)) (apply max)))))

(def deck 
  (->> (cartesian-product colors symbols) (map (fn [[c s]] (card c s))) set))

(defn init [players] 
  "Create the inital data for a game.
  Keys:
    :players - list of players
    :lives-left - map of players to lives"
  {:players players :lives-left (into {} (for [p players] [p 3]))})
