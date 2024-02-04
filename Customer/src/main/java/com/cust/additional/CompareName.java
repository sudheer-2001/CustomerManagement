package com.cust.additional;

import java.util.Comparator;

import org.springframework.stereotype.Component;

import com.cust.entity.CustomerEntity;

@Component
public class CompareName implements Comparator<CustomerEntity> {

	@Override
	public int compare(CustomerEntity o1, CustomerEntity o2) {
		String name1 = o1.getFirstName();
		String name2 = o2.getFirstName();
		return name1.compareTo(name2);
	}
}
