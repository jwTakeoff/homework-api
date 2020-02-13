(ns homework-api.handler
  (:require [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [schema.core :as s]
            [homework-api.service.permission.user :as user-service]
            [ring.util.http-response :as response]
            [compojure.api.exception :as ex]))

(s/defschema UserCredentials
  {:email    s/Str
   :password s/Str})

(s/defschema Permissions
  [s/Str])

(s/defschema XToken
  {:token s/Str})

(s/defschema PermissionHeaders
  {:x-token  s/Str
   s/Keyword s/Str})

(defn- error-handler-factory [f type]
  (fn [^Exception e data request]
    (f {:message (.getMessage e), :type type})))

(def app
  (api
    {:swagger
     {:ui   "/"
      :spec "/swagger.json"
      :data {:info {:title       "Homework-api"
                    :description "Compojure Api example"}
             :tags [{:name "api", :description "Very important API endpoints"}]}}
     :exceptions
     {:handlers
      {:authentication (error-handler-factory response/unauthorized :authentication)
       :ex/default     (error-handler-factory response/internal-server-error :unknown)}}}

    (context "/api" []
      :tags ["api"]

      (GET "/permissions" [:as {headers :headers}]
        :return Permissions
        :headers [h PermissionHeaders]
        :summary "Gives a list of permissions for the user.")
      ;;(ok (do
      ;;      (println (str "X-Token: " h))
      ;;      verify/verify-token (get h :x-token)
      ;;      ["a" "b" "c"])))

      (POST "/generate-token" []
        :return XToken
        :body [user-credentials UserCredentials]
        :summary "Generates a token for a given user."
        (ok {:token (user-service/generate-jwt user-credentials)})))))

;;(ok {:token (generate/generate-token)})))))
