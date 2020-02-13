(ns homework-api.service.permission.user
  (:require [homework-api.authentication.token-utils :as token-utils]
            [homework-api.repository.db.user :as user-repository]))

(def authentication-error-message "Wrong input data")

(defn- authenticated? [{:keys [password email]}]
  (and (some? password)
       (not= password "")
       (= password (user-repository/get-user-password email))))

(defn authenticate [user-credentials]
  (when-not (authenticated? user-credentials)
    (throw (ex-info authentication-error-message {:type :authentication}))))

(defn generate-jwt [user-credentials]
  (authenticate user-credentials)
  (token-utils/generate-token))