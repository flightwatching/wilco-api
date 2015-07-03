#General workflow
The first thing you see when you open wilco is a page that looks like this:

![img alt](https://github.com/flightwatching/wilco-api/blob/master/docs/UsersManual/img/Homepage.PNG)

#Timelines
Timelines describe the activities of each plane over time, and allow you to access the reports with data from each plane.  There are several methods of selecting which plane's timeline you wish to see.  

##How to access timelines

* One way is to go to ops, which will look like this: 
![img alt](https://github.com/flightwatching/wilco-api/blob/master/docs/UsersManual/img/ops.PNG)
   Click on the plane you want to monitor (represented by an airplane in the diagram), and its timeline will come up.  You can click and drag to move the view around, and scroll to zoom.

* Another way is to click on MCC, or Maintenance control center, which will look like this:
![img alt](https://github.com/flightwatching/wilco-api/blob/master/docs/UsersManual/img/MCC.PNG)
   All of the plane will be listed; click on one to bring up its timeline.  

* Alternatively, you can go to ENG:
![img alt](https://github.com/flightwatching/wilco-api/blob/master/docs/UsersManual/img/ENG.PNG)
   The planes will all be listed there, planes with alerts in a section above those without.  Once again, click on a any plane to bring up its timeline.

##Using Timelines
When you open a plane's timeline, you will see something like this:
![img alt](https://github.com/flightwatching/wilco-api/blob/master/docs/UsersManual/img/Timeline_01.PNG)
A few notes to assist interpretation:
* The yellow-green horizontal dotted line running through the timeline represents the present; everything below it is the past, and above it is the future.  
* Areas marked in blue on the timeline represent time the plane is in flight; the other areas are when the plane is at rest.  
* The messages marked in green are reports, and clicking them will take you to a dashboard with all the detailed information concerning the plane.
* Messages marked in orange, like the one below, are alerts, and clicking on them will bring up the particulars of the alert, or a dashboard with the alert made visible.
![img alt](https://github.com/flightwatching/wilco-api/blob/master/docs/UsersManual/img/Timeline_02.PNG)

**How to navigate the timeline:**

The search bar in the top left can be used to identify particular reports; for example, typing in "doors" will remove all reports without the word "doors" in their title.  
Next to the search bar are several words, highlighted in blue; clicking on any one of them will remove all results that fit the category in blue from the timeline.  In other words, it filters out unwanted results.  Clicking "acms" will remove all the reports, for example.  Similarly, clicking on "Alerts only" will remove all messages except alerts, or messages in orange.  
Clicking a point along the vertical timeline will open a box that will allow you to make a comment at that time.  

#Dashboards
At the timeline, you can click on a report (acms) to make a dashboard come up.  The dashboard contains a multitude of information about the plane's systems.  

There are different kinds of reports that will bring up different kinds of dashboards; for example, a take off report will look like this:
![img alt](https://github.com/flightwatching/wilco-api/blob/master/docs/UsersManual/img/takeoff_01.PNG)

While an ECS report will look like this:
![img alt](https://github.com/flightwatching/wilco-api/blob/master/docs/UsersManual/img/ECS_01.PNG)

If you look at the second one, you'll notice a timeline across the top of the picture that does not appear in the take off report.  This timeline signifies that there are several samples of data coming in for this one ECS report, and its absence in the first report means that that one was a snapshot, one instant in time.  You can navigate this timeline by clicking points along it or using the arrow keys, or simply clicking "play." 

There are several kinds of data points, represented by colors:
* Lime green points are normal data points collected; nothing unusual about them.
* Orange data points represent an alert; something is unusual or wrong with the data point, or it is outside acceptable parameters.
* Cyan points in general are points that were manually entered, as opposed to automatically collected.  

Clicking on any of the various gauges or labels will bring up a chart with the history of that data point; other inputs can be plotted against each other in the chart by clicking off of the chart back onto the main page, then clicking on the second data point.  A curve can be removed by clicking on the name of the unwanted curve in the bottom left of the chart window (below the actual chart).  

There are two types of trend charts: there is the default chart, which is simpler, and more automatic, and then there user customizable chart, which is more dynamic and is customizable, allowing the user to define which curves he or she wishes to include, as well as letting the user display several plots near each other; because of this, it is useful for plotting a data point in a general context.  

For example, this is the default curve for two data points:
![img alt](https://github.com/flightwatching/wilco-api/blob/master/docs/UsersManual/img/DefaultTrend.PNG)

And this is a user customizable chart containing the data point (Transfered Pressure) as well as some context (Engine Number 1):
![img alt](https://github.com/flightwatching/wilco-api/blob/master/docs/UsersManual/img/NewTrend.PNG)

* In either chart, you can click and drag across an area to zoom the chart in, and double click to zoom out.  
* This action can be repeated multiple times for greater zoom.  
* Clicking on a data point brings you to the dashboard at the time the data point was taken, allowing you to see all the information available at that time.
