(ns bistro-beacon-bot.db.project
  (:require [dialog.logger :as log]
            [datomic.api :as d]
            [cloregram.db :as db]))

(defn get-projects-count
  [user]
  (or (first (d/q '[:find (count ?p)
                    :in $ ?user-id
                    :where [?p :project/owner ?user-id]]
                  (db/db) (:user/id user)))
      0))

(defn get-list
  [user]
  (d/q '[:find ?p
         :in $ ?user-id
         :where [?p :project/owner ?user-id]]
       (db/db) (:user/id user)))
