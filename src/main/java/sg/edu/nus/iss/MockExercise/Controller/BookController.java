package sg.edu.nus.iss.MockExercise.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.nus.iss.MockExercise.Model.Book;
import sg.edu.nus.iss.MockExercise.Repo.BookRepository;
import sg.edu.nus.iss.MockExercise.Service.BookInterface;


@Controller
@RequestMapping("/")
public class BookController {

    @Autowired
    BookInterface bService;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    BookRepository bRepo;

    @RequestMapping(value = {"/","/home"})
    public String landingPage(Model model){
	
        //List<Book> bookList = bService.findAll();

        return listByPage(model, 1, "title", "asc", "10");
    }

	@RequestMapping("/page/{pageNumber}")
	public String listByPage(Model model, 
	@PathVariable("pageNumber") Integer currentPage,
	@Param("sortField") String sortField,
	@Param("sortDir") String sortDir,
	@RequestParam(value = "itemsPerPage", required = false) String itemsPerPage){

		Integer items = 0;

		if(itemsPerPage == null){
			items = bService.getItemsPerPage();
		}
		else{
			items = Integer.parseInt(itemsPerPage);
			bService.recordNoOfItemsPerPage(items);
		}
		Page<Book> page = bService.listAll(currentPage, sortField, sortDir, items);
		Long totalItems = page.getTotalElements();
		Integer totalPages = page.getTotalPages();
		List<Book> bookList = page.getContent();

		model.addAttribute("currentPage", currentPage);
        model.addAttribute("bookList", bookList);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("items", items);


		String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
		model.addAttribute("reverseSortDir", reverseSortDir);

        return "index";

	}

	@RequestMapping("/search")
	public String searchBook(@RequestParam(value = "titleSearch", required = false) String titleSearch,
							 @RequestParam(value = "authorSearch", required = false) String authorSearch, 
							  Model model){

		//System.out.println(searchParam);
		
		List<Book> searchResult = new ArrayList<>();
		String error = "";

		if(titleSearch.isEmpty() && authorSearch.isEmpty()){
			return "redirect:/home";
		}
		else{

			if(titleSearch.isEmpty()){
				searchResult = bService.searchAuthor(authorSearch.toLowerCase());
			}
			else if (authorSearch.isEmpty()){
				searchResult = bService.searchTitle(titleSearch.toLowerCase());
			}
			else{

				List<String> titleAndAuthor = new ArrayList<>();
				titleAndAuthor.add(titleSearch.toLowerCase()); titleAndAuthor.add(authorSearch.toLowerCase());
				searchResult = bService.searchTitleAndAutor(titleAndAuthor);
			}
		}

		if(searchResult.isEmpty()){

			error = "No results found";
		}

		model.addAttribute("searchResult",searchResult);
		model.addAttribute("error",error);


		return "searchResult";
	}

	@GetMapping("/searchId")
	public String searchBookById(@RequestParam String bookIdSearch, Model model){

		List<Book> searchResult = new ArrayList<>();
		String error = "";

		if(!bookIdSearch.isEmpty()){

			var result = bRepo.findById(bookIdSearch); 
			searchResult = result.stream().collect(Collectors.toList());
		}

		if(searchResult.isEmpty()){
			error = "No results found";
		}

		model.addAttribute("searchResult", searchResult);
		model.addAttribute("error", error);

		return "searchResult";

	}


}
