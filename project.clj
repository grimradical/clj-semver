(defproject grimradical/clj-semver "0.4.0-SNAPSHOT"
  :description "Parsing, comparison, and manipulation of semantic version strings"
  :url "http://github.com/grimradical/clj-semver"
  :license {:name "Apache License 2.0"
            :url "http://www.apache.org/licenses/LICENSE-2.0.txt"
            :distribution :repo}
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.228"]]
  :profiles {:dev {:dependencies [[org.clojure/math.combinatorics "0.1.1"]]
                   :plugins [[lein-cljsbuild "1.1.2"]]}}
  :cljsbuild {:builds
              {:dev
               {:source-paths ["src"]
                :compiler {
                  :output-dir "target"
                  :output-to "target/clj-semver.js"
                  :optimizations :none
                  :cache-analysis true
                  :source-map true}}
               :test
               {:source-paths ["src" "test"]
                :compiler
                {:optimizations :whitespace
                 :pretty-print true
                 :output-dir "target/test"
                 :output-to "target/test/clj-semver.js"
                 :source-map "target/test/clj-semver.js.map"}}}
              :test-commands {"cljs" ["phantomjs"
                                      "phantom/unit-test.js"
                                      "phantom/unit-test.html"]}})
