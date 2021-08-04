package com.IBA.SERVER.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.IBA.SERVER.mapper.dbMapper;

import java.util.HashMap;
import java.util.LinkedHashMap;

@Service
public class rootCalculation {

    @Autowired
    private dbMapper DBMapper;

    int[][] root= {{0, 0, 1, 1, 2, 3},{0, 0, 1, 3, 3, 2}};

    public LinkedHashMap<String, Object> serviceInRobot(int numberOfBooks, int[] bookUID){
        int[] bookLocation = new int[numberOfBooks];

        LinkedHashMap<String, Object> result = new LinkedHashMap<>();

        result.put("responseID", 23);
        //책의 수만큼 각각 위치를 받아온 뒤 최단경로 Map Table 에 넣기
        for(int i =0; i < numberOfBooks; i++){
            LinkedHashMap<String, Integer> bookRoot = new LinkedHashMap<>();
            bookLocation[i] = DBMapper.get_bookUID(bookUID[i]);

            switch(bookLocation[i]){
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
            result.put("book"+(i+1), bookRoot);
            //bookRoot.clear();
        }
        return result;
    }
}
