package com.example.demo.Services;

import com.example.demo.Models.Office;
import com.example.demo.Repositories.OfficeRepository;
import com.example.demo.UtilityClasses.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfficeService {

    @Autowired
    private OfficeRepository officeRepository;

    public Office registerOffice(Office office){
        String generatedID;
        do {
            generatedID= IdGenerator.generateOfficeID();
        }while (officeRepository.existsById(generatedID));
        office.setOfficeCode(generatedID);
        return officeRepository.save(office);
    }


    public List<Office> fetchAllOffices(){
       return officeRepository.findAll();
    }

    public Optional<Office> getOfficeById(String officeCode){
        return officeRepository.findById(officeCode);
    }

    public Optional<Office> deleteOffice(String officeCode){
        Optional<Office> office=officeRepository.findById(officeCode);
        if (office.isPresent()){
            officeRepository.deleteById(officeCode);
            return office;
        }
        return Optional.empty();
    }
}
