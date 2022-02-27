package com.example.demo.repository.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.AssetsRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.datamodel.Asset;
import com.example.demo.repository.datamodel.User;


@Service
public class HomePageServiceImpl implements HomePageService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AssetsRepository assetsRepository;

	@Override
	public List<User> getAllListUsers() throws Exception {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public List<Asset> getAllListAsets() throws Exception {
		// TODO Auto-generated method stub
		return assetsRepository.findAll();
	}

	@Override
	public void addAsets(Asset asset) throws Exception {
		// TODO Auto-generated method stub
		 assetsRepository.save(asset);
		
	}

	@Override
	public List<Asset> findByAccount(String account) {
		// TODO Auto-generated method stub
		return assetsRepository.findByAccount(account);
	}

	@Override
	public List<Asset> findBySite(String site) {
		// TODO Auto-generated method stub
		return assetsRepository.findBySite(site);
	}

	@Override
	public void deleteAsset(String id_asset) {
		// TODO Auto-generated method stub
		assetsRepository.deleteById(id_asset);
		
	}

	
}
