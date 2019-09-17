# Release notes for version release-pig-1

This release has been developped during Y2019 and released at the end of 2019. It mainly contains performance improvements on accessing the samples to cover the issues of slow trend/matrix rendering. This slow rendering also leads to server connexion overloads.

To have a complete view in the features and fixes

- Front-end: https://github.com/flightwatching/wilco-desktop-client/commits/master
- back-end: https://github.com/flightwatching/fleet-monitor/commits/master
- IFTs: https://github.com/flightwatching/wilco-ift/commits/master

--------------

# New dashboard chart feature

This feature allows the user to display a small chart. It is not as powerfull as the trend feature, but you can easily add chart widgets in a standard dashboard. Plus, you can access to fleet wide stacked trends.


## API

### creation of the graph
`const graph = new WILCO.Graph(container, width, height, options)`
- `container`: a d3 `<g>` element that will host the graph (ex: `my_anim.go()`)
- `width`: the width of the graph (number)
- `height`: the height of the graph (number)
- `options`: it is an object that defines customizations:
    - xScale: a d3 scale: `d3.time.scale()` to force the X span
    - yScale: a d3 scale: `d3.scale` to force the Y span
    - margin: a structure like `margin: {left:0, top:0, bottom:10,right:0}` to set the margins of the chart. By default, it is `margin: {left:30, top:30, bottom:30,right:30}`,
    - axis: do you want to display the axis: `axis: { showX:true, showY:false}`

### set the title of the graph
`graph.title(title)`
- `title`: A string that will be displayed as the title of the graph

### draw some zones
A zone helps to split the graph into zones. Zones are between 2 X values
`drawZone(zones)`
- `zones` is an array of zone objects
   - `color`: the color of the zone as web color or RGB hex (will be a little bit transparent)
   - `sn`: a short text that will be displayed vertically onto the border of the zone
   - `extent`: an array of 2 dates/numbers which are the X min and the X max values of the zone

### draw a serie (curve)
`drawSerie(name, data, opts)`
- `name`: the name of the serie. Has to be unique in the chart. It will identify the curve among others.
- `data`: an array of {x, y} elements, where x is a date and y a number
- `opts`: it is an object that defines customizations: 
   - `autoScaleX`: The X axis is recomputed to match the data.
   - `autoScaleY`: The Y axis is recomputed to match the data.
   - `color`: the color as web color or RGB hex
   - `width`: the width
   - `opacity`: the opacity of the curve (0..1)
   - `type`: can be `AREA` or `LINE`. If not defined, then it is a line.

### draw horizontal line
A shorthand to create a flat curve (horizontal line) at a y value. `name` and `opts` are the same as for `drawSerie`
`drawHorizontalLine(name,y, opts)`


### draw vertical line
A shorthand to create a vertical curve (vertical line) at a x value. `name` and `opts` are the same as for `drawSerie`
`drawVerticalLine(name, x, opts)`


### draw dots

`drawDots(dots, color, radius, opacity)`
- `dots`: a array of `dot` structures like this:
   - `x`: the x position of the dot (date)
   - `y`: the y position of the dot (date)
   - `title`: the tooltip of the dot (text)
- `color`: the color of the dot (string). can also be a function where the parameter is the `dot`. the return should be a web color or a hex value
- `radius`: the size of the circle of the dot (string). can also be a function where the parameter is the `dot`. the return should be a number
- `opacity`: the opacity of the circle of the dot (string). can also be a function where the parameter is the `dot`. the return should be a number between 0 and 1

## Migration guide

- replace `drawDots` with `drawCircles`
- replace the xDomain and yDomain with setting the values when creating the graph or use autoScaleX and autoScaleY

## Example

```javascript

//we prevent from the chart to be recreated each time a new data is received
if (this.oilConsumptionChart) return;

//we find the bounding box of the chart
const bbox = oil_chart_anim.go().node().getBBox();
const container = oil_chart_anim.go().attr('transform', 'translate('+[bbox.x, bbox.y]+')');

const DATE_PARSER = d3.time.format("%Y-%m-%dT%H:%M:%S");

//we create a void chart
this.oilConsumptionChart = new WILCO.Graph(container, bbox.width, bbox.height);

//we set the title
this.oilConsumptionChart.title(`OIL csp`);

//we deal with the look of the curve
const options = {
    color:'#63b8ff', 
    width:1, 
    opacity:1, 
    yDomain=[OIL_CONSUMPTION.minScale,OIL_CONSUMPTION.maxScale]
  };

// the points of the curve is always a {x, y} structure (numbers or dates)
const points = OIL_CONSUMPTION.history().map(d=>{
  return {
	x:DATE_PARSER.parse(d.date),
  	y:+d
  };
});

//now we draw it.
this.oilConsumptionChart.drawSerie('OIL consumption',points,options);


// there are shortcuts to create lines
if (OIL_CONSUMPTION.maxOK) {
  this.oilConsumptionChart.drawHorizontalLine('maxOK', OIL_CONSUMPTION.maxOK, {color:'red', width:1.5, opacity:1});
}
if (OIL_CONSUMPTION.minOK) {
  this.oilConsumptionChart.drawHorizontalLine('minOK', OIL_CONSUMPTION.minOK, {color:'darkorange', width:1.5, opacity:1});
}

```

![graph example](./img/graph-example.png)

------------------

# Least square algorithm

------------------

# Performance improvements

## Back-end performance improvements

While retaining compatibility with the current API, parts of the back-end have been modified to :
 - limit CPU and memory resources
 - limit database accesses
 - use newly created database indexes

The results are :
 - Up to 92% response time improvement when querying samples.
 - Up to 95% response time improvement when retrieving the last event. 
 - Up to 25% response time improvement when retrieving events with tag filtering.

## Front-end performance improvements

The front-end directly benefits the performance improvements of the back-end, while also grouping calls to the backend for better efficiency.
 
The results are:
 - Up to 50% response time improvement when viewing the fleet status.
 - Up to 39% response time improvement when viewing performance charts.

------------------


# Titles for all the windows

Now, all, the pages have a meaningfull title so that you can organize better your browser tabs

------------------


# Fleet dashboard URL parameters

