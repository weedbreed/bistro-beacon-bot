(ns bistro-beacon-bot.db.project
  (:require [dialog.logger :as log]
            [datomic.api :as d]
            [cloregram.db :as db]))

(defn get-projects-count
  "Returns count of `user`'s Projects"
  [user]
  (or (ffirst (d/q '[:find (count ?p)
                     :in $ ?u-id
                     :where
                     [?p :project/owner ?u]
                     [?u :user/id ?u-id]]
                   (db/db) (:user/id user)))
      0))

(defn get-list
  "Returns list of `user`'s Projects"
  [user]
  (map first (d/q '[:find (pull ?p [*])
                    :in $ ?user-id
                    :where
                    [?p :project/owner ?u]
                    [?u :user/id ?user-id]]
                  (db/db) (:user/id user))))

(defn is-name-free?
  "Returns `true` if for this `user` name `p-name` for Project is available (not used already)"
  [user p-name]
  (empty? (d/q '[:find ?p
                 :in $ ?p-name ?u-id
                 :where
                 [?p :project/name ?p-name]
                 [?p :project/owner ?u-id]]
               (db/db) p-name (:user/id user))))

(defn create!
  "Creates empty project for `user` with name `p-name`. Returns `:db/id` of new Project"
  [user p-name]
  (let [tx-result (d/transact (db/conn) [{:db/id "pid"
                                          :project/name p-name
                                          :project/owner [:user/id (:user/id user)]}])]
    (get-in tx-result [:tempids "pid"])))

(defn load
  "Returns Project's full info"
  [user p-id]
  (ffirst (d/q '[:find (pull ?p [*])
                 :in $ ?u-id ?p
                 :where
                 [?p :project/owner ?u]
                 [?u :user/id ?u-id]]
               (db/db) (:user/id user) p-id)))
