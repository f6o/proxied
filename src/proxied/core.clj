(ns proxied.core
 (:gen-class)
 (:import (io.netty.handler.codec.http HttpContent))
 (:import (org.littleshoot.proxy HttpFiltersSourceAdapter HttpFiltersAdapter))
 (:import (org.littleshoot.proxy.impl DefaultHttpProxyServer))
 (:require [clojure.string :as str]))

(defmulti h1-to-h2 class)

(defmethod h1-to-h2 HttpContent [content]
  (println :http-content))

(defmethod h1-to-h2 :default [x]
  (println :not-found))

(def response-handler
  (proxy [HttpFiltersSourceAdapter] []
    (filterRequest [res ctx]
       (proxy [HttpFiltersAdapter] [res]
         (proxyToClientResponse [obj]
           (h1-to-h2 obj)
           obj)))))

(defn -main
 ""
  [& args]
  (println "START")
  (.start
   (.withFiltersSource
    (.withPort
     (DefaultHttpProxyServer/bootstrap)
     18080)
    response-handler)))

