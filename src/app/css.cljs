(ns app.css
  (:require [app.utils :refer [->camel-case ->base64]]
            [garden.core :refer [css]]))

(defonce ^:private elements (atom {}))

(defn- elem
  [name]
  (.createElement js/document name))

(defn- by-name
  [name]
  (into [] (js/Array.from
             (js/document.getElementsByTagName name))))

(defn- attr
  [elem k value]
  (doto elem
    (.setAttribute (->camel-case (name k)) value)))

(defn load
  [k & garden-css]
  (let [base64 (->base64 (apply css garden-css))
        href (str "data:text/css;base64," base64)
        parent (first (by-name "body"))
        link (-> (elem "link")
               (attr :type "text/css")
               (attr :rel "stylesheet")
               (attr :href href))
        ]
    (when-let [old-link (k @elements)]
      (.removeChild parent old-link))
    (swap! elements assoc k link)
    (.appendChild parent link)
    ))
