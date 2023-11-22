(ns bistro-beacon-bot.handler
  (:require [cloregram.api :as api]))

(defn common
  [{:keys [user message]}]
  (api/send-message user
                    (format "Hello, %s!\n\nWelcome to Bistro Beacon Bot.\nWe are happy to provide the best management experience to you." (:user/username user))
                    [[["My Projects" 'bistro-beacon-bot.handlers.main-menu/my-projects []]]
                     [["My Account" 'bistro-beacon-bot.handlers.main-menu/my-account []]]
                     [["F.A.Q." 'bistro-beacon-bot.handlers.main-menu/faq []]]]))
