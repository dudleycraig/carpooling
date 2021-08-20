(ns user
  (:require [cprop.source :as cprop-source]
            [integrant.repl :as integrant-repl :refer [go halt reset reset-all]]
            [integrant.core :as integrant]
            [carpooling.core :as core]
            [carpooling.system :as system]))

(def system-properties (cprop-source/from-props-file "resources/carpooling/system.properties"))

(def integrant-configuration (system/populate-integrant-configuration system-properties))

(integrant-repl/set-prep!
  (fn [] integrant-configuration))

(comment
  (go)
  (halt)
  (reset))
