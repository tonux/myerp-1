package com.dummy.myerp.business.impl.manager;

import com.dummy.myerp.business.contrat.BusinessProxy;
import com.dummy.myerp.business.impl.TransactionManager;
import com.dummy.myerp.consumer.dao.contrat.DaoProxy;
import com.dummy.myerp.consumer.dao.impl.DaoProxyImpl;
import com.dummy.myerp.consumer.dao.impl.db.dao.ComptabiliteDaoImpl;
import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.JournalComptable;
import com.dummy.myerp.model.bean.comptabilite.LigneEcritureComptable;
import com.dummy.myerp.technical.exception.FunctionalException;
import com.dummy.myerp.technical.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.Validator;
import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

public class ComptabiliteManagerImplTest {

    class ComptabiliterManagerMock extends ComptabiliteManagerImpl {
        @Override
        protected BusinessProxy getBusinessProxy() {
            return super.getBusinessProxy();
        }

        @Override
        protected DaoProxy getDaoProxy() {
            return super.getDaoProxy();
        }

        @Override
        protected TransactionManager getTransactionManager() {
            return super.getTransactionManager();
        }

        @Override
        protected Validator getConstraintValidator() {
            return super.getConstraintValidator();
        }
    }

    private EcritureComptable vEcritureComptable;

    private ComptabiliteManagerImpl vComptabiliteManager;

    private ComptabiliterManagerMock vComptabiliteManagerMock;
    private ComptabiliterManagerMock vComptabiliteManagerSpy;

    private ComptabiliteDaoImpl vComptabiliteDaoMock;
    private ComptabiliteDaoImpl vComptabiliteDaoSpy;

    private DaoProxyImpl vDaoProxyMock;
    private DaoProxyImpl vDaoProxySpy;

    @BeforeEach
    public void setUpBeforeEach() {
        vComptabiliteManagerMock = mock(ComptabiliterManagerMock.class);
        vComptabiliteManagerSpy = spy(vComptabiliteManagerMock);

        vComptabiliteDaoMock = mock(ComptabiliteDaoImpl.class);
        vComptabiliteDaoSpy = spy(vComptabiliteDaoMock);

        vDaoProxyMock = mock(DaoProxyImpl.class);
        vDaoProxySpy = spy(vDaoProxyMock);

        vComptabiliteManager = new ComptabiliteManagerImpl();

        vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptable.setDate(new Date());
        vEcritureComptable.setLibelle("Libelle");
        vEcritureComptable.setReference("AC-2019/00001");
    }

    @Test
    public void checkEcritureComptableUnit() throws Exception {
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                null, new BigDecimal(123),
                null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
                null, null,
                new BigDecimal(123)));

        vComptabiliteManager.checkEcritureComptableUnit(vEcritureComptable);
    }

    @Test
    public void checkEcritureComptableContext() throws NotFoundException {
        assertDoesNotThrow(() -> vComptabiliteManagerSpy.checkEcritureComptableContext(vEcritureComptable));
    }

    @Test
    public void checkEcritureComptableContextWithExistingRefWithoutId() throws FunctionalException, NotFoundException {
        EcritureComptable vEcritureComptableSample = new EcritureComptable();
        vEcritureComptableSample.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptableSample.setDate(new Date());
        vEcritureComptableSample.setLibelle("Libelle");
        vEcritureComptableSample.setReference("AC-2019/00001");

        assertThrows(FunctionalException.class, () ->  {
            vComptabiliteManager.checkEcritureComptableContext(vEcritureComptableSample);
        });
    }

    @Test
    public void checkEcritureComptableContextWithExistingRef() throws FunctionalException, NotFoundException {
        vEcritureComptable.setId(1);
        EcritureComptable vEcritureComptableSample = new EcritureComptable();
        vEcritureComptableSample.setId(1);
        vEcritureComptableSample.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptableSample.setDate(new Date());
        vEcritureComptableSample.setLibelle("Libelle");
        vEcritureComptableSample.setReference("AC-2019/00001");


        assertThrows(FunctionalException.class, () -> vComptabiliteManager.checkEcritureComptableContext(vEcritureComptableSample));
    }

    @Test
    public void checkEcritureComptableUnitViolation() throws Exception {
        assertThrows(FunctionalException.class, () -> vComptabiliteManager.checkEcritureComptableUnit(vEcritureComptable));
    }

    @Test
    public void checkEcritureComptableUnitRG2() throws Exception {
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                null, new BigDecimal(123),
                null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
                null, null,
                new BigDecimal(1234)));

        assertThrows(FunctionalException.class, () -> vComptabiliteManager.checkEcritureComptableUnit(vEcritureComptable));
    }

    @Test
    public void checkEcritureComptableUnitRG3() throws Exception {
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                null, new BigDecimal(123),
                null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                null, new BigDecimal(123),
                null));

        assertThrows(FunctionalException.class, () -> vComptabiliteManager.checkEcritureComptableUnit(vEcritureComptable));
    }

    @Test
    public void checkEcritureComptableUnitRG3_NombreLignes() throws Exception {
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                null, new BigDecimal(123),
                null));

        assertThrows(FunctionalException.class, () -> vComptabiliteManager.checkEcritureComptableUnit(vEcritureComptable));
    }

    @Test
    public void checkEcritureComptableUnitRG5WithWrongYear() throws Exception {
        vEcritureComptable.setReference("AAC-2018/00001");

        vComptabiliteManagerMock.addReference(vEcritureComptable);

        assertThrows(FunctionalException.class, () -> vComptabiliteManager.checkEcritureComptableUnit(vEcritureComptable));
    }

    @Test
    public void checkEcritureComptableUnitRG5WithWrongCode() throws Exception {
        vEcritureComptable.setReference("AAC-2019/00001");

        vComptabiliteManagerMock.addReference(vEcritureComptable);

        assertThrows(FunctionalException.class, () -> vComptabiliteManager.checkEcritureComptableUnit(vEcritureComptable));
    }

    @Test
    public void checkAddReferenceAsFirst() throws Exception {
        vEcritureComptable.setReference(null);

        vComptabiliteManager.addReference(vEcritureComptable);

        assertEquals("AC-2019/00001", vEcritureComptable.getReference());
    }

    @Test
    public void checkAddReferenceAsNew() throws Exception {
        EcritureComptable vNewEcritureComptable = new EcritureComptable();
        vNewEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        vNewEcritureComptable.setDate(new Date());
        vNewEcritureComptable.setLibelle("Libelle");

        vComptabiliteManager.addReference(vNewEcritureComptable);

        assertEquals("AC-2019/00001", vNewEcritureComptable.getReference());
    }

