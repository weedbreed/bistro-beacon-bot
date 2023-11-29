(ns bistro-beacon-bot.handlers.main-menu
  (:require [dialog.logger :as log]
            [bistro-beacon-bot.texts :refer [txt]]
            [bistro-beacon-bot.db.project :as db-project]
            [cloregram.api :as api]))

(defn- project->btnrow
  [p]
  [[(:project/name p) 'bistro-beacon-bot.handlers.project/home {:project-id (:db/id p)}]])

(defn my-projects
  [{:keys [user]}]
  (let [projects (db-project/get-list user)
        pr-btns (vec (map project->btnrow projects))
        btns (conj pr-btns
                   [[(txt [:core :my-projects :btns :new-project]) 'bistro-beacon-bot.handlers.project/new nil]]
                   [[(txt [:common :btns :to-main-menu]) 'bistro-beacon-bot.handler/common nil]])]
    (api/send-message user (txt [:core :my-projects :msg] [(db-project/get-projects-count user)]) btns)))

(defn faq
  [{:keys [user]}]
  (api/send-message user (txt [:core :faq :msg])
                    [[[(txt [:common :btns :to-main-menu]) 'bistro-beacon-bot.handler/common nil]]]
                    :markdown))
