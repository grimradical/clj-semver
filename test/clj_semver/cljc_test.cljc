(ns clj-semver.cljc-test
  "A stub namespace to run tests in `.cljc` files.

  Needed to run tests in ClojureScript *and* Clojure (until `lein test`
  natively supports `.cljc` files.)"
  (:require #?(:clj  [clojure.test :as test]
               :cljs [cljs.test :as test :include-macros true])
            [clj-semver.core-test]))

#?(:cljs (enable-console-print!))

#?(:cljs
    (defmethod test/report [::test/default :summary] [m]
      (println "\nRan" (:test m) "tests containing"
               (+ (:pass m) (:fail m) (:error m)) "assertions.")
      (println (:fail m) "failures," (:error m) "errors.")
      (aset js/window "test-failures" (+ (:fail m) (:error m)))))

(defn test-runner []
  (test/run-tests
    'clj-semver.core-test))

#?(:clj (defn test-ns-hook [] (test-runner)))
