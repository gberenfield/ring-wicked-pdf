# ring-wicked-pdf

Simple (m|f)iddleware for sending a webpage to wkhtmltopdf for rendering as a PDF
file

## Usage

Add `[org.clojars.gberenfield/ring-wicked-pdf" 0.2.0"]` to your leingingen dependencies.

Require it via `(:require [ring-wicked-pdf :as wicked])`

To render a page as a pdf, simply call `(wicked/as-pdf [contents])` with
the full html contents to render with wkhtmltopdf.

NB: Resources (css/images) default to "resources/public/" path in
the main directory of your web app.

You can optionally pass in another resources directory.
`(wicked/as-pdf [contents your-resource-subdir])`

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

###Todo

* Handle local-random filenames and delete after use.
* locate wkhtmltopdf binary rather than hard-coding to /usr/local/bin
## License

Copyright (C) 2012 Greg Berenfield

Distributed under the Eclipse Public License, the same as Clojure.
