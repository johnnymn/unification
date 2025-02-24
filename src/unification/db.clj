(ns unification.db
  (:require [unification.core :as c]))

(defn query
  "Searches a database of facts 'db' for any that can
  satisfy the query 'q'. It does this by  attempting to
  unify each fact with 'q'.

  Params:
  - q: a query we want to fulfill
  - db: a database of facts to search through

  Returns: the list of facts that satisfy the query
  "
  [q db]
  (for [fact db
        :let [subs (c/unify q fact {})]
        :when (not= subs :failure)] subs))
