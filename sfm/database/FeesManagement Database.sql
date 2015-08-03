--drop table payment;
--drop table charges;
--drop table fees;
--drop table userProfile;
--drop Table User;
--drop Table role;
--drop Table databaseBackup;

-- User 
Create Table User(
id int(11) NOT NULL AUTO_INCREMENT
,firstName varchar(50)
,middleName varchar(50)
,lastName varchar(50)
,fatherName varchar(150)
,userName varchar(50)
,password varchar(50)
,Batch varchar(25)
,session varchar(10)
,addmissionDate TIMESTAMP 
,status int
,studentFees DECIMAL(11,2) default 0.0
,updateBy varchar(50)
,updatedOn datetime
,PRIMARY KEY (id));

-- ALTER TABLE `sfm`.`user`  ADD COLUMN `studentFees` DOUBLE NULL DEFAULT 0.0 AFTER `fatherName`;
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
,stream integer(2)
,percentage varchar(5)
,marksInEnglish int(5)
,groupType int(2)
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

Create Table faculty(
id int(11) NOT NULL AUTO_INCREMENT
,facultyName varchar(50)
,facultyHourlyRate DECIMAL(11,2) default 0.0
,subject varchar(50)
,updateBy varchar(50)
,updatedOn TIMESTAMP 
,PRIMARY KEY (id)
);

Create Table attendance(
id int(11) NOT NULL AUTO_INCREMENT
,facultyId int(11) NOT NULL
,attendanceDate TIMESTAMP
,noOfHours DECIMAL(11,2) default 0.0
,FOREIGN KEY (facultyId) REFERENCES faculty(id) ON DELETE CASCADE
,PRIMARY KEY (id)
,updateBy varchar(50)
,updatedOn TIMESTAMP 
);

-- Database backup
Create Table databaseBackup(
id int(11) NOT NULL AUTO_INCREMENT,
lastBackUpDate TIMESTAMP
);