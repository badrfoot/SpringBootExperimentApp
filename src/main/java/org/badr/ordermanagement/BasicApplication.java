package org.badr.ordermanagement;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import javax.annotation.PostConstruct;
import org.badr.ordermanagement.entity.BonusCard;
import org.badr.ordermanagement.entity.CreditCard;
import org.badr.ordermanagement.entity.Customer;
import org.badr.ordermanagement.entity.enums.TypeCreditCard;
import org.badr.ordermanagement.respository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
@EnableTransactionManagement
public class BasicApplication {	

	public static void main(String[] args) {
		SpringApplication.run(BasicApplication.class, args);
	}
	
}
