(ns nam.word)

(defn create-word
  "Represent string as nam word."
  [s]
  {:word       s
   :end        false
   :is-changed false})

(defn word-to-string
  "Represent nam word as string."
  [{:keys [word _ _]}]
  word)