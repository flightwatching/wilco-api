#Gear lever and data

One kind of report sent by the plane and received by wilco is a gear lever status report.  This report tells wilco whether the landing gear are down or up, and what the position of the lever is, and represents it like this:
![img alt](https://github.com/flightwatching/wilco-api/blob/master/docs/UsersManual/img/Gearlever_01.PNG)

There are several key elements to the reading of this diagram:
* First of all, the three panels at the top.  
 - The grayed out letters spelling "UNLK," or unlock, signify that the landing gear is currently locked in place.  This does not mean it cannot be rectracted, simply that it will not move on its own.  When the letters light up, it means that the landing gear is no longer locked in place; in other words, it is in a transition phase from up to down, or vice versa
 - The green arrows facing down signify that the landing gear is currently down.  
In other words, right now, the landing gear is down and locked in place.
* Next is the lever, represented by the two green cylinders.  There are three positions available to this lever: Up, Down, and Neutral (some only have up and down).  Up means the landing gear has been fully retracted; Down means the landing gear has been fully deployed; and Neutral means retracted, but a step down from up, ready to be redeployed.  
* After that are the two Data points on the right: GSEL_ND stands for Ground Support Equipment List_NotDown.  In other words, if it is marked as "1," or true, the landing gear is not down.  Similarly, GSEL_NU stands for Ground Support Equipment List_NotUp, meaning the landing gear is not up.  Therefore, when in the neutral position, the landing gear is neither up nor down, so both GSEL_ND and GSEL_NU will be marked as 1, and the lever position will be in the middle.

#Data input

Now onto something a little more complicated: how wilco receives data, and what it does to process it.  Originally, when data, like the GSEL_ND for example, is collected by the plane, before it is sent down to the ground, it is in a binary format.  Each true/false data point is recorded as one number in a 32 bit binary chain (each number in the chain is a bit, labeled in this program from bit1 to bit32, with bit1 on the far right), so that a 1 in the fifteenth bit (for example) will always mean that the same condition is being marked "true."  In this way, by looking at the proper bit in the chain, we can decipher all of the information in the report.  

However, the information is not sent down in its binary format; it is compressed, to take up less space and fewer characters, making it easier to send, into a hexadecimal, or base 16 format (0-9 and A-F; F is 15, 10 is 16, 11 is 17, 1F is 31, etc.)












