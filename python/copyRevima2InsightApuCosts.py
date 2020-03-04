import sys, getopt
import io
import datetime
import requests
import json

apikey = 'local-e5ee347a4ba5'
fromSite = 'revima'
toSite = 'insight'
tag = 'REP202'
toLayoutId=557327

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
    e['flightId']=e['flightId'].replace('EY', 'HX')
    if 'from' in e:
        e['from']=e['from'].replace('OMAA', 'VHHH')
    if 'to' in e:
        e['to']=e['to'].replace('OMAA', 'VHHH')
    e['reg']='HIDDEN_APU_USAGE'
    e.pop('fwot', None)
    e.pop('style', None)
    e.pop('klass', None)
    e.pop('dateIsEstimated', None)
    e.pop('tags', None)
    e.pop('dashboardIds', None)
    e.pop('category', None)
    e.pop('analysis', None)
    filteredSamples = []
    for s in e['samples']:
        if s['name'].startswith("airport") and 'value' in s:
            s['value']=s['value'].replace('OMAA', 'VHHH')
        if s['name'].startswith("APU_FUEL") or s['name'].startswith("airport") or s['name'].startswith("latitude") or s['name'].startswith("longitude"):
            s['date']=e['computedDate']
            filteredSamples.append(s)
    e['samples'] = filteredSamples
    e.pop('lastUpdate', None)
    e['title'] = "APU usage"
    e['description'] = "APU usage"
    e.pop('sumUp', None)
    e['layoutId']=toLayoutId
    
    

def display(e):
    print(json.dumps(e, sort_keys=True, indent=4, separators=(',', ': ')))
    #print(str(e['computedDate']+' from '+e['reg']))

def push(e):
    urlPostToWilco = 'https://'+toSite+'.flightwatching.com/fleet/apiv3/insert/eventV3IO'
    resp=requests.post(urlPostToWilco, headers={'api-key': apikey, 'Content-type': 'application/json'}, data=json.dumps(e))
    print(urlPostToWilco)
    print('posted to dest '+str(e['computedDate'])+' -> CODE#'+str(resp.status_code))


# get the list of events
urlGetEventList = 'https://'+fromSite+'.flightwatching.com/fleet/apiv3/events'
params={
    'from':'2019-07-30T09:33:35',
    'to':'2019-08-30T09:33:35',
    'tag':tag,
    'count':4000,
    'format':'json'
}

resp = requests.get(urlGetEventList, headers={'api-key': apikey}, params=params)

for e in resp.json()['events']:
    evt=getEvent(e)
    transform(evt)
    #display(evt)
    push(evt)


