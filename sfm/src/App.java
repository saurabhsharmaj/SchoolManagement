
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;

import com.sfm.model.Fees;
import com.sfm.model.User;
import com.sfm.model.UserProfile;
import com.sfm.util.HibernateUtil;
 
public class App {
	public static void main(String[] args) {
 
        System.out.println("Hibernate one to many (Annotation)");
	Session session = HibernateUtil.getSessionFactory().openSession();
 
	session.beginTransaction();

	/*User user = new User();
	user.setFirstName("saurabh");
	user.setMiddleName("kumar");
	user.setLastName("sharma");
	user.setUserName("demo");
	user.setPassword("demo");
	user.setBatch("First");
	user.setSession("2015-16");
	user.setStatus(true);
	user.setAddmissionDate(new Date());
	user.setUpdateBy("saurabh");
	user.setUpdatedOn(new Date());
//        session.save(user);
 
    
     * userId
roleId
gender
imageURL
DOB
email
contactNo
updatedBy
updatedOn

    Userprofile profile = new Userprofile();
    profile.setUser(user);
    profile.setRoleId(1);
    profile.setGender(true);
    profile.setUpdateBy("1");
    profile.setUpdatedOn(new Date());
//    session.save(profile);
//	session.getTransaction().commit();
*/	
    List<User> stlist = session.createQuery("from User u").list();
    for (User user2 : stlist) {
    	Set<Fees> f = user2.getFeeses();
    	System.out.println("f size:"+f.size());
    	for (Fees fees : f) {
			System.out.println(fees.getTotalFees());
		}
    	System.out.println("FEES: "+user2.getFeeses().toString());
		System.out.println(user2.getFirstName());
	}
    
	System.out.println("Done");
	}
}