"use strict";
let apiv3 = require('./apiv3');
let q = require('q')
var jsonfile = require('jsonfile')
let argv = require('yargs')
.usage('Usage: $0 -s [string] -u [string] -p [string]')
.demand(['s','u', 'p'])
.argv;

let site = argv.s;
let login = argv.u;
let password = argv.p;
let conf = {site:argv.s, login: argv.u, password:argv.p};


apiv3.configure(conf);
apiv3.get('layouts')
.then(function(res) {
	let promises = res.layouts.map((acc,l) => {
		if (l.actype==='A380') {
			console.log('getting '+'layouts/'+l.id);
			acc.push(apiv3.get('layouts/'+l.id));
		}
		return acc;
	}, []);
	return q.all(promises);
})
.then(cnts => {
	return q.all(cnts.map(l=>{
		return jsonfile.writeFile('layouts/'+l.actype+'_'+l.name.replace(/[^a-z0-9]/gi, '_')+'.json', l, {spaces: 2}, err=>{
		  console.error(err)
		})
	}));
})
.catch(function(err) {
	console.log(err);
});
