package com.sfm.dao;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sfm.model.Charges;
import com.sfm.model.CompoundExpenses;
import com.sfm.model.CompoundFees;
import com.sfm.model.Data;
import com.sfm.model.Fees;
import com.sfm.model.User;
import com.sfm.util.JQueryDataTableParamModel;

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

	@Override
	public int listCount(Class clazz) {
		Criteria criteria = getSession().createCriteria(clazz.getName());
		Object result = criteria.setProjection(Projections.rowCount()).uniqueResult();	 
		return (null == result) ? 0 : ((Number) result).intValue();
	}

	private int listCountParam(JQueryDataTableParamModel param, Class clazz){
		String extraParam = getSearchCreteria(param)+ getOrderBy(param);
		String strQry = "select count(*) from " + clazz.getName() + " "+ extraParam ;
		
		Query query = getSession().createQuery(strQry);
		Long count = (Long) query.uniqueResult();
		return count.intValue();
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
	public Data list(JQueryDataTableParamModel param, Class clazz) {
		String extraParam = getSearchCreteria(param)+ getOrderBy(param);
		String strQry = "from " + clazz.getName() + " "+ extraParam ;

		Query query = getSession().createQuery(strQry);
		query.setFirstResult(param.getiDisplayStart());
		query.setMaxResults(param.getiDisplayLength());		
		List list = query.list(); 
		return new Data(list,listCountParam(param, clazz),list.size()); // list - display record - total record
	}

	private String getSearchCreteria(JQueryDataTableParamModel param) {
		StringBuffer searchStr = new StringBuffer();
		if(StringUtils.isNotEmpty(param.getSearchTerm())){
			searchStr.append(" WHERE ");
			String []cols = null;
			if(param.getsColumns().length() > 0 ){
				cols = param.getsColumns().split(",");	
			}
			for (int index = 0 ; index < cols.length ; index++) {
				if(cols.length -1 == index){
					searchStr .append(cols[index]+" like '%"+param.getSearchTerm()+"%'");
				} else {
					searchStr .append(cols[index]+" like '%"+param.getSearchTerm()+"%'").append(" OR ");
				}
			}
		}
		return searchStr.toString();
	}

	private String getOrderBy(JQueryDataTableParamModel param) {
		String []cols = null;
		if(param.getsColumns().length() > 0 ){
			cols = param.getsColumns().split(",");	
		}
		int index = param.getiSortColumnIndex();
		return (cols!=null && cols.length > 0)?" order by " + cols[index] +" "+ param.getsSortDirection(): "";
	}

	@Override
	public List listFees() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CompoundFees getCompoundFeesByUserId(Integer id) {
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
	public Data listCompoundFees(JQueryDataTableParamModel param) {

		String searchParam = param != null? getSearchCreteria(param):"";
		String orderByParam = param != null? getOrderBy(param):"";
		String SQL="SELECT * FROM (Select u.id id," +
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
				"group by f.userId ) a " +searchParam + orderByParam;
		System.out.println(SQL);
		SQLQuery query = getSession().createSQLQuery(SQL);
		if(param != null){
			System.out.println("search with: "+param.getiDisplayStart()+" - "+param.getiDisplayLength() );
			query.setFirstResult(param.getiDisplayStart());
			query.setMaxResults(param.getiDisplayLength());	
		}
		List list = query.addEntity("f", CompoundFees.class).list();
		System.out.println("list size: "+list.size());
		return new Data(list,countParam(param),list.size());
	}

	private int countParam(JQueryDataTableParamModel param) {
		String searchParam = param != null? getSearchCreteria(param):"";
		String orderByParam = param != null? getOrderBy(param):"";
		String SQL="SELECT count(distinct id) FROM (Select u.id id," +
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
				"group by f.userId ) a " +searchParam + orderByParam;
		System.out.println(SQL);
		BigInteger count = (BigInteger)getSession().createSQLQuery(SQL).uniqueResult();
		System.out.println("total:"+count);
		return count.intValue();
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
