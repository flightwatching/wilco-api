XY-graph dashboards
======


Classical trends only supports time in x axis. XY graph dashboard allows the user to plot a WILCO parameter versus an other. Furthermore, they allow plotting the data for several FWOTs

The XY-graphs are customizable using the URL parameters. This documentation explains how to use them

![main view][main]
[main]: xy-images/main.png "global view of the graph"

# Configuring the dashboard

## Specifying the type of fwots to display
**mandatory field**:
It is mandatory to specify the type of fwots. They are necessary to find the parameters. WILCO parameters are grouped by fwot types so that A320 and A330 can have the same parameter name.

In the search URL path, add a `type` parameter:

`https://<site>/wilco/#/dashboards/<db_id>/?type=A380&y=EGTKidle&x=FFKidle`




## Specifying the x-y parameters
**mandatory field**: Specify the name of the parameters to display in `x` and `y`
The parameter names are then written along the axis. You can search for the names going to `edit params`. A dot is plotted only when wilco finds the 2 params in the same message. If there are several parameters with the same name in the same message (different timestamps), the first 2 ones are picked up. the considered date is the `x` param date.

## Specifying the fwots to display
If not specified, all the fwots from the specified type will be listed. You can specify a sub list by adding a `reg` list of key pairs.

![filtering on fwots][fwots]
[fwots]: xy-images/fwots.png

`https://<site>/wilco/#/dashboards/<db_id>/?reg=REV-FA&reg=REV-FB&type=A380&y=EGTKidle&x=FFKidle`

## Specifying the time span
You can specify a pair of dates between which you want to fetch the samples. the both `from` and `to` dates is optional:
* if none of `from` and `to` are provided, we get the last `count` samples (to now)
* if `from` only is provided, we get the following `count` samples (therefore not necessarily to now) from `from`
* if `to` only is provided, we get the last `count` samples to `to`
* if both `from` and `to` are provided, we get the samples between the dates. If `count` is less than the samples between the dates, most recent samples are fetch.

The format of the date times are like `YYYY-MM-DDTHH:mm:ss`. Example: `2015-04-01T00:00:00`

![filtering on dates][dates]
[dates]: xy-images/dates.png
`&from=2015-11-20T00:00:00&to=2015-12-20T00:00:00&...`

## Specifying the count of samples to fetch from the database
`count` limits the number of samples that are fetched. If not specified, 1000 is assumed. 5000 is the higher limit

## Specifying the initial x-y bounds
You can set the bound for the x-axis and y-axis with `xmin` `xmax` `ymin` `ymax`

You can specify a min bound that is greater than a max bound. In that case, the axis will be oriented in a descending order

Bounds are optional, you can specify only one bound, the other will be automatic

![axis bounds][bounds]
[bounds]: xy-images/bounds.png
`&ymin=630&...`


# User interactions

## goto an event
You can jump to an event by shift-clicking on a dot. you will be directly brought to the event page (system dashboard)

## zoom and pan
Click and pan or scroll on the graph to pan it or zoom in or out

## Focus on a sub time span
Hover the bottom timeline to focus the graph on the dots that belongs to the area you are hovering

![focus][focus]
[focus]: xy-images/focus.png
