/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.badr.ordermanagement.service;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.badr.ordermanagement.service.impl.OrderServiceImpl;
import org.badr.ordermanagement.AbstractITest;
import org.badr.ordermanagement.entity.draft.E;
import org.badr.ordermanagement.entity.draft.E1;
import org.badr.ordermanagement.entity.draft.E2;
import org.badr.ordermanagement.entity.draft.E3;
import org.badr.ordermanagement.entity.draft.En;
import org.badr.ordermanagement.entity.draft.En1;
import org.badr.ordermanagement.entity.draft.En2;
import org.badr.ordermanagement.entity.draft.En3;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 *
 * @author OBD
 */
public class OrderServiceTest extends AbstractITest{

	@Autowired
	private OrderServiceImpl orderService;

    @PersistenceContext private EntityManager entityManager;


	@Test
    @Transactional
	public void justSimpleTest(){

        En1 en1 = new En1();
        E1 e1 = new E1();
        E2 e2 = new E2();
        E3 e3 = new E3();
        En2 en2 = new En2();
        En3 en3 = new En3();

        List<En> list = new ArrayList<>();

//        En2 <-> E1,   E3
//        En3 <->    E2,E3

        en2.setE(e3);
        e3.getEn().add(en2);

        en3.setE(e3);
        e3.getEn().add(en3);

        entityManager.persist(e3);
        entityManager.persist(en3);
        entityManager.persist(en2);

        entityManager.flush();
        entityManager.clear();

        entityManager.find(E3.class, e3.getId()).getEn()
                                                .forEach(System.out::println);




	}


}
