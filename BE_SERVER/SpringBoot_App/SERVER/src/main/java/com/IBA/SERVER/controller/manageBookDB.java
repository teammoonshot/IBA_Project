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
public class manageBookDB {
    @GetMapping(value="/allbooklists")
    public String getAllBookLists(){
        return "Module 6 OK";
    }
}
