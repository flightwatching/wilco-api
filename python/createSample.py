import requests
import sys


key = sys.argv[1]
id = sys.argv[2]
sUrl = 'https://revima.flightwatching.com/fleet/apiv3/events/{}?api-key={}'.format(id, key)

data= {};
data['value'] = "OFF"
data['name'] = "H_REP"
data['msgId'] = id

#   "name": "H_REP",
#   "value": "ON",
#   "paramId": 495,
#   "state": "VALID",
#   "date": "2020-02-06T03:16:00",
#   "timelabel": "AT_EVENT",
respCreate = requests.post('https://revima.flightwatching.com/fleet/apiv3/events/{}/samples?api-key={}'.format(id, key), json=data)
sample = respCreate.json();
respUpdate = requests.put('https://revima.flightwatching.com/fleet/apiv3/samples/{}?api-key={}'.format(sample['id'], key), json=sample)

print(respUpdate.json())

# oil['value'] = str(-float(oil['value']))
# print '{} /apiv3/samples/{} -> {}'.format(oil['date'], oil['id'], oil['value'])
# requests.put('https://revima-qtr.flightwatching.com/fleet/apiv3/samples/{}?api-key=local-e5ee347a4ba5'.format(id), json=oil)
