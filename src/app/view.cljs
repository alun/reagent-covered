(ns app.view
  (:import goog.dom.classlist)
  (:require [reagent.core :as r]
            [app.css :as css]
            [app.sources.list :as list]
            [app.utils :refer [random-color log rand-num]]))

(css/load ::styles
          [:body {:padding 0
                  :margin 0
                  :font-family "monospace"
                  :background "url(http://api.thumbr.it/whitenoise-361x370.png?background=4ea6caff&noise=626262&density=36&opacity=15)"
                  :font-size :20px}]
          [:html :body {:width "100%"
                        :height "100%"
                        }]
          [:.container {
            :padding-left :2em
            :padding-right :2em
           }])

(defn component []
  [:div.container [list/component]])

