(ns calendar.view-test
  (:require
    [calendar.view :as view]
    [cljs.test :refer-macros [deftest is testing]]
    [clj-di.test :refer-macros [with-reset]]
    [reagent.core :as r]
    [calendar.utils :as utils]
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

(deftest component-raw
  (is (not (nil? (view/component-raw)))))

(deftest renders
  (let [container (utils/create-lazy-container "test")]
    (r/render-component [view/component] container)
    (r/unmount-component-at-node container)))

(deftest my-test
  (is (= 1 (.-a (clj->js {:a 1})))))


