(ns app.view-test
  (:require
    [cljs.test :refer-macros [deftest is testing]]
    [reagent.core :as r]
    [app.view :as view]
    [app.utils :as utils]
    ))

(deftest it-renders
  (let [container (utils/create-lazy-container "test")]
    (r/render-component [view/component] container)
    (r/unmount-component-at-node container)))

