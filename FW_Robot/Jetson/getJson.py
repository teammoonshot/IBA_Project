import json
import requests

    #To use "GetJson" function module, You must import libary "json" and "requests".
    #This function takes URL that contains JSON data. 
    #The example of this function is:
    # str = getJson("http://localhost:8080/orders")

def getJson(url):

    text = (requests.get(url)).text
    data = json.loads(text)

    return text

#str = getJson("http://localhost:8080/orders")
#print(str)