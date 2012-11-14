# ring-wicked-pdf

Simple (m|f)iddleware for sending a webpage to wkhtmltopdf for rendering as a PDF
file

## Usage

Add `[org.clojars.gberenfield/ring-wicked-pdf" 0.2.1"]` to your leingingen dependencies.

Require it via `(:require [ring-wicked-pdf :as wicked])`

Make sure you have a "tmp" subdirectory in your resource directory tree.

To render a page as a pdf, simply call `(wicked/as-pdf [contents])` with
the full html contents to render with wkhtmltopdf.

###Compojure Example
The following route renders `/pdf` directly as a pdf.

```clojure
(GET "/pdf" [] (wicked/as-pdf "<h1>Hello World</h1>"))
```

###Noir Example

The following renders the `/pdf` page directly to the
browser as a pdf file.

```clojure
(defpage "/" []
  "<h1>Hello World</h1>")

(defpage "/pdf" []
  (resp/content-type "application/pdf" (wicked/as-pdf (render "/"))))
```

###Options
You can optionally pass in another resource directory as well as page
orientation.
`(wicked/as-pdf contents :orientation "landscape" :resource-dir "my-resource-subdir")`

NB: Resources (css/images) default to "resources/public/" path in
the main directory of your web app.

### License

Copyright (C) 2012 Greg Berenfield

Distributed under the Eclipse Public License, the same as Clojure.
