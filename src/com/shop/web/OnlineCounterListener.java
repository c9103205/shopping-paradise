package com.shop.web;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.shop.model.OnlineCounter;

@WebListener()
public class OnlineCounterListener implements HttpSessionListener {
	
	@Override
    public void sessionCreated(HttpSessionEvent se)  { 
    	System.out.println("raise in listener");
    	OnlineCounter.raise();
    }

	@Override
    public void sessionDestroyed(HttpSessionEvent se)  { 
    	System.out.println("reduce in listener");
    	OnlineCounter.reduce();
    }
	
}
