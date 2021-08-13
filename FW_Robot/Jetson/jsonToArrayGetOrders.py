def jsonToArrayGetorders(json_res):
    length = len(json_res["book1"])
    array = [0]*(length+3)

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
