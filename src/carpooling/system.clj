(ns carpooling.system
  "System state management"
  (:require [integrant.core :as integrant]
            [ring.adapter.jetty :as jetty]
            [carpooling.router :refer [router]]))

(defn populate-integrant-configuration 
  "populate integrant configuration from system properties"
  [props] 
  {:service/http {:options (merge {:join? false} (get-in props [:carpooling :http]))
                  :handler (integrant/ref :service/app)}
   :service/app {:options {}
                 :handler nil}})

(defonce ^:private system nil)

(def alter-system (partial alter-var-root #'system))

(defmethod integrant/init-key :service/app [_ _]
  (println (str "Starting router"))
  (router))

(defmethod integrant/init-key :service/http [_ {:keys [options handler]}]
  (println (str "Starting http server on port " (get options :port)))
  (let [handler (atom (delay handler))]
    {:handler handler
     :system (jetty/run-jetty (fn [request] (@@handler request)) options)}))

(defmethod integrant/halt-key! :service/http
  [_ {:keys [system]}]
  (.stop system))

(defn go
  "Start system, initializing application services' state"
  ([props]
   (println (str "Initializing system"))
   (alter-system (constantly (integrant/init (populate-integrant-configuration props))))))

(defn halt 
  "Stop system"
  []
  (println (str "Stopping system"))
  (alter-system integrant/halt!))

(defn reset 
  "Restart system"
  [props]
  (println (str "Restarting system"))
  (halt)
  (go props))

(comment
  (in-ns 'carpooling/system))
