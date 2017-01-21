(ns calendar.main-test
  (:require
    [doo.runner :refer-macros [doo-all-tests]]
    [calendar.view-test]
    [calendar.utils-test]
    ))

(doo-all-tests #"calendar\..*")
