# Reagent covered

A bare bones ClojureScript project which has:
1. [Reagent][Reagent] as a UI building tool
2. [Figwheel][Figwheel] for rapid development cycle
3. Tests made with [cljs.test][Cljs-Testing] and run with [doo][Doo]
4. [Instanbul][Instanbul] code coverage reports

## Prerequisites

The project needs [leiningen][Lein] and [npm][Npm] installed. Python or any tool to run ad hoc web server.

## Usage

### Installation

    npm install

### Run rebuild/test cycle

    ./doo.sh

### Run dev tools

    python -m SimpleHTTPServer 9898
    ./figwheel.sh

Open http://localhost:9898 save any file application gets rebuilt incrementally and auto reloaded in the browser without page update.

[Reagent]: https://github.com/reagent-project/reagent
[Figwheel]: https://github.com/bhauman/lein-figwheel
[Doo]: https://github.com/bensu/doo
[Cljs-Testing]: https://github.com/clojure/clojurescript/wiki/Testing
[Instanbul]: https://github.com/gotwarlost/istanbul
[Lein]: https://leiningen.org/
[Npm]: https://www.npmjs.com/



