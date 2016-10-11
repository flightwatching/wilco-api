add 3 fields in ScheduledAction (SVC retries)

    ALTER TABLE scheduledaction ADD COLUMN attempts integer;
    ALTER TABLE scheduledaction ADD COLUMN recipient character varying(255);
    ALTER TABLE scheduledaction ADD COLUMN upkmessage character varying(4000);


# Passing from D3.3 to D3.4

d3.scale.linear ↦ d3.scaleLinear
d3.time.scale ↦ d3.scaleTime
d3.time.scale.utc ↦ d3.scaleUtc

d3.svg.axis and axis.orient, D3 4.0 now provides four constructors for each orientation: d3.axisTop, d3.axisRight, d3.axisBottom, d3.axisLeft

# Clean up the DB for FW.set sample state (VALID->X if B787 ... params)

    update sample set state=1 where state=0 AND btrim(value, '.')='';
