var https = require('https');

var site = process.argv[ process.argv.length-3];
var login = process.argv[ process.argv.length-2];
var password = process.argv[ process.argv.length-1];

function apiv3(path, method, callback) {
	var request = https.request(
		{'hostname': site+'.flightwatching.com',
	     'path':path,
	     'auth': login+":"+password,
	     'method': method
	    },
		function(res) {
		  var body = "";
		  res.on('data', function(d) {
		  	body+=d;
		  });
		  res.on('end', function(d) {
		    callback(JSON.parse(body));
		  });
		});
	request.end();
}



function fr24Airports(callback) {
	var request = https.request(
		{'hostname': 'www.flightradar24.com',
	     'path':'/_json/airports.php',
	     'method': 'GET'
	    },
		function(res) {
		  var body = "";
		  res.on('data', function(d) {
		  	body+=d;
		  });
		  res.on('end', function(d) {
		    console.log(body);
		    callback(JSON.parse(body));
		  });
		});
	request.end();
}

var findInFr24 = function (array, icao) {
	for(var i=0; i<array.length; i++) {
        if (array[i].icao == icao) return array[i];
    }
}

var updateWilco = function(fwot) {
	var js = JSON.stringify(fwot)
	var request = https.request(
		{'hostname': site+'.flightwatching.com',
	     'path':'/fleet/apiv3/airports/'+fwot.reg,
	     'auth': login+":"+password,
	     'method': 'PUT',
	     headers: {
          'Content-Type': 'application/json',
          'Content-Length': js.length
      	 }
	    },
		function(res) {
			//console.log(res);
		});
	request.write(js);
	request.end();
}

fr24Airports(function(fr24airports) {
	apiv3('/fleet/apiv3/airports', 'GET', function(json) {
		json.airports.forEach(function(a) {
			if (!a.lat || !a.lon) {
				var fr24a = findInFr24(fr24airports.rows, a.reg)
				if (fr24a) {
					console.log('updating '+a.reg)
					a.lat=fr24a.lat;
					a.lon=fr24a.lon;
					a.coolName=fr24a.name;
					updateWilco(a);
				}
			}
		})
});

})
