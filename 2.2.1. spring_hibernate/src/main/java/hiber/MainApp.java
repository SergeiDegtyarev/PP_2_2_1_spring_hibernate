package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("User1", "User1", "user1@mail.ru");
      User user2 = new User("User2", "User2", "user2@mail.ru");
      User user3 = new User("User3", "User3", "user3@mail.ru");

      Car car1 = new Car("lada",2112);
      Car car2 = new Car("lada",2109);
      Car car3 = new Car("lada",2110);

      user1.setCar(car1);
      user2.setCar(car2);
      user3.setCar(car3);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+user.getCar());
         System.out.println("=============");
      }


      System.out.println("Машина Лада 2112 у пользователя: " + userService.getUserCar("lada",2112));
      System.out.println("Машина Лада 2110 у пользователя: " + userService.getUserCar("lada",2110));
      System.out.println("Машина Лада 2109 у пользователя: " + userService.getUserCar("lada",2109));
      context.close();
   }
}
