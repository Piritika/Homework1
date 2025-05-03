import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Person {
    private String lastName;
    private String firstName;
    private String middleName;
    private Date birthDate;
    private long phoneNumber;
    private char gender;
    private int age;

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");

    public Person(String lastName, String firstName, String middleName,
                  String birthDateStr, String phoneStr, String genderStr, String ageStr) throws Exception {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;

        try {
            this.birthDate = DATE_FORMAT.parse(birthDateStr);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Ошибка формата даты: " + birthDateStr);
        }

        try {
            this.phoneNumber = Long.parseUnsignedLong(phoneStr);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Неверный формат номера телефона: " + phoneStr);
        }

        if (!(genderStr.equalsIgnoreCase("f") || genderStr.equalsIgnoreCase("m"))) {
            throw new IllegalArgumentException("Пол должен быть 'f' или 'm', а не: " + genderStr);
        }
        this.gender = genderStr.toLowerCase().charAt(0);

        try {
            this.age = Integer.parseInt(ageStr);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Неверный формат возраста: " + ageStr);
        }
    }

    public String toFileFormat() {
        return String.format("%s %s %s %s %d %c %d",
                lastName, firstName, middleName,
                DATE_FORMAT.format(birthDate), phoneNumber, gender, age);
    }

    public String getLastName() {
        return lastName;
    }
}
