#General workflow
The first thing you see when you open wilco is a page that looks like this:

![img alt](https://github.com/flightwatching/wilco-api/blob/master/docs/UsersManual/img/login.PNG)

If you have an account, enter your email and password in the appropriate fields.  If you do not have an account, click the register button, fill out the required fields, and click register.  A confirmation email will be sent to the email adress you provided.  Open it, click the specified link, and wait for a FlightWatching admin to confirm your request; you can send one an email at the specified adress.  

Once you have logged in, the default homescreen is this: 

![img alt](https://github.com/flightwatching/wilco-api/blob/master/docs/UsersManual/img/Homepage.PNG)

This can be customized, to any of the following pages:

* Eng (or FSE), for looking at the current activities and state of the fleet (which planes are in flight, which are on the ground, which have alerts, etc.):

![img alt](https://github.com/flightwatching/wilco-api/blob/master/docs/UsersManual/img/ENG.PNG)

Planes with alerts appear at the top of the page; planes in flight have a blue "flying" indicator next to the destination and departure points (the airport abbreviations).  If it is not in flight, the plane's current stage of preparation will appear in grey instead (gatein, pwrup, engon, etc).  The plane's most recent alert is indicated in the top right, along with how long ago it occurred; when a new message or report comes up for a plane, a blinking green dot lights up in the top right corner of the box.  

* MCC, or Maintenance control center, to see all of the planes in a more zoomed-out view, with all the alerts and flights:

![img alt](https://github.com/flightwatching/wilco-api/blob/master/docs/UsersManual/img/MCC.PNG)

Alerts appear as Yellow text with a number next to them signifying the number of times the alert has appeared in the past two days.  The plane's most recent or current flight is displayed in the bottom left of the panel as the airport abbreviations for the departure and destination points.  

* Ops, a map of the earth with all of the planes displayed on it; pan by clicking and dragging, zoom by scrolling (very detailed, down to the adresses):

![img alt](https://github.com/flightwatching/wilco-api/blob/master/docs/UsersManual/img/ops.PNG)

#Timelines
Timelines describe the activities of each plane over time, and allow you to access the reports with data from each plane.  Simply click on the plane whose timeline you wish to see to bring it up.

##Using Timelines
When you open a plane's timeline, you will see something like this:
![img alt](https://github.com/flightwatching/wilco-api/blob/master/docs/UsersManual/img/Timeline_01.PNG)
A few notes to assist interpretation:
* The yellow horizontal dotted line running through the timeline represents the present time; everything below it is the past, and above it is the future.  
* Areas marked in blue on the timeline represent time the plane is in flight; the other areas are when the plane is on the ground.  
* The messages marked in green are reports that have been decoded by the tool, and clicking them will take you to a dashboard with all the detailed information concerning the plane.
* Messages marked in orange, like the one below, are alerts, and clicking on them will bring up the particulars of the alert, or a dashboard with the alert made visible.
* Messages marked in grey are either simple messages with no dashboards, such as "Take Off," or messages that have not yet been decoded and assigned dashboards.  
![img alt](https://github.com/flightwatching/wilco-api/blob/master/docs/UsersManual/img/Timeline_02.PNG)

**How to navigate the timeline:**

The search bar in the top left can be used to identify particular reports; for example, typing in "doors" will remove all reports without the word "doors" in their title.  
Next to the search bar are several words, highlighted in blue: 
![img alt](https://github.com/flightwatching/wilco-api/blob/master/docs/UsersManual/img/takeoff_01.PNG)

Clicking on any one of them will remove all results that fit the category in blue from the timeline.  In other words, it filters out unwanted results.  Clicking "acms" will remove all the reports, for example.  Similarly, clicking on "Alerts only" will remove all messages except alerts, or messages in orange.  
Clicking a point along the vertical timeline will open a box that will allow you to make a comment at that time.  
Also, scrolling down will automatically load past information.  

#Dashboards
At the timeline, you can click on a report (acms) to make a dashboard come up.  The dashboard contains a multitude of information about the plane's systems.  

There are different kinds of reports that will bring up different kinds of dashboards; for example, a Take Off Report will look like this:
![img alt](https://github.com/flightwatching/wilco-api/blob/master/docs/UsersManual/img/takeoff_01.PNG)

While an ECS report will look like this:
![img alt](https://github.com/flightwatching/wilco-api/blob/master/docs/UsersManual/img/ECS_01.PNG)

If you look at the second one, you'll notice a timeline across the top of the picture that does not appear in the take off report.  This timeline signifies that there are several samples of data coming in for this one ECS report, and its absence in the first report means that that one was a snapshot, one instant in time.  You can navigate this timeline by clicking points along it or using the arrow keys, or simply clicking "play." 

![img alt](https://github.com/flightwatching/wilco-api/blob/master/docs/UsersManual/img/DVD.PNG)

In both of them there is a bar across the top with a forward skip, a backwards skip, and a play button, as on a DVD player; the backwards skip arrow will take you to the previous report of the same type (on a take off report it will take you to the previous take off, on an ECS report the the previous ECS, etc.), and the forward skip to the next one of the same type.  The "play" arrow will put the system into live; as soon as another report of the same type is received, it will automatically refresh to that page.  In other words, having the "play" arrow activated will keep the screen always on the most up to date report of the same type available.  

Next to those buttons is the name of the plane; clicking on that name will bring you back to the timeline, at the time the dashboard you came from was reported.  

There are several kinds of data points, represented by colors:
* Lime green points are normal data points collected; nothing unusual about them.
* Orange data points represent an alert; something is unusual or wrong with the data point, or it is outside acceptable parameters.
* Cyan points in general are points that were manually entered (by the crew for example), as opposed to automatically computed by avionic equipment.  

An example of the colors in use:
![img alt](https://github.com/flightwatching/wilco-api/blob/master/docs/UsersManual/img/ECS_02.PNG)

Clicking on any of the various gauges, labels, or dials will bring up a chart with the history of that data point; other inputs can be plotted against each other in the chart by clicking off of the chart back onto the main page, then clicking on the second data point.  A curve can be removed by clicking on the name of the unwanted curve in the bottom left of the chart window (below the actual chart).  

There are two types of trend charts: there is the default chart, which is simpler, and more automatic, and then there user customizable chart, which is more dynamic and is customizable, allowing the user to define which curves he or she wishes to include, as well as letting the user display several plots near each other; because of this, it is useful for plotting a data point in a general context.  

For example, this is the default curve for two parameters, FCV_1 and FCV_2:
![img alt](https://github.com/flightwatching/wilco-api/blob/master/docs/UsersManual/img/DefaultTrend.PNG)

And this is a user customizable chart containing the data point (Transfered Pressure) as well as some context (Engine Number 1):
![img alt](https://github.com/flightwatching/wilco-api/blob/master/docs/UsersManual/img/NewTrend.PNG)

* In either chart, you can click and drag horizontally or vertically across an area to zoom the chart in, and double click to zoom out.  
* This action can be repeated multiple times for greater zoom.  
* Clicking on a data point brings you to the dashboard at the time the data point was taken, allowing you to see all the information available at that time.
* Shift click-and-drag will move you along the chart.  

To note: 1 and 2 when referring to avionic parameters refer to the side of the plane from which the data was taken.  Looking at the plane from above with the nose facing forward, side 1 is always the left side of the plane, and side 2 the right side; so FCV_1 and FCV_2 are the left and right side Flow Control Valves, respectively.  

##Alert Workflow

The recommended alert mechanism is as follows:
When an alert comes up, a message is automatically sent to you notifying you of the problem.  At the same time, the plane for which the alert appeared is moved up to the alert section of the fleet, as in the example at the top of the page, and an orange alert report appears on the timeline.  Clicking on the report will bring up a dashboard displaying the system that sent an alert.  On this dashboard, the part that gave rise to the alert will appear marked orange, like this:

![img alt](https://github.com/flightwatching/wilco-api/blob/master/docs/UsersManual/img/Dashboard_01.PNG)

Since this particular error was in the Bleed system, clicking on the Bleed button in the top right corner will bring up a matrix for the various Bleed systems in all the planes over the past week:

![img alt](https://github.com/flightwatching/wilco-api/blob/master/docs/UsersManual/img/BleedMatrix_01.PNG)

This matrix shows the alerts received by the program for each of the bleed systems for each of the planes over the past week.  Looking at the middle row, you can see that there has been one alert on the HPV this week for the F-GSTB, which matches the alert seen on the dashboard earlier.  These matrices help you keep track of the alerts, and check for recurring alerts and problems on a plane.  You can adjust the time-frame by switching the number and the count; the default is 7 day count, or a week, but you can switch it to up to 30 days, weeks, months, or years.  





