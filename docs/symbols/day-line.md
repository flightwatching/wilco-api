> console.log is a good way of debugging with a browser.
> you can open the console in [chrome](https://developer.chrome.com/devtools/docs/console)
> or in [firefox](https://developer.mozilla.org/en/docs/Tools/Web_Console)
> 
> you can log any variable in the console in the symbol functions or dashboard rules very easily 
> calling in your code console.log(myVariable)


# The day-line symbol

The day-line symbol shows an horizontal bar with integer number slots. Each slot is clickable and a user callback can be called each time a click occurs

##initialize the day-line

you will have to call `span(count, callback)`

* `count`: the max of the line. Calling `span(12)` will generate a line with | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 | 11 | 12 | boxes. Each number is clickable
* `callback`: a function that is called when a click occurs. 

In the below example, each time a box is clicked, the number is logged in the console
```javascript
span(12, function(d) {
  console.log(d);
}
```

## manually select a cell
Of course, you may want to select a cell programatically, for example at the initialization.
`select(10)` will select the cell with a 10 in it.
