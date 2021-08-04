package com.IBA.SERVER.mapper;


import java.util.List;

import com.IBA.SERVER.configuration.DBConfiguration;
import org.apache.ibatis.annotations.Mapper;
import com.IBA.SERVER.domain.bookListsTableDTO;
import com.IBA.SERVER.domain.mapTableVO;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;


@Mapper
public interface dbMapper {
    //책ID를 받아서 위치를 반환하는 호출
    public int get_bookUID(int Book_ID);

    //경로 리스트를 반환하는 호출
    //public List<mapTableVO> get_mapTable();

    //책 ID를 통해서 저자 출판사 등의 정보 가져오기
    //public int getBookStatus(int Book_ID);

    //검색어를인자로 받으면 검색어에 대응하는 데이터를 얻어온다.
    public List<bookListsTableDTO> selectBook(String Title);

    //전체 책 리스트에 대한 데이터 호출
    public List<bookListsTableDTO> getAllBooks();

}
