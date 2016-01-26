# Release notes for version release-monkey-1


##add a FW.setTitle IFT function

You can override the default title for any event in an IFT. You can compose the title with the context of the event. Here is a sample:

```javascript
if (TRIGGER_CODE==='8100') {
  FW.setTitle('ECS REPORT<19> (UPK)')
}
```

![timeline rendering](img/setTitle.png)

Prefer it to `FW.reportInfo()` function for simple text information.

----

# NOT DONE YET

## JAVA 8

## node 5.5

## Fix ACARS multipart messages handling

## mongoDB

## change event properties in IFTs
