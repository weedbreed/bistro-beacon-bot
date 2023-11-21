(ns bistro-beacon-bot.core-test
  (:require [clojure.test :refer :all]
            [cloregram.test.fixtures :as fix]
            [cloregram.test.infrastructure.users :as u]
            [cloregram.test.infrastructure.client :as c]
            [cloregram.test.infrastructure.inspector :as i]
            [bistro-beacon-bot.core :refer :all]))

(use-fixtures :once fix/use-test-environment fix/setup-schema)

(deftest core-test
  (testing "Main menu"
    (u/add :client-1)

    (c/send-text :client-1 "Hello!")
    (-> (u/wait-main-message :client-1)
        (i/check-text "HELLO!"))))
