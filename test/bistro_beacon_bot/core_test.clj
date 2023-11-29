(ns bistro-beacon-bot.core-test
  (:require [clojure.test :refer :all]
            [cloregram.test.fixtures :as fix]
            [cloregram.test.infrastructure.users :as u]
            [cloregram.test.infrastructure.client :as c]
            [bistro-beacon-bot.tests.main-menu :refer [test-main-menu]]
            [bistro-beacon-bot.tests.project-home :refer [test-project-home]]))

(use-fixtures :once fix/use-test-environment fix/setup-schema)

(deftest core-test
  (u/add :owner-1)

  (c/send-text :owner-1 "/start")

  (test-main-menu)
  (test-project-home)

  (Thread/sleep 1000)
  (is (= 1 1)))

