Migration notes to upgrade to 2016-monkey-1
=========

TODO list
---
Check specific login pages (revima/ETD) because login page has changed
disable pingdom
upgrade node, npm and bower (install n).
    In case remove `rm -rf  app/components/mapbox.js`
enable WS in nginx
run DB upgrades
send email to users

DB
---
as per commit https://github.com/flightwatching/fleet-monitor/commit/9bc409bee558626f082a740d22727068690c7d32 add a columnn ("formula") to ieiParam:

`ALTER TABLE ieiparam ADD COLUMN formula character varying(10000);`

Add a style column to acEvent (string)
`ALTER TABLE acevent ADD COLUMN style character varying(255);`


Add photo field to bookmarks
`ALTER TABLE bookmark ADD COLUMN photo character varying(255);`

Add a api-key table
```sql
CREATE TABLE apikey
(
  id bigint NOT NULL,
  details character varying(255),
  expiration timestamp without time zone,
  token character varying(255),
  profile character varying(255),
  username character varying(255),
  user_id bigint,
  CONSTRAINT apikey_pkey PRIMARY KEY (id),
  CONSTRAINT fk75462a0547140efe FOREIGN KEY (user_id)
      REFERENCES t_user (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT apikey_token_key UNIQUE (token)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE apikey;
  OWNER TO <myuser>;
```


Add a fwottype photo
`ALTER TABLE fwottype ADD COLUMN photo character varying(255);`

IFTs
---

** JAVA 8 is not in this release

Java 8 has brought Nashhorn to replace Rhino. The change implies some javascript changes
replace `new Date()` with `new java.util.Date()`
