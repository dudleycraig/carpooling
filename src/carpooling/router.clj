(ns carpooling.router
  "System state management"
  (:require [reitit.core :as reitit]
            [reitit.ring :as ring]
            [reitit.coercion.spec]
            [cheshire.core :as json]))

(defn root-handler 
  "/ http handler"
  [_]
  {:name ::root
   :status 200
   :headers {"Content-Type" "application/json; charset=utf-8"}
   :body (json/encode {:testing "http response"})})

(def routes
  ["/" {:get root-handler}])

(defn router []
  (ring/ring-handler
   (ring/router routes)))

(comment
  (in-ns 'carpooling/router))
