(ns app.sources.list
  "A component refleting project source and test files to the Web UI"
  (:require-macros [app.sources.loader :refer [load-sources]])
  (:require [clojure.string :as s]
            [app.css :as css]
            [reagent.core :as r]
            [cljsjs.codemirror]
            [cljsjs.codemirror.mode.clojure]))

(css/load ::css
          [[:.editor]])

(defn ->id
  [path]
  (s/replace path #"[/ .]" "_"))

;; Editor

(defn bind-editor!
  [this]
  (let [node (r/dom-node this)
        [_ code] (-> this .-props .-argv)]
    (set! (.-editor this)
          (js/CodeMirror node
                         (clj->js {:value code
                                   :mode "clojure"
                                   :lineNumbers true
                                   :readOnly true})
                         ))))

(def editor
  (with-meta
    (fn [code] [:div.editor])
    {:component-did-mount bind-editor!
     }))

;; Components

(defn coverage-link
  [path]
  (when (and (s/ends-with? path ".cljs")     ;; show coverage link only for
             (not (re-find #"_test" path)))  ;; non test clojurescript
    (let [link (->> (s/split path "/")
                    (drop 1)                 ;; drop src/ and test/
                    (concat ["coverage"])    ;; add coverage prefix
                    (s/join "/"))
          link (s/replace link #"\.cljs" ".js.html")]
      [:a {:href link} "coverage"])))

(defn render-source
  [[path code]]
  (let [id (->id path)]
    [:div {:key id}
     [:h2.file path " " [coverage-link path]]
     [editor code]]))

(defn component
  []
  (let [sources (load-sources)]
    [:div.file-list
     (map render-source sources)]))
