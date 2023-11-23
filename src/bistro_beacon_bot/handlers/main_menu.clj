(ns bistro-beacon-bot.handlers.main-menu
  (:require [dialog.logger :as log]
            [bistro-beacon-bot.texts :refer [txt]]
            [cloregram.api :as api]))

(defn faq
  [{:keys [user]}]
  (api/send-message user (txt [:core :faq :msg])
                    [[[(txt [:common :btns :to-main-menu]) 'bistro-beacon-bot.handler/common nil]]]
                    :markdown))
