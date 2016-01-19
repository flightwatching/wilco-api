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
			console.log("tptp")
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
apiv3('fwots', 'get', function(data) {
	console.log(data);
});
