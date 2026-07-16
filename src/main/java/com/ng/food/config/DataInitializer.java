package com.ng.food.config;

import com.ng.food.model.FoodItem;
import com.ng.food.repository.FoodItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

	@Bean
	CommandLineRunner initDatabase(FoodItemRepository repository) {
		return args -> {
			if (repository.count() == 0) {
				repository.save(new FoodItem("Chicken Biryani", 120, "750 kcal",
						"Fragrant basmati rice layered with juicy marinated chicken chunks.",
						"https://images.unsplash.com/photo-1563379091339-03b21ab4a4f8?w=400&auto=format&fit=crop&q=60"));
				repository.save(new FoodItem("Chicken Fried Rice", 80, "620 kcal",
						"Wok-tossed long-grain rice with shredded chicken breast and eggs.",
						"https://images.unsplash.com/photo-1603133872878-684f208fb84b?w=400&auto=format&fit=crop&q=60"));
				repository.save(new FoodItem("Mutton Biryani", 180, "820 kcal",
						"Premium tender mutton pieces slow-cooked in rich spices.",
						"https://images.unsplash.com/photo-1633945274405-b6c8069047b0?w=400&auto=format&fit=crop&q=60"));
				repository.save(new FoodItem("Pizza", 200, "850 kcal",
						"Fresh baked crust topped with herb-infused marinara and melted mozzarella.",
						"https://images.unsplash.com/photo-1513104890138-7c749659a591?w=400&auto=format&fit=crop&q=60"));
				repository.save(new FoodItem("Burger", 85, "490 kcal",
						"Toasted soft brioche buns enclosing a crisp savory patty and fresh layers.",
						"https://images.unsplash.com/photo-1568901346375-23c9450c58cd?w=400&auto=format&fit=crop&q=60"));
				repository.save(new FoodItem("Chicken Leg (1)", 90, "280 kcal",
						"Deep-marinated single whole chicken drumstick cooked till crispy golden.",
						"https://images.unsplash.com/photo-1562967914-608f82629710?w=400&auto=format&fit=crop&q=60"));
				repository.save(new FoodItem("Chicken Wings", 150, "450 kcal",
						"Plump juicy chicken wings glazed with sweet tangy barbecue sauce.",
						"https://images.unsplash.com/photo-1567620832903-9fc6debc209f-9?w=400&auto=format&fit=crop&q=60"));
				repository.save(new FoodItem("Tandoori Chicken", 140, "550 kcal",
						"Smoky bone-in chicken thighs marinated in fiery tandoori spices.",
						"https://www.seema.com/wp-content/uploads/2021/08/shutterstock_1199926645-scaled.jpg"));
				repository.save(new FoodItem("Paneer Butter Masala", 110, "680 kcal",
						"Succulent cubes of fresh paneer simmered in smooth creamy tomato gravy.",
						"https://www.cookwithmanali.com/wp-content/uploads/2019/05/Paneer-Butter-Masala.jpg"));
				repository.save(new FoodItem("Chicken Tikka Roll", 95, "410 kcal",
						"Roasted spicy chicken tikka wrapped inside warm flatbread with chutney.",
						"https://bakewithzoha.com/wp-content/uploads/2024/03/chicken-tikka-paratha-rolls-featured.jpg"));
				repository.save(new FoodItem("Gulab Jamun (2)", 40, "320 kcal",
						"Soft berry-sized sweet milk dumplings soaked completely in sugar syrup.",
						"https://upload.wikimedia.org/wikipedia/commons/5/58/Two_Gulab_Jamun_in_a_plate_01.jpg"));

				System.out.println(
						"✅ All 11 Delicious food items populated into PostgreSQL cloud database successfully!");
			}
		};
	}
}