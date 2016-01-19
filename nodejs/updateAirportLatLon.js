var https = require('https');
var fs= require('fs');
var argv = require('yargs')
    .usage('Usage: $0 -s [string] -u [user] -p [passwd]')
    .demand(['s','u', 'p'])
    .argv;



var site = argv.s;
var login = argv.u;
var password = argv.p;

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
	fs.readFile('airports.json', 'utf8', function (err, data) {
	  if (err) throw err;
          callback(JSON.parse(data));
	});
}

var findInFr24 = function (array, icao) {
	for(var i=0; i<array.length; i++) {
		if (array[i].icao == icao) return array[i];
	}
};

var updateWilco = function(fwot) {
	var js = JSON.stringify(fwot);
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
};

fr24Airports(function(fr24airports) {
	apiv3('/fleet/apiv3/airports', 'GET', function(json) {
		json.airports.forEach(function(a) {
			if (!a.lat || !a.lon) {
				var fr24a = findInFr24(fr24airports.rows, a.reg);
				if (fr24a) {
					console.log('updating '+a.reg);
					a.lat=fr24a.lat;
					a.lon=fr24a.lon;
					a.coolName=fr24a.name;
					//updateWilco(a);
				}
			}
		});
	});

})
