(ns nam.word)

(defn create-nam-word
  "Represent s as nam word."
  [s]
  {:word       s
   :end        false
   :is-changed false})