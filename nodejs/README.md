updateAirportLatLon.js
====

installation:

```bash
npm install async
npm install https
npm install yargs
```
You can also get the data from https://api.flightradar24.com/common/v1/airport.json?code=HDAM

used to update the lat, lon and name of the airports according to FR24 database (persisted in airports.json)

`node  updateAirportLatLon.js -s <site>  -u <user> -p <thepassword>`

`node  updateAirportLatLon.js -s demo  -u hodac@flightwatching.com -p tucroispasquejevaisteledonner`
