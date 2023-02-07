(ns nam
  (:require [nam.nam-core :as core]
            [nam.word :as word]))

(defn nam
  "Starts the nam of nam word s using rules.
  Allows to write rules in almost true nam way."
  [s & rules]
  (-> (word/create-word (str s))
      (core/nam-start
       (map
        #(let [[a rule b] %] [rule (str a) (str b)])
        (partition 3 rules)))
      (word/word-to-string)))

(defmacro nam-macro
  "Starts the nam of nam word s using rules.
  Allows to write rules in almost true nam way and avoid parentheses"
  [s & rules]
  (-> (word/create-word (str s))
      (core/nam-start
       (map
        #(let [[a rule b] %] [(resolve rule) (str `~a) (str `~b)])
        (partition 3 rules)))
      (word/word-to-string)))