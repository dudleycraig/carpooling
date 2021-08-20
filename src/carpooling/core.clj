(ns carpooling.core
  "main application entry point."
  (:require [carpooling.system :as system]
            [cprop.source :as cprop-source]))

(def system-properties (cprop-source/from-props-file "resources/carpooling/system.properties"))

(defn -main
  "run application"
  [& args]
  (system/go system-properties))

(comment 
  (in-ns 'carpooling.core)
  (system/go system-properties)
  (system/halt)
  (system/reset))
