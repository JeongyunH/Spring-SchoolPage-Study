package com.sp.fc.user.service;

import com.sp.fc.user.domain.User;
import com.sp.fc.user.service.helper.UserTestHelper;
import com.sp.fc.user.service.helper.WithUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class StudentTest extends WithUserTest {

    User teacher;
    User student;

    @BeforeEach
    void before(){
        prepareUserService();
        this.teacher = this.userTestHelper.createTeacher(school, "teacher1");
        this.student = this.userTestHelper.createStudent(school, teacher, "student1", "1");
    }

    @DisplayName("1. 학생 등록")
    @Test
    void test_1(){
        List<User> studentList = userService.findStudentList();
        assertEquals(1, studentList.size());
        UserTestHelper.assertStudent(school, teacher, studentList.get(0), "student1", "1");
    }

    @DisplayName("2. 선생님Id로 리스트를 가져오면 선생님의 학생이 조회된다")
    @Test
    void test_2(){
        List<User> studentList = userService.findTeacherStudentList(teacher.getUserId());
        assertEquals(1, studentList.size());
        UserTestHelper.assertStudent(school, teacher, studentList.get(0), "student1", "1");
    }

    @DisplayName("3. 학교로 학생이 조회된다.")
    @Test
    void test_3(){
        List<User> studentList = userService.findBySchoolStudentList(school.getSchoolId());
        assertEquals(1, studentList.size());
        UserTestHelper.assertStudent(school, teacher, studentList.get(0), "student1", "1");
    }
}
