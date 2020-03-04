import sys, getopt
import io
import datetime
import requests
import json

def deleteEvent(id):
    urlDeleteEvent = 'https://insight-val.flightwatching.com/fleet/apiv3/events/'+str(id)
    params={
    #    'nested':True
    }
    resp = requests.delete(urlDeleteEvent, headers={'api-key': 'local-e5ee347a4ba5'}, params=params)
    print(id, '\tdeleted')

def copyEventRaw(id):
    urlGetEvent = 'https://insight-val.flightwatching.com/fleet/apiv3/events/'+str(id)+'/raw'
    resp = requests.get(urlGetEvent, headers={'api-key': 'local-e5ee347a4ba5'})
    rawFile = open("deletedRaws/"+id+".txt", "w")
    n = rawFile.write(resp.text)
    rawFile.close()

filepath = 'toDel100T.txt'
with open(filepath) as fp:
   line = fp.readline()
   while line:
        id=line.strip()
        copyEventRaw(id)
        deleteEvent(id)
        sys.stdout.flush()
#       print(json.dumps(event, indent=2))
        line = fp.readline()
