#Gear lever and data

One kind of report sent by the plane and received by wilco is a gear lever status report.  This report tells wilco whether the landing gear are down or up, and what the position of the lever is, and represents it like this:
![img alt](https://github.com/flightwatching/wilco-api/blob/master/docs/UsersManual/img/Gearlever_01.PNG)

There are several key elements to the reading of this diagram:
* First of all, the three panels at the top.  
 - The grayed out letters spelling "UNLK," or unlock, signify that the landing gear is currently locked in place.  This does not mean it cannot be rectracted, simply that it will not move on its own.  When the letters light up, it means that the landing gear is no longer locked in place; in other words, it is in a transition phase from up to down, or vice versa
 - The green arrows facing down signify that the landing gear is currently down.  
In other words, right now, the landing gear is down and locked in place.
* Next is the lever, represented by the two green cylinders.  There are three positions available to this lever: Up, Down, and Neutral (some only have up and down).  Up means the landing gear has been fully retracted; Down means the landing gear has been fully deployed; and Neutral means ready to change states; it doesn't imply a physical movement of the landing gear.  
* After that are the two Data points on the right: GSEL_ND stands for Ground Support Equipment List_NotDown.  In other words, if it is marked as "1," or true, the landing gear is not down.  Similarly, GSEL_NU stands for Ground Support Equipment List_NotUp, meaning the landing gear is not up.  Therefore, when in the neutral position, the landing gear is neither up nor down, so both GSEL_ND and GSEL_NU will be marked as 1, and the lever position will be in the middle.

Next, we will look at an error: 

![img alt](https://github.com/flightwatching/wilco-api/blob/master/docs/UsersManual/img/Gearlever_02.PNG)

In the image above, something is clearly wrong.  Although the lever is marked as being in the neutral position, and GSEL_ND and GSEL_NU are both 1, the green arrows are lit up; in other words, the landing gear is down, but the lever is in the neutral position.  This alone doesn't seem all that terrible; but, when keeping in mind the purpose and usage of the neutral position, an error becomes apparent.  When the plane takes off, the pilot puts the gear from Down to Up.  When it reaches a certain altitude, at cruise, the pilot will move the lever down to Neutral.  As the pilot makes his final approach, right before landing, he or she will move the lever from Neutral to Down, lowering the landing gear.  Never does the pilot move the lever from Down to Neutral, which is what this reading is suggesting.  There are several possible explanations for this error; it could be a reading error, a transmission error (a boolean in the wrong state), or even a pilot error.  The first place to look to identify the definitive cause of the error is the timeline.  Clicking on "Alerts Only" at the top of the page will let you see all the alerts for the plane, and in this case, it looks like this: 

![img alt](https://github.com/flightwatching/wilco-api/blob/master/docs/UsersManual/img/Error_04.PNG)

There are even more below; so clearly this is a recurring error, that has happened many times.  That rules out pilot error; one pilot might make that error once, but various pilots, over the course of that many days?  The odds are rather slim.  This discovery also pretty much rules out transmission errors; due to the parity bit, explained later, for an error in a boolean to go unnoticed, there would have to be two errors, to keep the parity odd; once again, this might happen once or even twice, but not that many times, and not that often.  That leaves us with a reading error; to verify our theory, once again we can refer to the timeline, this time with all messages.  

![img alt](https://github.com/flightwatching/wilco-api/blob/master/docs/UsersManual/img/Error_02.PNG)
![img alt](https://github.com/flightwatching/wilco-api/blob/master/docs/UsersManual/img/Error_01.PNG)
![img alt](https://github.com/flightwatching/wilco-api/blob/master/docs/UsersManual/img/Error_05.PNG)

All of the other errors look similar to these three; either the blip happens right before takeoff, right before landing, or right after landing.  Once again, there is no reason for the pilot to put the landing gear in Neutral; the error is probably in reading or in the filing of the report.  

To see the full scope of the error, for how long it has been occurring, and how often, in this case you can click on LDG in the top right corner.  This will bring up the matrix with all the landing gear errors for all the planes, and you can change the timeframe to find the first error.  

![img alt](https://github.com/flightwatching/wilco-api/blob/master/docs/UsersManual/img/Matrix_01.PNG) 
![img alt](https://github.com/flightwatching/wilco-api/blob/master/docs/UsersManual/img/Date_01.PNG)
![img alt](https://github.com/flightwatching/wilco-api/blob/master/docs/UsersManual/img/Matrix_02.PNG) 
![img alt](https://github.com/flightwatching/wilco-api/blob/master/docs/UsersManual/img/Gearerrorplot.PNG)
![img alt](https://github.com/flightwatching/wilco-api/blob/master/docs/UsersManual/img/Gearerrorplot_02.PNG)

From there, clicking on any point will bring you to the dashboard for that point, and from the dashboard, clicking the name of the plane at the top of the page will bring you back to the timeline, at the time of the dashboard.  

![img alt](https://github.com/flightwatching/wilco-api/blob/master/docs/UsersManual/img/Gearlever_03.PNG)

#Data input

Now onto something a little more complicated: how wilco receives data, and what it does to process it.  Originally, when data, like the GSEL_ND for example, is collected by the plane, before it is sent down to the ground, it is in a binary format.  Each true/false data point is recorded as one number in a 32 bit binary chain (each number in the chain is a bit, labeled in this program from bit1 to bit32, with bit1 on the far right, although in other contexts the first bit might be labeled bit0), so that a 1 in the fifteenth bit (for example) will always mean that the same condition is being marked "true."  In this way, by looking at the proper bits in the chain, we can decipher all of the information in the report.  

##A Little "Bit" of Binary

In order to understand exactly what is happening, it is necessary to have a some knowledge of the ins and outs (or ons and offs) of binary.  Binary is a base 2 number system, which is to say that it has two numbers: 0 and 1.  So 0 is 0, 1 is 1, but 2 in decimal is 10 in binary.  3 is 11, 4 is 100, and so on and so forth.  Since binary only has two possible states for a number, we can perform certain operations on binary numbers that would be meaningless, or at least not very useful, in decimal.  One of these is the "AND" function.  This function compares the states of each bit of one binary number to its corresponding bit in another number.  If the bits match, the end value will return the value of the bit in question (two 0s will return 0, two 1s will return 1).  If they do not match, the function will return 0.  In other words, it is a function to see if both number A *and* number B are 1 in that bit.  So, for example, testing 1010 AND 1100 will return the number 1000, because bit1 in both of them is a 0, so the function returns 0, they don't match in bits 2 or 3, and in the end, only bit4 in each of them is equal to 1.  The AND function returns a 1 for a bit only if both of the corresponding bits in the factors are also 1.  Any other combination returns a 0 for that bit.  

There are many things one can do with the AND function; one of them is to test the state of each bit individually; in other words, to extract a wanted bit from the whole chain.  This is done by running an AND function with a 1 in one bit and 0s in all the rest.  For example, 1001100 AND 1000 returns 1000; the program can then check whether this value equals 1000; if it does, the value of the original bit is 1, and the program can display the corresponding information accordingly.  

Inside the plane itself, from system to system, data is exchanged in binary, in this general format (may vary a little):
![img alt](https://github.com/flightwatching/wilco-api/blob/master/docs/UsersManual/img/BCDformat.PNG)

From right to left: 
* The Label tells in what order information was recorded, and what kind of information it is.  
* The SDI, simply put, tells the program which side of the plane the information is coming from.  It is not always used.
* The useful part of the report is bits 11-29, which contains either Discretes (individual bits each with a meaning), Data (strings of bits that together define one avionic parameter), or some hybrid of both, as in the example.  
* Note that bits in the Data section are ordered from left to right as "Most Significant Bits" (MSB) to "Least Significant Bits" (LSB), as shown.  
* The SSM, or Sign/Status Matrix, tells the program whether the system is malfunctioning, or if there are errors in data collection.  
* The Parity bit is used to make the sum of all the bits odd.  By doing this, if there was an error in the transmission of one of the bits, the total with the parity bit will be even, the program can detect the error, and ask for the data to be retransmitted.  

However, the aircraft does not send us (the program) data in binary; it converts it into a hexadecimal, or base 16 format (0-9 and A-F; F is 15, 10 is 16, 11 is 17, 1F is 31, etc.).  However, the process for determining the identity of each of the bits in binary remains the same; we still do an AND function with a 1 in the appropriate bit and 0s in the rest, only instead of doing it in binary, we do it in hexadecimal.  So, for example, if we wanted to test the identity of the fourth bit in binary, we'd use 1000; but since we're not in binary, we would use the hexa equivalent, 8.  So our function would look like ________ AND 8.  We would then check the return for 0 or 1, and use the data accordingly.   






