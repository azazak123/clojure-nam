(defproject nam "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "MIT"}
  :dependencies [[org.clojure/clojure "1.11.1"]]

  :source-paths ["src"]
  :test-paths ["test"]

  :repl-options {:init-ns nam.core}
  :profiles {:dev {:plugins [[lein-cljfmt "0.9.0"]]}})