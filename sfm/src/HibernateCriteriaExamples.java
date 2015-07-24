import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.sfm.model.CompoundFees;
import com.sfm.model.User;
import com.sfm.util.HibernateUtil;


public class HibernateCriteriaExamples {

	
	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	Session session = getSession(sessionFactory);
	Transaction tx = session.beginTransaction();
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// Prep work
		/*int recNo =1;
		for (int i = 1; i <= 4; i++) {			
			System.out.println("-page-"+i+"-");
			List<User> userList = new HibernateCriteriaExamples().listByPage(User.class, i, 3, "firstName", true);
			for (User user : userList) {
				System.out.println(recNo++  +":" + user.getFirstName());
			}
		}*/
		
		 CompoundFees c= new HibernateCriteriaExamples().test();
		 System.out.println(c.getTotalFees());
	}


	protected List listByPage(Class clazz, int pageNumber, int pageSize, String orderBy, boolean direction) {
        String strQry = "from " + clazz.getName() + " order by "+orderBy+" " + getDirection(direction);
 
        Query query = getSession(sessionFactory).createQuery(strQry);
        query.setFirstResult(pageSize * (pageNumber - 1));
        query.setMaxResults(pageSize);
       
        return query.list();
    }
	
	private String getDirection(boolean direction) {
		return direction?"ASC":"DESC";
	}

	
	public static Session getSession(SessionFactory sessionFactory) throws HibernateException {         
		Session sess = null;       
		try {         
			sess = sessionFactory.getCurrentSession();  
		} catch (org.hibernate.HibernateException he) {  
			sess = sessionFactory.openSession();     
		}             
		return sess;
	}
	
	public CompoundFees test(){
		
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
				"inner join User u on u.id=f.userId and u.id="+18 +" "+
				"left outer join (select userId, sum(amount) totalExpenses from charges) ch on f.userId = ch.userId " +
				"group by f.userId order by u.id asc";
		return (CompoundFees) getSession(sessionFactory).createSQLQuery(SQL).addEntity(CompoundFees.class).uniqueResult();
				
	}

}