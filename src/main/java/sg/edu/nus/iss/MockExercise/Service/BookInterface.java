package sg.edu.nus.iss.MockExercise.Service;

import java.util.List;

import org.springframework.data.domain.Page;

import sg.edu.nus.iss.MockExercise.Model.Book;

public interface BookInterface {
    
    public List<Book> findAll();

    public Page<Book> listAll(Integer pageNumber, String sortField, String sortDir);
}
