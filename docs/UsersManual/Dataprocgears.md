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

Now onto something a little more complicated: how wilco receives data, and what it does to process it.  Originally, when data, like the GSEL_ND for example, is collected by the plane, before it is sent down to the ground, it is in a binary format.  Each true/false data point is recorded as one number in a 32 bit binary chain (each number in the chain is a bit, labeled in this program from bit1 to bit32, with bit1 on the far right, although in other contexts the first bit might be labeled bit0), so that a 1 in the fifteenth bit (for example) will always mean that the same condition is being marked "true."  In this way, by looking at the proper bit in the chain, we can decipher all of the information in the report.  

##A Little "Bit" of Binary

In order to understand exactly what is happening, it is necessary to have a some knowledge of the ins and outs (or ons and offs) of binary.  Binary is a base 2 number system, which is to say that it has two numbers: 0 and 1.  So 0 is 0, 1 is 1, but 2 in decimal is 10 in binary.  3 is 11, 4 is 100, and so on and so forth.  Since binary only has two possible states for a number, we can perform certain operations on binary numbers that would be meaningless, or at least not very useful, in decimal.  One of these is the "And" function.  This function compares the states of each bit of one binary number to its corresponding bit in another number.  If the bits match, the end value will return the value of the bit in question (two 0s will return 0, two 1s will return 1).  If they do not match, the function will return 0.  In other words, it is a function to see if both number A *and* number B are 1 in that bit.  So, for example, testing 1010 And 1100 will return the number 1000, because bit1 in both of them is a 0, so the function returns 0, they don't match in bits 2 or 3, and in the end, only bit4 in each of them is equal to 1.  The And function returns a 1 for a bit only if both of the corresponding bits in the factors are also 1.  Any other combination returns a 0 for that bit.  

There are many things one can do with the And function; one of them is to test the state of each bit individually; in other words, to extract a wanted bit from the whole chain.  This is done by running an And function with a 1 in one bit and 0s in all the rest.  For example, 1001100 AND 1000 returns 1000; the program can then check whether this value equals 1000; if it does, the value of the original bit is 1, and the program can display the corresponding information accordingly.  

Inside the plane itself, from system to system, data is exchanged in binary, generally in BCD format, which looks like this:
![img alt](https://github.com/flightwatching/wilco-api/blob/master/docs/UsersManual/img/BCDformat.PNG)
From right to left: 
* The Label tells in what order information was recorded, and what kind of information it is.  
* The SDI
* 

into a hexadecimal, or base 16 format (0-9 and A-F; F is 15, 10 is 16, 11 is 17, 1F is 31, etc.).  












