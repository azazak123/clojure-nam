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

(defn nam
  "Starts the nam of nam word s using rules. Allows to write rules in almost true nam way."
  [s & rules]
  (nam-start
    (create-nam-word (str s))
    (map
      #(let [[a rule b] %] [rule (str a) (str b)])
      (partition 3 rules))))

(defmacro nam-macro
  "Starts the nam of nam word s using rules. Allows to write rules in almost true nam way and avoid parentheses"
  [s & rules]
  (nam-start (create-nam-word (str `~s))
             (map
               #(let [[a rule b] %] [(resolve rule) (str `~a) (str `~b)])
               (partition 3 rules))))