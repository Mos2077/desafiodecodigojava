import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

// Classe TimeFactory:
class TimeFactory {
  // TODO: Crie o método estático para criar um objeto LocalTime a partir de uma string:
  public static LocalTime createTime(String timeString) {
    try {
      // Usando LocalTime.parse() para converter a string em LocalTime:
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
      return LocalTime.parse(timeString, formatter);
    } catch (DateTimeParseException e) {
      return null;
    }
  }
}

public class MeetingScheduler {

  private static final LocalTime MIN_TIME = LocalTime.of(9, 0); 
  private static final LocalTime MAX_TIME = LocalTime.of(18, 0); 
   
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    
    String desiredTimeInput = scanner.nextLine();
    String currentTimeInput = scanner.nextLine();
    scanner.close();
    //TODO: Implemente o TimeFactory.createTime() para converter desiredTimeInput em LocalTime:
    LocalTime desiredTime = TimeFactory.createTime(desiredTimeInput); 

    //TODO: Implemente o TimeFactory.createTime() para converter currentTimeInput em LocalTime:
    LocalTime currentTime = TimeFactory.createTime(currentTimeInput); 

    if (isMeetingSchedulable(desiredTime)) {
      System.out.println("Reuniao pode ser agendada.");
    } else {
      System.out.println("Reuniao nao pode ser agendada. Horario fora do intervalo permitido.");
    }
  }

  private static boolean isMeetingSchedulable(LocalTime desiredTime) {
    return !desiredTime.isBefore(MIN_TIME) && !desiredTime.isAfter(MAX_TIME);
  }
}
