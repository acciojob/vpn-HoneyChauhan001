package com.driver.services.impl;

import com.driver.model.Admin;
import com.driver.model.Country;
import com.driver.model.CountryName;
import com.driver.model.ServiceProvider;
import com.driver.repository.AdminRepository;
import com.driver.repository.CountryRepository;
import com.driver.repository.ServiceProviderRepository;
import com.driver.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminRepository adminRepository1;

    @Autowired
    ServiceProviderRepository serviceProviderRepository1;

    @Autowired
    CountryRepository countryRepository1;

    @Override
    public Admin register(String username, String password) {
        Admin admin  = new Admin();
        admin.setUsername(username);
        admin.setPassword(password);
        Admin savedAdmin = adminRepository1.save(admin);
        return savedAdmin;
    }

    @Override
    public Admin addServiceProvider(int adminId, String providerName) {
        Admin admin = adminRepository1.findById(adminId).get();

        ServiceProvider serviceProvider = new ServiceProvider();
        serviceProvider.setName(providerName);
        serviceProvider.setAdmin(admin);

        admin.getServiceProviders().add(serviceProvider);
        Admin savedAdmin = adminRepository1.save(admin);
        return savedAdmin;


    }

    @Override
    public ServiceProvider addCountry(int serviceProviderId, String countryName) throws Exception{
        //add a country under the serviceProvider and return respective service provider
        //country name would be a 3-character string out of ind, aus, usa, chi, jpn. Each character can be in uppercase or lowercase. You should create a new Country object based on the given country name and add it to the country list of the service provider. Note that the user attribute of the country in this case would be null.
        //In case country name is not amongst the above mentioned strings, throw "Country not found" exception
        ServiceProvider serviceProvider = serviceProviderRepository1.findById(serviceProviderId).get();

        String upper_string = countryName.toUpperCase();
        CountryName countryName1;
        if(upper_string == "IND"){
            countryName1 = CountryName.IND;
        }
        else if(upper_string == "AUS"){
            countryName1 = CountryName.AUS;
        }
        else if(upper_string == "USA"){
            countryName1 = CountryName.USA;
        }
        else if(upper_string == "CHI"){
            countryName1 = CountryName.CHI;
        }
        else if(upper_string == "JPN"){
            countryName1 = CountryName.JPN;
        }
        else {
            throw new Exception("Country not found");
        }

        Country country = new Country();
        country.setCountryName(countryName1);
        country.setCode(countryName1.toCode());
        country.setServiceProvider(serviceProvider);

        serviceProvider.getCountryList().add(country);

        ServiceProvider savedServiceProvider = serviceProviderRepository1.save(serviceProvider);

        return savedServiceProvider;
    }

}
