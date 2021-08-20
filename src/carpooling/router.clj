(ns carpooling.router
  "System state management"
  (:require [reitit.core :as reitit]
            [reitit.ring :as ring]
            [reitit.coercion.spec]
            [carpooling.samferda.api :as samferda-api]
            [cheshire.core :as json]))

(defn passengers-handler 
  "/passengers request"
  [_]
  {:name ::root
   :status 200
   :headers {"Content-Type" "application/json; charset=utf-8"}
   :body (samferda-api/get-passengers)})

(defn drivers-handler 
  "/drivers request"
  [_]
  {:name ::root
   :status 200
   :headers {"Content-Type" "application/json; charset=utf-8"}
   :body (samferda-api/get-drivers)})

(def routes
  [["/passengers" {:get passengers-handler}]
   ["/drivers" {:get drivers-handler}]])

(defn router []
  (ring/ring-handler
   (ring/router routes)))

(comment
  (in-ns 'carpooling/router))
