package com.example.demo.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.constant.Const;
import com.example.demo.entity.Device;
import com.example.demo.form.ConditionSearchForm;

//https://www.baeldung.com/spring-data-criteria-queries
//https://stackoverflow.com/questions/18069449/criteriabuilder-and-criteriabuilder-or-how-to
//https://docs.jboss.org/hibernate/entitymanager/3.5/reference/en/html/querycriteria.html#querycriteria-from-root
//https://docs.oracle.com/cd/E19798-01/821-1841/gjitv/index.html

@Repository
public class SearchRepository {

	@Autowired
	EntityManager em;;

	public List<Device> findBooksByAuthorNameAndTitle(ConditionSearchForm conditionSearchForm) {

		String categoryId = conditionSearchForm.getCategoryId();
		String version = conditionSearchForm.getVersion();
		String deviceIdOrName = conditionSearchForm.getDeviceIdOrName();
		String site = conditionSearchForm.getSite();
		String statusId = conditionSearchForm.getStatus();
		String bookerId = conditionSearchForm.getBookerId();
		
		Date borrowTime = conditionSearchForm.getBorrowedTime();
		Date returnedTime = conditionSearchForm.getReturnedTime();

		/** CriteriaBuilder instance **/
		CriteriaBuilder cb = em.getCriteriaBuilder();
		/** create a criteriaQuery Object **/
		CriteriaQuery<Device> cq = cb.createQuery(Device.class);
		/** create a Root Object -> references to the queried entity **/
		Root<Device> root = cq.from(Device.class);

		List<Predicate> predicates = new ArrayList<>();
		// search by category
		if (StringUtils.isNotBlank(categoryId)) {
			predicates.add(cb.equal(root.get("category").get("id"), categoryId));
		}
		// search by version
		if (StringUtils.isNotBlank(version)) {
			predicates.add(cb.equal(root.get("version"), version));

		}
		// search by deviceID or deviceName
		if (StringUtils.isNotBlank(deviceIdOrName)) {
			predicates.add(cb.or(cb.equal(root.get("id"), deviceIdOrName),
					(cb.like(root.get("deviceName"), "%" + deviceIdOrName + "%"))));
		}
		// search by site
		if (StringUtils.isNotBlank(site)) {
			predicates.add(cb.equal(root.get("site"), site));
		}
		// not start -> insert the same status to DB

		// search by status
		if (StringUtils.isNotBlank(statusId)) {
			String statusName = Const.LIST_STATUS_MAP.get(Integer.valueOf(statusId));
			predicates.add(cb.equal(root.get("status"), statusName));
		}
		// search by user(booker)
		if (StringUtils.isNotBlank(bookerId)) {
			predicates.add(cb.equal(root.get("booker").get("id"), bookerId));
		}
		
		// search by borrowedTime or returnedTime returnedTime
		if (borrowTime!=null && returnedTime != null) {
			predicates.add(cb.and(cb.greaterThanOrEqualTo(root.<Date>get("borrowedTime"), borrowTime),
					cb.lessThanOrEqualTo(root.<Date>get("returnedTime"), returnedTime)));
		} 
		else if (borrowTime!=null && returnedTime == null) {
			predicates.add(cb.greaterThanOrEqualTo(root.<Date>get("borrowedTime"), borrowTime));
		} else if (returnedTime!=null && borrowTime == null) {
			predicates.add(cb.lessThanOrEqualTo(root.<Date>get("returnedTime"), returnedTime));
		}
	

		cq.where(predicates.toArray(new Predicate[0]));

		List<Device> lstDevice = em.createQuery(cq).getResultList();
		return lstDevice;
	}

}
