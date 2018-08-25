(ns proxied.core
 (:gen-class)
 (:import (org.littleshoot.proxy HttpFiltersSourceAdapter HttpFiltersAdapter))
 (:import (org.littleshoot.proxy.impl DefaultHttpProxyServer))
 (:require [clojure.string :as str]))

(defn -main
 ""
  [& args]
  (println "START")
  (.start
   (.withFiltersSource
    (.withPort
     (DefaultHttpProxyServer/bootstrap)
     18080)
    (proxy [HttpFiltersSourceAdapter] []
      (filterRequest [res ctx]
        (proxy [HttpFiltersAdapter] [res]
          (serverToProxyResponse [obj]
            obj)))))))

