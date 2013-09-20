(ns clj-semver.test.core
  (:use [clj-semver.core]
        [clojure.math.combinatorics :only (combinations)])
  (:use [clojure.test]))

(deftest validation
  (is (true? (valid? {:major 1 :minor 0 :patch 0})))
  (is (true? (valid? {:major 1 :minor 0 :patch 0 :pre-release "alpha"})))
  (is (true? (valid? {:major 1 :minor 0 :patch 0 :pre-release "rc.1" :build "build.1"})))
  (is (false? (valid? {:major "A" :minor 0 :patch 0})))
  (is (false? (valid? {:major -1 :minor 0 :patch 0})))
  (is (false? (valid? "foobar"))))

(deftest parsing
  (is (= (parse "1.2.3")
         {:major 1 :minor 2 :patch 3 :pre-release nil :build nil}))
  (is (= (parse "1.2.3-alpha")
         {:major 1 :minor 2 :patch 3 :pre-release "alpha" :build nil}))
  (is (= (parse "1.2.3+build.1")
         {:major 1 :minor 2 :patch 3 :pre-release nil :build "build.1"}))
  (is (= (parse "1.2.3-alpha+build.1")
         {:major 1 :minor 2 :patch 3 :pre-release "alpha" :build "build.1"}))
  (is (= (parse "1.2.3+alpha-build.1")
         {:major 1 :minor 2 :patch 3 :pre-release nil :build "alpha-build.1"}))
  (is (thrown? AssertionError (parse "1.2.3-alpha!@.12")))
  (is (thrown? AssertionError (parse "1.2")))
  (is (thrown? AssertionError (parse "1.a.3+aewf-123"))))

(deftest comparison
  ;; These are taken from the semver spec
  (let [candidates ["1.0.0-alpha1"
                    "1.0.0-alpha.1"
                    "1.0.0-beta.2"
                    "1.0.0-beta.11"
                    "1.0.0-rc.1"
                    "1.0.0-rc.1+build.1"
                    "1.0.0"
                    "1.0.0+0.3.7"
                    "1.3.7+build"
                    "1.3.7+build.2.b8f12d7"
                    "1.3.7+build.11.e0f985a"]]
    ;; Pick all 2-item combinations from the above list, and ensure
    ;; that they compare according to the order in which they appear
    ;; in the list
    (doseq [[[i1 v1] [i2 v2]] (combinations (map-indexed vector candidates) 2)]
      (if (> i1 i2)
        (is (newer? v1 v2))
        (is (older? v1 v2))))))
