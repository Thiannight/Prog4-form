package com.example.form.service.register;

import com.example.form.entity.Course;
import com.example.form.entity.User;
import com.example.form.entity.dto.RegisterRequest;
import com.example.form.repository.CourseRepository;
import com.example.form.repository.UserRepository;
import jakarta.ws.rs.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationService {
  private final UserRepository userRepository;
  private final CourseRepository courseRepository;

  public String register(RegisterRequest registerRequest) {
    User user = userRepository.findById(registerRequest.getUserId()).orElseThrow();
    Course course = courseRepository.findById(registerRequest.getCourseId()).orElseThrow();
    if (course.getUsers().contains(user)) {
      throw new BadRequestException("User already registered");
    }
    ;
    course.getUsers().add(user);
    courseRepository.save(course);
    return user.getEmail();
  }
}
