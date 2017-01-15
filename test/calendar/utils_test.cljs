(ns calendar.utils-test
  (:require
    [calendar.utils :as utils]
    [cljs.test :refer-macros [deftest is testing]]
    [clj-di.test :refer-macros [with-reset]]
    ))

(deftest rand-num
  (testing "just random"
    (let [r (utils/rand-num 100)]
      (is (number? r))
      ))
  (testing "with suffix"
    (let [r (utils/rand-num 100 "px")]
      (is (.endsWith r "px"))
      ))
  (testing "arity"
    (try
      (utils/rand-num)
      (catch js/Object e (utils/log (.-message e)))
      )))

(deftest log
  (let [calls (atom [])
        mock (clj->js
               {:log (fn [& args] (swap! calls conj args))})
        ]
    (with-reset [js/console mock]
      (utils/log "bla" "bla" "bla")
      (is (= (first @calls) ["bla" "bla" "bla"]))
      (apply utils/log ["and" "bla"])
      (is (= (last @calls) ["and" "bla"]))
      ;; and try with no args
      (utils/log)
      )))

(deftest create-lazy-container
  (let [elem (utils/create-lazy-container "some")]
    (is (not (nil? elem)))
    (is (= elem (utils/create-lazy-container "some")))
    ))

(deftest random-color
  (let [c (utils/random-color)
        matches (re-seq #"rgb\((\d+),(\d+),(\d+)\)" c)
        [_ r g b] (first matches)]
    (doseq [c [r g b]]
      (is (number? (js/parseInt c)))
      )))
