(ns app.sources.loader
  (:require [me.raynes.fs :as fs]
            [clojure.string :as s]))

(defn ->path
  [file]
  (.getPath file))

(defn base-pattern
  []
  (-> fs/*cwd*
      ->path
      s/re-quote-replacement
      re-pattern))

(defn relativize
  [file]
  (let [base (base-pattern)
        path (->path file)]
    (->> (s/replace-first path base "")
         rest
         (apply str))))

(defn list-sources
  []
  (into []
        (map relativize)
        (concat
          (fs/find-files "src" #"[^#]*\.cljs?$")
          (fs/find-files "test" #"[^#]*\.cljs?$")
          )))

(defn ->name-content
  [name]
  [name (slurp name)])

(defmacro load-sources
  []
  (into {}
        (map ->name-content)
        (list-sources)))
