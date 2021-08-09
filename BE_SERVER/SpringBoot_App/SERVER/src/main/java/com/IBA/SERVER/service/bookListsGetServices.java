package com.IBA.SERVER.service;

import com.IBA.SERVER.domain.bookListsTableDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.IBA.SERVER.mapper.dbMapper;

import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.*;

@Service
public class bookListsGetServices {

    @Autowired
    private dbMapper DBMapper;

    public List getBookListbyQuery(String query){
        return DBMapper.selectBook(query);
    }

    public List getAllBookListsServices(){
        return DBMapper.getAllBooks();
    }
}
