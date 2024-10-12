# Unification

A simple Clojure implementation of the unification algorithm described in chapter 4 of the book Structure and Interpretation of Computer Programs (Abelson et al., 1996). I did this to fulfill an assignment for the CS4720 Artificial Intelligence course at SUU.

## Usage
The query function allows you to search through a database of facts to find any entries that satisfy a given query by unifying the query with each fact.
Installation:

Since this library is not available in Clojars or other package repositories, you'll need to clone the repository or include the relevant source files in your project manually.

### Adding to your project:
1. Clone or download the source code from the repository.
2. Ensure the namespaces are properly included in your project files.
3. Import the required namespace:
"""
(ns your-namespace
  (:require [unification.db :as db]))
"""

### Example Usage:
1. Basic Fact Matching:
"""
(def facts
  '[(likes alice pizza)
    (likes bob ice-cream)
    (likes carol pizza)])

(db/query '(likes ?who pizza) facts)
;; => [{'?who 'alice} {'?who 'carol}]

"""
2. Matching with Multiple Variables:
"""
(def facts
  '[(likes alice pizza)
    (likes bob ice-cream)])

(db/query '(likes ?who ?what) facts)
;; => [{'?who 'alice, '?what 'pizza}
;;     {'?who 'bob, '?what 'ice-cream}]
"""

## License

Copyright © 2024 Johnny M.

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
