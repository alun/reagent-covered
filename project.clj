(defproject reagent-covered "1.0.0"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.946"]
                 [reagent "0.6.0"]
                 [cljs-ajax "0.7.2"]
                 [clj-di "0.5.0"]
                 [garden "1.3.2"]
                 [me.raynes/fs "1.4.6"]
                 [cljsjs/codemirror "5.21.0-2"]
                 ]
  :plugins [[lein-cljsbuild "1.1.5"]
            [lein-doo "0.1.8"]
            [katlex/github-cdn "0.1.4"]
            ]
  :github-cdn {:dir "dist"
               :repository "git@github.com:katlex/reagent-covered"
               :branch "gh-pages"
               }
  :clean-targets [:target-path "out"]
  :doo {:debug true
        :build "test"
        :paths {:karma "./node_modules/.bin/karma --port 9881 --no-colors"}
        :alias {:default [:chrome]}
        :coverage {:packages ['app]
                   :reporter {:check {:global {:statements 95}
                                      :each {:statements 95}}}}}
  :cljsbuild {:builds [{:id           "prod"
                        :source-paths ["src"]
                        :compiler     {:main bootstrap.main
                                       :output-to "prod/main.js"
                                       :output-dir "prod"
                                       :optimizations :simple
                                       }}
                       {:id           "dev"
                        :source-paths ["src"]
                        :figwheel     true
                        :compiler     {:main bootstrap.main
                                       :output-to "out/main.js"
                                       :output-dir "out"
                                       :optimizations :none
                                       :pretty-print true
                                       }}
                       {:id          "test"
                        :source-paths ["src" "test"]
                        :compiler   {:output-to "out/testable.js"
                                     :main starter.doo
                                     :optimizations :none
                                     :pretty-print true
                                     }}]}
  :profiles {:dev {:dependencies [[figwheel-sidecar "0.5.14"]
                                  [com.cemerick/piggieback "0.2.2"]]
                   :figwheel { :nrepl-port 7888 }
                   :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}
                   :plugins [[lein-figwheel "0.5.14"]]
                   }})
