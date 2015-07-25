package com.sfm.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sfm.model.Charges;
import com.sfm.model.CompoundExpenses;
import com.sfm.model.CompoundFees;
import com.sfm.model.Fees;
import com.sfm.model.User;

@Repository
public class DaoImpl<T, PK extends Serializable> implements Dao {

	@Autowired
	private SessionFactory sessionFactory;

	//private Class<T> clazz;


	@Override
	public void add(Object t) {
		getSession().saveOrUpdate(t);		
	}

	@Override
	public void update(Object t) {
		getSession().update(t);		
	}

	@Override
	public List list(Class clazz) {
		return getSession().createQuery( "from " + clazz.getName() +" order by updatedOn desc" ).list();
	}
	
	protected List listByPage(Class clazz, int firstResult, int maxResult, String orderBy, boolean direction) {
        String strQry = "from " + clazz.getName() + " order by "+orderBy+" " + getDirection(direction);
 
        Query query = this.sessionFactory.getCurrentSession().createQuery(strQry);
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResult);
 
        return query.list();
    }
	
	

	private String getDirection(boolean direction) {
		return direction?"ASC":"DESC";
	}

	@Override
	public Object getById(Integer id, Class clazz) {
		return (T) getSession().get( clazz, id );
	}


	@Override
	public void remove(Object t) {
		getSession().delete(t);		
	}

	@Override
	public List list(Integer deptId, Integer year, Integer semester,
			Class clazz) {
		String sql ;
		StringBuffer buffer = new StringBuffer();
		if(deptId != 0){
			buffer.append("department = "+ deptId);
		}

		if(year != 0){
			if(StringUtils.isNotBlank(buffer.toString())){
				buffer.append("year = "+ year);
			}else {
				buffer.append("AND year = "+ year);
			}
		}

		if(semester !=0){
			if(StringUtils.isNotBlank(buffer.toString())){
				buffer.append("semester = "+ semester);
			} else {
				buffer.append("AND semester = "+ semester);
			}
		}
		if(StringUtils.isNotBlank(buffer.toString())){
			sql  = "from "+ clazz.getName() + " where " + buffer.toString();
		} else {
			sql  = "from "+ clazz.getName();
		}
		return getSession().createQuery( sql ).list();
	}

	@Override
	public List listFees() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CompoundFees getCompoundFees(Integer id) {
		String SQL="Select u.id id," +
				"concat(u.firstName,' ',u.middleName,' ',u.lastname) fullName, " +
				"u.fatherName fatherName,"+
				"totalFees, " +
				"count(noOfInstallment)," +
				"sum(paidFees) totalPaidFees," +
				"sum(additionCharges) totalAdditionCharges," +
				"totalFees+ totalExpenses-(sum(paidFees) + sum(additionCharges)) totalPendingFees," +
				"nextPaymentDueDate nextDueDate, " +
				"totalExpenses  " +
				"from " +
				"fees f " +
				"inner join User u on u.id=f.userId and u.id="+id +" "+
				"left outer join (select userId, sum(amount) totalExpenses from charges) ch on f.userId = ch.userId " +
				"group by f.userId order by u.id asc";
		return (CompoundFees) getSession().createSQLQuery(SQL).addEntity(CompoundFees.class).uniqueResult();
	}
	
	@Override
	public List<CompoundFees> listCompoundFees() {
		String SQL="Select u.id id," +
				"concat(u.firstName,' ',u.middleName,' ',u.lastname) fullName, " +
				"u.fatherName fatherName,"+
				"totalFees, " +
				"count(noOfInstallment)," +
				"sum(paidFees) totalPaidFees," +
				"sum(additionCharges) totalAdditionCharges," +
				"totalFees+ totalExpenses-(sum(paidFees) + sum(additionCharges)) totalPendingFees," +
				"nextPaymentDueDate nextDueDate, " +
				"totalExpenses  " +
				"from " +
				"fees f " +
				"inner join User u on u.id=f.userId " +
				"left outer join (select userId, sum(amount) totalExpenses from charges) ch on f.userId = ch.userId " +
				"group by f.userId order by u.id asc";
		return getSession().createSQLQuery(SQL).addEntity("f", CompoundFees.class).list();
	}

	@Override
	public List<CompoundExpenses> listCompoundExpenses() {
		String SQL= "Select " +
				"c.userId id , " +
				"concat(u.firstName,' ',u.middleName,' ',u.lastName) fullName ," +
				"u.fatherName, sum(c.Amount) totalAmount " +
				"from " +
				"charges c " +
				"left outer join user u on c.userId = u.id " +
				"group by userId order by userId asc;";
		return getSession().createSQLQuery(SQL).addEntity("f", CompoundExpenses.class).list();
	}

	private Session getSession() {
		try{
			return sessionFactory.getCurrentSession();
		}catch(Exception ex){
			return sessionFactory.openSession();
		}
	}

	@Override
	public List<Charges> getChargesByUserId(Integer userId) {
		Criteria criteria = getSession().createCriteria(Charges.class);
		criteria.add(Expression.eq("user",(User)getById(userId,User.class)));
		criteria.addOrder(Order.asc("updatedOn"));
		return criteria.list();
	}

	@Override
	public  List<User>  listUsersByName(String userName) {
		 Criteria criteria = getSession().createCriteria(User.class);
		 criteria.add(Restrictions.ilike("firstName", userName, MatchMode.START));
		 return criteria.list();
	}

	@Override
	public List<Fees> listFeesByUserId(Integer userId) {
		Criteria criteria = getSession().createCriteria(Fees.class);
		criteria.add(Expression.eq("user",(User)getById(userId,User.class)));
		criteria.addOrder(Order.asc("updatedOn"));
		return criteria.list();
	
	}

	@Override
	public List<Object[]> getUserByNextPaymentDate() {
		String SQL = "Select * from User u right outer join" +
				" (select * from fees where nextPaymentDueDate BETWEEN  now() and DATE_ADD( now(), INTERVAL 1 month )" +
				" group by userId order by nextPaymentDueDate) f" +
				" on u.id = f.userId";
		
		return getSession().createSQLQuery(SQL).addEntity("u",User.class).addEntity("f",Fees.class).list();
	}

	@Override
	public User validateUser(String username, String password) {
		Criteria criteria = getSession().createCriteria(User.class);
		criteria.add(Expression.eq("userName",username));
		criteria.add(Expression.eq("password",password));		
		return (User)criteria.uniqueResult();
	}	
}
