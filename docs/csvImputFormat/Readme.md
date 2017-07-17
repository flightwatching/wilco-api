# Purpose

The CSV option enables WILCO to deal with CSV data format. WILCO need a minimal structure of the CSV file to map the data to the right FWOT and the right date

# Description
## Example
first of all, a simple example:

| LAYOUT  | FWOT   | DATE       | TIME     | PARAM_1 | PARAM_2 | ... |
|:------:|:------:|:----------:|:--------:|:-------:|:-------:|:---:|
|  REP04 | FW-DAO | 23/09/2014 | 12:54:46 | 9       | TRAT    | ... |
|  REP04 | FW-DAO | 23/09/2014 | 12:55:46 | 10      | MATT    | ... |
|  REP04 | FW-DAO | 23/09/2014 | 12:58:46 | 12      | RATUCH  | ... |
| MANUAL | FW-LUC | 23/09/2014 | 12:54:46 | 0.45    | 0.48    | ... |
| MANUAL | FW-LUC | 23/09/2014 | 13:53:46 | 0.45    | 1.25    | ... |
|        |        |            |          |         |         | ... |
| MANUAL | FW-LUC | 23/09/2014 | 14:54:46 | 0.47    | 4.456   | ... |
| MANUAL | FW-LUC | 23/09/2014 | 15:54:46 |         | 1       | ... |

This CSV table will create 3 messages:
* a REP04 message for the FWOT named FW-DAO. It will contain 6 samples with 3 different timestamps
* a MANUAL message for the FWOT named FW-LUC. It will contain 4 samples with 2 different timestamps
* a MANUAL message for the FWOT named FW-LUC. It will contain 3 samples with 2 different timestamps

## Format

> THE FIELD DELIMITER IS THE `;`

> THE RECORD DELIMITER IS THE `new line`

> THE DECIMAL DELIMITER IS THE `.`

##Date format

The date format has to be one of the following. Be careful, Excel can be very random with date exportation. In case of error, we suggest checking the data with a simple text editor

* date `yyyy/MM/dd` and time `HH:mm`
* date `yyyy/MM/dd` and time `HH:mm:ss`
* date `dd/MM/yyyy` and time `HH:mm:ss`
* date `yyyy-MM-dd` and time `HH:mm:ss.SSS`
* date `yyyy-MM-dd` and time `HH:mm:ss`

The CSV file has at least the 4 first columns in this order. It also have the header line (mandatory)

The parameters are created on the fly if they do not exist (PARAM_1, PARAM_2)

While 2 contiguus records have the same FWOT, the records will belong to the same [message](https://github.com/flightwatching/wilco-api/blob/master/java/com/fw/wilco/api/InputMessageV3IO.java). 

To force the creation of a new message
* insert an empty line between them
* Change the FWOT and/or the LAYOUT

end
