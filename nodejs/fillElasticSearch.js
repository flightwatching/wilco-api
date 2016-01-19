var https = require('https');
var async = require('async');

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


function insertInEs(path, sample, callback) {
	var js = JSON.stringify(sample);
	var request = https.request(
		{'hostname': 'localhost',
		 'port':9200,
	     'path':path+'/'+sample.id,
	     'method': 'PUT'
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
	request.write(js);
	request.end();
}

var page=0;

var processPage = function() {
	page++;
	apiv3('/fleet/apiv3/samples?length=3&page='+page, 'GET', function(list) {
		var sampleCalls = [];
		list.samples.forEach(function(a) {
			sampleCalls.push(function(callback) {
				apiv3('/fleet/apiv3/samples/'+a.id, 'GET', function(sample) {
					insertInEs('etd/samples', sample, callback)
				});
			});
		});
		async.series(sampleCalls, function(err, results){
			if (page<10) {
				console.log("processed page="+page);
				processPage()				
			}
		});
	});	
}

processPage();
