(ns bistro-beacon-bot.handler
  (:require [cloregram.utils :refer [api-wrap]]
            [telegrambot-lib.core :as tbot]
            [cloregram.system.state :refer [bot]]
            [clojure.string :as str]))

(defn common ;; TODO: Move tbot and bot interaction to Cloregram!
  [{:keys [user message]}]
  (api-wrap tbot/send-message (bot)
            {:chat_id (:user/id user)
             :text (str/upper-case (:text message))}))
