(ns calendar.main-test
  (:require
    [doo.runner :refer-macros [doo-tests]]
    [calendar.view-test]
    [calendar.utils-test]
    ))

(doo-tests
  'calendar.view-test
  'calendar.utils-test
  )
