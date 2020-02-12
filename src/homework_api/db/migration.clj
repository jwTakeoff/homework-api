(ns homework-api.db.migration
  (:require [ragtime.jdbc :as jdbc]
            [ragtime.core :as ragtime]))



(def config {:datastore (jdbc/sql-database  {:dbtype "postgresql" :dbname "homework" :port 5433 :user "takeoff" :password "12345678"})
             :migrations (jdbc/load-resources "migration")})


(defn migrate-postgres []
  (ragtime/migrate-all (:datastore config)
                       {}
                       (:migrations config)))

