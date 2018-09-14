/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codes;

import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Andrea
 */
public class BankAccountHelper {

    Session session = null;

    public BankAccountHelper() {
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }
    
    public boolean existBankAccountNo(int accountNo){
        BankAccountTbl name = null;
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        //session = HibernateUtil.getSessionFactory().openSession();
        try{
            try {
                session.beginTransaction();
                Query q = session.createQuery("from BankAccountTbl as ba where ba.accountNo=" + accountNo);
                name = (BankAccountTbl) q.uniqueResult();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(name==null){
                return false;
            } 
            return true;
        } finally {
            session.close();
        }
        
    }
       
    //get Data Customer Full
    public List getDataCustomer(int accountNo) {
        List<BankAccountTbl> baList = null;
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("from BankAccountTbl as ba where ba.accountNo=" + accountNo);
            baList = (List<BankAccountTbl>) q.list();
            //tx.commit(); for update insert delete
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
        }
        return baList;
    }
    
    public List getDataTransaction(int accountNo) {
        List<TransactionTbl> tList = null;
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("from TransactionTbl as t where t.accountNo=" + accountNo);
            tList = (List<TransactionTbl>) q.list();
            //tx.commit(); for update insert delete
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
        }
        return tList;
    }

    public Integer addNewAccount(String name, String phone, String email) {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Integer accountNo = null;
      try {
         tx = session.beginTransaction();
         BankAccountTbl ba = new BankAccountTbl();
         ba.setName(name);
         ba.setPhone(phone);
         ba.setEmail(email);
         ba.setBalance(0);
         accountNo = (Integer) session.save(ba); 
         tx.commit();
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
      return accountNo;
    }
    
    //addTransaction Deposit or Withdraw To transaction_tbl
    public void addTransaction(int accountNo, Float amount, Float balance, Date datetime) {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
      try {
         tx = session.beginTransaction();
         TransactionTbl tt = new TransactionTbl();
         tt.setAccountNo(accountNo);
         tt.setAmount(amount);
         tt.setBalance(balance);
         tt.setDatetime(datetime);
         session.save(tt);
         tx.commit();
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
    }
    
    //get current balance from bank_acount_tbl
    public Float getCurrentBalance(Integer accountNo) {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        BankAccountTbl ba = null;
      try {
         tx = session.beginTransaction();
         Query q = session.createQuery("from BankAccountTbl as ba where ba.accountNo=" + accountNo);
         ba = (BankAccountTbl) q.uniqueResult();
         tx.commit();
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
      return ba.getBalance();
    }
    
    //uodateBalance in Bank Account Tbl
    public void updateBalance(int accountNo, Float balance) {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
      try {
         tx = session.beginTransaction();
         BankAccountTbl ba = (BankAccountTbl)session.get(BankAccountTbl.class, accountNo); 
         ba.setBalance( balance );
	 session.update(ba);
         tx.commit();
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
    }    

}
