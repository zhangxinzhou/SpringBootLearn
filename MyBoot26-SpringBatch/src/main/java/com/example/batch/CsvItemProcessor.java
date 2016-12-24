package com.example.batch;

import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;

import com.example.domain.Person;

public class CsvItemProcessor extends ValidatingItemProcessor<Person>{

	@Override
	public Person process(Person item) throws ValidationException {
		super.process(item);//需执行super.process(item)才会调用自定义的校验器
		
		if(item.getNation().equals("汉族")){
			item.setNation("01");
		}else{
			item.setNation("02");
		}
		return item;
	}

	
}
