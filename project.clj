 (defproject homework-api "0.1.0-SNAPSHOT"
   :description "FIXME: write description"
   :dependencies [[org.clojure/clojure "1.10.0"]
                  [metosin/compojure-api "2.0.0-alpha30"]
                  [http-kit "2.4.0-alpha5"]
                  [ring/ring-core "1.8.0"]
                  [cprop "0.1.15"]
                  [prismatic/schema "1.1.12"]
                  [mount "0.1.16"]
                  [clj-jwt "0.1.1"]
                  [ragtime "0.8.0"]
                  [hikari-cp "2.10.0"]
                  [org.postgresql/postgresql "42.2.10"]
                  [webjure/jeesql "0.4.5"]
                  [cprop "0.1.13"]]
   :ring {:handler homework-api.handler/app}
   :uberjar-name "server.jar"
   :profiles {:dev {:dependencies [[javax.servlet/javax.servlet-api "3.1.0"]
                                  [ring/ring-mock "0.3.2"]]
                   :plugins [[lein-ring "0.12.5"]]}})
