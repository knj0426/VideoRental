import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * RentalManager.java
 * VideoRental 프로젝트의 business logic layer에 해당하는 class
 * 기존의 VRUI.java에서 담당하던 business logic을 별도의 class로 분리하여
 * UI는 VRUI, business logic은 RentalManager에서 담당하도록 refatoring
 */
public class RentalManager {
    List<Customer> customers = new ArrayList<Customer>();
    List<Video> videos = new ArrayList<Video>();

    public RentalManager() {
    }

    public void clearRentals(String customerName) {
        // remove duplicate code
        Customer foundCustomer = FindCustomer(customerName);

        if (foundCustomer == null) {
            System.out.println("No customer found");
        } else {
            System.out.println("Name: " + foundCustomer.getName() +
                    "\tRentals: " + foundCustomer.getRentals().size());
            for (Rental rental : foundCustomer.getRentals()) {
                System.out.print("\tTitle: " + rental.getVideo().getTitle() + " ");
                System.out.print("\tPrice Code: " + rental.getVideo().getPriceCode());
            }

            List<Rental> rentals = new ArrayList<Rental>();
            foundCustomer.setRentals(rentals);
        }
    }
    void init() {
        Customer james = new Customer("James");
        Customer brown = new Customer("Brown");
        customers.add(james);
        customers.add(brown);

        Video v1 = new Video("v1", Video.CD, Video.REGULAR, new Date());
        Video v2 = new Video("v2", Video.DVD, Video.NEW_RELEASE, new Date());
        videos.add(v1);
        videos.add(v2);

        Rental r1 = new Rental(v1);
        Rental r2 = new Rental(v2);

        james.addRental(r1);
        james.addRental(r2);
    }

    public void listVideos() {
        System.out.println("List of videos");

        for (Video video : videos) {
            System.out.println("Price code: " + video.getPriceCode() + "\tTitle: " + video.getTitle());
        }
        System.out.println("End of list");
    }

    public void listCustomers() {
        System.out.println("List of customers");
        for (Customer customer : customers) {
            System.out.println("Name: " + customer.getName() +
                    "\tRentals: " + customer.getRentals().size());
            for (Rental rental : customer.getRentals()) {
                System.out.print("\tTitle: " + rental.getVideo().getTitle() + " ");
                System.out.print("\tPrice Code: " + rental.getVideo().getPriceCode());
            }
        }
        System.out.println("End of list");
    }

    public void getCustomerReport(String customerName) {
        // remove duplicate code
        Customer foundCustomer = FindCustomer(customerName);

        if (foundCustomer == null) {
            System.out.println("No customer found");
        } else {
            System.out.println(foundCustomer.getReport());
        }
    }

    // SRP 적용 duplicate code 제거
    Customer FindCustomer(String customerName) {
        Customer foundCustomer = null ;
        for ( Customer customer: customers) {
            if ( customer.getName().equals(customerName)) {
                foundCustomer = customer ;
                break ;
            }
        }
        return foundCustomer;
    }

    public void UpdateVideo(Customer foundCustomer, String videoTitle) {
        List<Rental> customerRentals = foundCustomer.getRentals() ;
        for ( Rental rental: customerRentals ) {
            if ( rental.getVideo().getTitle().equals(videoTitle) && rental.getVideo().isRented() ) {
                rental.returnVideo();
                rental.getVideo().setRented(false);
                break ;
            }
        }
    }

    public void registerCustomer(String customerName) {
        Customer customer = new Customer(customerName);
        customers.add(customer);
    }

    public void registerVideo(String videoName, int videoType, int priceCode) {
        Date registeredDate = new Date();
        Video video = new Video(videoName, videoType, priceCode, registeredDate);
        videos.add(video);

    }

    public void rentVideo(String customerName, String videoTitle) {
        // remove duplicate code
        Customer foundCustomer = FindCustomer(customerName);
        if ( foundCustomer == null ) return ;

        Video foundVideo = findVideo(videoTitle);

        if ( foundVideo == null ) return ;
        Rental rental = new Rental(foundVideo) ;

        UpdateRental(foundCustomer, foundVideo, rental);

    }
    // Encapsulation
    public void UpdateRental(Customer customer,  Video video, Rental rental ) {
        video.setRented(true);
        customer.addRental(rental);
    }

    // SRP 적용 duplicate code 제거
    public Video findVideo(String videoTitle) {
        Video foundVideo = null ;
        for ( Video video: videos) {
            if ( video.getTitle().equals(videoTitle) && !video.isRented()) {
                foundVideo = video ;
                break ;
            }
        }
        return foundVideo;
    }
}

