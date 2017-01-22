(ns app.view
  (:import goog.dom.classlist)
  (:require [reagent.core :as r]
            [app.css :as css]
            [app.sources.list :as list]
            [app.utils :refer [random-color log rand-num]]))

(css/load ::css
          [[:body {:padding 0
                   :margin 0
                   :font-family "monospace"
                   }]
           [:html :body {:width "100%"
                         :height "100%"
                         }]
           ])

(defn component []
  [:div.container [list/component]])

