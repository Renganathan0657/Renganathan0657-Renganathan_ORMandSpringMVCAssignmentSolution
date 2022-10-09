package com.greatLearning.customerRelationshipManagement.service;

import java.util.List;

import com.greatLearning.customerRelationshipManagement.entity.Customer;

public interface CustomerService {
	public List<Customer> findAll();

	public Customer findById(int theId);

	public void save(Customer thecustomer);

	public void deleteById(int theId);

}
