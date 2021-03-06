# Help page for IFT V2.0 available functions

**a pre-requisite is to know javascript.**

The functions belongs to a FW javascript object.
You can use some libraries (and associated variables):
* [moment](http://momentjs.com/docs/) to manipulate the dates,
* [\_](http://underscorejs.org/) to manipulate the arrays and the collections

    Some of the following functions are not available with the webservice/webhook connector as they are not linked to a message or an event.
    The best practice with those connectors is to create an event with `FW.postEvent` with a defined layout and then work on the layout IFTs to screen or enhance

## Accessing to the current fwot
You can access the current fwot by using the `fwot` variable that contains a [FwotV3IO](https://github.com/flightwatching/wilco-api/blob/master/java/com/fw/wilco/api/FwotV3IO.java) structure

## Accessing the samples in your IFT
The samples for the currently processed message or constants are provided in the script for computation. A variable is automatically created for each sample or constant. The variable contains the value of the sample as a string, whatever the status. To use it as a number, just add the sign _+_ before it.

```javascript
var EGT_1="655"; //created by WILCO
var EGT_2="658"; //created by WILCO

//can be used like this:

var EGTdiff = Math.abs(+EGT_1-EGT_2);
```

    If the name of the variable cannot be a javascript variable (contains spaces, is only a sequence of numbers, contains special characters), this variable is not created.

In any case, the sample can be accessed with the `FW` object. And this time it is the sample as an object with [those fields](https://github.com/flightwatching/wilco-api/blob/master/java/com/fw/wilco/api/SampleV3IO.java)

You can for example do this:

```javascript

if (FW["EGT_1"].state=="VALID" && FW["EGT_2"].state=="VALID" ) {
  FW.setStyle('color', 'SpringGreen');
  var EGTdiff = Math.abs(+FW["EGT_1"].value-FW["EGT_2"].value);
}
  FW.setStyle('color', 'RED');
}
```



## `FW.getEvent()`
Accesses the currently processed event. returns a [EventV3IO](https://github.com/flightwatching/wilco-api/blob/master/java/com/fw/wilco/api/EventV3IO.java) object

## `FW.getEvents(reg, from, to, withDismissed, withHidden, severities, tags, count)`
requests the database for events filtered according to the parameter passed
* reg: the registration of the FWOT. if not passed, would return events for all your fleet
* from: the min date to search, moment or YYYY-MM-DDTHH:mm:ss string format
* to  : the max date to search, moment or YYYY-MM-DDTHH:mm:ss string format
* withDismissed : include or not the dismissed events
* showHidden : includes or not the hidden events
* severities: an array of severities to narrow the search (OR operator). Severities are one of IGNORE, CREW, WARNING, FAULT, ERROR, INFO. If not passed or null, takes all the severities
* tags: a list of tags to search for (OR operator).
* count: the max count for the search (throttled to 5000 elements max)

The call returns a promise that is catchable with .then(). the callback function returns a list of matching events. If an error occurs, the promise is catchable with .catch()

```javascript
var cms=FW.getEvent();

var tailNb = cms.reg;
var toDate = cms.computedDate;
var fromDate = moment.utc(cms.computedDate).add(-2, 'days');

//FW.getEvents(reg, from, to, withDismissed, showHidden, severities, tags, count)
//we search the events where tags are in "2420FJV7", "2420FJV8" in the last 2 days
FW.getEvents(cms.reg, fromDate, toDate, true, false, null, ["2420FJV7", "2420FJV8" ], 2)
.then(function(events) {
  if (events.length>0) { // if we find at least 1 event
    FW.notify ("Magdalena.TRZECIAKOWSKA@revima-apu.com",
               "LGB possible distress",
               "APU Chip Det - APU GEN A under-frequency");
  }
});
```




## `FW.getFwot(reg)`

    only in V2

Access any FWOT using its registration. returns a [FwotV3IO](https://github.com/flightwatching/wilco-api/blob/master/java/com/fw/wilco/api/FwotV3IO.java):
```javascript
FW.updateSomeFwotProperty('FW-LUC', 'key', 'oldValue');
FW.updateSomeFwotProperty('FW-LUC', 'key', FW.getFwot('FW-LUC').properties.key+' newValue');

//would set the property **key** of FW-LUC to **oldValue newValue**
```

## `FW.getFwots()`

    only in V2

You can filter it with [\_](http://underscorejs.org/):
```javascript
const a320s = _.where(FW.getFwots(), {type:'A320'});
```


returns an array of [FwotV3IO](https://github.com/flightwatching/wilco-api/blob/master/java/com/fw/wilco/api/FwotV3IO.java):
```javascript
FW.updateSomeFwotProperty('FW-LUC', 'key', 'oldValue');
FW.updateSomeFwotProperty('FW-LUC', 'key', FW.getFwot('FW-LUC').properties.key+' newValue');

//would set the property **key** of FW-LUC to **oldValue newValue**
```



## `FW.notify(who, subject, body)`
Sends an e-mail to `who` with subject and eventually a body. A signature is always added to the message. It can be overridden with the AppConfig property `MAIL_TEMPLATE` using the groovy  syntax ([cheatsheet](https://www.playframework.com/documentation/1.5.x/cheatsheet/templates)). Few parameters are available.

	Check ${msg.sumUp} details <a href="${url}">here</a>
	<br>
	<br>
	<em>Health Monitoring by Flightwatching</em>


```javascript
FW.notify('support@flightwatching.com', //recipient list
'My subject', // subject
'Dear FW support team, ...' //body
);
```

  - `who`: A string with brackets, coma or semicolon separator of email addresses. The function will create the array of recipients from it. any field that does not contains the character @ will be ignored
  - `subject`: the subject of the email
  - `body`: the content of the message.

## `FW.notifyWithTemplate(who, subject, templateId, params)`
The purpose of this is to reference the body from a complex html file that will be customized with parameters.

Same as `FW.notify` but the body of the message is created from the template that is passed as parameter.

A template is a document (in your admin page the `docs` models). The document file is a text file that will be a html format.

  - `templateId` has to be a number (not a string) to be considered as a reference to a template.
  - `params` is a javascript object that contains all the data used by the template. This object is referenced using the groovy language ([cheatsheet](https://www.playframework.com/documentation/1.5.x/cheatsheet/templates))

### Example

``` javascript
FW.notifyWithTemplate("user1@example.com, user2@example.com", "test template", 48017774, {
  event:{
      id:FW.getEvent().id
    },
    toto:'Japan'
  }
);
```

with template#48017774 is

```html
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title></title>
  </head>
  <body>
  	<h1>HELLO FROM ${toto}</h1>
    <p>The event is #<code>${ event.id }</code></p>
  </body>
</html>
```


## `FW.setFrom(reg)` and `FW.setTo(reg)`

Sets the `from` fwot and `to` fwot for the current message. the passed parameter has to be the registration of a known FWOT

```javascript
FW.setFrom('LFBO');
FW.setTo('EDHI');
```

## `FW.setTitle(title)`

You can override the default title for any event in an IFT. You can compose the title with the context of the event. Here is a sample:

```javascript
if (TRIGGER_CODE==='8100') {
  FW.setTitle('ECS REPORT<19> (UPK)')
}
```

![timeline rendering](img/setTitle.png)

Prefer it to `FW.reportInfo()` function for simple text information.

## `FW.setSeverity(level)`
You can override the default severity for any event in an IFT. Severity must be one of `WARNING, FAULT, ERROR, INFO`.

Here is a sample:

```javascript
if (TRIGGER_CODE==='4900') {
  FW.setSeverity('WARNING')
}
```

## `FW.setComputedDate(date|moment|ISO8601)`
sets the computed date. If some samples were previously created in the event, their date remains unchanged unless they have `timelabel=AT_EVENT`

```javascript
FW.setComputedDate(moment.utc(X, 'DDMMYYHHmmss'));
```


## `FW.setStyle(name, value)`
You can dynamically set the style of your event in the timeline view by setting some styling properties with this function. any name/value pair is accepted. 2 names are predefined for the display in the timeline:

* color: sets the color of the event in the timeline color is in the CSS3 format (http://www.w3schools.com/cssref/css_colors_legal.asp)
* pos: `'left'` or `'right'`. The event is on the left or the right side of the vertical time bar. Note that the position is set on the left on small devices (smartphones)

```javascript
FW.setStyle('color', 'yellow');
FW.setStyle('pos', 'left');
```

![timeline rendering](img/setStyle.png)
> those styles can be set manually for the comments thru the timeline


## `FW.getMessage()`
returns the current message. (EventV3IO)
## `FW.getSamples()`
returns the samples of the message. Instead of being separate variables, it is an array of SampleV3IO
## `FW.report(txt, severity, timestamp, flightPhase, faultCode)`
creates a new event with the passed severity. The timestamp is either a date, a moment object or a ISO8601 string. Flighphase is a string in constants.FlightStatus
the timestamp and flightphase are optional. If not provided, the message values are used (computedDate, severity)
## `FW.reportInfo(title, timestamp, flightPhase)`
shorthand for `FW.report(txt, 'INFO', timestamp, flightPhase)`
## `FW.reportWarn(txt, timestamp, flightPhase)`
shorthand for `FW.report(txt, 'WARNING', timestamp, flightPhase)`
## `FW.reportFault(txt, timestamp, flightPhase)`
shorthand for `FW.report(txt, 'FAULT', timestamp, flightPhase)`
## `FW.reportError(txt, timestamp, flightPhase)`
shorthand for `FW.report(txt, 'ERROR', timestamp, flightPhase)`
## `FW.log(txt)`
useful for testing. The logs are displayed in the administrator's logs
## `FW.updateProperties(props)`
Updates a field of the current event (associated with the event) a property is a property of an eventV3IO. Property is javascript object like (`{title:'new title'}`)
## `FW.setFlightStatus(flightStatus)`
shorthand for `FW.updateProperties({status:flightStatus})`
## `FW.setLoc(locOrReg, d)`
## `FW.setComputedDate(date, setBeforeTransmissionDate)`
## `FW.setTitle(title)`
shorthand for `FW.updateProperties({title:title})`
## `FW.setSeverity(severity)`
shorthand for `FW.updateProperties({severity:severity})`
## `FW.set(param, value, timestamp)`
creates a sample for the passed parameter. if timestamp is not passed, then the event timestamp is used

The timestamp has to be in the format: "yyyy-MM-dd'T'HH:mm:ss"

With moment, you can format it like this: `moment(timestamp+1000).utc().format("YYYY-MM-DDTHH:mm:ss")`


## `FW.updateSomeFwotProperty(reg, name, value)`
updates a property for a FWOT (reg)
## `FW.updateFwotProperty(name, value)`
updates a property for the current FWOT (FWOT of the event)
## `FW.removeFwotProperty(name)`
removes a property for the current FWOT (FWOT of the event)
## `FW.removeSomeFwotProperty(reg, name)`
removes a property for a FWOT (reg)

## `FW.postMessage(inputMessage)`
posts a message. Message is in the format inputMessageV3IO **deprecated. Use FW.postEvent(EventV3IO)**

## `FW.postEvent(eventV3IO)`
posts an event. There are help functions to make it easier.

The eventV3IO complies to the definition found [here](https://github.com/flightwatching/wilco-api/blob/master/java/com/fw/wilco/api/EventV3IO.java)

When creating a date, we first check if the string matches known [ISO 8601](https://en.wikipedia.org/wiki/ISO_8601) formats, we then check if the string matches the [RFC 2822](https://tools.ietf.org/html/rfc2822#section-3.3) Date time format before dropping to the fall back of new Date(string) if a known format is not found.


Example:

```javascript
let event = new EventV3IO("MY_FWOT");
event.addSample("extTemperature", 18) // would crate a parameter if not exists and a sample
event.addSample("extHumidity", 40) // would crate a parameter if not exists and a sample
event.title="Sensor temp="+18;
event.layoutId=4917788; // the ID of the layout associated with the message (if you need it)
event.date = '1973-03-03'; // would set the computedDate
//event.visible=false;
var hour=moment().hours(); // you can use the moment library and the _ library
if (hour>=8&&hour<=19) {
	event.status="flying";
} else {
	event.status="gatein";
}
FW.postEvent(event)
```



## `FW.uplink(layoutId, delayInSec)`
uplinks the message in `delayInSec` seconds
## `FW.tag(tag)`
tags the event with tag; If called several times, tag remains unique (no effect)
## `FW.untag(tag)`
removes a tag from the event
## `FW.reportFaultCode(code, timestamp, flightPhase, sev, description)`
Creates a fault message (fault code)


## `FW.querySamples`
You can now query WILCO for samples. It allows to make real analytics on message reception.
a new function. Each sample is a [SampleV3IO](https://github.com/flightwatching/wilco-api/blob/master/java/com/fw/wilco/api/SampleV3IO.java)

```
FW.querySamples(
  regs,           // an array of fwot registrations. if null, this field is replaced by the event's fwot.
  names,          // the name of a parameter or an array of parameter names
  from,           // A date, a moment or a string that specifies the begin time window of the request. can be null
  to,             // A date, a moment or a string that specifies the end time window of the request. if null or not passed, the date of the event is considered
  withInvalid,    // a boolean to mention if WILCO has to return the invalid data. null or no parameter means that it should not
  chronological,  // the ordering of the returned array
  page,           // the paging. 1 by default
  count           // the max count of samples to be returned
)
```

This function is powerful being used asynced

```
const s = await FW.querySamples("ABS", 'A', '2017-05-15T00:00:00');
//returns all the samples of parameter A of fwot ABS since 2017-05-15T00:00:00, up to the event's date
const values = s.map(s=>s.value);
//values is an array of all the values.
```
