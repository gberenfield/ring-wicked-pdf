# ring-wicked-pdf

Simple (m|f)iddleware for sending a webpage to wkhtmltopdf for rendering as a PDF
file

## Usage

Add `[org.clojars.gberenfield/ring-wicked-pdf" 0.3.2"]` to your leingingen dependencies.

Require it via `(:require [ring-wicked-pdf :as wicked])`

Make sure you have a "tmp" subdirectory in your resource directory tree.  Also "wkhtmltopdf" needs to be in your webapp's binary executable path.

To render a page as a pdf, simply call `(wicked/as-pdf contents)` with the full html contents to render with wkhtmltopdf.

##Compojure Example
The following route renders `/pdf` directly as a pdf.

```clojure
(GET "/pdf" [] (wicked/as-pdf "<html><body><h1>Hello World</h1></body></html>"))
```

##Noir Example

The following renders the `/pdf` page directly to the
browser as a pdf file.

```clojure
(defpage "/" []
  "<html><body><h1>Hello World</h1></html></body>")

(defpage "/pdf" []
  (resp/content-type "application/pdf" (wicked/as-pdf (render "/"))))
```

##Options
You can optionally pass in:
<table>
  <tr><th>Item</th><th>option</th><th>default</th><th>possibilities</td></tr>
  <tr><td>resource directory</td><td>resource-dir</td><td>"resources/public/"</td><td><sub-directory string></tr>
  <tr><td>page orientation</td><td>:orientation</td><td>:portrait</td><td>:portrait :landscape</td></tr>
  <tr><td>io-type (file or stream)|</td><td>:io-type</td><td>:stream</td><td> :stream :file</td></tr>
</table>

`(wicked/as-pdf contents :orientation :landscape :resource-dir "my-resource-subdir" :io-type :file)`


NB: Resources (css/images) default to "resources/public/" path in the main directory of your web app.

## License

Copyright (C) 2012 Greg Berenfield

Distributed under the Eclipse Public License, the same as Clojure.
