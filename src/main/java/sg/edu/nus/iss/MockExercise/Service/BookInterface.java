package sg.edu.nus.iss.MockExercise.Service;

import java.util.List;

import org.springframework.data.domain.Page;

import sg.edu.nus.iss.MockExercise.Model.Book;

public interface BookInterface {
    
    public List<Book> findAll();

    public Page<Book> listAll(Integer pageNumber, String sortField, String sortDir, Integer itemsPerPage);

    public void recordNoOfItemsPerPage(Integer itemsPerPage);

    public Integer getItemsPerPage();

    public List<Book> searchTitle(String title);

    public List<Book> searchAuthor(String author);

    public List<Book> searchTitleAndAutor(List<String> titleAndAuthor);
}
