Migrate from 2015 June version
=====

Database script
-----
Some fields are new in the model, and some optimization have to be done:

### add external source layout fields
```sql
#add external source layout fields
ALTER TABLE abstractlayout ADD COLUMN poolperiodseconds integer;
ALTER TABLE abstractlayout ADD COLUMN urltemplate character varying(255);
ALTER TABLE abstractlayout ADD COLUMN varname character varying(255);
```

### boost fwot cache creation
```sql
#boost fwot cache creation
CREATE INDEX computeddateindex ON acevent USING btree (computeddate);
#boost  sample querying
CREATE INDEX idx_sample_ac_timestamp ON sample USING btree (ac_id, "timestamp");
```

### change `WILCO.appendSamplesInDashboard`
This function does not work anymore. Who is using it?
```sql
select dashboard_id, count(*) from dashboardrule where expression like '%appendSamplesInDashboard%' group by dashboard_id limit 100;;
```


we replace `WILCO.appendSamplesInDashboard(data);` with ` var PMCs = window.SVGA.Utils.convertSamples(data);` and all the data that are used are prefixed with `PMCs.`

```sql
#at least replace the call. but then you will have to prefix the params with PMCs.
update dashboardrule set expression=replace(expression, 'WILCO.appendSamplesInDashboard(data);', 'var PMCs = window.SVGA.Utils.convertSamples(data);') where expression like '%appendSamplesInDashboard%' and dashboard_id=937292;
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


ATI migration notes
====
the dashboards containing `appendSamplesInDashboard` are `274, 830799 and 937292`

A lot of symbols contains some deprecated functions. We modify the branch of code to inject the deprecated.
