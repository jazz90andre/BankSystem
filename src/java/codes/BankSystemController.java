/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codes;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author Andrea
 */
@Named(value = "bankSystemController")
@ManagedBean
@SessionScoped
public class BankSystemController implements Serializable {
    

        private BankAccountHelper helper;
        private BankAccountTbl current;
        DataModel dataCustomer;
        DataModel dataTransaction;

        /**
         * Creates a new instance of BankSystemController
         */        
        public BankSystemController() {
            helper = new BankAccountHelper();
            current = new BankAccountTbl();
        }
    
	private Integer accountNo;
        private String name;
        private String email;
        private String phone;
        private Float balance;
        private Float amount;
        private Date datetime;

	public Integer getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(Integer accountNo) {
		this.accountNo = accountNo;
	}
        
        public String getName() {
		return name;
	}
        public void setName(String name) {
		this.name = name;
	}	
        
        public String getPhone() {
		return phone;
	}
        public void setPhone(String phone) {
		this.phone = phone;
	}	
        
        public String getEmail() {
		return email;
	}
        public void setEmail(String email) {
		this.email = email;
	}
        
        public Float getBalance() {
            return balance;
        }

        public void setBalance(Float balance) {
            this.balance = balance;
        }

        public Float getAmount() {
            return amount;
        }

        public void setAmount(Float amount) {
            this.amount = amount;
        }     
        
        public Date getDatetime() {
            return datetime;
        }

        public void setDatetime(Date datetime) {
            this.datetime = datetime;
        }      
        
        //Valid Input
        public String validData(){
            if(accountNo==null){
                return "index";
            } //else if(helper.existBankAccountNo(accountNo)) {
              //  return "customer";
            //}
            dataCustomer = null;
            return "customer";
        }
        
        //Get Data Customer Full
        public DataModel getDataCustomer() {
            if (dataCustomer == null) {
                dataCustomer = new ListDataModel(helper.getDataCustomer(accountNo));
            }
            return dataCustomer;
        }
        
        //Cari Data History Transaksi Accountt
        public String validTransaction(){
            if(accountNo==null){
                return "Please Input Bank Account No.";
            } 
            dataTransaction = null;
            return "transaction";
        }
        
        //Get Data Transaksi History
        public DataModel getDataTransaction() {
            if (dataTransaction == null) {
                dataTransaction = new ListDataModel(helper.getDataTransaction(accountNo));
            }
            return dataTransaction;
        }
        
        //validNewAccount
        public String validNewAccount(){
            if(!name.equals("") && !email.equals("") && !phone.equals("")){
                accountNo = helper.addNewAccount(name,phone,email);
                return "newaccountsucces";
            }
            return "Please fill the blank column";
        }
        
        //validNewAccount
        public String validDeposit(){
            if(accountNo != null && amount != null && amount!=0){                
                balance = helper.getCurrentBalance(accountNo);
                if(balance+amount <= 0){
                    return "insuffience fund";
                } else {
                    balance = balance+amount;
                    datetime = new java.util.Date();
                    helper.updateBalance(accountNo, balance);
                    if(balance==null){}//sudah update
                    helper.addTransaction(accountNo, amount, balance, datetime);
                    
                    //Send Email Berhasil
                    //SendMail sm = new SendMail();
                    //sm.sendMail(email,"Transaction berhasil");
                    
                    return "depositsucces";
                }
            }
            return "Please fill the blank column Or Amount Column Cannot zero or 0";
        }
    
}
