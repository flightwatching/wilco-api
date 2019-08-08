var https = require('https');
var fs= require('fs');
var argv = require('yargs')
.usage('Usage: $0 -s [string] -u [string] -p [string] -f [string]')
.demand(['s','u', 'p'])
.argv;



var site = argv.s;
var login = argv.u;
var password = argv.p;
var fwot = argv.f;

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

var setIata = function(fwot, iata) {
  //POST /apiv3/{category}s/{reg}/property?name&value
  if (!iata) {
    console.log(`${a.reg} no iata iata` );
    return;
  }
  if (fwot.properties && fwot.properties.IATA) {
    //console.log(" > iata already set" );
    return;
  }
  var request = https.request(
    {'hostname': site+'.flightwatching.com',
    'path':'/fleet/apiv3/airports/'+fwot.reg+'/property?name=IATA&value='+iata,
    'auth': login+":"+password,
    'method': 'POST',
    headers: {
      'Content-Type': 'application/json',
      'Content-Length': 0
    }
  },
  function(res) {
    //console.log(res);
  });
  request.write('');
  request.end();
};

var icaoMatch = /^\w{4}$/;

var update = function(a, airports) {
  //console.log("processing "+a.reg);
  if (!icaoMatch.test(a.reg)) {
    console.log(`${a.reg} not 4 digits`);
    return;
  }
  var fr24a = findInFr24(airports.rows, a.reg);
  if (fr24a) {
    setIata(a, fr24a.iata);
    if (!a.lat || !a.lon) {
      console.log(`${a.reg} updating to ${fr24a.lat}, ${fr24a.lon}`);
      a.lat=fr24a.lat;
      a.lon=fr24a.lon;
      a.coolName=fr24a.name;
      updateWilco(a);
      //console.log('done');
    } else {
      //console.log(" > lat/lon already set to "+[a.lat, a.lon]);
    }
  } else {
    console.log(`${a.reg}  not found`);
  }
};

fr24Airports(function(fr24) {
  if (fwot) {
    apiv3('/fleet/apiv3/airports/'+fwot, 'GET', function(json) {
      update(json, fr24);
    });
  } else {
    apiv3('/fleet/apiv3/airports', 'GET', function(json) {
      json.fwots.forEach(function(a) {
        update(a, fr24);
      });
    });
  }

});
