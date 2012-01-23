# ring-wicked-pdf

Middleware for sending a webpage to wkhtmltopdf for rendering as a PDF
file

## Usage

Add [org.clojars.gberenfield/ring-wicked-pdf" 1.0.0"] to your leingingen dependencies.
Then `(require '[ring-wicked-pdf :as wicked])`

To render a page as a pdf, simply call `(wicked/as-pdf [contents])` with
the full html contents to render with wkhtmltopdf.

###Noir Example

The following renders the `/expenses/plainhtml` table directly to the
browser as a pdf file.
```clojure
(defpage "/expenses/pdf" []
  (resp/content-type "application/pdf" (wicked/as-pdf (render "/expenses-plainhtml"))))
```
## License

Copyright (C) 2012 Greg Berenfield

Distributed under the Eclipse Public License, the same as Clojure.
