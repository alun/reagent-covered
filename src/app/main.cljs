(ns app.main
  (:require [reagent.core :as r]
            [calendar.utils :refer
             [rand-num
              random-color
              log
              create-lazy-container
              ]]
            [calendar.view :refer [component]]))

(let [container (create-lazy-container "app")]
  (r/render-component [component] container))
