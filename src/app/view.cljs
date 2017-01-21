(ns app.view
  (:require [reagent.core :as r]
            [app.utils :refer [random-color]]))

(defonce state (r/atom {}))

(defn update-elem [elem]
  (when elem
    (let [style (.-style elem)]
      (set! (.-color style) (random-color))
      elem)))

(defn- component-raw []
  [:div
   [:h3 "I am the component!"]
   [:p.someclass
    "I have " [:b "bold"] " and " [:i "italic"]
    " text."]])

(defn- component-did-mount [this]
  (let [node (r/dom-node this)
        render-node (partial update-elem node)
        timeout-id (js/setInterval render-node 400)]
    (swap! state assoc ::timeout-id timeout-id)))

(defn- component-will-unmount [this]
  (let [timeout-id (::timeout-id @state)]
    (js/clearInterval timeout-id)))

(def component
  (with-meta component-raw
    {:component-did-mount    component-did-mount
     :component-will-unmount component-will-unmount}))
