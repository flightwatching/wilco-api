Migration notes to upgrade to 2016-monkey-1
=========

IFTs
---

Java 8 has brought Nashhorn to replace Rhino. The change implies some javascript changes
replace `new Date()` with `new java.util.Date()`
