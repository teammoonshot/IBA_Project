package com.IBA.SERVER.controller;

import com.IBA.SERVER.service.bookListsGetServices;
import com.IBA.SERVER.service.rootCalculation;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("")
public class searchBookLists {

    @Autowired
    rootCalculation RootCalculation;
    @Autowired
    bookListsGetServices BookListsGetServices;

    Gson gson = new Gson();

    //Module 5
    @GetMapping(value="/booklists/")
    public List getBookLists(@RequestParam("query") String query)
    {
        return BookListsGetServices.getBookListbyQuery(query);
    }
    //Module 4
    @GetMapping(value="/selectedbook/")
    public String getbookId(@RequestParam("bookid") int bookid)
    {
        //사용자가 검색을 요청한 책에 대한 경로를 계산해서 임시 DB에 저장하는 서비스 호출
        int process = RootCalculation.selectedbookRootSave(bookid);
        if(process == 1)
            return "OK";
        else
            return "Error to save book root datas";

    }



}
