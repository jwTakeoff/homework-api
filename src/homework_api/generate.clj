(ns homework-api.generate
  (:require [clj-jwt.core  :refer :all]
            [clj-jwt.key   :refer [private-key]]
            [clj-time.core :refer [now plus hours]]))

(def claim
  {:iss "Takeoff"
   :exp (plus (now) (hours 1))
   :iat (now)})

;; RSA256 signed JWT
(defn generate-token [& _] 
  (-> claim 
      jwt 
      (sign :HS256 "secret") 
      to-str))