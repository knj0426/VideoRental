import java.util.Scanner;



public class VRUI {
	private static Scanner scanner = new Scanner(System.in) ;

	/**
	 * rentalManager(RentalManager) : VRUI에서 business logic 부분을 분리해 새로 만든 Class
	 */
	private final RentalManager rentalManager = new RentalManager();

	public static void main(String[] args) {
		VRUI ui = new VRUI() ;

		boolean quit = false ;
		while ( ! quit ) {
			int command = ui.showCommand() ;
			switch ( command ) {
				case 0: quit = true ; break ;
				case 1: ui.listCustomers() ; break ;
				case 2: ui.listVideos() ; break ;
				// SRP 적용
				// 두가지 일을 하던 기존의 register() method를 registerCustomer, registerVideo로 나눔
				case 3: ui.registerCustomer(); ; break ;
				case 4: ui.registerVideo(); break ;

				case 5: ui.rentVideo() ; break ;
				case 6: ui.returnVideo() ; break ;
				case 7: ui.getCustomerReport() ; break;
				case 8: ui.clearRentals() ; break ;
				case -1: ui.init() ; break ;
				default: break ;
			}
		}
		System.out.println("Bye");
	}

	private void clearRentals() {
		// SRP 적용. UI와 Business Logic 분리
		// 사용자 이름을 VRUI에서 입력받고 RentalManager에서 clearRental 처리
		System.out.println("Enter customer name: ");
		String customerName = VRUI.scanner.next();
		rentalManager.clearRentals(customerName);
	}

	private void returnVideo() {
		// SRP 적용. UI와 Business Logic 분리
		// 사용자 이름과 비디오 타이틀을 VRUI에서 입력받고 RentalManager에서 비디오 반납 처리
		System.out.println("Enter customer name: ") ;
		String customerName = scanner.next() ;
		Customer foundCustomer = rentalManager.FindCustomer(customerName);
		if ( foundCustomer == null ) return;

		System.out.println("Enter video title to return: ") ;
		String videoTitle = scanner.next() ;

		rentalManager.UpdateVideo(foundCustomer, videoTitle);
	}

	private void init() {
		// SRP 적용. UI와 Business Logic 분리
		rentalManager.init();
	}

	public void listVideos() {
		// SRP 적용. UI와 Business Logic 분리
		rentalManager.listVideos();
	}

	public void listCustomers() {
		// SRP 적용. UI와 Business Logic 분리
		rentalManager.listCustomers();
	}

	public void getCustomerReport() {
		// SRP 적용. UI와 Business Logic 분리
		// VRUI에서는 UI작업(고객 이름 입력)만 하고 logic은 rentalManager에서 처리
		System.out.println("Enter customer name: ");
		String name = VRUI.scanner.next();
		rentalManager.getCustomerReport(name);
	}

	public void rentVideo() {
		// SRP 적용. UI와 Business Logic 분리
		// 사용자 이름과 비디오 타이틀을 입력받고 rentalManager에서 logic 처리
		System.out.println("Enter customer name: ") ;
		String customerName = scanner.next() ;

		System.out.println("Enter video title to rent: ") ;
		String videoTitle = scanner.next() ;

		rentalManager.rentVideo(customerName, videoTitle);
	}

	public void registerCustomer() {
		// SRP 적용. UI와 Business Logic 분리
		// 고객이름을 입력받고 rentalLManager에서 register 하도록 호출
		System.out.println("Enter customer name: ");
		String name = VRUI.scanner.next();
		rentalManager.registerCustomer(name);
	}

	public void registerVideo() {
		// SRP 적용. UI와 Business Logic 분리
		// 비디오 이름을 입력받고 rentalManager에서 register하도록 호출
		System.out.println("Enter video title to register: ");
		String title = VRUI.scanner.next();

		System.out.println("Enter video type( 1 for VHD, 2 for CD, 3 for DVD ):");
		int videoType = VRUI.scanner.nextInt();

		System.out.println("Enter price code( 1 for Regular, 2 for New Release ):");
		int priceCode = VRUI.scanner.nextInt();

		rentalManager.registerVideo(title, videoType, priceCode);
	}

	public int showCommand() {
		System.out.println("\nSelect a command !");
		System.out.println("\t 0. Quit");
		System.out.println("\t 1. List customers");
		System.out.println("\t 2. List videos");
		System.out.println("\t 3. Register customer");
		System.out.println("\t 4. Register video");
		System.out.println("\t 5. Rent video");
		System.out.println("\t 6. Return video");
		System.out.println("\t 7. Show customer report");
		System.out.println("\t 8. Show customer and clear rentals");

		int command = scanner.nextInt() ;

		return command ;
	}
}
