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

(defn generate-jwt [{:keys [email] :as user-credentials}]
  (authenticate user-credentials)
  (token-utils/generate-token email))

(defn verify-jwt [jwt]
  (let [decoded-jwt (token-utils/verify-token jwt)]
    (if (some? decoded-jwt)
      decoded-jwt
      (throw (ex-info authentication-error-message {:type :authentication})))))

(defn get-permissions [email]
  (->> (homework-api.repository.db.user/get-user-permissions email)
       (mapv :permission)))

