(ns unification.expressions)

(defn variable?
  "Returns true if the given expression is a variable."
  [exp]
  ;; An expression is a variable if it's a symbol
  ;; and if the name begins with the "?" character.
  (and (symbol? exp) (boolean (re-find #"^\?" (name exp)))))

(defn substitute
  "Substitutes the variables in expression git the 
  corresponding values in a substitution map, if 
  no matches are found, return the expression."
  [exp subs]
  (if (variable? exp)
    (get subs exp exp)
    exp))

