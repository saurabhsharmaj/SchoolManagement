--drop table payment;
--drop table charges;
--drop table fees;
--drop table userProfile;
--drop Table User;

-- User 
Create Table User(
id int(11) NOT NULL AUTO_INCREMENT
,firstName varchar(50)
,middleName varchar(50)
,lastName varchar(50)
,userName varchar(50)
,password varchar(50)
,Batch varchar(25)
,session varchar(10)
,addmissionDate TIMESTAMP 
,status boolean
,updateBy varchar(50)
,updatedOn datetime
,PRIMARY KEY (id));


-- userProfile
Create Table userProfile(
id int(11) NOT NULL AUTO_INCREMENT
,userId int(11) NOT NULL
,roleId int(2) NOT NULL
,gender boolean
,imageURL varchar(100)
,dob timestamp
,email varchar(25)
,contactno varchar(15)
,addmissionDate TIMESTAMP 
,status boolean
,updateBy varchar(50)
,updatedOn TIMESTAMP 
,FOREIGN KEY (userId) REFERENCES User(id) ON DELETE CASCADE
,PRIMARY KEY (id));

-- role
Create Table role(
id int(11) NOT NULL AUTO_INCREMENT
,roleName varchar(50)
,description varchar(100)
,status boolean
,updateBy varchar(50)
,updatedOn TIMESTAMP 
,PRIMARY KEY (id));

-- Fees
Create Table fees(
id int(11) NOT NULL AUTO_INCREMENT
,userId int(11) NOT NULL
,totalFees DECIMAL(11,2) default 0.0
,noOfInstallment int(3) default 1
,paidFees DECIMAL(11,2) default 0.0
,pendingFees DECIMAL(11,2) default 0.0
,additionCharges DECIMAL(11,2) default 0.0
,nextPaymentDueDate timestamp
,updateBy varchar(50)
,updatedOn TIMESTAMP 
,FOREIGN KEY (userId) REFERENCES User(id) ON DELETE CASCADE
,PRIMARY KEY (id));

-- charges
Create Table charges(
id int(11) NOT NULL AUTO_INCREMENT
,userId int(11) NOT NULL
,ExpenseType int(2)
,Description varchar(100)
,Amount DECIMAL(11,2) default 0.0
,updateBy varchar(50)
,updatedOn TIMESTAMP 
,FOREIGN KEY (userId) REFERENCES User(id) ON DELETE CASCADE
,PRIMARY KEY (id));

-- payment
Create Table payment(
id int(11) NOT NULL AUTO_INCREMENT
,userId int(11) NOT NULL
,Amount DECIMAL(11,2) default 0.0
,updateBy varchar(50)
,updatedOn TIMESTAMP 
,FOREIGN KEY (userId) REFERENCES User(id) ON DELETE CASCADE
,PRIMARY KEY (id));