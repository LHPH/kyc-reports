package com.kyc.reports.mock.jasper;

import com.kyc.reports.model.ServiceRequest;

import java.util.ArrayList;
import java.util.List;

public class ListServiceFactory {

    public static List<ServiceRequest> generateCollection(){

        List<ServiceRequest> list = new ArrayList<>();
        list.add(new ServiceRequest(1,"SERVICE 1",1000.0D));
        //list.add(new ServiceRequest(2,"SERVICE 2",500.00));
        //list.add(new ServiceRequest(3,"SERVICE 3",200.00));

        return list;
    }
}
