package com.example.csvdemo2;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class Processor implements ItemProcessor<Sales,Sales> {

	@Override
	public Sales process(Sales sales) throws Exception {
		// TODO Auto-generated method stub
		System.err.println("PROCESSS");
		return sales;
	}

}
