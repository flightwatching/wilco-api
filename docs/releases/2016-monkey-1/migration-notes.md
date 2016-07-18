Migration notes to upgrade to 2016-monkey-1
=========

IFTs
---

Java 8 has brought Nashhorn to replace Rhino. The change implies some javascript changes
replace `new Date()` with `new java.util.Date()`

DB
---
as per commit https://github.com/flightwatching/fleet-monitor/commit/9bc409bee558626f082a740d22727068690c7d32 add a columnn ("formula") to ieiParam:

`ALTER TABLE ieiparam ADD COLUMN formula character varying(10000);`

Add a style column to acEvent (string)
`ALTER TABLE acevent ADD COLUMN style;`


Add photo field to bookmarks
`ALTER TABLE bookmark ADD COLUMN photo character varying(255);`
