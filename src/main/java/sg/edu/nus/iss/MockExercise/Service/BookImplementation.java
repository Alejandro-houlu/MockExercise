package sg.edu.nus.iss.MockExercise.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.MockExercise.Model.Book;
import sg.edu.nus.iss.MockExercise.Repo.BookRepository;
import sg.edu.nus.iss.MockExercise.Repo.PaginationRepository;

@Service
public class BookImplementation implements BookInterface {

    @Autowired
    BookRepository bRepo;

    @Autowired
    PaginationRepository rRepo;

    @Override
    public List<Book> findAll() {

        List<Book> bookList = new ArrayList<>();
        var result = bRepo.findAll();
        result.forEach(x->bookList.add(x));

        return bookList;
    }

    @Override
    public Page<Book> listAll(Integer pageNumber, String sortField, String sortDir){

        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNumber - 1 , 5, sort);
        return rRepo.findAll(pageable);
        
    }


    

    

    
    
}
