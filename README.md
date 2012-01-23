# ring-wicked-pdf

Simple (m|f)iddleware for sending a webpage to wkhtmltopdf for rendering as a PDF
file

## Usage

Add `[org.clojars.gberenfield/ring-wicked-pdf" 0.1.0-SNAPSHOT"]` to your leingingen dependencies.

Require it via `(:require [ring-wicked-pdf :as wicked])`

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

## License

Copyright (C) 2012 Greg Berenfield

Distributed under the Eclipse Public License, the same as Clojure.
