With WILCO, you can draw your dashboard in any tool like Adobe Illustrator and get it animated using dashboard IFTs

With IFTs you can access all the elements of the dashboard, but the standard way of working is to animate the symbols of your dashboard.
IFT is a javascript piece of code 

#Variables accessible in the dashboard

WILCO provides context related variables to the designer so that the dashboard is animated according to it.

##Dashboards for an event
Here is the exhaustive list of the variables you can access in yout IFT
###EVT
`EVT` is an object that represents the [EventV3IO API object](/java/com/fw/wilco/api/EventV3IO.java).


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