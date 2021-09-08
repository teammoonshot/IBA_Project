import time
import threading

import json
import requests

import serial

from datetime import datetime

PORT='COM5'
BaudRate = 9600

ARD = serial.Serial(PORT, BaudRate)

def getJson(url):

    text = (requests.get(url)).text
    data = json.loads(text)
    if(len(data) > 0):
        print("JSON Data Receive ......... OK !!")
    else:
        print("JSON Data Receive ......... Fail")
    return data

def jsonToArrayGetorders(json_res):
    if(len(json_res) > 0):
        length = len(json_res["book1"])
        array = [0]*(length+2)

        if(json_res["responseID"]==24):
            array[0] = 2
    
        #array[1]=0
        array[1]=json_res["book1"]["node0"] 
        array[2]=json_res["book1"]["direction0"] 
        array[3]=json_res["book1"]["node1"] 
        array[4]=json_res["book1"]["direction1"] 
        array[5]=json_res["book1"]["node2"] 
        array[6]=json_res["book1"]["direction2"] 
        #array[8]=json_res["book1"]["direction4"] 
    
        array[7] = json_res["bookUID"]
        #array[9] = 999
        return array
    else:
        return "None"


def jsonToArrayRequestBookDatas(json_res):
    length = 0

    for i in range(json_res["bookNum"]):
        length += len(json_res["book"+str(i+1)])

    length += 1

    result = [0]*length

    if(json_res["responseID"]==23):
        result[0] = 1
    
    for i in range(json_res["bookNum"]):
        result[1+len(json_res["book"+str(i+1)])*i] = json_res["book"+str(i+1)]["node0"]    
        result[2+len(json_res["book"+str(i+1)])*i] = json_res["book"+str(i+1)]["direction0"]
        result[3+len(json_res["book"+str(i+1)])*i] = json_res["book"+str(i+1)]["node1"]
        result[4+len(json_res["book"+str(i+1)])*i] = json_res["book"+str(i+1)]["direction1"]
        result[5+len(json_res["book"+str(i+1)])*i] = json_res["book"+str(i+1)]["node2"]
        result[6+len(json_res["book"+str(i+1)])*i] = json_res["book"+str(i+1)]["direction2"]
    
    return result


def arrayToString(array):
    res = ""
    for i in range(len(array)):
        res+= str(array[i])
    
    return res



def arrayToJson(array,id):
    #Rule of possible array : array = [bookUID1, bookUID2, bookUID3, ....  ] 
    #when requestID = 20, bookCaseNum parameter value is 0 (eg. arrayToJson(array, 20, 0)) 
    array_length = len(array)

    if(id == 20):
        sendData = {'requestId':id}
        sendData['bookNum'] = array_length
    if(id == 21):
        sendData = {'requestId': id}
        #sendData['bookCaseNumber'] = bookCaseNum


    for i in range(array_length):
        sendData['book'+str(i+1)] = array[i]

    return str(sendData)


def postJson(jsonData, url):
    print("---- POST Request Started ----")
    print(":::: Request URL = " + url+" ::::")
    print(":::: Method : POST ::::")
    return (requests.post(url, data=jsonData)).json()

def patchJson(jsonData, url):
    requests.patch(url, data=jsonData).json()


def StringToArray(ascii):
    arr =[0]*(len(ascii)//3)

    for i in range(len(ascii)//3):
        arr[i] = int(ascii[0+3*i]+ascii[1+3*i]+ascii[2+3*i])

    return arr



#-----------------------#
timer = 0

def moduleRequestBookDatas(url):
    #due에서 데이터를 보내고 바로 시리얼 통신을 닫는 과정이 필요
    global timer

    while True:
        if(timer % 30 == 0):
            moduleGetOrders("http://localhost:8080/orders")

        print("Serial("+PORT+") wait ....")
        if(ARD.readable()):
            print("Serial Data receive ..... OK !!")
            rd = ARD.readline()
            txt = rd.decode()[:len(rd)-1]

            print(txt)

            arr = StringToArray(txt)
            request_json_data = arrayToJson(arr, 20)
            response_json_data = postJson(request_json_data, url)
            response_arr=jsonToArrayRequestBookDatas(response_json_data)
            res_str=str(arrayToString(response_arr))
            res_str.encode('utf-8')

            print(res_str)
            ARD.write(b'\res_str')
            print("RequestBookData Serial write ..... OK !!")

        timer = timer+1
        print(timer)
        time.sleep(1)

def moduleGetOrders(url):
    response_json_data=getJson(url)
    response_arr=jsonToArrayGetorders(response_json_data)
    if(response_arr == "None"):
        return 

    res_str = arrayToString(response_arr)
    res_str.encode('utf-8')
    print("getOrders Serial write .... OK !!")
    print(res_str)
    ARD.write(b'\res_str')

    

    


def modulePatch(string, url):
    #string은 로봇으로 부터 받은 책 데이터
    arr = StringToArray(string)
    request_json = arrayToJson(arr)
    patchJson(request_json)


moduleRequestBookDatas("http://localhost:8080/requestBookDatas")