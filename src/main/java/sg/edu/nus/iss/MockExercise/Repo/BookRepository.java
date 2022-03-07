package sg.edu.nus.iss.MockExercise.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.MockExercise.Model.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, String> {
    
}
