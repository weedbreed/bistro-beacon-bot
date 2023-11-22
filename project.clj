(defproject bistro-beacon-bot "0.1.2"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}

  :java-cmd "/opt/homebrew/opt/openjdk/bin/java"

  :dependencies [[org.clojure/clojure "1.11.1"]
                 [cloregram "0.2.8"]]

  :plugins [[lein-eftest "0.6.0"]]

  :main ^:skip-aot bistro-beacon-bot.core
  :target-path "target/%s"
  :profiles {:test {:dependencies [[eftest "0.6.0"]]}
             :uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})