//    @Test
//    public void insertEcritureComptableTest() throws FunctionalException {
//        DefaultTransactionStatus vTransactionStatus = mock(DefaultTransactionStatus.class);
//        TransactionManager vTransactionManagerMock = mock(TransactionManager.class);
//
//        doReturn(vTransactionManagerMock).when(vComptabiliteManagerMock).getTransactionManager();
//
//        doReturn(vDaoProxySpy).when(vComptabiliteManagerSpy).getDaoProxy();
//
//        doReturn(vComptabiliteDaoSpy).when(vDaoProxySpy).getComptabiliteDao();
//
//        doNothing().when(vTransactionManagerMock).commitMyERP(vTransactionStatus);
//
//        doNothing().when(vComptabiliteManagerSpy).checkEcritureComptableContext(any(EcritureComptable.class));
//        doNothing().when(vComptabiliteDaoSpy).insertEcritureComptable(any(EcritureComptable.class));
//        doNothing().when(vComptabiliteManagerSpy).insertEcritureComptable(any(EcritureComptable.class));
//
//        vComptabiliteManagerSpy.insertEcritureComptable(vEcritureComptable);
//
//        verify(vComptabiliteManagerSpy).insertEcritureComptable(any(EcritureComptable.class));
//    }

//    @Test
//    public void updateEcritureComptableTest() throws FunctionalException {
//        DefaultTransactionStatus vTransactionStatus = mock(DefaultTransactionStatus.class);
//        TransactionManager vTransactionManagerMock = mock(TransactionManager.class);
//
//        doReturn(vTransactionManagerMock).when(vComptabiliteManagerMock).getTransactionManager();
//
//        when(vTransactionManagerMock.beginTransactionMyERP()).thenReturn(vTransactionStatus);
//
//        doReturn(vDaoProxySpy).when(vComptabiliteManagerSpy).getDaoProxy();
//
//        doReturn(vComptabiliteDaoSpy).when(vDaoProxySpy).getComptabiliteDao();
//
//        doNothing().when(vTransactionManagerMock).commitMyERP(vTransactionStatus);
//
//        doNothing().when(vComptabiliteDaoSpy).updateEcritureComptable(any(EcritureComptable.class));
//        doNothing().when(vComptabiliteManagerSpy).updateEcritureComptable(any(EcritureComptable.class));
//
//        vComptabiliteManagerSpy.updateEcritureComptable(vEcritureComptable);
//
//        verify(vComptabiliteManagerSpy).updateEcritureComptable(any(EcritureComptable.class));
//    }

//    @Test
//    public void deleteEcritureComptableTest() throws FunctionalException {
//        DefaultTransactionStatus vTransactionStatus = mock(DefaultTransactionStatus.class);
//        TransactionManager vTransactionManagerMock = mock(TransactionManager.class);
//
//        doReturn(vTransactionManagerMock).when(vComptabiliteManagerMock).getTransactionManager();
//
//        when(vTransactionManagerMock.beginTransactionMyERP()).thenReturn(vTransactionStatus);
//
//        doReturn(vDaoProxySpy).when(vComptabiliteManagerSpy).getDaoProxy();
//
//        doReturn(vComptabiliteDaoSpy).when(vDaoProxySpy).getComptabiliteDao();
//
//        doNothing().when(vTransactionManagerMock).commitMyERP(vTransactionStatus);
//
//        doNothing().when(vComptabiliteDaoSpy).deleteEcritureComptable(anyInt());
//        doNothing().when(vComptabiliteManagerSpy).deleteEcritureComptable(anyInt());
//
//        vComptabiliteManagerSpy.deleteEcritureComptable(0);
//
//        verify(vComptabiliteManagerSpy).deleteEcritureComptable(anyInt());
//    }
}
