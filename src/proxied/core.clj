(ns proxied.core
 (:gen-class)
 (:require [clojure.string :as str]))

(defn f [x]
 (print (str/split "1,2,3" #",")))

(defn -main
 ""
 [& args]
 (f 12))
