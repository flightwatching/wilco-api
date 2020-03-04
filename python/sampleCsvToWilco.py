import sys, getopt
import io
import datetime
import requests
import json

apikey = sys.argv[1]

eventId = 9846

def pushToWilco(sample):
    sample.pop('id', None)
    sample.pop('paramId', None)
    sample.pop('minScale', None)
    sample.pop('maxScale', None)
    sample.pop('minOK', None)
    sample.pop('maxOK' , None)
    sample['msgId']=eventId
    sample.pop('timelabel', None)
    if (sample['name']=='COMMENT'):
        sample['name']='AMOS'
    if (sample['name']=='REP7_TRIG'):
        sample['name']='PREDICTION'
    if (sample['name']=='REP6_TRIG'):
        sample['name']='EMAIL'
    resp=requests.post(urlPostToWilco, headers={'api-key': apikey, 'Content-type': 'application/json'}, data=json.dumps(sample))
    print('posted to dest'+str(sample['date'])+' -> CODE#'+str(resp.status_code))


# get the samples from the source WILCO
urlGetFromWilco = 'https://ati.flightwatching.com/fleet/apiv3/fwots/F-GSTB/samples'
urlPostToWilco = 'https://insight.flightwatching.com/fleet/apiv3/events/'+str(eventId)+'/samples'
params={
    'from':'2014-11-19T09:33:35',
    'to':'2017-11-15T23:33:35',
    'format':'json',
    'name': ['VBF_1','VBF_2','COMMENT', 'REP7_TRIG', 'REP6_TRIG']
}
resp = requests.get(urlGetFromWilco, headers={'api-key': apikey}, params=params)

for s in resp.json()['samples']:
    pushToWilco(s)


