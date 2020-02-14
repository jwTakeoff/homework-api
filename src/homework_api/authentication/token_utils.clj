(ns homework-api.authentication.token-utils
  (:require [clj-jwt.core :refer :all]
            [clj-jwt.key :refer [private-key]]
            [clj-time.core :refer [now plus hours]]))

(def token-key "secret")

(defn claim [subject]
  {:iss "Takeoff"
   :exp (plus (now) (hours 1))
   :iat (now)
   :sub subject})

;; RSA256 signed JWT
(defn generate-token [subject]
  (-> (claim subject)
      jwt
      (sign :HS256 token-key)
      to-str))

;; verify HMAC256 signed JWT
(defn verify-token [x-token]
  (let [decoded-jwt (str->jwt x-token)]
    (when (verify decoded-jwt token-key) decoded-jwt)))
