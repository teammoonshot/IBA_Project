package com.IBA.SERVER.controller;

import com.IBA.SERVER.service.bookListsGetServices;
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
public class manageBookDB {

    @Autowired
    bookListsGetServices BookListsGetServices;

    @GetMapping(value="/allbooklists")
    public List getAllBookLists(){
        return BookListsGetServices.getAllBookListsServices();

    }
}
