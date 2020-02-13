(ns homework-api.db.connection
  (:require [jeesql.core :refer [defqueries]]
            [hikari-cp.core :refer :all]))

(def datasource-options {:server-name   "localhost"
                         :port-number   5433
                         :adapter       "postgresql"
                         :database-name "homework"
                         :username      "takeoff"
                         :password      "12345678"})

(def datasource (delay (make-datasource datasource-options)))
