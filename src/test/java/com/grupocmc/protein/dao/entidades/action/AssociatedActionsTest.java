package com.grupocmc.protein.dao.entidades.action;


import com.grupocmc.protein.dao.model.administration.Action;
import com.grupocmc.protein.dao.model.administration.ActionType;
import com.grupocmc.protein.dao.repository.administration.ActionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AssociatedActionsTest {

@Autowired
    private ActionRepository actionRepository;

    @Test
    @Rollback(false)
    public void testSaveActionType() {

        ActionType actionType = new ActionType();
        actionType.setDescription("action.description.1");
        actionType.setName("action.name.1");
        actionType.setRule("a+b");
        actionType.setExpresionRule("sum");
        actionType.setNumIterations(2);

        Action actionPadre = new Action();
        actionPadre.setIsParent(true);
        actionPadre.setName("action1");
        actionPadre.setDescription("action.description.1");
        actionPadre.setMessage("message.descritpion.1");
        actionPadre.setPoints(20);
        actionPadre.setState(Boolean.TRUE);


        Action action = new Action();
        action.setIsParent(false);
        action.setActionParent(actionPadre);
        action.setName("action1");
        action.setDescription("action.description.1");
        action.setMessage("message.descritpion.1");
        action.setPoints(20);
        action.setState(Boolean.TRUE);


        Action accion1 = (Action) actionRepository.save(actionPadre);
        Action accion2 = (Action) actionRepository.save(action);

        assertNotNull(accion1);
        assertNotNull(accion1.getId());
        assertNotNull(accion2);
        assertNotNull(accion2.getId());
        assertTrue(accion1.getIsParent().equals(accion2.getActionParent().getId()));
    }

}
