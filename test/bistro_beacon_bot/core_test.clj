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
    (u/add :owner-1)

    (c/send-text :owner-1 "/start")
    (-> (u/wait-main-message :owner-1)
        (i/check-text "Hello, owner-1!\n\nWelcome to Bistro Beacon Bot.\nWe are happy to provide the best bistro management experience to you.")
        (i/check-btns [["My Projects"]
                       ["My Account"]
                       ["F.A.Q."]])
        (c/press-btn :owner-1 3 1))
    (-> (u/wait-main-message :owner-1)
        (i/check-text "*What is it?*\nBistro Beacon Bot\n\n*What is it for?*\nFor your comfort")
        (i/check-btns [["To Main Menu"]])
        (c/press-btn :owner-1 1 1))
    (-> (u/wait-main-message :owner-1)
        (i/check-text "Hello, owner-1!\n\nWelcome to Bistro Beacon Bot.\nWe are happy to provide the best bistro management experience to you.")
        (i/check-btns [["My Projects"]
                       ["My Account"]
                       ["F.A.Q."]])
        (c/press-btn :owner-1 1 1))
    (-> (u/wait-main-message :owner-1)
        (i/check-text "You have 0 projects:")
        (i/check-btns [["New Project"]
                       ["To Main Menu"]])
        (c/press-btn :owner-1 2 1))
    (-> (u/wait-main-message :owner-1)
        (i/check-text "Hello, owner-1!\n\nWelcome to Bistro Beacon Bot.\nWe are happy to provide the best bistro management experience to you.")
        (i/check-btns [["My Projects"]
                       ["My Account"]
                       ["F.A.Q."]])
        (c/press-btn :owner-1 1 1))
    (-> (u/wait-main-message :owner-1)
        (c/press-btn :owner-1 1 1))
    (-> (u/wait-main-message :owner-1)
        (i/check-text "*Yay!*\n\nYou are going to open new project. Please enter the name.")
        (i/check-btns [["Back"]
                       ["To Main Menu"]]))
    (c/send-text :owner-1 "Owner 1 Project 1")
    (-> (u/wait-main-message :owner-1)
        (i/check-text "🍾 *New project named \"Owner 1 Project 1\" created!*")
        (i/check-btns [["Go To Project"]
                       ["Back"]
                       ["To Main Menu"]])
        (c/press-btn :owner-1 2 1))
    (-> (u/wait-main-message :owner-1)       
        (i/check-text "You have 1 projects:")
        (i/check-btns [["Owner 1 Project 1"]
                       ["New Project"]
                       ["To Main Menu"]]))
    
    (Thread/sleep 3000)
    (is (= 1 1))))

