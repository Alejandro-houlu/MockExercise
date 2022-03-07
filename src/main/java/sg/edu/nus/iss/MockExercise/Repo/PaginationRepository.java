package sg.edu.nus.iss.MockExercise.Repo;

import org.springframework.data.repository.PagingAndSortingRepository;

import sg.edu.nus.iss.MockExercise.Model.Book;

public interface PaginationRepository extends PagingAndSortingRepository<Book,String>{
    
}
