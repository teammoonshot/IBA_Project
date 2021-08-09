package com.IBA.SERVER.service;

import com.IBA.SERVER.domain.tempDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.IBA.SERVER.mapper.dbMapper;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.LinkedHashMap;

@Service
public class rootCalculation {

    @Autowired
    private dbMapper DBMapper;
    @Autowired
    private tempDB tempdb;

    int[][] root= {{0, 0, 1, 1, 2, 3},{0, 0, 1, 3, 3, 2}};

    public LinkedHashMap<String, Integer> rootCalculation(int bookUID){

        int bookLocation = DBMapper.get_bookUID(bookUID);
        LinkedHashMap<String, Integer> bookRoot = new LinkedHashMap<>();

        switch(bookLocation){
            case 100:
                bookRoot.put("direction1", root[0][0]);
                bookRoot.put("node1", root[0][1]);
                bookRoot.put("direciton2", root[0][2]);
                bookRoot.put("node2", root[0][3]);
                bookRoot.put("direction3", root[0][4]);
                bookRoot.put("node3", root[0][5]);
                bookRoot.put("direciton4", 0); // direction 0 is foward direction
                break;
            case 200:
                bookRoot.put("direction1", root[1][0]);
                bookRoot.put("node1", root[1][1]);
                bookRoot.put("direciton2", root[1][2]);
                bookRoot.put("node2", root[1][3]);
                bookRoot.put("direction3", root[1][4]);
                bookRoot.put("node3", root[1][5]);
                bookRoot.put("direciton4", 0); // direction 0 is foward direction

                break;
        }

        return bookRoot;
    }
    //  /requestBookDatas에 대한 컨트롤러 요청을 처리하는 서비스
    public LinkedHashMap<String, Object> Requestbookrootdatas(int booknum, int bookUIDArray[]){
        LinkedHashMap<String, Object>  result = new LinkedHashMap<>();

        result.put("responseID", 23);

        for(int i = 0; i< booknum; i++)
        {
            result.put("book"+(i+1), rootCalculation(bookUIDArray[i]));
        }

        return result;
    }

    //  /orders에 대한 컨트롤러 요청을 처리하는 서비스
    public LinkedHashMap<String, Object> orderService(){
        return tempdb.getData();
    }

    // /selectedbook/?bookid=  에 대한 컨트롤러 요청을 처리하는 서비스
    public int selectedbookRootSave(int bookUID){

        LinkedHashMap<String, Object> result = new LinkedHashMap<>();

        result.put("responseID", 24);
        result.put("bookUID", bookUID);
        result.put("book1", rootCalculation(bookUID));

        tempdb.setData(result);
        return 1;
    }

    //  /clearorders 에 대한 컨트롤러 요청을 처리하는 서비스
    public boolean deleteBookRoot(){
        tempdb.deleteData();
        return true;
    }




}
