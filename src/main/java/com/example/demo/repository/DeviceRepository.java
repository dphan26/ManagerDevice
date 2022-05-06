package com.example.demo.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Device;

@Repository
public interface DeviceRepository extends JpaRepository<Device, String> {

//	@Query(value = "select d from Device d JOIN d.category c JOIN d.booker b")
//	List<Device> getAllDevice();

	//@Query(value = "select d from Device d JOIN d.category c JOIN d.booker b where d.id IN :ids")
	@Query(value = "select d from Device d where d.id IN :ids")
	List<Device> getListInforBooking(@Param("ids") List<String> ids);

	@Transactional
	@Modifying
    @Query("update Device u set u.status = :status, u.borrowedTime = :borrowedTime, "
    		+ "u.returnedTime = :returnedTime, u.remark = :remark where u.id = :id")
    void updateInforBooking(@Param(value = "id") String id, @Param(value = "status") String status,
    		@Param(value = "borrowedTime") Date borrowedTime,
    		@Param(value = "returnedTime") Date returnedTime,
    		@Param(value = "remark") String remark);

	@Query(value = "select d.id from Device d")
	List<String> getAllDevicesID(); 

}
