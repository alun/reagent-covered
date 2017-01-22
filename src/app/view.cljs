(ns app.view
  (:import goog.dom.classlist)
  (:require [reagent.core :as r]
            [app.css :as css]
            [app.utils :refer [random-color log]]))

(css/load ::app.view
          [[:body {:padding 0
                   :margin 0
                   :font-family "monospace"
                   }]
           [:.the-component
            {:transition "color 0.4s"}
            [:&.color-1 {:color (random-color)}]
            [:&.color-2 {:color (random-color)}]
            [:&.color-3 {:color (random-color)}]
            [:&.color-4 {:color (random-color)}]
            [:p.someclass {:font-size "2em"}]
            ]])

(defonce state (r/atom {}))

(defn remove-classes
  [elem & classes]
  (js/goog.dom.classlist.removeAll elem (clj->js classes)))

(defn add-classes
  [elem & classes]
  (js/goog.dom.classlist.addAll elem (clj->js classes)))

(defn update-elem [elem]
  (let [i (get @state :counter 0)
        nxt (mod (inc i) 4)]
    (swap! state assoc :counter nxt)
    (doto elem
      (remove-classes :color-1 :color-2 :color-3 :color-4)
      (add-classes (str "color-" (inc i))))))

(defn- component-raw []
  [:div.the-component
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
