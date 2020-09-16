package proj.tacos.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;
import proj.tacos.data.IngredientRepository;
import proj.tacos.data.TacoRepository;
import proj.tacos.data.UserRepository;
import proj.tacos.model.Ingredient;
import proj.tacos.model.Order;
import proj.tacos.model.Taco;
import proj.tacos.model.User;
import proj.tacos.model.Ingredient.Type;

@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

	private final IngredientRepository ingredientRepo;

	private TacoRepository tacoRepo;

	private UserRepository userRepo;

	/*
	 * public DesignTacoController(IngredientRepository ingredientRepo) {
	 * this.ingredientRepo = ingredientRepo; }
	 */

	@Autowired
	public DesignTacoController(IngredientRepository ingredientRepo, TacoRepository tacoRepo, UserRepository userRepo) {
		this.ingredientRepo = ingredientRepo;
		this.tacoRepo = tacoRepo;
		this.userRepo = userRepo;
	}

	@ModelAttribute
	public void addIngredientsToModel(Model model) {
		List<Ingredient> ingredients = new ArrayList<>();
		ingredientRepo.findAll().forEach(i -> ingredients.add(i));

		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
		}
	}

	@ModelAttribute(name = "order")
	public Order order() {
		return new Order();
	}

	@ModelAttribute(name = "taco")
	public Taco taco() {
		return new Taco();
	}

	@ModelAttribute(name = "user")
	public User user(Principal principal) {
		String username = principal.getName();
		User user = userRepo.findByUsername(username);
		return user;
	}

	@GetMapping
	public String showDesignForm(Model model) {
		return "design";
	}

	@PostMapping
	public String processDesign(@Valid Taco taco, Errors errors, @ModelAttribute Order order) {

		if (errors.hasErrors()) {
			return "design";
		}

		Taco saved = tacoRepo.save(taco);
		order.addDesign(saved);

		return "redirect:/orders/current";
	}

	private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
		return ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
	}

}
