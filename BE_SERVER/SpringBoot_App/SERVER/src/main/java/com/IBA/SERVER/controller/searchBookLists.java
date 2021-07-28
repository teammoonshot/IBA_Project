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
public class searchBookLists {

    //Module 5
    @GetMapping(value="/booklists/")
    public String getBookLists(@RequestParam("query") String query)
    {

        return query+" module 5 Test OK";
    }
    //Module 4
    @GetMapping(value="/selectedbook/")
    public String getbookId(@RequestParam("bookid") int bookid)
    {
        return Integer.toString(bookid) +"  module 4 Test OK";
    }



}
