'use strict'
const fs= require('fs');

const argv = require('yargs')
.usage('Usage: $0 -q [string] ')
.demand(['q'])
.argv;

async function readJsonFromEs() {
  let promise = await new Promise((resolve, reject) => {
    fs.readFile(argv.q, 'utf8', (err, data)=>{
      resolve(JSON.parse(data));
    });
  })
  .catch(err => {throw err});
  return promise;
}

(async () => {
    try {
      const res = await readJsonFromEs();

      //finds the temperature list
      const temps = {};
      res.aggregations.fwot.buckets
      .map(b=>b.temperature.buckets)
      .map(a=>{a.map(t=>{temps[''+t.key]={}})});


      //we fill by A/C
//      console.log(
      res.aggregations.fwot.buckets
      .map(b=>{
        //in b there is the key and the temperature buckets
        const fwot = b.key;
        b.temperature.buckets.map(t=>{
          temps[''+t.key][fwot]=t.QTY.value;
        })
      });
    //);

      console.log(Object.keys(temps)
        .map(t=>{
          return Object.keys(temps[t]).reduce((line,fwot)=>{
            //return `${fwot.t},${fwot},${}`
            return line+`,${temps[t][fwot]}`;
          }, `${t}`);
        }).reduce((table, line)=>{
          return table+'\n'+line;
        },Object.keys(temps[Object.keys(temps)[0]]).reduce((line,fwot)=>{return line+','+fwot}, 'TEMP'))
      );


    } catch (e) {
      console.log(e)
    }
})();
