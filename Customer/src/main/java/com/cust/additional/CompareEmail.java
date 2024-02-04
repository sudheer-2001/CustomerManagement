package com.cust.additional;

import java.util.Comparator;

import org.springframework.stereotype.Component;

import com.cust.entity.CustomerEntity;

@Component
public class CompareEmail implements Comparator<CustomerEntity>{

	@Override
	public int compare(CustomerEntity o1, CustomerEntity o2) {
		String name1 = o1.getEmail();
		String name2 = o2.getEmail();
		return name1.compareTo(name2);
	}
}
