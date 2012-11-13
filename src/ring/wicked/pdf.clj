(ns ring.wicked.pdf
  (:require [clojure.java.io :as io])
  (:use [clojure.java.shell :only [sh]]))

(defn as-pdf
  "Takes an entire html page as contents and defaults to
  'resources/public/ for any css/js/img items in the path
  of the running clojure web app. Optionally, a 'resources'
  subdir can be passed as well."
  ([contents]
  (let [fixed-content (clojure.string/replace page #"\"/" (str "\"" (System/getProperty "user.dir") "/resources/public/"))
        temp-file (io/file (str "resources/public/tmp/foo.pdf"))
        temp-filename (str (. temp-file getAbsoluteFile))
        pdf (:out (sh "/usr/local/bin/wkhtmltopdf" "-O" "landscape" "-" temp-filename :in fixed-content))]
    temp-file))
  ([contents resources]
  (let [fixed-content (clojure.string/replace page #"\"/" (str "\"" (System/getProperty "user.dir") resources))
        temp-file (io/file (str "resources/public/tmp/foo.pdf"))
        temp-filename (str (. temp-file getAbsoluteFile))
        pdf (:out (sh "/usr/local/bin/wkhtmltopdf" "-O" "landscape" "-" temp-filename :in fixed-content))]
    temp-file)))
