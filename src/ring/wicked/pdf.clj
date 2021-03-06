(ns ring.wicked.pdf
  (:require [clojure.java.io :as io]
            clojure.string)
  (:use [clojure.java.shell :only [sh]]))

(defn my-timestamp
  "Fetch localtime and return a string of 'year-month-day-hour-min-sec' using digits"
  []
  (let [simply-a-datetime-stamp (java.text.SimpleDateFormat. "yyyy-MM-dd-HH-mm-ss")]
    (.format simply-a-datetime-stamp (java.util.Date.))))

(defn as-pdf
  "Takes an entire html page as contents and defaults to
  'resources/public/ for any css/js/img items in the path
  of the running clojure web app. Options are page orientation
  io-type (:stream or :file), 'papersize' , 'tmp-dir' and a 'resource-dir'
  subdirectory can be passed as well."
  [contents & {:keys [orientation resource-dir tmp-dir io-type papersize wkhtmltopdf-path] :or {orientation :portrait resource-dir "resources/public/" tmp-dir "/tmp" io-type :stream papersize "Letter" wkhtmltopdf-path "wkhtmltopdf"}}]
  (let [fixed-content (clojure.string/replace contents #"\"/" (str "\"" (System/getProperty "user.dir") (str "/" resource-dir)))
        temp-file (io/file (str tmp-dir "/pdf-" (my-timestamp) ".pdf"))
        temp-filename (str (. temp-file getAbsoluteFile))
        _  (sh wkhtmltopdf-path "-O" (name orientation) "-s" (str papersize) "-" temp-filename :in fixed-content)
        pdf (io/input-stream temp-file)]
    (if (= io-type :stream)
      (do
        (io/delete-file temp-filename)
        pdf)
      temp-file)))
