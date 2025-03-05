package soheil.demo.start;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import soheil.demo.start.repository.UniversityRepository;
import soheil.demo.start.repository.UserRepository;
import soheil.demo.start.model.Admin;
import soheil.demo.start.model.University;
import soheil.demo.start.service.UniversityService;

@Component
public class DataNeeded implements CommandLineRunner {

    //Declaring Repositories & passwordEncoder
    private final UserRepository userRepository;
    private final UniversityService universityService;
    private final UniversityRepository universityRepository;
    private final PasswordEncoder passwordEncoder;

    //Constructor.
    //-------------------------------------------------------------------------------
    public DataNeeded(UserRepository userRepository,
                      UniversityService universityService,
                      UniversityRepository universityRepository,
                      PasswordEncoder passwordEncoder)
    {
        this.userRepository = userRepository;
        this.universityService = universityService;
        this.universityRepository = universityRepository;
        this.passwordEncoder = passwordEncoder;
    }
    //-------------------------------------------------------------------------------


    @Override
    public void run(String... args) throws Exception {
        if (!userRepository.existsById("Admin")) {
            Admin user = new Admin(
                    "Admin",
                    passwordEncoder.encode("Admin"),
                    "ADMIN"
            );
            userRepository.save(user);
        }
        if (universityRepository.count() == 0) {
            University university = new University("Shahrood University of Technology");
            universityService.addUniversity(university);
        }
    }
}
