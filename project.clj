(defproject calendar "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.293"]
                 [reagent "0.6.0" #_:exclusions #_[cljsjs/react]]
                 #_[cljsjs/react-with-addons "0.14.7-0"]
                 [clj-di "0.5.0"]]
  :plugins [[lein-cljsbuild "1.1.5"]
            [lein-doo "0.1.7"]
            ]
  :clean-targets [:target-path "out"]
  :doo {:debug true
        :build "test"
        :paths {:karma "./node_modules/.bin/karma --port 9881 --no-colors"}
        :alias {:default [:chrome]}
       }
  :cljsbuild {:builds [{:id           "prod"
                        :source-paths ["src"]
                        :compiler     {:main app.main
                                       :output-to "prod/main.js"
                                       :output-dir "prod"
                                       :optimizations :advanced
                                       }}
                        {:id           "dev"
                        :source-paths ["src"]
                        :figwheel     true
                        :compiler     {:main app.main
                                       :output-to "out/main.js"
                                       :output-dir "out"
                                       :optimizations :none
                                       :pretty-print true
                                       }}
                       {:id          "test"
                        :source-paths ["src" "test"]
                        :compiler   {:output-to "out/testable.js"
                                     :main calendar.main-test
                                     :optimizations :none
                                     :coverage
                                     :pretty-print true
                                     {:packages ["calendar"]}
                                     }}
                       ]}
  :profiles {:dev {:dependencies
                   :figwheel { :nrepl-port 7888 }
                   [[figwheel-sidecar "0.5.8"]
                    [com.cemerick/piggieback "0.2.1"]
                    ]
                   :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}
                   :plugins [[lein-figwheel "0.5.8"]]
                   }})
