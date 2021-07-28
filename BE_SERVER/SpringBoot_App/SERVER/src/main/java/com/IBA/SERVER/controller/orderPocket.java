package com.IBA.SERVER.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("")
public class orderPocket {

    public Map<String, Double> convertToMapObj(String str)
    {
        Gson gson = new Gson();

        Map<String, Double> mapObj = gson.fromJson(str, Map.class);

        return mapObj;
    }

    @PostMapping(value="/requestBookDatas")
    public String RequestBookDatas(@RequestBody String data){
        //Gson 객체 생성
        /*
        Gson gson = new Gson();

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

        //Service 영역의 7로 bookUID[0] 넘긴 후 JSon 형태로 결과를 리턴는 부분의 코드
        // 이후 JSon 객체를 String으로 변환 후 리턴
        String test = Integer.toString(requestId) + "::" + Integer.toString(bookUID[0]);
        return test;
    }

    @PatchMapping(value="/updateBookStatus/{bookCaseNum}")
    public void updateBookLists(@PathVariable Integer bookCaseNum, @RequestBody String data)
    {

    }


}
