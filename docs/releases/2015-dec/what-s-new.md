End 2015 release notes
==========

- [End 2015 release notes](#)
	- [Easier fleet  page](#)
	- [Easier fleet dashboard page](#)
	- [New dashboard designer page](#)
	- [WILCO operational reliability](#)
	- [Change font to leto-sans](#)
	- [Raw messages only visible to admins](#)
	- [Login fail behavior](#)
	- [Calendar selection in Timeline](#)
	- [reparse all limited to last 7 days from the current message](#)
	- [export CSV function from trend](#)
	- [Timeline -> add a repair in Timeline](#)
	- [libraries upgrade](#)
	- [Fault codes](#)
	- [parameters](#)
	- [IFTs](#)
	- [External layout support](#)
	- [email option](#)
	- [XLS adapters](#)

Easier fleet  page
-----
you can now create your own fleet page with filtered fwots:

1. set a property on your fwot (`actions > general admin > fwots > _your fwot_`)
![fwot property](img/fleet-page-1.png)

2. go to the fleet page with arguments

> example

> [https://site.flightwatching.com/wilco/#/fwots/aircraft?repair=ongoing](https://site.flightwatching.com/wilco/#/fwots/aircraft?repair=ongoing) where aircraft is the category of the fwot and `repair=ongoing` is the filter against props


You can also specify the count of columns you want in the page (1, 2, 3, 4, 6, 12)
> example

>[https://site.flightwatching.com/wilco/#/fwots/aircraft?cols=3&repair=ongoing](https://site.flightwatching.com/wilco/#/fwots/aircraft?cols=3&repair=ongoing)


Easier fleet dashboard page
-----
When creating a dashboard for a fleet, you don't need anymore to define the parameters you need to animate it and call `WILCO.getSamplesForFwot(FWOT.reg...)`

WILCO does it for you, it detects the parameters it needs and makes the call for you


> example

> [https://site.flightwatching.com/wilco/#/fwots/aircraft?db=22524850&cols=3&repair=ongoing](https://site.flightwatching.com/wilco/#/fwots/aircraft?db=22524850&cols=3&repair=ongoing)

You can also use a `sort` function in the rules to sort the fwots in the fleet view as described

in the following example, we sort from the greatest mean value to th e lowest.


![img/sort.png](img/sort.png)


New dashboard designer page
------
It has been redesigned with several rules for each `_anim`, and has syntax highlighting and full screen mode for editing large rules

![img/dashboard-designer.png](img/dashboard-designer.png)


WILCO operational reliability
-----
WILCO is monitored even when you are offline and you can access operational reliability on different geographic zones You can access the full stats for your private deployment. For security reason, a unique URL has to be created for you. If you want to access it, please send an e-mail to [support@flightwatching.com]()
so that we create this address.

![img/pingdom.png](img/pingdom.png)

> tip: bookmark it in WILCO

Change font to leto-sans
-----
The look and feel has been reviewed with a neat font, it enhances readibility and spacing management. The background is less dark and textured to light up dashboards

![img/look.png](img/look.png)

Raw messages only visible to admins
----
Raw messages are not for officers. It is displayed only for admins
![img/raw.png](img/raw.png)

Login fail behavior
------
In case you enter wrong crendentials, a better error message is displayed
![img/login.png](img/login.png)

Calendar selection in Timeline
------
Want to navigate in the timeline far away in the past? pick up the date in the Calendar
![img/calendar.png](img/calendar.png)


reparse all limited to last 7 days from the current message
------
Reparse all feature is limited to 7 days from the message currently viewed.
![img/reparse.png](img/reparse.png)


export CSV function from trend
-----
You can export the data to a CSV format from trend page view. You can freely use it in excel or any external tool.

> you could do it using the API for a long time. For automation, using the API is strongly recommended in that case.

![img/csv.png](img/csv.png)

Timeline -> add a repair in Timeline
-----
We have added a new status for the fwot in the timeline that fits to repairs
![img/closed.png](img/closed.png)


libraries upgrade
----
A bunch of libraries have been updated, better stability and cutting edge technology


Fault codes
-----
Fault codes are gathered and visible from the admin view (`actions > edit faultcodes`). they are paginated to speed up the navigation. You can search for faultcodes by typing the beginning of the code in the search box and WILCO makes live filtering across all the known fault codes.

![img/list-faultcodes.png](img/list-faultcodes.png)


You can attach an IFT to a fault code which will be executed as soon as WILCO receives the fault code (WARNING: may be in the PFR or after, say after the flight, it depends on the aircraft context)

![img/edit-faultcode.png](img/edit-faultcode.png)




parameters
------
The list of parameters is also redesigned. It is now paginated and each parameter is a row. The search box is usable and the filtering matches all the parameters that contains the searched text. If an IFT is attached to the parameter, it is also displayed.

![img/list-parameters.png](img/list-parameters.png)

IFTs
------
IFT listing is also paginated to handle large IFT amount

![img/list-parameters.png](img/list-ifts.png)



External layout support
-----
You can now reference websocket external sources

email option
------
Optionally, WILCO can have it's own email adress. Send an attachment to this adress, it will be injected into WILCO.

XLS adapters
-----
Excel files can specifically be decoded and injected into WILCO either manually or by sending them by e-mail. The decoding is subject to a purchase order
