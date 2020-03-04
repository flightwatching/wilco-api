import sys, getopt
import io
import datetime
import requests
import json

apikey = sys.argv[1]
fromSite = 'ati'
fromFwot = 'F-GSTB'
toSite = 'insight'
toFwot = 'HX005'
tag = 'REP4'
toLayoutId=38554

def getEvent(e):
    urlGetEvent = 'https://'+fromSite+'.flightwatching.com/fleet/apiv3/events/'+str(e['id'])
    print(urlGetEvent)
    params={
        'nested':True
    }
    resp = requests.get(urlGetEvent, headers={'api-key': apikey}, params=params)
    return resp.json()

def transform(e):
    e.pop('id', None)
    e['reg']=toFwot
    e.pop('fwot', None)
    #e.pop('samples', None)
    e.pop('lastUpdate', None)
    e['title'] = e['sumUp']
    e['description'] = e['sumUp']
    #e.pop('sumUp', None)
    e['layoutId']=toLayoutId
    

def display(e):
    print(json.dumps(e, sort_keys=True, indent=4, separators=(',', ': ')))

def push(e):
    urlPostToWilco = 'https://'+toSite+'.flightwatching.com/fleet/apiv3/insert/eventV3IO'
    resp=requests.post(urlPostToWilco, headers={'api-key': apikey, 'Content-type': 'application/json'}, data=json.dumps(e))
    print(urlPostToWilco)
    print('posted to dest '+str(e['computedDate'])+' -> CODE#'+str(resp.status_code))


# get the list of events
urlGetEventList = 'https://'+fromSite+'.flightwatching.com/fleet/apiv3/fwots/'+fromFwot+'/events'
params={
    'from':'2014-11-19T09:33:35',
#    'to':'2017-11-15T23:33:35',
    'to':'2015-08-20T13:27:49',
    'tag':tag,
    'count':10000,
    'format':'json'
}

resp = requests.get(urlGetEventList, headers={'api-key': apikey}, params=params)

for e in resp.json()['events']:
    evt=getEvent(e)
    transform(evt)
    #display(evt)
    push(evt)


