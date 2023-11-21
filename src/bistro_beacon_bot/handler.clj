(ns bistro-beacon-bot.handler
  (:require [cloregram.api :as api]
            [clojure.string :as str]))

(defn common
  [{:keys [user message]}]
  (api/send-message user (str/upper-case (:text message)) []))
