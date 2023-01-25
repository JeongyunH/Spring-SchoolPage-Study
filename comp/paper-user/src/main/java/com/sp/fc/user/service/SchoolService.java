package com.sp.fc.user.service;

import com.sp.fc.user.domain.School;
import com.sp.fc.user.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class SchoolService {

    private final SchoolRepository schoolRepository;

    // 저장할 때마다 시간 업뎃(최초면 created, 아니면 updated)
    public School save(School school){
        if(school.getSchoolId() == null){
            school.setCreated(LocalDateTime.now());
        }
        school.setUpdated(LocalDateTime.now());
        return schoolRepository.save(school);
    }

    // 학교가 없을수도 있으니까 Optional로..
    public Optional<School> updateName(Long schoolId, String name){
        return schoolRepository.findById(schoolId).map(school -> {
            school.setName(name);
            schoolRepository.save(school);
            return school;
        });
    }

    public List<String> cities(){
        return schoolRepository.getCities();
    }

    public List<School> findAllByCity(String city) {
        return schoolRepository.findAllByCity(city);
    }

}
