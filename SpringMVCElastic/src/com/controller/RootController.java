package com.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.Settings;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller

public class RootController {

   @Autowired
   TransportClient transportClient;
   
	@RequestMapping("/")
	public ModelAndView getIndexPage(HttpServletRequest request, HttpServletResponse response){
		System.out.println("hello");
		ModelAndView mav=new ModelAndView();
		mav.setViewName("index");
		System.out.println(request.getServletPath());
		return mav;
	}
	
	
	@RequestMapping("/getWelcome")
	public ModelAndView getWelcomePage(HttpServletRequest request, HttpServletResponse response){
		System.out.println("hello");
		ModelAndView mav=new ModelAndView();
		mav.setViewName("welcome");
		List<Settings> al=fetchRefreshInterval();
		   System.out.println(al);
		
		   System.out.println("Hashcode at controller class:"+transportClient.hashCode());
		
		System.out.println(request.getServletPath());
		return mav;
	}
	
	 public List<Settings> fetchRefreshInterval() {
			List<Settings> refreshList =new ArrayList<>();
			ObjectMapper objectMapper=new ObjectMapper();
			try {
				SearchRequestBuilder prepareSearch =transportClient.prepareSearch("settingsdata");
				prepareSearch.setTypes("settings");
				SearchResponse searchResponse = prepareSearch.get();
				SearchHit[] hits = searchResponse.getHits().getHits();
				for(SearchHit hit:hits){
					refreshList.add(objectMapper.readValue(hit.getSourceAsString(),Settings.class));
					} 
				
				}catch(Exception e){
					e.printStackTrace();
				}
			System.out.println("refreshList======="+refreshList);
			return refreshList;
		}
}
