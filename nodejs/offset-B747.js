const axios = require('axios');



async function doit() {
    const response = await axios.get('https://revima-ahm.flightwatching.com/fleet/apiv3/layouts/29888924?pretty&api-key=local-e5ee347a4ba5');
    delete response.data.dashboards;

    response.data.ieis.forEach(iei => {
        iei.param = iei.param.filter(p=>{return p.offset>=3;});
        iei.param.forEach(p=>p.offset-=3)
        iei.idxMin = Math.max(iei.idxMin-2, 0);
        iei.idxMax = Math.max(iei.idxMax-2, 0);
        if (iei.length===63 && iei.idxMin===0) {
            iei.code='B'
        }

    })


    console.log( JSON.stringify(response.data, null, 2));
}

(async function() {
  await doit();
})();

// fs.readFile('/Users/dao/dev/fleet-monitor/test/resources/layouts/B747-800-REP20.json', 'utf8', function (err, data) {
//     if (err) throw err;
    
//     const json = JSON.parse(data);

//     json.ieis.forEach(iei => {
//         iei.param = iei.param.filter(p=>{return p.offset>=3;});
//         iei.param.forEach(p=>p.offset-=3)
//         iei.idxMin = Math.max(iei.idxMin-2, 0);
//         iei.idxMax = Math.max(iei.idxMax-2, 0);
//     })

//     console.log(JSON.stringify(json, null, 2));

//   });
