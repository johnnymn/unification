(ns unification.db-test
  (:require [clojure.test :refer [deftest is]]
            [unification.db :refer [query]]))

(deftest test-query
  ;; Test a successful query with one matching fact.
  (is (= (query '(likes ?who pizza)
                '[(likes alice pizza) (likes bob ice-cream)])
         [{'?who 'alice}]))

  ;; Test a successful query with multiple matches.
  (is (= (query '(likes ?who pizza)
                '[(likes alice pizza) (likes bob pizza)])
         [{'?who 'alice} {'?who 'bob}]))

  ;; Test a query with no matches.
  (is (= (query '(likes ?who pizza)
                '[(likes bob ice-cream)])
         []))

  ;; Test a query with a nested structure.
  (is (= (query '(and (likes ?who pizza) (dislikes ?who broccoli))
                '[(and (likes alice pizza) (dislikes alice broccoli))
                  (and (likes bob pizza) (dislikes bob carrots))])
         [{'?who 'alice}]))

  ;; Test a query with multiple variables.
  (is (= (query '(likes ?who ?what)
                '[(likes alice pizza) (likes bob ice-cream)])
         [{'?who 'alice, '?what 'pizza}
          {'?who 'bob, '?what 'ice-cream}])))

