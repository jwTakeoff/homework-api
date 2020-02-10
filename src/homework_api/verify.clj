(ns homework-api.verify
  (:require
   [clj-jwt.core  :refer :all]))

;; verify HMAC256 signed JWT
(defn verify-token [x-token]
  (-> x-token
      str->jwt
      (verify "secret")))
