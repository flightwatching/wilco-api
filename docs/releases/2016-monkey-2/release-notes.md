# Release notes for version release-monkey-2

Dec 2016

# READ-ONLY role
A read-only role has been added. A user with this role can only read data. He cannot dismiss, commen, uplink, post messages, ...

![Read-only role](img/readonly.png)


# ACARS_RECIPIENT
Now you can put several ACARS_RECIPIENT separated by comma.
This field is used for the uplinks. WILCO will try each recipient while receiving a SVC message showing the A/C is not connected to the associated network.

The first ACARS_RECIPIENT that is picked up is the last message received address. if it is not in the list, the first element is chosen.


| AppConfig     | example     |
| :------------- | :------------- |
| ACARS_RECIPIENT       | BJSXCXA, BKKXCXA       |

# Force aircraft to ground when no message
For each FWOT, you can add a property named _forceFwotToGrdAfterSeconds_. It is a number that represents a count of seconds. If ETA is in the past, and last message received is this amount of seconds old, the aircraft is forced to the ground.

    The data is often cut before landing (due to communication issues)

    The events can also be bufferised onboard and sent on next power up

# IFTs: setVisible
You can call a `setVisible` function in your IFTs (V1.3 and V.20) It makes the event appear or not in the timeline of your FWOT. Even if the event is not visible, the samples are created and analysis are too.

`javascript
FW.setVisible(true);
FW.setVisible(false);
`
# view current connected users (admin)

you can see live connected users with the API: `https://MY_SITE.flightwatching.com/fleet/apiv3/status?pretty=true`

```json
{
  "dbWrite": "OK",
  "users": 34,
  "dbRead": "OK",
  "lastAcEventWriteDate": "Feb 1, 2017 10:30:30 AM",
  "fwots": 93,
  "connections": {
    "hodac@flightwatching.com": {
      "timestamp": "Feb 1, 2017 10:31:06 AM",
      "connected": true
    }
  },
  "version": "3.0.0"
}
```

# IFT: getBits(hex, n) from hex

Slices the `n`'th bit of the `hex` number into a 1 or 0. Bits are from 1 to 32 (like in acars standard docs)")
int getBit(String hex, int bit);
