(ns homework-api.handler
  (:require [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [schema.core :as s]
            [homework-api.verify :as verify]
            [homework-api.generate :as generate]))

(s/defschema UserCredentials
  {:email s/Str
   :password s/Str})

(s/defschema Permissions
  [s/Str])

(s/defschema XToken
  {:token s/Str})

(s/defschema PermissionHeaders
  {:x-token s/Str
   s/Keyword s/Str})

(def app
  (api
    {:swagger
     {:ui "/"
      :spec "/swagger.json"
      :data {:info {:title "Homework-api"
                    :description "Compojure Api example"}
             :tags [{:name "api", :description "Very important API endpoints"}]}}}

    (context "/api" []
      :tags ["api"]

      (GET "/permissions" [:as {headers :headers}]
        :return Permissions
        :headers [h PermissionHeaders]
        :summary "Gives a list of permissions for the user."
        (ok (do 
              (println (str "X-Token: " h))
              verify/verify-token (get h :x-token)
              ["a" "b" "c"])))

      (POST "/generate-token" []
        :return XToken
        :body [user UserCredentials]
        :summary "Generates a token for a given user."
        (ok {:token (generate/generate-token)})))))
