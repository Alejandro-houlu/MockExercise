package sg.edu.nus.iss.MockExercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;

import sg.edu.nus.iss.MockExercise.Model.Book;
import sg.edu.nus.iss.MockExercise.Repo.BookRepository;

@SpringBootApplication
public class MockExerciseApplication {

	@Autowired
	RedisTemplate<String, Object> redisTemplate;

	@Autowired
	BookRepository bRepo;

	public static void main(String[] args) {

		SpringApplication.run(MockExerciseApplication.class, args);

	}

	@Bean
	CommandLineRunner runner () {
		return args -> {
			var count = (Integer)redisTemplate.opsForValue().get("Count");
			System.out.println(count);

		if(count == null){

			redisTemplate.opsForValue().set("Count", 1);

			Book b1 = new Book("Ella the rose fairy" , "Daisy Meadows", "ella_the_rose_fairy.jpg" );
			Book b2 = new Book("Harry Potter and the socerer's stone" , "J.K. Rowiing", "harry-potter-p-stone.jpg" );
			Book b3 = new Book("The haunted tower" , "Leigh", "the_haunted_tower.jpg" );
			Book b4 = new Book("Yesterday is Today" , "Alejandro", "no_book_cover.jpg" );
			Book b5 = new Book("Winter Fairy" , "Durai Arati", "no_book_cover.jpg" );
			Book b6 = new Book("The Enchanted Ones" , "Kasey Oluwaseyi", "no_book_cover.jpg" );
			Book b7 = new Book("Together For A Day" , "Darija Kveta", "no_book_cover.jpg" );
			Book b8 = new Book("Aliens of a New Kind" , "Amihan Emyr", "no_book_cover.jpg" );
			Book b9 = new Book("Behind the Door" , "Birger Klimentina", "no_book_cover.jpg" );
			Book b10 = new Book("Galaxy Bugs" , "Milena Ulrich", "no_book_cover.jpg" );

			bRepo.save(b1);bRepo.save(b2);bRepo.save(b3);bRepo.save(b4);bRepo.save(b5);bRepo.save(b6);bRepo.save(b7);bRepo.save(b8);bRepo.save(b9);bRepo.save(b10);

		}

		if(count == 1){

			redisTemplate.opsForValue().set("Count", 2);

			Book b11 = new Book("A Few Hard Men" , "Melvin Vazquez", "no_book_cover.jpg" );
			Book b12 = new Book("Grinding Nemo" , "Karolina Walls", "no_book_cover.jpg" );
			Book b13 = new Book("Pulp Friction" , "Izzy Flowers", "no_book_cover.jpg" );
			Book b14 = new Book("Romeo in Juliet" , "Deez Nuts", "no_book_cover.jpg" );
			Book b15 = new Book("The Devil Wears Nada" , "Arthur Goff", "no_book_cover.jpg" );
			Book b16 = new Book("Oceans 11 Inches" , "Kali Hussain", "no_book_cover.jpg" );
			Book b17 = new Book("Everyone I Did Last Summer" , "Inaaya Mathis", "no_book_cover.jpg" );
			Book b18 = new Book("Evil Head" , "Leela Hamilton", "no_book_cover.jpg" );
			Book b19 = new Book("Schindlers Fist" , "Axel Kent", "no_book_cover.jpg" );
			Book b20 = new Book("Drill Bill" , "Eliot Little", "no_book_cover.jpg" );

			bRepo.save(b11);bRepo.save(b12);bRepo.save(b13);bRepo.save(b14);bRepo.save(b15);bRepo.save(b16);bRepo.save(b17);bRepo.save(b18);bRepo.save(b19);bRepo.save(b20);


		}


		};
	}

}
