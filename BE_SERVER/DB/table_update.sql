Create table book_tmp ( select * from book ORDER BY FIELD(Book_ID, 295,121,175,289,201,169,144,272,199,258));
DROP table book;
Create table book (select * from book_tmp);
