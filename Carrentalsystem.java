import java.util.*;
class Car{
private int carid;
private String carbrand;
private String carmodel;
private double baseprice;
private boolean isAvailable;
public Car(int carid,String carbrand,String carmodel,double baseprice){
    this.carid=carid;
    this.carbrand=carbrand;
    this.carmodel=carmodel;
    this.baseprice=baseprice;
    isAvailable=true;
    
}
public int getcarid(){
    return carid;
}
public String getcarbrand(){
    return carbrand;
}
public String getcarmodel(){
    return carmodel;
}
public double getbaseprice(){
    return baseprice;
}
public boolean isAvailable(){
    return isAvailable;
}
public void rent(){
    isAvailable=false;
}
public void returnCar(){
    isAvailable=true;
}
public double totalpay(int rentdays){
    return baseprice*rentdays;
}

}
class Customer{
    private int customerid;
    private String name;
    private String phoneno;
    public Customer(int customerid,String name,String phoneno){
        this.customerid=customerid;
        this.name=name;
        this.phoneno=phoneno;
    }
    public int getcustomerid(){
        return customerid;
    }
    public String getname(){
        return name;
    }
    public String getphoneno(){
        return phoneno;
    }

     
}
class Rental{
    private Car car;
    private Customer customer;
    private int rentdays;

    public Rental(Car car,Customer customer,int rentdays){
        this.car=car;
        this.customer=customer;
        this.rentdays=rentdays;
    }
     public Car getcar(){
        return car;
     }
     public Customer getcustomer(){
        return customer;
     }
     public int getrentdays(){
        return rentdays;
     } 

}




class Carrentalsystem{
    private List<Car> cars;
    private List<Customer> customers;
    private List<Rental> rentals;
    public Carrentalsystem() {
        cars = new ArrayList<>();
        customers = new ArrayList<>();
        rentals = new ArrayList<>();
    }
    public void addCar(Car car) {
        cars.add(car);
    }
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }
    public void rentcar(Car cars,Customer customers,int rentdays){
        if(cars.isAvailable()){
            Rental rental=new Rental(cars,customers,rentdays);
            rentals.add(rental);
            cars.rent();
            }else{
                System.out.println("car is not available for rent.");
            }
       }
       public void returncar(Car cars){
        Rental rentaltoremove=null;
        for(Rental rental:rentals){
            if(rental.getcar()==cars){
                rentaltoremove=rental;
                break;
            }
        }
        if(rentaltoremove!=null){
            rentals.remove(rentaltoremove);
            cars.returnCar();
        }else{
            System.out.println("car not rented.");
        }
         }


        
            public static void main(String[]args){
                
                    Carrentalsystem system = new Carrentalsystem();
                    
                    Car car1 = new Car(1, "mahindra", "thar", 200.0);
                    Car car2 = new Car(2, "toyota", "cruiser", 40.0);
                    Car car3 = new Car(3, "tata", "safari", 60.0);
                    system.addCar(car1);
                    system.addCar(car2);
                    system.addCar(car3);
                    
                    Scanner sc = new Scanner(System.in);
                    int choice;
                    
                    do {
                        System.out.println("========= Welcome to car rental system ===============");
                        System.out.println("Enter 1 for rent a car");
                        System.out.println("Enter 2 for return car");
                        System.out.println("Enter 3 for Exit");
                        System.out.println("Enter your choice");
                        choice = sc.nextInt();
                        sc.nextLine(); 
                        
                        switch (choice) {
                            case 1:
                                System.out.println("==Rent a Car==");
                                System.out.println("Enter your name:");
                                String customername = sc.nextLine();
                                System.out.println("List of Available cars");
                                for (Car car : system.cars) {
                                    if (car.isAvailable()) {
                                        System.out.println(car.getcarid() + "  " + car.getcarbrand() + "  " + car.getcarmodel());
                                    }
                                }
                                System.out.println("Enter car id you want:");
                                int carid = sc.nextInt();
                                sc.nextLine(); 
                                System.out.println("Enter the number of days you want the car:");
                                int rentaldays = sc.nextInt();
                                sc.nextLine(); 
                                System.out.println("Enter your phone number:");
                                String phoneNumber = sc.nextLine();
                                
                                Customer newCustomer = new Customer(system.customers.size() + 1, customername, phoneNumber);
                                system.addCustomer(newCustomer);
                                
                                Car selectedCar = null;
                                for (Car car : system.cars) {
                                    if (car.getcarid() == carid && car.isAvailable()) {
                                        selectedCar = car;
                                        break;
                                    }
                                }
                                
                                if (selectedCar != null) {
                                    double totalprice = selectedCar.totalpay(rentaldays);
                                    System.out.println("\n Rental Information \n");
                                    System.out.println("Customer ID: " + newCustomer.getcustomerid());
                                    System.out.println("Customer Name: " + newCustomer.getname());
                                    System.out.println("Car: " + selectedCar.getcarbrand() + " " + selectedCar.getcarmodel());
                                    System.out.println("Phone Number: " + newCustomer.getphoneno());
                                    System.out.println("Rental Days: " + rentaldays);
                                    System.out.println("Total Price: " + totalprice);
                                    System.out.print("Confirm rental (Y/N): ");
                                    String confirm = sc.nextLine();
                                    
                                    if (confirm.equalsIgnoreCase("y")) {
                                        system.rentcar(selectedCar, newCustomer, rentaldays);
                                        System.out.println("Car rented successfully");
                                    } else {
                                        System.out.println("Car rental unsuccessful");
                                    }
                                } else {
                                    System.out.println("Invalid car selection or car not available.");
                                }
                                break;
                                
                            case 2:
                                System.out.println("==== Return Car ====");
                                System.out.println("Enter car id you want to return:");
                                int returncarid = sc.nextInt();
                                sc.nextLine(); 
                                
                                Car carToReturn = null;
                                for (Car car : system.cars) {
                                    if (car.getcarid() == returncarid && !car.isAvailable()) {
                                        carToReturn = car;
                                        break;
                                    }
                                }
                                
                                if (carToReturn != null) {
                                    Customer returningCustomer = null;
                                    for (Rental rental : system.rentals) {
                                        if (rental.getcar() == carToReturn) {
                                            returningCustomer = rental.getcustomer();
                                            break;
                                        }
                                    }
                                    
                                    if (returningCustomer != null) {
                                        system.returncar(carToReturn);
                                        System.out.println("Car returned successfully by " + returningCustomer.getname());
                                    } else {
                                        System.out.println("Car return unsuccessful");
                                    }
                                } else {
                                    System.out.println("Invalid car selection or car not rented.");
                                }
                                break;
                                
                            case 3:
                                System.out.println("Thank you for using car rental system");
                                break;
                                
                            default:
                                System.out.println("Invalid choice. Please enter a valid option (1-3).");
                                break;
                        }
                        
                    } while (choice != 3);
                    
                    sc.close();
                }
                
}
