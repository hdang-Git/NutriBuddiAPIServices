package com.Temple.NutriBuddi.UserManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Temple.NutriBuddi.UserManagement.User;
import com.Temple.NutriBuddi.UserManagement.UserRepository;

@Controller    // This means that this class is a Controller
@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class MainController {
	@Autowired // This means to get the bean called userRepository
	           // Which is auto-generated by Spring, we will use it to handle the data
	private UserRepository userRepository;

	@GetMapping(path="/addNewUser") // Map ONLY GET Requests
	public @ResponseBody String addNewUser (@RequestParam String email
			, @RequestParam String password
			, @RequestParam String first
			, @RequestParam String last
			, @RequestParam String height
			, @RequestParam String weight
			, @RequestParam String age
			, @RequestParam String gender) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		User n = new User();
		if (!email.equals("")) {
			if (userRepository.findByEmail(email) == null) {
				n.setEmail(email);
			} else {
				return "Email is already registered";
			}
		} else {
			return "Valid email required";
		}
		if (!password.equals("")) {
			n.setPassword(password);
		} else {
			return "Password required";
		}
		if (!first.equals("")) {
			n.setFirstName(first);
		} else {
			return "First name required";
		}
		if (!last.equals("")) {
			n.setLastName(last);
		} else {
			return "Last name required";
		}
		if (!height.equals("")) {
			n.setHeight(Integer.parseInt(height));
		} else {
			return "Height required";
		}
		if (!weight.equals("")) {
			n.setWeight(Integer.parseInt(weight));
		} else {
			return "Weight required";
		}
		if (!age.equals("")) {
			n.setAge(Integer.parseInt(age));
		} else {
			return "Age required";
		}
		if (!gender.equals("")) {
			n.setGender(Integer.parseInt(gender));
		} else {
			return "Gender required";
		}
		
		userRepository.save(n);
		return "User added";
	}
	
	@GetMapping(path="/update") // Map ONLY GET Requests
	public @ResponseBody String updateUser (@RequestParam String name
			, @RequestParam String email) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request
		
		User n = userRepository.findByEmail(email);
		n.setLastName(name);
		userRepository.save(n);
		return "Saved";
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<User> getAllUsers() {
		// This returns a JSON or XML with the users
		return userRepository.findAll();
	}
}