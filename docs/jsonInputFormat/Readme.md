# Purpose

The wilco server embeds a generic ACARS parser, but you may want to use other data. We can help you writing custom parsers and adding it as plugins to the wilco server, or you may want to use the standardized input format. It is a JSON format. Sending it to the wilco API (method `POST:insertMessage` as the body)

This document describes the format for this kind of use

# Description
## JAVA object
We provide the [JAVA object](https://github.com/flightwatching/wilco-api/blob/master/java/com/fw/wilco/api/InputMessageV3IO.java) if you develop with JAVA and use the GSON object to string parsers (`new GSon().toJSON(MyInstance)`).

Check [this simple program](https://github.com/flightwatching/wilco-api/blob/master/java/com/fw/wilco/api/examples/InsertMessage.java) for an example

## straight JSON

First an example to figure out what it is
```json
{
  "reg": "R91",
  "computedDate": "2014-06-30T07:57:49",
  "status": "PWRUP",
  "sumup": "R91-status",
  "severity": "INFO",
  "samples": [
    {
      "name": "118-CV_loc",
      "value": "runway",
      "timestamp": "2014-06-30T07:57:49"
    }
  ],
  "provider": "tester",
  "namedLayout": "R91-status"
}
```
## Message description

**id** `Long` the ID of the message. If you want to update a message, use the field, else leave it null or missing

**reg** `String, Mandatory` is the identifier (registration) of the FWOT the message is dealing with. A message is necessarily a by fwot message. If you want to send messages for a whole fleet, you will have to push as many message as fwots


**computedDate** `String, Mandatory` the computed date of the message, in ISO8601 format, eg. 2014-05-23T13:45:2 and it is UTC time

**title** : `String` The title of the message. Short text (<40 characters)

**status** `String` the status of the fwot at the moment of the message computedDate. It is an enumerated (PWRUP, GATEOUT, ENGON, TAXIOUT, FLYING, TAXIIN, GATEIN, CLOSED). This status is important in the timeline to make the difference between flying and at airport. PWRUP, GATEOUT, ENGON, TAXIOUT consider **from** as current airport and  TAXIIN, GATEIN, CLOSED consider to as current airport

**sumup** `String` a text to describe the message (<1500 characters), displayed as the header (may be truncated on small devices)

**extUrl** `String` an external URL to the resource. In the HMI, when clicking on the resource, you will be redirected to this URL if specified

**severity** `String` the severity of the message. It is an enumerated (IGNORE, CREW, WARNING, FAULT, ERROR, INFO). Each level is associated to a color:
* IGNORE: not shown in the timeline
* CREW: white
* WARNING: amber
* FAULT: yellow
* ERROR: red
* INFO: light gray

**tags** `String array` a list of strings used for search and filter purposes

**smi** `String, Deprecated (used for ACARS)` If the message is not acars, then SMI.FW can be used

**samples** `Sample array` a list of samples. Description below

**flightId** `String` a leg identifier

**from** `String` the registration (reg field) of a fwot representing the starting point

**to** `String` the registration (reg field) of a fwot representing the starting point

**provider** `String` the application identifier that sent the data

**layoutId** `Positive integer` the id of the layout that matches the message. The layout allows you to associate some IFTs and Dashboards to all the messages with the same layout. If it is not provided, namedLayout is tried

**namedLayout** `String` the exact name of the associated layout. if it does not exist, a new layout is created. 

**visible** `boolean` if set and false, the message will not be viewable by the user. The message is only here for IFT purposes. In that case, WILCO will not ensure the persistency forever


## Sample description

**name** `String, Mandatory` the name of the parameter the sample is about. If the parameter does not exist, it is created on the fly

**value** `String or JSON, Mandatory` the value of the sample. it can be a string or a complex JSON object that will be usable in the dashboard, but not for trend purpose. If the string contains a number (can be a decimal) it will be cast as such, enabling chart trends

**timestamp**  `String` the date of the sample, in ISO8601 format, eg. 2014-05-23T13:45:2 and it is UTC time 

**timelabel** `String` a label if you want to tag the timestamp

---
#MANY EXAMPLES

```json
{ "reg": "LFBO", 
  "computedDate": "2014-07-15T08:59:46", 
  "status": "PWRUP", 
  "sumup": "aie aie aie", 
  "severity": "WARNING", 
  "tags": [], 
  "samples": [], 
  "provider": "tester", 
  "title": "RW C14 CLOSED" 
}
```