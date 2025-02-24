(ns unification.core
  (:require [unification.expressions :as e]))

(declare unify)

(defn unify-var
  "Attempts to unify a variable with a given expression.

  Params:
  - var: the variable to unify
  - exp: the expression to unify
  - subs: a substitutions map

  Returns: the updated subs or a :failure"

  [var exp subs]
  (let [var-val (get subs var)]
    (cond
      ;; The variable is already bound to a value in the
      ;; substitution map so unify the value that the
      ;; variable is bound to with the new expression.
      var-val (unify var-val exp subs)

      ;; The expression is also a variable so check if it's
      ;; already bound to a value in the substitution map. If
      ;; it is, recursively unify, if not, bind it.
      (e/variable? exp) (if-let [exp-val (get subs exp)]
                          (unify var exp-val subs)
                          (assoc subs var exp))

      ;; The variable has not been bound and the expression is not a
      ;; variable so bound the variable to the expression.
      :else (assoc subs var exp))))

(defn unify
  "Attempts to unify two expressions using a substitution map.

  Params:
  - a: first expression
  - b: second expression
  - subs: a substitutions map

  Returns: the updated subs or a :failure"
  [a b subs]
  (cond
    ;; Base case: the unification failed already.
    (= subs :failure) :failure

    ;; If a and b are equal after we substitute
    ;; both then return the subs map.
    (= (e/substitute a subs) (e/substitute b subs)) subs

    ;; If a is a variable then unify it with b.
    (e/variable? a) (unify-var a b subs)

    ;; Same thing but for b.
    (e/variable? b) (unify-var b a subs)

    ;; If a and b are sequences and they have the same
    ;; length then unify the pairs of expressions
    ;; formed from them.
    (and (sequential? a) (sequential? b)
         (= (count a) (count b)))
    (reduce (fn [s [a b]] (unify a b s)) subs (map vector a b))

    :else :failure))

