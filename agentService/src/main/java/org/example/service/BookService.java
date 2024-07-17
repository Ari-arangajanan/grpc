package org.example.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.example.model.Book;
import org.example.repository.BookDao;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookService extends ServiceImpl<BookDao, Book> {

    private BookDao bookDao;
    public boolean insert(Book book) {
        try {
            bookDao.insert(book);
            return true;
        }catch (Exception e){
            throw new RuntimeException();
        }
    }


}
