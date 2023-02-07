(ns nam.core-test
  (:require [clojure.test :refer :all]
            [nam :refer [nam nam-macro]]
            [nam.substitution :refer [--> ==>]]))

(deftest substitute-eq-test
  (is (let [result (nam "abbbcaa"
                        "aa" --> "a"
                        "bb" --> "b"
                        "cc" --> "c")]
        (= result "abca"))))

(deftest increment-test
  (is (let [result (nam "1000"
                        "*0" --> "0*"
                        "*1" --> "1*"
                        "0*" ==> "1"
                        "1*" --> "/0"
                        "1/" --> "/0"
                        "0/" ==> "1"
                        "/" ==> "1"
                        "" --> "*")]
        (= result "1001"))))

(deftest substitute-eq-macro-test
  (is (let [result (nam-macro abbbcaa
                              aa --> a
                              bb --> b
                              cc --> c)]
        (= result "abca"))))