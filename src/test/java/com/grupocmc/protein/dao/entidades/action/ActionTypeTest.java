package com.grupocmc.protein.dao.entidades.action;

import com.grupocmc.protein.dao.model.administration.ActionType;
import com.grupocmc.protein.dao.model.administration.ActionTypeMin;
import com.grupocmc.protein.dao.model.administration.ActionTypeTemp;
import com.grupocmc.protein.dao.repository.administration.ActionTypeRepository;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ActionTypeTest {

    @Autowired
    private ActionTypeRepository actionTypeRepository;

    @Test
    @Rollback(false)
    public void testSaveActionType() {

        ActionType actionType = new ActionType();
        actionType.setDescription("action.description.1");
        actionType.setName("action.name.1");
        actionType.setRule("a+b");
        actionType.setExpresionRule("sum");
        actionType.setNumIterations(2);

        ActionType newAction = (ActionType) actionTypeRepository.save(actionType);
        assertNotNull(newAction);
        assertNotNull(newAction.getId());
    }


    @Test
    @Rollback(false)
    public void testSaveActionTypeMin() {

        ActionTypeMin actionType = new ActionTypeMin();
        actionType.setDescription("action.description.2");
        actionType.setName("action.name.2");
        actionType.setRule("a+b+9");
        actionType.setExpresionRule("sumD");
        actionType.setNumIterations(21);
        actionType.setValue(300);

        ActionType newAction = (ActionType) actionTypeRepository.save(actionType);
        assertNotNull(newAction);
        assertNotNull(newAction.getId());
    }

    @Test
    @Rollback(false)
    public void testSaveActionTypeTemp() {

        ActionTypeTemp actionType = new ActionTypeTemp();
        actionType.setDescription("action.description.3");
        actionType.setName("action.name.3");
        actionType.setRule("a+b+C");
        actionType.setExpresionRule("sumA");
        actionType.setNumIterations(12);
        actionType.setNumDays(300);
        actionType.setStartDate(new DateTime(2020, 8, 1, 12, 0, 0, 0, DateTimeZone.UTC));
        actionType.setEndDate(new DateTime(2020, 8, 30, 12, 0, 0, 0, DateTimeZone.UTC));
        ActionType newAction = (ActionType) actionTypeRepository.save(actionType);
        assertNotNull(newAction);
        assertNotNull(newAction.getId());
    }

    @Test
    public void testFindActionType(){

       ActionType comun=  (ActionType) actionTypeRepository.findById(28L).orElse(null);
       ActionTypeMin min=  (ActionTypeMin) actionTypeRepository.findById(29L).orElse(null);
        ActionTypeTemp temp=  (ActionTypeTemp) actionTypeRepository.findById(27L).orElse(null);
        assertNotNull(comun);
        assertNotNull(min);
        assertNotNull(temp);
    }

    @Test
    public void testFindAll(){
        List<ActionType> list = actionTypeRepository.findAll();
        assertNotNull(list);
        assertTrue(list.size()> 0);
    }
}
