(ns app.sources.list
  "A component refleting project source and test files to the Web UI"
  (:require-macros [app.sources.loader :refer [load-sources]])
  (:require [ajax.core :refer [GET POST]]
            [clojure.string :as s]
            [app.css :as css]
            [reagent.core :as r]
            [cljsjs.codemirror]
            [cljsjs.codemirror.mode.clojure]))

#_(GET "/coverage/app/utils.js.html")

(css/load ::styles
          {:vendors ["webkit", "moz"]}
          [:.editor {:border [[:1px :solid :black]]
                     :width :100%}
           ^:prefix {:box-shadow [[:10px :10px :12px :-8px "rgba(0, 0, 0, 0.75)"]]}]
          [:.file {:color :#ddd}]
          [:.CodeMirror {:height :auto}]
          )

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
             (not (re-find #"test/" path))   ;; non test clojurescript
             (not (re-find #"main\." path))  ;; non main bootstrap file
             )
    (let [link (->> (s/split path "/")
                    (drop 1)                 ;; drop src/ and test/
                    (concat ["coverage"])    ;; add coverage prefix
                    (s/join "/"))
          link (s/replace link #"\.cljs" ".js.html")]
      [:a {:href link} "coverage"])))

(defn render-source
  [path code]
  (let [show-source (r/atom false)
        _ (.log js/console )
        id (->id path)]
    (fn []
      [:div {:key id}
       [:h2.file [:button {:on-click #(swap! show-source not)}
                  (if @show-source "hide" "show")]
        " " path " " [coverage-link path]]
       (if @show-source
         [editor code])
       ])))

(defn component
  []
  [:div [:h1 "Application sources"]
   [:p "lists application sources with links to coverage report"]
   (let [sources (load-sources)]
     [:div.file-list
      (for [[path code] (sort #(compare (first %1) (first %2)) sources)]
        [render-source path code])
      ])])
