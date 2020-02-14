(ns homework-api.init
  (:require [cprop.core :as cprop]
            [mount.core :as mount :refer [defstate]]))

(defn- load-config []
  (cprop/load-config :resource "default-config.edn"))

(defstate config :start (load-config))

(defn initialize [] (mount/start))
