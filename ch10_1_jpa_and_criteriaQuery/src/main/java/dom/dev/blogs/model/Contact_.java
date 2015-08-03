package dom.dev.blogs.model;

import java.util.Date;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * 
 * @author zheka
 * 
 * Метамодель сущностного класса
 * Данная метамодель используется в строго типизированных API-интерфейсах критериев.
 * Передаваемый в запрос критерий основывается на метамодели отображенных сущностных классов.
 * В результате каждый указанный критерий является строго типизированным,
 * и ошибки обнаруживаются на этапе компиляции, а не во время выполнения
 * Типизированный API-интерфейс критериев JPA 2
 *
 */
@StaticMetamodel(Contact.class)
public abstract class Contact_ {
	public static volatile SingularAttribute<Contact, Long> id;
	public static volatile SetAttribute<Contact, ContactTelDetail> contactTelDetails;
	public static volatile SingularAttribute<Contact, String> lastName;
	public static volatile SingularAttribute<Contact, Date> birthDate;
	public static volatile SetAttribute<Contact, Hobby> hobbies;
	public static volatile SingularAttribute<Contact, String> firstName;
	public static volatile SingularAttribute<Contact, Integer> version;
}