package soheil.demo.start;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import soheil.demo.start.Repository.universityRepository;
import soheil.demo.start.Repository.userRepository;
import soheil.demo.start.model.admin;
import soheil.demo.start.model.university;
import soheil.demo.start.service.universityService;

@Component
public class dataNeeded implements CommandLineRunner {

    //Declaring Repositories & passwordEncoder
    private final userRepository userRepository;
    private final universityService universityService;
    private final universityRepository universityRepository;
    private final PasswordEncoder passwordEncoder;

    //Constructor.
    //-------------------------------------------------------------------------------
    public dataNeeded(userRepository userRepository,
                      universityService universityService,
                      universityRepository universityRepository,
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
        if (!userRepository.existsById("admin")) {
            admin user = new admin(
                    "admin",
                    passwordEncoder.encode("admin"),
                    "ADMIN"
            );
            userRepository.save(user);
        }
        if (universityRepository.count() == 0) {
            university university = new university("shahrood University of Technology");
            universityService.addUniversity(university);
        }
    }
}
