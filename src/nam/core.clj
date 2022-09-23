(ns nam.core
  (:require [clojure.string :as str]))

(defn create-nam-word
  "Represent s as nam word."
  [s]
  {:word       s
   :end        false
   :is-changed false})

(defn- substitute
  "Substitutes the first instance of a with b in nam word s."
  [s a b]
  (let [{:keys [word is-changed _] :as all} s]
    (case is-changed
      false (assoc all :word (str/replace-first word a b))
      true all)))

(defn -->
  "Substitutes the first instance of a with b in nam word s."
  [s a b]
  (let [{:keys [_ is-changed _] :as s} s]
    (case is-changed
      false (let [new-s (substitute s a b)]
              (case (= s new-s)
                false (assoc new-s :is-changed true)
                true s))
      true s)))

(defn ==>
  "Substitutes the first instance of a with b in nam word s and finish nam."
  [s a b]
  (let [new-s (--> s a b)]
    (case (not= s new-s)
      true (assoc new-s :end true)
      false s)))

(defn create-nam-program
  "Creates nam program."
  [& rules]
  rules)

(defn- nam-do-one-substitution
  "Does one nam substitution"
  [s rules]
  (let [[f a b] (first rules)
        next-rules (next rules)]
    (case (nil? next-rules)
      false (recur (f s a b) next-rules)
      true (f s a b))))

(defn nam-start
  "Starts the nam of nam word s using rules."
  [s rules]
  (let [new-s (nam-do-one-substitution s rules)]
    (case (or (= s new-s) (:end new-s))
      false (recur (assoc new-s :is-changed false) rules)
      true (-> new-s
               (assoc :end false)
               (assoc :is-changed false)))))