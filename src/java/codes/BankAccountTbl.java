package codes;
// Generated Sep 14, 2018 2:30:53 AM by Hibernate Tools 4.3.1



/**
 * BankAccountTbl generated by hbm2java
 */
public class BankAccountTbl  implements java.io.Serializable {


     private Integer accountNo;
     private Float balance;
     private String name;
     private String phone;
     private String email;

    public BankAccountTbl() {
    }

	
    public BankAccountTbl(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
    public BankAccountTbl(Float balance, String name, String phone, String email) {
       this.balance = balance;
       this.name = name;
       this.phone = phone;
       this.email = email;
    }
   
    public Integer getAccountNo() {
        return this.accountNo;
    }
    
    public void setAccountNo(Integer accountNo) {
        this.accountNo = accountNo;
    }
    public Float getBalance() {
        return this.balance;
    }
    
    public void setBalance(Float balance) {
        this.balance = balance;
    }
    public void setBalance(Integer balance) {
        this.balance = Float.intBitsToFloat(balance);
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }




}


