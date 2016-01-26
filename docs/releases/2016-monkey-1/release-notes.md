# Release notes for version release-monkey-1


## add a FW.setTitle IFT function (V2.0 language)

You can override the default title for any event in an IFT. You can compose the title with the context of the event. Here is a sample:

```javascript
if (TRIGGER_CODE==='8100') {
  FW.setTitle('ECS REPORT<19> (UPK)')
}
```

![timeline rendering](img/setTitle.png)

Prefer it to `FW.reportInfo()` function for simple text information.

## add a FW.setSeverity IFT function (V2.0 language)


You can override the default severity for any event in an IFT. Severity must be one of `WARNING, FAULT, ERROR, INFO`.

Here is a sample:

```javascript
if (TRIGGER_CODE==='4900') {
  FW.setSeverity('WARNING')
}
```

----

# NOT DONE YET

## JAVA 8

## node 5.5

## Fix ACARS multipart messages handling

## mongoDB

## change event properties in IFTs
