import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;
import java.text.DecimalFormat;


public class HotelManagement {

    private static final RoomRepository roomRepository = new RoomRepository();
    private static final UserRepository userRepository = new UserRepository();
    private static final EmployeeRepository employeeRepository = new EmployeeRepository();
    private static final PaymentsRepository paymentsRepository = new PaymentsRepository();

    public static void main(String[] args) throws SQLException {
        roomRepository.save(new Room("Ekonomiškos klasės kambarys", "Dvivietis kambarys su privačiu vonios kambariu", 30, true));
        roomRepository.save(new Room("Ekonomiškos klasės kambarys", "Dvivietis kambarys su privačiu vonios kambariu", 30, false));
        roomRepository.save(new Room("Ekonomiškos klasės kambarys", "Dvivietis kambarys su privačiu vonios kambariu", 30, true));
        roomRepository.save(new Room("Ekonomiškos klasės kambarys", "Dvivietis kambarys su privačiu vonios kambariu", 30, false));
        roomRepository.save(new Room("Ekonomiškos klasės kambarys", "Dvivietis kambarys su privačiu vonios kambariu", 30, true));
        roomRepository.save(new Room("Standartinis kambarys", "Dvivietis kambarys su privačiu vonios kambariu", 45, true));
        roomRepository.save(new Room("Standartinis kambarys", "Dvivietis kambarys su privačiu vonios kambariu", 45, false));
        roomRepository.save(new Room("Standartinis kambarys", "Dvivietis kambarys su privačiu vonios kambariu", 45, false));
        roomRepository.save(new Room("Standartinis kambarys", "Dvivietis kambarys su privačiu vonios kambariu", 45, true));
        roomRepository.save(new Room("Standartinis trivietis kambarys", "Trivietis kambarys su vaizdu į miestą", 60, false));
        roomRepository.save(new Room("Standartinis trivietis kambarys", "Trivietis kambarys su vaizdu į miestą", 60, false));
        roomRepository.save(new Room("Standartinis trivietis kambarys", "Trivietis kambarys su vaizdu į miestą", 60, false));
        roomRepository.save(new Room("Standartinis trivietis kambarys", "Trivietis kambarys su vaizdu į miestą", 60, false));
        roomRepository.save(new Room("Šeimyninis kambarys", "Keturvietis kambarys su išskleidžiama sofa", 75, true));

        employeeRepository.save(new Employee("Krokas", "Brokas", 0));
        employeeRepository.save(new Employee("Algo", "Ogo", 0));
        employeeRepository.save(new Employee("Popas", "Popsas", 0));
        employeeRepository.save(new Employee("Kurmis", "Žemė", 0));


        userRepository.save(new User("Ivas", "Krokas", "2000-01-01", 1));
        userRepository.save(new User("Ropė", "Daržovė", "1986-03-04", 3));
        userRepository.save(new User("Toras", "Moras", "1970-03-12", 5));
        userRepository.save(new User("Braškė", "Avietė", "1995-19-10", 2));
        userRepository.save(new User("Kieta", "Kaktis", "2000-12-12", 1));
        userRepository.save(new User("Tulis", "Dražė", "2002-05-12", 7));





        paymentsRepository.save(new Payments(1000, "Mokesčiai"));
        paymentsRepository.save(new Payments(150, "Mokesčiai"));
        paymentsRepository.save(new Payments(300, "Alga"));
        paymentsRepository.save(new Payments(100, "Buitinė chemija"));




        Scanner skaityti = new Scanner(System.in);
        System.out.println("Norint prisijungti, turite įvesti administratoriaus slaptažodį.");

        String prisijungimas = skaityti.nextLine();
        boolean arDarbasBaigtas = false;
        while(arDarbasBaigtas == false) {
        if (Objects.equals(prisijungimas, "ADMIN")){
                System.out.println("Ką norite padaryti?\n 1 - peržiūrėti kambarių sąrašą,\n 2 - peržiūrėti darbuotojų sąrąšą,\n " +
                        "3 - sumokėti darbuotojui algą,\n 4 - pridėti naują darbuotoją,\n 5 - atleisti darbuotuoją,\n 6 - peržiūrėti gyventojų sąrąšą," +
                        "\n 7 - pakeisti kambario kainą,\n 8 - Įvesti naują mokėjimą-minusą,\n 9 - Peržiūrėti bendrą mokėjimų-minusų suvestinę,\n" +
                        " 10 - peržiūrėti pelno sąrašą,\n 11 – baigti darbą.\n" +
                        "------------------------");
                String kaDaroAdmin = skaityti.nextLine();
                if (Objects.equals(kaDaroAdmin, "1")) {
                    System.out.println("Kambariai:\n-----------------------");
                    System.out.println(roomRepository.findAll());
                    System.out.println("---------------------\nAr norite baigti darbą? Jei taip, rašykite TAIP.");
                    String darboPabaiga = skaityti.nextLine();
                    if (Objects.equals(darboPabaiga, "TAIP")){
                        arDarbasBaigtas = true;
                    }
                }
                else if (Objects.equals(kaDaroAdmin, "2")) {
                    System.out.println("Darbuotojai:\n-------------------");
                    System.out.println(employeeRepository.findAll());
                    System.out.println("---------------------\nAr norite baigti darbą? Jei taip, rašykite TAIP.");
                    String darboPabaiga = skaityti.nextLine();
                    if (Objects.equals(darboPabaiga, "TAIP")){
                        arDarbasBaigtas = true;
                    }
                }
                else if (Objects.equals(kaDaroAdmin, "3")) {
                    System.out.println("---------------------");
                    System.out.println("Jūsų darbuotojų sąrašas:");
                    System.out.println(employeeRepository.findAll());
                    System.out.println("---------------------\nKuriam darbuotojui norite sumokėti algą? Įveskite ID");
                    String darb = skaityti.nextLine();
                    var alga = employeeRepository.getById(Integer.parseInt(darb));
                    System.out.println(alga);
                    System.out.println("---------------------\nKiek mokėsite darbuotojui?");
                    String kiekPinigu = skaityti.nextLine();
                    int salary = Integer.parseInt(kiekPinigu);
                    alga.setSalary(salary);
                    employeeRepository.update(alga);
                    System.out.println("---------------\nJūsų darbuotojui sumokėta.");
                    System.out.println(alga);
                    paymentsRepository.save(new Payments(salary, "Alga."));
                    System.out.println("---------------------\nAr norite baigti darbą? Jei taip, rašykite TAIP.");
                    String darboPabaiga = skaityti.nextLine();
                    if (Objects.equals(darboPabaiga, "TAIP")){
                        arDarbasBaigtas = true;
                    }
                }
                else if (Objects.equals(kaDaroAdmin, "4")) {
                    System.out.println("Įveskite darbuotojo vardą.");
                    String vardas = skaityti.nextLine();
                    System.out.println("Įveskite darbuotojo pavardę.");
                    String pavarde = skaityti.nextLine();
                    employeeRepository.save(new Employee(vardas, pavarde, 0));
                    System.out.println("Naujas darbuotojas įvestas. Jūsų darbuotojų sąrašas:");
                    System.out.println(employeeRepository.findAll());
                    System.out.println("---------------------\nAr norite baigti darbą? Jei taip, rašykite TAIP.");
                    String darboPabaiga = skaityti.nextLine();
                    if (Objects.equals(darboPabaiga, "TAIP")){
                        arDarbasBaigtas = true;
                    }
                }
                else if (Objects.equals(kaDaroAdmin, "5")) {
                    System.out.println("Jūsų darbuotojai:");
                    System.out.println(employeeRepository.findAll());
                    System.out.println("--------------------------");
                    System.out.println("Įveskite ID darbuotojo, kurį norite atleisti.");
                    String darbID = skaityti.nextLine();
                    var darbuotojas = employeeRepository.getById(Integer.parseInt(darbID));
                    employeeRepository.delete(darbuotojas);
                    System.out.println("Darbuotojas atleistas. Naujasis darbuotojų sąrašas:");
                    System.out.println(employeeRepository.findAll());
                    System.out.println("---------------------\nAr norite baigti darbą? Jei taip, rašykite TAIP.");
                    String darboPabaiga = skaityti.nextLine();
                    if (Objects.equals(darboPabaiga, "TAIP")){
                        arDarbasBaigtas = true;
                    }
                }
                else if (Objects.equals(kaDaroAdmin, "6")) {
                    System.out.println("---------------------\nJūsų viešbučio gyventojai:\n" +
                            "--------------------");
                    System.out.println(userRepository.findAll());
                    System.out.println("---------------------\nAr norite baigti darbą? Jei taip, rašykite TAIP.");
                    String darboPabaiga = skaityti.nextLine();
                    if (Objects.equals(darboPabaiga, "TAIP")){
                        arDarbasBaigtas = true;
                    }
                }
                else if (Objects.equals(kaDaroAdmin, "7")) {
                    System.out.println("Visi kambariai:");
                    System.out.println(roomRepository.findAll());
                    System.out.println("----------------\nKurio kambario kainą norite atnaujinti?");
                    String kurisKambarys = skaityti.nextLine();
                    var room = roomRepository.getById(Integer.parseInt(kurisKambarys));
                    System.out.println(room);
                    System.out.println("Kokia jo kaina nuo šiol?");
                    String kaina = skaityti.nextLine();
                    room.setPrice(Integer.parseInt(kaina));
                    room.setDescription("Atnaujintas kambarys");
                    roomRepository.update(room);

                    System.out.println(roomRepository.getById(Integer.parseInt(kurisKambarys)));
                    System.out.println("---------------------\nAr norite baigti darbą? Jei taip, rašykite TAIP.");
                    String darboPabaiga = skaityti.nextLine();
                    if (Objects.equals(darboPabaiga, "TAIP")){
                        arDarbasBaigtas = true;
                    }
                }
                else if(Objects.equals(kaDaroAdmin, "8")){
                    System.out.println("---------------------\nĮveskite mokesčio sumą.");
                    String moka = skaityti.nextLine();
                    System.out.println("---------------------\nĮveskite mokesčio paskirtį.");
                    String paskirtis = skaityti.nextLine();
                    paymentsRepository.save(new Payments(Integer.parseInt(moka), paskirtis));
                    System.out.println("---------------------\nMokėjimas įvestas. Visi jūsų mokėjimai: ");
                    System.out.println(paymentsRepository.findAll());
                    System.out.println("---------------------\nAr norite baigti darbą? Jei taip, rašykite TAIP.");
                    String darboPabaiga = skaityti.nextLine();
                    if (Objects.equals(darboPabaiga, "TAIP")){
                        arDarbasBaigtas = true;
                    }
                }
                else if(Objects.equals(kaDaroAdmin, "9")){
                    System.out.println("---------------------\nJūsų mokėjimų sąrašas:");
                    System.out.println(paymentsRepository.findAll());
                    System.out.println("---------------------\nBendras viešbučio minusas:");
                    System.out.println(paymentsRepository.bendraiMokejimu());
                    System.out.println("---------------------\nAr norite išeiti iš sistemos? Jei taip, rašykite TAIP.");
                    String darboPabaiga = skaityti.nextLine();
                    if (Objects.equals(darboPabaiga, "TAIP")){
                        arDarbasBaigtas = true;
                    }
                }
                else if(Objects.equals(kaDaroAdmin, "10")){
                    System.out.println("---------------------\nBendras viešbučio pelnas:");
                    int kiekPliuso = userRepository.bendraiPliuso() * 200;
                    System.out.println(kiekPliuso);
                    System.out.println("Pelnas su minusu:");
                    int bendraiPelnas = kiekPliuso - paymentsRepository.bendraiMokejimu();
                    System.out.println(bendraiPelnas);
                    System.out.println("---------------------\nAr norite išeiti iš sistemos? Jei taip, rašykite TAIP.");
                    String darboPabaiga = skaityti.nextLine();
                    if (Objects.equals(darboPabaiga, "TAIP")){
                        arDarbasBaigtas = true;
                    }
                }
                else if (Objects.equals(kaDaroAdmin, "11")) {
                    System.out.println("--------------");
                    System.out.println("Viso gero...");
                    arDarbasBaigtas = true;
                }
                else System.out.println("Neteisinga įvestis. Bandykite dar kartą.\n" +
                            "------------------------------------");
                    kaDaroAdmin = skaityti.nextLine();
            }
        else {
            System.out.println("Neteisinga įvestis. Bandykite dar kartą.\n" +
                    "------------------------------------\n" +
                    "Jei nesate šio viešbučio administratorius, neturite prieigos prie šios sistemos ir rašykite EXIT.");
            prisijungimas = skaityti.nextLine();
            if(Objects.equals(prisijungimas, "EXIT")){
                arDarbasBaigtas = true;
            }
        }
        }
    }

}
