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
