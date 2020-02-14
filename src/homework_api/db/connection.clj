(ns homework-api.db.connection
  (:require [jeesql.core :refer [defqueries]]
            [hikari-cp.core :refer :all]
            [homework-api.init :refer [config]]))

(def datasource (delay (make-datasource (:postgres config))))
