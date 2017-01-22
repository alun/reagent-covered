(ns starter.doo
  (:require
    [doo.runner :refer-macros [doo-all-tests doo-tests]]
    [app.view-test]
    [app.utils-test]
    [app.css-test]
    ))

(doo-all-tests #"app\..*(?:-test)$")

