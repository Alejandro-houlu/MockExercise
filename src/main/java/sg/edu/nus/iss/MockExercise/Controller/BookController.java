package sg.edu.nus.iss.MockExercise.Controller;

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

        return listByPage(model, 1, "title", "asc");
    }

	@GetMapping("/page/{pageNumber}")
	public String listByPage(Model model, 
	@PathVariable("pageNumber") Integer currentPage,
	@Param("sortField") String sortField,
	@Param("sortDir") String sortDir){

		Page<Book> page = bService.listAll(currentPage, sortField, sortDir);
		Long totalItems = page.getTotalElements();
		Integer totalPages = page.getTotalPages();
		List<Book> bookList = page.getContent();

		model.addAttribute("currentPage", currentPage);
        model.addAttribute("bookList", bookList);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);

		String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
		model.addAttribute("reverseSortDir", reverseSortDir);

        return "index";

	}

	@RequestMapping("/search")
	public String searchBook(@RequestParam(value = "titleAndAuthor", required = false) String searchParam, Model model){

		//System.out.println(searchParam);

		String[] search = searchParam.split(",");

		if(search.length == 0){
			return "redirect:/home";
		}

		return "searchResult";
	}


}
