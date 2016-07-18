# Help page for IFT V2.0 available functions

**a pre-requisite is to know javascript. The functions belongs to a FW javascript object.**

## `FW.notify(who, subject, body)`
Sends an e-mail to `who` with subject and eventually a body. A signature is always added to the message. It can be overridden with the AppConfig property `MAIL_TEMPLATE` using the groovy syntax. Few parameters are available.

	Check ${msg.sumUp} details <a href="${url}">here</a>
	<br>
	<br>
	<em>Health Monitoring by Flightwatching</em>

```javascript
FW.notify('support@flightwatching.com',
'My subject',
'Dear FW support team, ...');
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
