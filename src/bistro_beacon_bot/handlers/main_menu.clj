(ns bistro-beacon-bot.handlers.main-menu
  (:require [dialog.logger :as log]
            [bistro-beacon-bot.texts :refer [txt]]
            [bistro-beacon-bot.db.project :as db-project]
            [cloregram.api :as api]))

(defn my-projects
  [{:keys [user]}]
  (api/send-message user (txt [:core :my-projects :msg] [(db-project/get-projects-count user)])
                    [[[(txt [:common :btns :to-main-menu]) 'bistro-beacon-bot.handler/common nil]]]))

(defn faq
  [{:keys [user]}]
  (api/send-message user (txt [:core :faq :msg])
                    [[[(txt [:common :btns :to-main-menu]) 'bistro-beacon-bot.handler/common nil]]]
                    :markdown))
