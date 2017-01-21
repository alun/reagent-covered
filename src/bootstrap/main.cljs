(ns bootstrap.main
  (:require [reagent.core :as r]
            [app.utils :refer [create-lazy-container]]
            [app.view :refer [component]]))

(let [container (create-lazy-container "app")]
  (r/render-component [component] container))
