package com.example.demo.repository;

import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.Remove;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.repository.datamodel.Asset;

@Repository
public interface AssetsRepository extends JpaRepository<Asset, String> {

	List<Asset> findByAccount(String account);

	List<Asset> findBySite(String site);

	

}
