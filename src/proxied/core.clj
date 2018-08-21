(ns proxied.core
 (:gen-class)
 (:import (org.littleshoot.proxy.impl DefaultHttpProxyServer))
 (:require [clojure.string :as str]))

(defn -main
 ""
 [& args]
 (print (DefaultHttpProxyServer/bootstrap)))
