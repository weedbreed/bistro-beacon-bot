(ns bistro-beacon-bot.handler
  (:require [cloregram.api :as api]
            [bistro-beacon-bot.texts :refer [txt]]))

(defn common
  [{:keys [user message]}]
  (api/send-message user
                    (txt [:core :main-menu :msg] [(:user/username user)])
                    [[[(txt [:core :main-menu :btns :my-projects]) 'bistro-beacon-bot.handlers.main-menu/my-projects nil]]
                     [[(txt [:core :main-menu :btns :my-account]) 'bistro-beacon-bot.handlers.main-menu/my-account nil]]
                     [[(txt [:core :main-menu :btns :faq]) 'bistro-beacon-bot.handlers.main-menu/faq nil]]]))
