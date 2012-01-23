(ns ring.middleware.wicked
  (:require [clojure.java.io :as io])
  (:use [clojure.java.shell :only [sh]]))


;(defn- pdf-request?
;  [req]
;  (if-let [#^String type (:content-type req)]
;    (not (empty? (re-find #"^application/.*pdf" type)))))


(defn as-pdf [contents]
  (let [tempfile (io/file (str "resources/public/tmp/foo.pdf"))
        tempfilename (str (. tempfile getAbsoluteFile))
        tehpdf (:out (sh "/usr/local/bin/wkhtmltopdf" "-O" "landscape" "-" tempfilename :in contents))]
    tempfile))
    ;(resp/content-type "application/pdf" tempfile)))

(defn wrap-wicked-pdf [handler]
  (fn [req]
    ;(let [status (:status req)]
      ;(prn "checking content-type for " (:content-type req))
      ;(if-let [body (and (pdf-request? req) (:body req))]
      (if-let [body (and (pdf-request? req) (:body req))]
      ;(if (and (= status 200) (pdf-request? req))
      ;(if (= status 200)
        (let [tempfile (io/file (str "resources/public/tmp/foo.pdf"))
              tempfilename (str (. tempfile getAbsoluteFile))
              tehpdf (:out (sh "/usr/local/bin/wkhtmltopdf" "-O" "landscape" "-" tempfilename :in body))]
          ;(doseq [] (prn (:body req))
          ;(assoc resp :body (ByteArrayInputStream. (.toByteArray bout)))))
          (prn "trying to go PDFy!")
          (assoc (handler req) :body tehpdf))
        (handler req))))

;(defn wrap-wicked-pdf [handler]
;  (fn [req]
;    (let [body (:body req)
;           status (:status req)]
;      (if (and (= status 200)
;               (pdf-request? req))
;               ;(not (get-in resp [:headers "content-encoding"])) ; no content-encoding defined
;               ;(or
;               ;  (and (string? body) (> (count body) 200))
;               ;  (instance? InputStream body)
;               ;  (instance? File body)))
;        (let [tehpdf (:out (sh "/usr/local/bin/wkhtmltopdf" "-" "-" :in body))]
;        ; should have an if stmt check return code from wkhtmltopdf and
;        ; render resp if error
;          (do (prn body)
;          (merge (handler req) {:body tehpdf :content-type "application/x-pdf"})))
;        (handler req)))))
