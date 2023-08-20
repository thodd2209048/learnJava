package tho.dd.crub;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private static StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public List<Student> getStudent() {
        return studentRepository.findAll();
    }

    public void addStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("Email taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        boolean exists = studentRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Student with id " + id + " does not exists");
        }
        studentRepository.deleteById(id);
    }

//    @Transactional
//    public void updateStudent(Long id, String name, String email) {
//        Student student = studentRepository.findById(id)
//                .orElseThrow(() -> (
//                        new IllegalStateException("Student with id " + id + " does not exists")
//                ));
//
//        if (name != null &&
//                !name.isEmpty() &&
//                Objects.equals(student.getName(), name)) {
//            student.setName(name);
//        }
//
//        if (email != null &&
//                !email.isEmpty() &&
//                Objects.equals(email, student.getEmail())
//        ) {
//            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
//            if (studentOptional.isPresent()) {
//                throw new IllegalStateException("Email is taken");
//            }
//            student.setEmail(email);
//        }
//    }

    @Transactional
    public void updateStudent(Long id, String name, String email) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> (
                        new IllegalStateException("Student with id " + id + " does not exists")
                ));


        if (name != null &&
                !name.isEmpty() &&
                !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }

        if (email != null &&
                !email.isEmpty() &&
                !Objects.equals(email, student.getEmail())
        ) {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("Email is taken");
            }
            student.setEmail(email);
        }
    }
}
