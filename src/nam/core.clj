(ns nam.core
  (:require [nam.word :as word]
            [nam.nam-core :as core]))

(defn nam
  "Starts the nam of nam word s using rules. Allows to write rules in almost true nam way."
  [s & rules]
  (core/nam-start
   (word/create-nam-word (str s))
   (map
    #(let [[a rule b] %] [rule (str a) (str b)])
    (partition 3 rules))))

(defmacro nam-macro
  "Starts the nam of nam word s using rules. Allows to write rules in almost true nam way and avoid parentheses"
  [s & rules]
  (core/nam-start (word/create-nam-word (str `~s))
                  (map
                   #(let [[a rule b] %] [(resolve rule) (str `~a) (str `~b)])
                   (partition 3 rules))))