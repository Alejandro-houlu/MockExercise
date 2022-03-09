package sg.edu.nus.iss.MockExercise.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
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

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public List<Book> findAll() {

        List<Book> bookList = new ArrayList<>();
        var result = bRepo.findAll();
        result.forEach(x->bookList.add(x));

        return bookList;
    }

    @Override
    public Page<Book> listAll(Integer pageNumber, String sortField, String sortDir, Integer itemsPerPage){

        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNumber - 1 , itemsPerPage, sort);
        return rRepo.findAll(pageable);
        
    }

    @Override
    public void recordNoOfItemsPerPage(Integer itemsPerPage) {
        
        redisTemplate.opsForValue().set("itemsPerPage", itemsPerPage);

    }

    @Override
    public Integer getItemsPerPage() {

        Integer itemsPerPage = (Integer)redisTemplate.opsForValue().get("itemsPerPage");

        return itemsPerPage;
    }

    @Override
    public List<Book> searchTitle(String title) {

        List<Book> booklist = findAll();

       booklist.stream().forEach(x->System.out.println(x.getTitle().toLowerCase()));

        List<Book> searchResult = booklist.stream().filter(x->x.getTitle().toLowerCase().contains(title)).collect(Collectors.toList());



        return searchResult;
    }

    @Override
    public List<Book> searchAuthor(String author) {
        List<Book> booklist = findAll();

        List<Book> searchResult = booklist.stream().filter(x->x.getAuthor().toLowerCase().contains(author)).collect(Collectors.toList());

        return searchResult;
    }

    @Override
    public List<Book> searchTitleAndAutor(List<String> titleAndAuthor) {

        Set<String> ids = new HashSet<>();
        List<Book> searchResult = new ArrayList<>();
        
        List<Book> title = searchTitle(titleAndAuthor.get(0));
        List<Book> author = searchAuthor(titleAndAuthor.get(1));

        for(Book b : title){
            ids.add(b.getId());
        }

        for(Book b : author){
            if(!ids.add(b.getId())){
                searchResult.add(b);
            }
        }
        return searchResult;
    }


    

    

    
    
}
