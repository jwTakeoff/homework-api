(ns homework-api.repository.db.user
  (:require [clojure.java.jdbc :as jdbc]
            [homework-api.queries.user :as query]
            [homework-api.db.connection :as db]))

(defn get-user-password [email]
  (jdbc/with-db-connection [conn {:datasource @db/datasource}]
                           (query/get-user-password conn {:email email})))
