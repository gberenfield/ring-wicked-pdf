(ns ring.middleware.wicked
  (:use [clojure.java.shell :only [sh]])
  (:import (java.io InputStream
                    File
                    ByteArrayInputStream
                    ByteArrayOutputStream)))


(defn wrap-wicked-pdf [handler]
  (fn [req]
    (let [{body :body
           status :status
           :as resp} (handler req)]
      (if (and (= status 200)
               (not (get-in resp [:headers "content-encoding"])) ; no content-encoding defined
               (or
                 (and (string? body) (> (count body) 200))
                 (instance? InputStream body)
                 (instance? File body)))
        (let [tehpdf (:err (sh "usr/local/bin/wkhtmltopdf" {:in body}))]
        ; should have an if stmt check return code from wkhtmltopdf and
        ; render resp if error
          (merge resp {:body tehpdf :content-type "application/x-pdf"}))
        resp))))
