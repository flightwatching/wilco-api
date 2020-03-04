import sys, getopt
import io
import datetime
import requests
import json

def getEvent(id):
    urlGetEvent = 'https://insight-val.flightwatching.com/fleet/apiv3/events/'+str(id)
    params={
    #    'nested':True
    }
    resp = requests.get(urlGetEvent, headers={'api-key': 'local-e5ee347a4ba5'}, params=params)
    return resp.json()

def getEventRaw(id):
    urlGetEvent = 'https://insight-val.flightwatching.com/fleet/apiv3/events/'+str(id)+'/raw'
    resp = requests.get(urlGetEvent, headers={'api-key': 'local-e5ee347a4ba5'})
    return resp.text


def alreadyReprocessed(evt):
#    if filter(lambda tag: tag['id']=='REPROCESSED', evt['tags']).len()==1:
    if len([tag for tag in evt['tags'] if tag['id'] =='REPROCESSED' ])>0:
        return True
    else:
        return False
    
def triggerCode(raw):
    for ln in raw.splitlines():
        if ln.startswith("C1"):
            return ln.split(",")[7]
    return 'TC NOT FOUND'


def reprocess(evt):
    #/apiv3/events/{eventId}/reprocess
    urlReprocess = 'https://insight-val.flightwatching.com/fleet/apiv3/events/'+str(id)+'/reprocess'
    resp = requests.put(urlReprocess, headers={'api-key': 'local-e5ee347a4ba5'})
    newId = resp.json()['id']
    print(evt['id'], '\treprocess\t'+evt['reg']+'\t'+evt['computedDate']+'\thttps://insight-val.flightwatching.com/fleet/apiv3/events/'+newId)


def skip(id, reason):
    print(id, '\tskip\t', reason)
#    print(json.dumps(evt['tags'], indent=2))

filepath = 'rep39.txt'
with open(filepath) as fp:
   line = fp.readline()
   while line:
        id=line.strip()
        event = getEvent(id)
        if 'id' in event and not alreadyReprocessed(event):
            raw = getEventRaw(id)
            if triggerCode(raw)=='4000':
                result = reprocess(event)
            else:
                result = skip(id, 'no TC 4000 but '+triggerCode(raw))
        else:
           result = skip(id, 'already reprocessed')
        sys.stdout.flush()
#       print(json.dumps(event, indent=2))
        line = fp.readline()
