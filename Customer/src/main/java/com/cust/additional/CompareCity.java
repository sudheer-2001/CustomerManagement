package com.cust.additional;

import java.util.Comparator;

import org.springframework.stereotype.Component;

import com.cust.entity.CustomerEntity;

@Component
public class CompareCity implements Comparator<CustomerEntity>{

	@Override
	public int compare(CustomerEntity o1, CustomerEntity o2) {
		String name1 = o1.getCity();
		String name2 = o2.getCity();
		return name1.compareTo(name2);
	}
}
