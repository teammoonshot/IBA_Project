package com.IBA.SERVER.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.PostConstruct;
import java.util.LinkedHashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.HashMap;

@Repository
public class tempDB {
    private LinkedHashMap<String, Object> TemporaryData = new LinkedHashMap<>();

    public LinkedHashMap<String, Object> getData(){

        return TemporaryData;
    }

    public void setData(LinkedHashMap<String, Object> set){
        TemporaryData=set;
    }

    public void deleteData(){
        TemporaryData.clear();
    }



}