package com.IBA.SERVER.controller;

import com.IBA.SERVER.service.rootCalculation;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.LinkedHashMap;
import java.util.Map;


@RestController
@RequestMapping("")
public class orderPocket {

    @Autowired
    private rootCalculation RootCalculation;

    public Map<String, Double> convertToMapObj(String str)
    {
        Gson gson = new Gson();

        Map<String, Double> mapObj = gson.fromJson(str, Map.class);

        return mapObj;
    }

    @PostMapping(value="/requestBookDatas")
    public LinkedHashMap<String, Object> RequestBookDatas(@RequestBody String data){
        //Gson 객체 생성

        Gson gson = new Gson();
        /*
        //String 형태로 받아온 JSon 문자열을 Map 객체로 변환
        Map<String, Double> bookDatas = gson.fromJson(data, Map.class);
        */

        Map<String, Double> bookDatas = convertToMapObj(data);


        //requestID 값 추출
        int requestId = bookDatas.get("requestId").intValue();

        //책의 수 추출
        int bookNum = bookDatas.get("bookNum").intValue();
        int[] bookUID = new int[bookNum];

        //책의 수 만큼 들어온 책들의 UID를 bookUID 배열에 저장
        for(int i = 0; i < bookNum; i++)
        {
            bookUID[i] = bookDatas.get("book"+(i+1)).intValue();
        }
        LinkedHashMap<String, Object> result= new LinkedHashMap<>();
        result = RootCalculation.Requestbookrootdatas(bookNum, bookUID);

        //String result = gson.toJson(rootResult);

        return result;
    }

    @PatchMapping(value="/updateBookStatus")
    public void updateBookLists(@RequestParam("bookCaseNum") int bookCaseNum, @RequestBody String data)
    {
        System.out.println(bookCaseNum+" :  TEST OK");
    }

    @GetMapping(value="/orders")
    public LinkedHashMap<String,Object> getOrders(){

        LinkedHashMap<String, Object> result = new LinkedHashMap<>();

        result = RootCalculation.orderService();

        return result;
    }

    @GetMapping(value="/clearorders")
    public String clearTempRootData(){
        boolean check = RootCalculation.deleteBookRoot();
        if(check == true)
            return "Clearing Temporary data compolete";
        else
            return "Error clearing Temporary data";
    }


}
