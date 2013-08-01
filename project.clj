(defproject grimradical/clj-semver "0.2.0-SNAPSHOT"
  :description "Parsing, comparison, and manipulation of semantic version strings"
  :url "http://github.com/grimradical/clj-semver"
  :license {:name "Apache License 2.0"
            :url "http://www.apache.org/licenses/LICENSE-2.0.txt"
            :distribution :repo}
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "[1.3.0,)"]]
  :profiles {:dev {:dependencies [[org.clojure/math.combinatorics "0.0.2"]]}})
