(ns nam.substitution
  (:require [clojure.string :as str]))

(defn- substitute
  "Substitutes the first instance of a with b in nam word s."
  [{:keys [word _ _] :as all} a b]
  (assoc all :word (str/replace-first word a b)))

(defn -->
  "Substitutes the first instance of a with b in nam word s."
  [{:keys [_ is-changed _] :as s} a b]
  (case is-changed
    false (let [new-s (substitute s a b)]
            (case (= s new-s)
              false (assoc new-s :is-changed true)
              true s))
    true s))

(defn ==>
  "Substitutes the first instance of a with b in nam word s and finish nam."
  [s a b]
  (let [new-s (--> s a b)]
    (case (not= s new-s)
      true (assoc new-s :end true)
      false s)))