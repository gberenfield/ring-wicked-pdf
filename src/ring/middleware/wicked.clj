(ns ring.wicked.pdf
  (:require [clojure.java.io :as io])
  (:use [clojure.java.shell :only [sh]]))

(defn as-pdf [contents]
  (let [tempfile (io/file (str "resources/public/tmp/foo.pdf"))
        tempfilename (str (. tempfile getAbsoluteFile))
        tehpdf (:out (sh "/usr/local/bin/wkhtmltopdf" "-O" "landscape" "-" tempfilename :in contents))]
    tempfile))
