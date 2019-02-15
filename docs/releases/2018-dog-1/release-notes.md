# Release notes for version release-dog-1

This release has been developped during Y2018 and released at the beginning of Y2019. It includes several backend improvements on stability and performances. A few new features have been included.

To have a complete view in the features and fixes

- Front-end: https://github.com/flightwatching/wilco-desktop-client/commits/master
- back-end: https://github.com/flightwatching/fleet-monitor/commits/master
- ifts: https://github.com/flightwatching/wilco-ift/commits/master


# User features

## Push/pull connector URL template
The given URL is now a template that means that you can dynamically compute it. Some of the webservices takes the date in parameter, for instance. You can use it. The syntax is groovy templating. you can find a cheatsheet here: [https://www.playframework.com/documentation/1.5.x/cheatsheet/templates](https://www.playframework.com/documentation/1.5.x/cheatsheet/templates)


Here is an example that computes 2 dates, `from:1 day ago`, `to:now`


![./img/pushpull-connector.png](./img/pushpull-connector.png)
```
https://my.api.provider.com?id=6191&from=${ new Date(new Date().getTime()-86400000).format('yyyy-MM-dd hh:mm:ss')}&to=${ new Date().format('yyyy-MM-dd hh:mm:ss')}
```


## On the fly samples usable in Iei formulaes
Samples that have been created within a iei param are now usable either to compute the time offset of a following iei or in other iei IFTs

![](./img/ieiparam.png)

## Helpers in uplink request layouts
Uplink feature  needs alphacall ups or ARINC addresses. This information has to be set in the parameters advanced page. If it is missing, or if the uplink request is a mix of addresses and alphacall ups, then the uplink fails.

When creating a uplink request, WILCO checks for the completion and the consistancy of the request and helps the user to validate the uplink before saving it

**MISSING IMAGE**

## Uplink options
According to the uplink template, it is now possible to pass some extra parameters thru the IFT.
The extra parameters are used in the template as per [https://www.playframework.com/documentation/1.5.x/cheatsheet/templates](https://www.playframework.com/documentation/1.5.x/cheatsheet/templates)

``` javascript
//this will uplink the uplink request 12345 in 0 seconds with an extra parameter inhibitionDayCount.
FW.uplink(12345, 0, ["inhibitionDayCount:99"]);
```

## External services notifications
The email sending has been delegated to a third party service (Mailgun). In addition of ensuring better delivery, it allows to add notifications on delivery failures (permanent and temporary failing addresses) and allows to add notifications when the email links are clicked. Today, this configuration can only be set up by FW team (request has to be sent to the support)

![](./img/mailgun.png)



## Documents

## Notify body templates

## Notify testing

## Async/Await support

## Query samples in IFT

## Math/Stats support


## Access to big data information in WILCO
