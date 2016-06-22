var https = require('https');
//var request = require('request');
var Q = require('q');
var restify = require('restify');
var moment = require('moment');
var request = require('request');

var client;
var connectionConf;


exports.configure = function(conf) {
	connectionConf = conf;
	client = restify.createJsonClient({
		url: 'https://'+conf.site+'.flightwatching.com'
	});
	client.basicAuth(conf.login,conf.password);
};

exports.get = function(path) {
	var def = Q.defer();
	client.get('/fleet/apiv3/'+path, function(err, req, res, obj) {
		if (err) {
			def.reject(err);
		} else {
			def.resolve(obj);
		}
	});
	return def.promise;
};

exports.getSamples = function(fwot, oldestDate, format) {
	var url = 'fwots/'+fwot+'/samples?count=1500&nested=true&pretty=true';
	if (format) {
		url+="&format="+format;
	}
	if (oldestDate) {
		url+="&to="+moment.utc(oldestDate).add(1,'second').format();
	}
	console.log(url);
	return this.getApiV3(url);
};


exports.getCsv = function(fwot, oldestDate) {
	var def = Q.defer();
	var url = 'https://'+connectionConf.site+'.flightwatching.com/fleet/apiv3/fwots/'+fwot+'/samples?count=2000&format=csv';
	if (oldestDate) {
		url+="&to="+moment.utc(oldestDate).add(1,'second').format();
	}
	console.log(url);
	var auth = new Buffer(connectionConf.login + ':' + connectionConf.password).toString('base64');
	var req = {
		url: url,
		method: 'GET',
		headers: {
			Authorization: 'Basic ' + auth,
			'Content-Type': 'application/json'
		}
	};
	request(req,function (error, response, body) {
		if (error) {
			def.reject(error);
		} else {
			def.resolve(body);
		}
	});
	return def.promise;
};
