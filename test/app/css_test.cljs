(ns app.css-test
  (:import goog.dom.query)
  (:require
    [cljs.test :refer-macros [deftest is testing]]
    [app.css :as css]
    ))

(defn elems
  [query]
  (-> (goog.dom.query query)
      js/Array.from
      seq))

(deftest load
  (let [n (count (elems "link"))]
    (css/load ::styles [[:body {:width 0 :height 0}]])
    (is (= (inc n) (count (elems "link"))))
    (css/load ::styles [[:body {:width 0 :height 0}]])
    (is (= (inc n) (count (elems "link"))))
    ))
