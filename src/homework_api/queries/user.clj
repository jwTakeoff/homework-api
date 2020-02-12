(ns homework-api.queries.user)
  ;;(:require [jeesql.core :refer [defqueries]]))

(def query-dir "resources/sql-queries")
(defn- build-path [sub-path] (str query-dir sub-path))
;;
;;(defqueries (build-path "/user.sql"))