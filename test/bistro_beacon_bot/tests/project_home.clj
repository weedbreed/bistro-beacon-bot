(ns bistro-beacon-bot.tests.project-home
  (:require [clojure.test :refer :all]
            [cloregram.test.infrastructure.users :as u]
            [cloregram.test.infrastructure.client :as c]
            [cloregram.test.infrastructure.inspector :as i]))

(defn test-project-home
  []
  (testing "Project Home"
    (-> (u/wait-main-message :owner-1)
        (i/check-text "Home Menu of\n*\"Owner 1 Project 1\"*.\n\nSelect option below:"))))
