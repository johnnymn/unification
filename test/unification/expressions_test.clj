(ns unification.expressions-test
  (:require [clojure.test :refer [deftest is]]
            [unification.expressions :as e]))

(deftest test-variable?
  ;; Test that variables starting
  ;; with "?" are detected.
  (is (true? (e/variable? '?x)))
  (is (true? (e/variable? '?who)))
  (is (true? (e/variable? '?what)))

  ;; Test that non-variable symbols 
  ;; are not detected as variables.
  (is (false? (e/variable? 'x)))
  (is (false? (e/variable? 'who)))
  (is (false? (e/variable? 'what)))

  ;; Test that nil is not a variable.
  (is (false? (e/variable? nil)))

  ;; Test that keywords are not variables.
  (is (false? (e/variable? :?var))))

(deftest test-substitute
  ;; Test that variables are substituted 
  ;; correctly from the substitution map.
  (is (= 'alice (e/substitute '?who {'?who 'alice})))
  (is (= 'pizza (e/substitute '?what {'?what 'pizza})))

  ;; Test that variables not in the
  ;; substitution map return themselves.
  (is (= '?who (e/substitute '?who {})))
  (is (= '?what (e/substitute '?what {'?other 'bob})))

  ;; Test that non-variables are returned as-is.
  (is (= 'bob (e/substitute 'bob {})))
  (is (= 'pizza (e/substitute 'pizza {'?who 'alice}))))

