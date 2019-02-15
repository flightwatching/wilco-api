# Release notes for version release-rooster-2

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
