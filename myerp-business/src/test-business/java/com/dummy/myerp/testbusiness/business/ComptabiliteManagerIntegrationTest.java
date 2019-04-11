package com.dummy.myerp.testbusiness.business;

import com.dummy.myerp.business.contrat.manager.ComptabiliteManager;
import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.JournalComptable;
import com.dummy.myerp.model.bean.comptabilite.LigneEcritureComptable;
import com.dummy.myerp.technical.exception.FunctionalException;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ComptabiliteManagerIntegrationTest {

    private ComptabiliteManager vComptabiliteManager = BusinessTestCase.getBusinessProxy().getComptabiliteManager();
    private EcritureComptable vEcritureComptableSample;

    public ComptabiliteManagerIntegrationTest() {
        super();
    }

    @BeforeEach
    public void setupBeforeEach() {
        vEcritureComptableSample = new EcritureComptable();
        vEcritureComptableSample.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptableSample.setDate(Date.from(Instant.now()));
        vEcritureComptableSample.setLibelle("Libelle");
        vEcritureComptableSample.setReference("AC-2019/00001");

        vEcritureComptableSample.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                null, new BigDecimal(123),
                null));
        vEcritureComptableSample.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
                null, null,
                new BigDecimal(123)));
    }

    @Test
    @Order(1)
    public void insertEcritureComptableTest() throws FunctionalException {
        vComptabiliteManager.insertEcritureComptable(vEcritureComptableSample);
    }

    @Test
    @Order(2)
    public void updateEcritureComptable() throws FunctionalException {
        vEcritureComptableSample.setLibelle("Un nouveau libelle de test");
        vComptabiliteManager.updateEcritureComptable(vEcritureComptableSample);
    }

    @Test
    @Order(3)
    public void deleteEcritureComptableTest() {
        vComptabiliteManager.deleteEcritureComptable(vComptabiliteManager.getListEcritureComptable().size() - 1);
    }
}
