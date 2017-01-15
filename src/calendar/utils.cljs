(ns calendar.utils)

(defn rand-num
  ([amp suff]
   (let [v (->> (.random js/Math) (* amp) (.trunc js/Math))]
     (if suff
       (str v suff)
       v)))
  ([amp] (rand-num amp nil)))

(defn create-lazy-container
  [id]
  (let [body (.-body js/document)
        div (or (.getElementById js/document id)
                (->> "div"
                     (.createElement js/document)
                     (.appendChild body)))]
    (set! (.-id div) id)
    div))

(defn color [r g b]
  (str "rgb(" r "," g "," b ")"))

(defn random-color []
  (color (rand-num 255) (rand-num 255) (rand-num 255)))

(defn log [& args]
  (.apply (.-log js/console) js/console (clj->js args)))
