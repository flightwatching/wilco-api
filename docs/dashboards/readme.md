With WILCO, you can draw your dashboard in any tool like Adobe Illustrator and get it animated using dashboard IFTs

With IFTs you can access all the elements of the dashboard, but the standard way of working is to animate the symbols of your dashboard.
IFT is a javascript piece of code 

#Debugging your IFTs
OK, you wrote an ITF and nothing happends? here are some tips to figure out what did not happend

##use the chrome inspector
Your friend is the chrome inspector. The Dashboard and Symbol IFTs are executed by you browser, not by the server. That's why it is necessary to debug it from the client browser.
To access the chrome inspector, go to the page that represents your dashboard in conditions, for example an event page with the dashboard activated. Right-click on any element and choose `inspect element`

WILCO names each one of your IFT so that it is accessible within the inspector (see [this thread](https://developer.chrome.com/devtools/docs/javascript-debugging#breakpoints-dynamic-javascript))

When your page is loaded, activate the inspector. Since in the inspector, you can ctrl+p (or cmd+p on mac). Type IFT (prefix) and a combo list will display all the loaded IFTs. choose your IFT, it will be fetched in the inspector. On the left, click on the line numbers to add/remove breakpoints. Make the IFTs to be evaluated by refreshing your page, changing the event or moving around with the samples timeline. The ITFs will be called and breakpoints activated. Hover with your mouse or right-click -> watch. You will actually see your IFT working live with access to each step.

##use no-cache
WILCO creates a cache with your dashboards, symbols and rules to accelerate the loading process. When designing rules, this is not very efficient as computing the cache is slow. add a no-cache url parameter to directly use your ongoing version of the dashboard/IFTs.

e.g convert `http://localhost:9000/#/RAF-11/events/11857` to  `http://localhost:9000/#/RAF-11/events/11857?no-cache`

##use the debug option
debug option logs more things in the console (chrome inspector tab) about the context and rules.
e.g convert `http://localhost:9000/#/RAF-11/events/11857` to  `http://localhost:9000/#/RAF-11/events/11857?debug`

you can use both options with 
e.g convert `http://localhost:9000/#/RAF-11/events/11857` to  `http://localhost:9000/#/RAF-11/events/11857?no-cache&debug`

##console.log
In your IFT, add some `console.log(anything)` and it will appear in your inspector (console tab)

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

====

#Symbols

##refering to a symbol
A symbol is described [here](../symbols/readme.md)

The symbols are automatically mapped on an IFT as soon as its id (the name of the object in illustrator) ends with `_anim`. For example, `ALT_anim` will be mapped to the IFT named `ALT`.
The symbol ALT must exist in the symbol list, and its type has to match (a svg symbol or a svg standard tag)

If the symbol has a default function, you can simply tick the auto checkbox, and the default function will be called with a sample in parameter. The sample is chosen like this:
symbol name minus `_anim`. The example above will lead to `theFunction(ALT)`

##symbol functions

Each symbol becomes a javascript object which name is the ID of the symbol. I has a function to ease access to sub components and to manage its behavior. For example, you can have a `ALT_anim` variable created for you.

###clickForTrend(sample)
This function binds the click event on the symbol. When clicked, the parameter of the sample is used to display the Trend modal box.

```javascript
ALT_anim.clickForTrend(ALT);
```

###clickForDashboard(db_id)
*Still not supported*

###clickForMessage(samples)
When the symbol is clicked, a new message is created with the given samples associated. The message is the same as the current one (fwot, layout...) but the computedDate is the current date (now). 

Samples is an array of objects with at least 2 fields: name and value. To ensure the sample continuity, we recommand modifying [SAMPLES](#samples) and passing it as parameter (or filtering it before passing)

```javascript
ALT_anim.clickForMessage(SAMPLES);
```


###inlineDashboard(db_id)
This function will take the bounding box of the element and display the given dashbaord (db_id) within it. We recommend that the element is a rect. The rect can be rotated or scaled, the dashboard will try to fit it the best

The db_id is the unique ID of the dashboard as it appears in the dashboard list

```javascript
ALT_anim.inlineDashboard(12445);
```

###d3(the_sym)
This function give you access to child elements of the symbol. The child elements are the elements which suffix is \_sym. Do not specify this suffix.
If your sub element is named `needle_sym` you will get access to it with the following. You can now use the [d3](http://d3js.org/) functions

```javascript
ALT_anim.d3("needle").attr("opacity", )
```

====

# Managing objects, using D3

In the Symbols chapter, we described how to animate static elements of your dashboard. But in many cases, you cannot know in advance the elements that will appear in your dashboard.

* Some planes, GSE in a airbase. The count of elements depends with the time
* the configuration of a FWOT: is it equipped or not?
* Depending on the type of aircraft, there is 2 or 4 engines
* ...

In those cases, WILCO allows you to dynamically create associated symbols. It also enables the user to translate or rotate them manually directly within the dashboard with smart tools.

##cloning a template
To achieve that, the user can create some template symbols, and WILCO will map the template to the data. Here is the code snippet for that


```javascript

var type2symbol = {
"A330": "a330_template",
"A340": "a340_template"
}

//clone (template, samples, uniqueId, targetGroupLayer)

var instances = clone(
    function(d) {return '#'+nature2symbol[d.value.type]},
    usefulSamples, 
    function(d) {return d.name}, 
    '#acs');
```

In this example, we will clone a template a330_template or a340_template according the the value.type of the sample see [samples description](#your-samples-by-parameter-name).
The list of samples is [SAMPLES](#samples) but it could be any custom array. The identification of the clone is the sample name and the clone will be appended in the layer which id is `acs` (the dashes are the standard id reference in javascript)

the `d` parameter used in the 2nd argument is each element of the passed array. You are not compelled to deal with samples. Could be FWOTS or any array you have




