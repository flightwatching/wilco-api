Migrate from 2015 June version
=====

Database script
-----
Some fields are new in the model, and some optimization have to be done:

```sql
#add external source layout fields
ALTER TABLE abstractlayout ADD COLUMN poolperiodseconds integer;
ALTER TABLE abstractlayout ADD COLUMN urltemplate character varying(255);
ALTER TABLE abstractlayout ADD COLUMN varname character varying(255);
```

```sql
#boost fwot cache creation
CREATE INDEX computeddateindex ON acevent USING btree (computeddate);
#boost  sample querying
CREATE INDEX idx_sample_ac_timestamp ON sample USING btree (ac_id, "timestamp");
```

```sql
#change in the IFT formulas and symbol functions (SVGA has changed)
TODO
```

npm/bower update
------
```bash
#like always, but mandatory: ngmin -> ngannotate
npm install
bower install
```


JAVA update
----
