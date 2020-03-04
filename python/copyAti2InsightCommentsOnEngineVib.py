import sys, getopt
import io
import datetime
import requests
import json

apikey = 'local-e5ee347a4ba5'
fromSite = 'ati'
fromEventIds = [6114039, 8173219]
toSite = 'insight'
toLayoutId=38554

def getEvent(id):
    urlGetEvent = 'https://'+fromSite+'.flightwatching.com/fleet/apiv3/events/'+str(id)
    print(urlGetEvent)
    params={
        'nested':True
    }
    resp = requests.get(urlGetEvent, headers={'api-key': apikey}, params=params)
    return resp.json()

def transform(e):
    e.pop('id', None)
    e['reg']='HX005'
    e.pop('fwot', None)
    #e.pop('samples', None)
    e.pop('lastUpdate', None)
    e['description'] = e['title']
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

for id in fromEventIds:
    evt=getEvent(id)
    transform(evt)
    #display(evt)
    push(evt)


