package sg.edu.nus.iss.MockExercise.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/searchClass")
public class classController {

    @RequestMapping("/")
    public String citySearch(Model model){

        return "class";

    }

    @GetMapping("/weather")
    public String searchResult(@RequestParam String city, @RequestParam String units, Model model){


        model.addAttribute("city", city);
        model.addAttribute("units", units);

        return "classSearchResult";
    }


}