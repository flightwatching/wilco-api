With WILCO, you can draw your dashboard in any tool like Adobe Illustrator and get it animated using dashboard IFTs

With IFTs you can access all the elements of the dashboard, but the standard way of working is to animate the symbols of your dashboard.
IFT is a javascript piece of code 

#Variables accessible in the dashboard

##libraries
some useful libraries are included in the IFTs. You can use it:
* [jquery](http://api.jquery.com/) to navigate in the DOM structure of your dashboard
* [underscore](http://underscorejs.org/) to handle arrays (search, filter, sort...)
* [d3](http://d3js.org/) to access and animate your dashboard
* [moment](http://momentjs.com/docs/) to handle dates

WILCO provides context related variables to the designer so that the dashboard is animated according to it.

##Dashboards for an event
Here is the exhaustive list of the variables you can access in yout IFT
###EVT
`EVT` is an object that represents the [EventV3IO API object](/java/com/fw/wilco/api/EventV3IO.java).
you can access the fields like this:
```javascript
var theCurrentDate = EVT.computedDate;
//use the variable (display...)
```
the dates are strings of formatted dates in ISO format (e.g 2014-11-26T23:56:12)
you can use [moment.js](http://momentjs.com/docs/) to process it

```javascript
var date = moment(EVT.computedDate)
date.format("dddd, MMMM Do YYYY, h:mm:ss a"); // "Sunday, February 14th 2010, 3:25:50 pm"
date.fromNow(); // 4 hours ago
```

###FWOTS
`FWOTS` is an array of known FWOTs (like in fleet view). The format of each is the same as [FwotV3IO API object](/java/com/fw/wilco/api/FwotV3IO.java)
you can loop on the FWOT array with a standard javascript loop

```javascript
for (var i = 0; i < FWOTS.length; i++) {
	var fwot = FWOTS[i];
	//do the stuff...
};
```

if you want to select an aircraft (for example the current event one) you can use underscore to avoid the loop
```javascript
	var acEvts=_.where(evts, {reg: EVT.reg});
```
###SAMPLES
even if the samples are accessible one by one, you may want to have an array with each one. The ```SAMPLES``` array is for you.

Each sample is described below

###Your samples by parameter name
Each of your samples is accessible by its parameter name. WILCO creates variables which name is the name of the parameter the sample refers. A sample is still not exactly the same as [SampleV3IO API object](/java/com/fw/wilco/api/SampleV3IO.java) but we are working on it.

Assuming your sample's param is TAT, here is what you can call

```javascript

TAT.name; //contains the string 'TAT'
moment(TAT.date); // will return the moment date of the sample

TAT.minOK //contains the minOK as defined in the parameter definition (float)
TAT.maxOK //contains the maxOK as defined in the parameter definition (float)
TAT.minScale //contains the minOK as defined in the parameter definition (float)
TAT.maxScale //contains the maxOK as defined in the parameter definition (float)

TAT; //will return the value of the sample TAT. May be a string or an Object if the TAT value was a JSON serialized string
+TAT; //will return the value of the sample TAT as a float or NaN (Not a Number) if it cannot be cast

```



#Symbols

##refering to a symbol
A symbol is described [here](../symbols.readme.md)

The symbols are automatically mapped on an IFT as soon as its id (the name of the object in illustrator) ends with `\_anim`. For example, `ALT_anim` will be mapped to the IFT named `ALT`.
The symbol ALT must exist in the symbol list, and its type has to match (a svg symbol or a svg standard tag)

If the symbol has a default function, you can simply tick the auto checkbox, and the default function will be called with a sample in parameter. The sample is chosen like this:
symbol name minus `_anim`. The example above will lead to `theFunction(ALT)`

##symbol functions

Each symbol has a function to ease access to sub components and to manage its behavior.
###clickForTrend(sample)
This function binds the click event on the symbol. When clicked, the parameter of the sample is 