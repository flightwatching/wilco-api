var apiv3 = require('./apiv3');
var argv = require('yargs')
.usage('Usage: $0 -s [string] -u [string] -p [string] -q [string]')
.demand(['s','u', 'p', 'q'])
.argv;

var site = argv.s;
var login = argv.u;
var password = argv.p;
var conf = {site:argv.s, login: argv.u, password:argv.p};


apiv3.configure(conf);
apiv3.get(argv.q)
.then(function(res) {
	console.log(res);
})
.catch(function(err) {
	console.log(err);
});
