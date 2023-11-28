(ns bistro-beacon-bot.db.project
  (:require [dialog.logger :as log]
            [datomic.api :as d]
            [cloregram.db :as db]))

(defn get-projects-count
  [user]
  (or (ffirst (d/q '[:find (count ?p)
                    :in $ ?u-id
                    :where [?p :project/owner ?u-id]]
                  (db/db) (:user/id user)))
      0))

(defn get-list
  [user]
  (map first (d/q '[:find (pull ?p [*])
                    :in $ ?user-id
                    :where [?p :project/owner ?user-id]]
                  (db/db) (:user/id user))))

(defn is-name-free?
  [user p-name]
  (empty? (d/q '[:find ?p
                 :in $ ?p-name ?u-id
                 :where [?p :project/name ?p-name]
                        [?p :project/owner ?u-id]]
               (db/db) p-name (:user/id user))))

(defn create!
  [user p-name]
  (d/transact (db/conn) [{:project/name p-name
                          :project/owner (:user/id user)}]))