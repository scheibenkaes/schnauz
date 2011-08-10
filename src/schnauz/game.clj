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
  (->> (cartesian-product colors symbols) (map (fn [[c s]] (card c s)))))

(defn deal-cards [players cards] 
  "Deal the cards to players. Returns a map of the following keys:
  :not-dealt-cards - the cards that were not dealt to the players.
  :players-cards - a map players to three cards in a vector."
  (let [part (partition 3 cards)]
    {
     :players-cards (apply hash-map (interleave players part))
     :not-dealt-cards (drop (* 3 (count players)) cards)
     }))

(defn init [players] 
  "Create the inital data for a game.
  Keys:
    :players - list of players
    :lives-left - map of players to lives
    :player-at-turn - the player who's turn it is.
    :hands - players to cards map"
  {:players players :lives-left (into {} (for [p players] [p 3])) :hands (deal-cards players (shuffle deck))})
