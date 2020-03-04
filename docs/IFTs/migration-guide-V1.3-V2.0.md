# Migration guide from IFT V1.3 to IFT V2.0

> As mentionned in the latest releases, IFT 1.3 are now deprecated. We want to move them to IFT V2.0.
This guide helps you to spot the syntax changes and apply them when migrating

Basically, the language is the same (Javascript). A major change is linked to the business objects that are passed to the IFT.

# The Fwot access

# The event access

# The samples access

# Accessing the history of a sample

`IFT V1.3` was providing a method directly available from the sample itself:

`MY_SAMPLE.history(count, onlyValid, thisMessageOnly)`

- `count` is an integer to threshold the number of samples returned
- `onlyValid` is a boolean that filters out the invalid or dashed samples
- `thisMessageOnly` is a boolean that filters for the samples belonging to the same message as the current one.

`IFT V2.0` provides a more powerfull method to access the samples  see https://github.com/flightwatching/wilco-api/blob/master/docs/IFTs/ift-V2.0.md#fwquerysamples

Here are some examples on how to transform:

|         |      Migration      |
| ------------- |:-------------|
| IFT V1.3  | `var samples = MY_SAMPLE.history(100, true, false);`  |
|           | we get the last 100 samples only with the valids      |
| IFT V2.0  | `await FW.querySamples(null, "MY_SAMPLE", null, null, false, false, 1, 100);`      |
|           | we get the 100 last samples of "MY_SAMPLE", without the invalids, and in the non-chronological order (as in IFT V1.3) |


|         |      Migration      |
| ------------- |:-------------|
| IFT V1.3  | `MY_SAMPLE.history(100, true, true);`  |
|           | we get the last 100 samples only with the valids      |
| IFT V2.0  | ```let samples = await FW.querySamples(null, "MY_SAMPLE", null, null, false, false, 1, 100).filter(s=>s.msgId==FW.getEvent().id);```      |
|           | we get the 100 last samples of "MY_SAMPLE", without the invalids, and in the non-chronological order (as in IFT V1.3). then, we filter for the current message |

Complex example
```javascript
  //checks if a bit has changed
  const msgId = FW.getEvent().id;
  var changedBits = [];
  const samples = (await FW.querySamples(null, "MAINTWD1", null, null, false, false, 1, 100)).filter(s=>s.msgId==msgId);
//  samples.map(s=>FW.log(`${msgId}vs${s.msgId} - ${s.value} -> ${s.date}`));
  
  const newest = samples[0];
  const oldest = samples[samples.length-1];
  
  for (var b=1; b<33 && oldest.value!=newest.value; b++) {
    var b1=Math.max(FW.getBit(newest.value, b), 0);
    var b2=Math.max(FW.getBit(oldest.value, b), 0);
    if ((b1!=b2) && (b2 == 0)) {
      FW.log(b+" "+b1+"!="+b2)
      changedBits.push(b);
    }
  }

```


# Notifying by email


