package pt.donors.club.donors_club.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import pt.donors.club.donors_club.models.Ad;
import pt.donors.club.donors_club.models.User;
import pt.donors.club.donors_club.repositories.AdRepository;
import pt.donors.club.donors_club.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private AdRepository adRepository;

  @Override
  public void run(String... args) throws Exception {

    User u1 = new User(null, "Felipe Silva", "fgsilva94@hotmail.com", "967585955", "123456", "lisboa");
    User u2 = new User(null, "Willian Santa Ana", "willian@hotmail.com", "967585946", "654321", "lisboa");

    userRepository.saveAll(Arrays.asList(u1, u2));

    Ad a1 = new Ad(null, "Computer", "MACBOOK PRO i5 - 4gb ddr4 - 512gb ssd", "Technology", u2);
    Ad a2 = new Ad(null, "The Lord of the Rings", "all books as new", "Books", u1);
    Ad a3 = new Ad(null, "TV", "32 inches samsung", "Home", u2);

    adRepository.saveAll(Arrays.asList(a1, a2, a3));
  }

}
