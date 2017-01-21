(ns app.view-test
  (:require
    [cljs.test :refer-macros [deftest is testing]]
    [reagent.core :as r]
    [app.view :as view]
    [app.utils :as utils]
    ))

(deftest update-elem
  (testing "updates"
    (let [elem (.createElement js/document "div")]
      (view/update-elem elem)
      (is (re-matches #"^rgb\(\d+, *\d+, *\d+\)$"(-> elem .-style .-color)))
      ))
  (testing "works with null"
    (is (nil? (view/update-elem nil)))
    ))

(deftest it-renders
  (let [container (utils/create-lazy-container "test")]
    (r/render-component [view/component] container)
    (r/unmount-component-at-node container)))

