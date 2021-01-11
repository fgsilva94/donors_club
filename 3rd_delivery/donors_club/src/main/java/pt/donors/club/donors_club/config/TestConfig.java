// package pt.donors.club.donors_club.config;

// import java.time.LocalDate;
// import java.util.Arrays;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.context.annotation.Profile;

// import pt.donors.club.donors_club.models.AdPost;
// import pt.donors.club.donors_club.models.Chat;
// import pt.donors.club.donors_club.models.Message;
// import pt.donors.club.donors_club.models.User;
// import pt.donors.club.donors_club.repositories.AdRepository;
// import pt.donors.club.donors_club.repositories.ChatRepository;
// import pt.donors.club.donors_club.repositories.MessageRepository;
// import pt.donors.club.donors_club.repositories.UserRepository;

// @Configuration
// @Profile("test")
// public class TestConfig implements CommandLineRunner {

//   @Autowired
//   private UserRepository userRepository;

//   @Autowired
//   private AdRepository adRepository;

//   @Autowired
//   private ChatRepository chatRepository;

//   @Autowired
//   private MessageRepository messageRepository;

//   @Override
//   public void run(String... args) throws Exception {

//     User u1 = new User(null, "Felipe Silva", "felipe@hotmail.com", "925 432 167", "123456", "Lisboa");
//     User u2 = new User(null, "Willian Santa Ana", "willian@hotmail.com", "965 587 654", "654321", "Lisboa");
//     User u3 = new User(null, "Chad Lopez", "chad.lopez@example.com", "967585946", "jockey", "Sintra");
//     User u4 = new User(null, "Cathy Fields", "cathy.fields@example.com", "921 473 546", "visitor", "Porto");

//     userRepository.saveAll(Arrays.asList(u1, u2, u3, u4));

//     AdPost a1 = new AdPost(null, "Computer", "MACBOOK PRO i5 - 4gb ddr4 - 512gb ssd", "Technology", u2);
//     AdPost a2 = new AdPost(null, "The Lord of the Rings", "all books as new", "Books", u1);
//     AdPost a3 = new AdPost(null, "TV", "32 inches samsung", "Home", u2);
//     AdPost a4 = new AdPost(null, "Bike", "YT Jeffsy pro race 29 bike, size L.", "Sports", u3);
//     AdPost a5 = new AdPost(null, "Couch",
//         "Large sofa with chaise longue, dark gray fabric. Purchased in 2018. It has no defects / problems.", "Home",
//         u4);

//     adRepository.saveAll(Arrays.asList(a1, a2, a3, a4, a5));

//     Chat c1 = new Chat(null, u1, a1);
//     Chat c2 = new Chat(null, u2, a2);
//     Chat c3 = new Chat(null, u1, a4);
//     Chat c4 = new Chat(null, u3, a5);
//     Chat c5 = new Chat(null, u4, a1);

//     chatRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5));

//     Message m1 = new Message(null, "T1: user1 sending on chat1", LocalDate.now(), c1, u1);
//     Message m2 = new Message(null, "T2: user2 sending on chat1", LocalDate.now(), c1, u2);
//     Message m3 = new Message(null, "T3: user2 sending on chat2", LocalDate.now(), c2, u2);
//     Message m4 = new Message(null, "T4: user1 sending on chat2", LocalDate.now(), c2, u1);
//     Message m5 = new Message(null, "T5: user1 sending on chat3", LocalDate.now(), c3, u1);
//     Message m6 = new Message(null, "T6: user3 sending on chat4", LocalDate.now(), c4, u3);
//     Message m7 = new Message(null, "T7: user4 sending on chat5", LocalDate.now(), c5, u4);
//     Message m8 = new Message(null, "T8: user3 sending on chat3", LocalDate.now(), c3, u3);

//     messageRepository.saveAll(Arrays.asList(m1, m2, m3, m4, m5, m6, m7, m8));
//   }
// }
