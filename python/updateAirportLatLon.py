import requests
import json
from requests.auth import HTTPBasicAuth

username = 'dao.hodac@gmail.com'
password = 'toto'

urlWilco = 'http://localhost:9001/apiv3/fwots'

resp = requests.get(urlWilco, auth=(username, password))
print resp.json().fwots

