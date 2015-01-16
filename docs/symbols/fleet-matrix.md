# The fleet-matrix symbol

The fleet-matrix symbol is dedicated to display a matrix of colored boxes.

As rows we use some FWOTS (aircrafts)

Columns can be anything that is specified with the matrix initialization (see below). The matrix queries WILCO to get all the samples on a timespan. The user can then define formulas to apply its algorithm to those sample and return a real number (averages, maximums, minimums or a totally specific algorithm).

#initializing the matrix

To initialize the matrix, you will have to call `init(fwots, cols, params, width, height)`. It has to be called before any matrix update. 

This method creates the matrix, lines and columns and prepares it to receive some data from the WILCO server.

##fwots
This parameter is an array containing the fwots. A fwot is a structure with at least a reg property. It can be an aircraft, an airport, an equipment, or whatever object that is able to send some messages:

```javascript
var myFwot = 
  {
	reg:FW-DAO
  }
```

You should never create this list by yourself. WILCO comes with a prefilled variable named ```FWOTS```which is an array of all the fwots of WILCO. Each item contains the ```reg``` and a serie of useful fields:

```json
[ {
      "reg": "FW-LUC",
      "category": "aircraft",
      "type": "A320",
      "status": "gatein",
      "properties": {
          status: "CHECK-C"
       }
    },
    {
      "reg": "OMDB",
      "coolName": "Dubai",
      "category": "airport",
      "type": "standard",
      "status": "gatein",
      "properties": {},
      "lat": 25.252777,
      "lon": 55.364445,
      "alt": 62.0
    } ]
```

You can use [\_.filter()](http://underscorejs.org/#filter) or  [\_.where()](http://underscorejs.org/#where) to filter it:

```javascript
//returns all the aircraft of type A320
var a320s = _. where(FWOTS, {type: "A320"});
//returns all the aircraft which registration starts with FW-
var fwFleet = _. filter(FWOTS, function(ac) {
   return ac.reg.indexOf("FW-") == 0;)
  };
```

##cols
This parameter is an array of columns of the matrix. It contains list of structures that defines the behavior of each column. The structure has a `name`, a `domain`, a `colors`, a `formula` and eventually a `text` field

```javascript 
var columns = [
  { name: "FF max",
    domain: [600,1000],
    colors: ['black', 'lime'],
    formula: function(data) {
      return  _.pluck(data,'APUFF').max();
    },
    text: function(val) {return val.toFixed(0);} // displays the value without the decimals
  },
  { name: "IDLE EGT HI",
    domain: [640,720],
    colors: ['black', 'lime'],
    formula: function(data) {
      return  _.pluck(data,'I_APUEGT').max();
    },
    text: function(val) {return val.toFixed(2);} // displays the value ith 2 decimals
  },
];
```

* `name`: the name as it appears in the header of the column. Should be short, (overflow is hidden)
* `domain`: an array of values that the `formula` could return. WILCO transforms values to cell's color. The elements of the domain array are waypoints for WILCO to compute the colors. You can have as many waypoints as you want. If a `formula` returns a value outside of the range, it is clamped to the min or max value. The values have to be increasing or decreasing.
* `colors`: an array of colors. Each color corresponds to an element of the `domain` field. `domain` and `color` fields must have the same length. The colors can be [HTML color names](http://www.w3schools.com/htmL/html_colornames.asp) or hexadecimal RGB strings (`'#33AFBC'`of `'#A3D'` for instance)
* 'formula`: a function that returns a value from the big sample table. This part is discussed later on
* `text`: if text is provided and is a function, a short text is printed on the colored square. The content of the text is the the string returned by the text function. Text function is optional

##Params
Params is an array with the names of all the parameters used by the formulas. It will drive the query to the server

```javascript
var params = ["APUSDID", "APUFF", "I_APUEGT"];
```

##width
the width of the matrix. WILCO will divide this width so that cols are equally distributed

##height
the height of the matrix. WILCO will divide this height so that fwots are equally distributed


#Updating the matrix
The matrix can be filled using `matrix_anim.updateMatrix(count, unit, refDate)`. Calling this function, WILCO will build a query to get all the parameters required (see _initializing the matrix_)

* `count`: the mount of units before the refDate or before now
* `unit`: the unit (seconds, minutes, hours, days, weeks, months, years)
* `refDate`: the date of the newest sample.

Example:  `matrix_anim.updateMatrix(30, 'days', '2014-05-01T00:00:00')` will query all the data between `2014-04-01T00:00:00` and `2014-05-01T00:00:00`. `matrix_anim.updateMatrix(1, 'months', '2014-05-01T00:00:00')` will query the same timespan

#The formulas
When WILCO gathers all the data from the server, it builds a big table from all the samples. All the formula functions are then called and all the text functions too.

##How to write the formulas
the formula is a function like this: `function(data) {//your code}
The data is a 2-dimension table with the samples of 1 fwot only.

|Date|APUN1|APUN2|
|-|-|-|
|2012/08/13 07:24:51|100 |91.9|
|2012/08/13 07:24:58|100 |91.9|
|2012/08/13 07:24:59|100 |92|
|2012/08/13 07:25:00|100 |92|
|2012/08/13 07:25:01|68.3|66.8|
|2012/08/13 07:25:03|40  |42|
|2012/08/13 07:25:05|28.8|31.1|
|2012/08/13 07:25:07|22.7|24.5|
|2012/08/13 07:25:09|18.4|20.1|
|2012/08/13 07:25:11|15.4|17|
|2013/01/04 13:34:41|0   |5.7|
|2013/01/04 13:34:42|0   |7.3|

You often do not need the first column. You can use the good old for loop to loop on the matrix:

```javascript
function(data) {
  var sumAvg = 0;
  for (var i=0; i<data.length; i++) {
    var APUN1=data[i][1]; // row index starts at 0
    var APUN2=data[i][2]; // row index starts at 0
    sumAvg = sumAvg+(APUN1+APUN2)/2
  }
  return sumAvg/data.length;
}
``` 
[underscore.js](http://underscorejs.org/) is a good way of working with this table.

for example:
```javascript
  _.pluck(data, "APUN1") 
  // will create an array with [100,100,100,100,68.3,40,28.8,22.7,18.4,15.4,0,0]
  _.map(data, function(row){ return row[1] * 2 ; });
  // will create an array with [300,300,300,300,136.6,80,57.6,45.4,36.8,30.8,0,0]
  _.filter(data, function(row){ return row[1]>0; });
  // will create an matrix for all the lines where APUN1 is greater than 0
```

WILCO comes with simple computation on float arrays
```javascript
  _.pluck(data, "APUN1").mean()
  // will return the mean of [100,100,100,100,68.3,40,28.8,22.7,18.4,15.4,0,0]
  _.pluck(data, "APUN1").max()
  // will return 100
  _.pluck(data, "APUN1").min()
  // will return 0
```

Full example
```javascript

formula: function(data) {
  //return the mean value of APUN1, excluding the samples where APUN1 is 0
  return _.pluck(_.filter(data, function(row){ return row[1]>0; }), "APUN1").mean()
}

```
