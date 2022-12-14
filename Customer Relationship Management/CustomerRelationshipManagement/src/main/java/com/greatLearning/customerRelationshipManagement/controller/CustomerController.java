package com.greatLearning.customerRelationshipManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greatLearning.customerRelationshipManagement.entity.Customer;
import com.greatLearning.customerRelationshipManagement.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@RequestMapping("/list")
	public String listStudents(Model theModel) {

		List<Customer> theCustomers = customerService.findAll();

		theModel.addAttribute("Customers", theCustomers);

		return "list-Customers";
	}

	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		Customer theCustomer = new Customer();

		theModel.addAttribute("Customer", theCustomer);

		return "Customer-form";
	}

	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel) {

		Customer theCustomer = customerService.findById(theId);

		theModel.addAttribute("Customer", theCustomer);

		return "Customer-form";
	}

	@PostMapping("/save")
	public String saveCustomer(@RequestParam("id") int id, @RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("email") String email) {

		Customer theCustomer;
		if (id != 0) {
			theCustomer = customerService.findById(id);
			theCustomer.setFirstName(firstName);
			theCustomer.setLastName(lastName);
			theCustomer.setEmail(email);

		} else
			theCustomer = new Customer(firstName, lastName, email);

		customerService.save(theCustomer);

		return "redirect:/customer/list";

	}

	@RequestMapping("/delete")
	public String delete(@RequestParam("customerId") int theId) {

		customerService.deleteById(theId);

		return "redirect:/customer/list";

	}

}
