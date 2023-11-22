(ns bistro-beacon-bot.texts
  (:require [clojure.java.io :as io]
            [clojure.edn :as edn]
            [dialog.logger :as log]))

(def ^:private txt-map (-> "texts.edn" io/resource io/reader slurp edn/read-string))

(defn txt
  ([path] (txt path []))
  ([path args] (txt :en path args))
  ([lang path args]
   (let [bprint (get-in txt-map (conj path lang))]
     (assert (some? bprint) (format "Attempt to get unexistant text: %s %s" path lang))
     (apply format bprint args))))
