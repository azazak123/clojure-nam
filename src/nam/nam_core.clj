(ns nam.nam-core)

(defn create-nam-program
  "Creates nam program from provided rules."
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