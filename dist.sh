#!/bin/sh

set -e

lein cljsbuild once prod
rm -rf dist
mkdir dist
cp prod.html dist/index.html
mkdir dist/assets
cp prod/main.js dist/assets
mkdir dist/coverage
cp -R coverage/*/* dist/coverage
cp circle.yml dist


