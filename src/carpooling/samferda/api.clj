(ns carpooling.samferda.api
  "samferda.net carpooling API"
  (:import (java.net URLEncoder)
           (java.nio.charset Charset)
           (javax.crypto Mac)
           (javax.crypto SecretKey)
           (javax.crypto.spec SecretKeySpec)
           (org.apache.commons.codec.binary Base64)))

(def protocol "https")
(def domain "apis.is")

(defn query
  "build query given uri"
  [uri]
  (let [request (str protocol "://" domain "/" uri)]
    (println request)
    (slurp request)))

(defn get-drivers 
  "get list of drivers"
  []
  (query "rides/samferda-drivers"))

(defn get-passengers 
  "get list of passengers"
  []
  (query "rides/samferda-passengers"))

(comment 
  (in-ns 'carpooling.samferda.api)
  (get-drivers)
  (get-passengers))




