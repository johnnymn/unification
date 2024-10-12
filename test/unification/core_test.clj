(ns unification.core-test
  (:require [clojure.test :refer [deftest is]]
            [unification.core :refer [unify-var unify]]))

(deftest test-unify-var
  ;; Test with a variable already bound in substitution map.
  (is (= (unify-var '?x 'alice {'?x 'bob}) :failure))

  ;; Test that a variable can unify with an unbound value.
  (is (= (unify-var '?x 'alice {}) {'?x 'alice}))

  ;; Test when both expressions are variables, one is bound.
  (is (= (unify-var '?x '?y {'?y 'pizza}) {'?x 'pizza, '?y 'pizza}))

  ;; Test when both variables are unbound.
  (is (= (unify-var '?x '?y {}) {'?x '?y}))

  ;; Test that a non-variable expression binds the variable.
  (is (= (unify-var '?x 'pizza {}) {'?x 'pizza})))

(deftest test-unify
  ;; Test with the same expressions.
  (is (= (unify 'alice 'alice {}) {}))

  ;; Test unifying variables with constants.
  (is (= (unify '?who 'alice {}) {'?who 'alice}))

  ;; Test unifying two variables.
  (is (= (unify '?who '?what {}) {'?who '?what}))

  ;; Test unifying sequences of the same length.
  (is (= (unify '(likes ?who pizza) '(likes alice pizza) {})
         {'?who 'alice}))

  ;; Test that unification fails when sequence lengths don't match.
  (is (= (unify '(likes ?who pizza) '(likes alice) {}) :failure))

  ;; Test unifying nested sequences.
  (is (= (unify '(and (likes ?who pizza) (likes ?who ice-cream))
                '(and (likes alice pizza) (likes alice ice-cream)) {})
         {'?who 'alice})))

