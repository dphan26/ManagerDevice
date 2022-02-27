package com.example.demo.repository.service;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.repository.datamodel.Asset;
import com.example.demo.repository.datamodel.User;

//import com.csc.gdn.integralpos.underwriting.service.CaseModel;
//import com.csc.gdn.integralpos.underwriting.service.EmailModel;
//import com.csc.gdn.integralpos.underwriting.service.QuotationModel;
//import com.csc.gdn.integralpos.underwriting.service.Underwriting;
//import com.example.demo.repository.datamodel.User;
//import com.fasterxml.jackson.core.JsonParseException;
//import com.fasterxml.jackson.databind.JsonMappingException;

@Service
public interface HomePageService {
	
	List<User> getAllListUsers() throws Exception;
	List<Asset> getAllListAsets() throws Exception;
//	List<Asset> getAllListAsets() throws Exception;
	 List<Asset> findByAccount(String account);
	 List<Asset> findBySite(String site);
	void addAsets(Asset asset) throws Exception;
	void deleteAsset(String id_asset);
	
//	QuotationModel generateUQuotation(Underwriting uwModel, Principal user) throws Exception;
//
//	List<EmailModel> doSendEmail(Underwriting uw, Principal user, String role) throws Exception;
//	
//	CaseModel acceptUW(Underwriting uw, Principal user) throws Exception;
//
//	CaseModel updateUQuotationForCase(Underwriting uwModel, QuotationModel quoModel, Principal user) throws Exception;
//
//	CaseModel getCaseById(Underwriting uwModel, Principal user) throws Exception;
//
//	CaseModel updateCaseById(String docId, CaseModel caseModel, Principal user) throws Exception;
//	
//	Map<String, Object> getFollowUpScreen(String policyNumber) throws JsonParseException, JsonMappingException, IOException;

}
