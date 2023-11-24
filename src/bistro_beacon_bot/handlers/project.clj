(ns bistro-beacon-bot.handlers.project
  (:require [dialog.logger :as log]
            [bistro-beacon-bot.texts :refer [txt]]
            [bistro-beacon-bot.db.project :as db-project]
            [cloregram.api :as api]))

(defn new
  [{:keys [user]}]
  (api/send-message user (txt [:core :new-project :msg])
                    [[[(txt [:common :btns :back]) 'bistro-beacon-bot.handlers.main-menu/my-projects nil]]
                     [[(txt [:common :btns :to-main-menu]) 'bistro-beacon-bot.handler/common nil]]]))
