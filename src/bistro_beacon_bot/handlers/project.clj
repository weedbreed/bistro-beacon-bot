(ns bistro-beacon-bot.handlers.project
  (:require [dialog.logger :as log]
            [bistro-beacon-bot.texts :refer [txt]]
            [bistro-beacon-bot.db.project :as db-project]
            [cloregram.users :as u]
            [cloregram.api :as api]))

(defn new
  [{:keys [user]}]
  (u/set-handler user 'bistro-beacon-bot.handlers.project/new-enter-name nil)
  (api/send-message user (txt [:core :new-project :msg])
                    [[[(txt [:common :btns :back]) 'bistro-beacon-bot.handlers.main-menu/my-projects nil]]
                     [[(txt [:common :btns :to-main-menu]) 'bistro-beacon-bot.handler/common nil]]]
                    :markdown))

(defn- apply-new-project
  [user p-name]
  (log/debugf "Name '%s' for Project of User %s is vacant" p-name  user)
  (let [project-id (db-project/create! user p-name)]
    (log/infof "User %s created new Project '%s'" user project-id)
    (api/send-message user (txt [:project :new :created] [p-name])
                      [[[(txt [:project :new :btns :to-project]) 'bistro-beacon-bot.handles.project/home {:project-id project-id}]]
                       [[(txt [:common :btns :back]) 'bistro-beacon-bot.handlers.main-menu/my-projects nil]]
                       [[(txt [:common :btns :to-main-menu]) 'bistro-beacon-bot.handler/common nil]]]
                      :markdown)))

(defn- message-name-is-not-available
  [user p-name]
  (log/warn "Name '%s' for Project of User %s is unavailable" p-name user)
  (u/set-handler user 'bistro-beacon-bot.handlers.project/new-enter-name nil)
  (api/send-message user (txt [:project :new :wrong-name] [p-name])
                    [[[(txt [:common :btns :back]) 'bistro-beacon-bot.handlers.main-menu/my-projects nil]]
                     [[(txt [:common :btns :to-main-menu]) 'bistro-beacon-bot.handler/common nil]]]
                    :markdown))

(defn new-enter-name
  [{:keys [user message]}]
  (let [p-name (:text message)]
    (if (db-project/is-name-free? user p-name)
      (apply-new-project user p-name)
      (message-name-is-not-available user p-name))))

(defn home
  [{:keys [user project-id]}]
  (let [project (db-project/load user project-id)]
    (log/debug "Loading Home page of Project" project)
    (api/send-message user (txt [:project :home :msg] [(:project/name project)]) [] :markdown)))
