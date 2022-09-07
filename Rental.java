import java.util.Date;

public class Rental {
	/// remove MagicNumber
	private final int SEC_TO_MSEC = 1000;
	private final int MAX_SEC = 60;
	private final int MAX_MINUTE = 60;
	private final int MAX_HOUR = 24;

	private Video video ;
	private int status ;
	private Date rentDate ;
	private Date returnDate ;

	public Rental(Video video) {
		this.video = video ;
		status = 0 ;
		rentDate = new Date() ;
	}

	public Video getVideo() {
		return video;
	}

	public int getStatus() {
		return status;
	}

	public void returnVideo() {
		if ( status == 1 ) {
			// remove unnecessary code
			returnDate = new Date() ;
		}
	}
	// Remove Duplicate
	public int getDaysRentedLimit() {
		int limit = 0 ;
		int daysRented = getdaysRented();

		if (daysRented <= 2) return limit ;

		switch ( video.getVideoType() ) {
			case Video.VHS: limit = 5 ; break ;
			case Video.CD: limit = 3 ; break ;
			case Video.DVD: limit = 2 ; break ;
		}
		return limit ;
	}
	// Remove Duplicated code
	// Extract Method
	public int getdaysRented(){
		long diff = 0;
		if (getStatus() == 1) { // returned Video
			diff = returnDate.getTime() - rentDate.getTime();
		} else { // not yet returned
			diff = new Date().getTime() - rentDate.getTime();
		}
		int daysRented = (int) (diff / (SEC_TO_MSEC * MAX_MINUTE * MAX_SEC * MAX_HOUR)) + 1;

		return daysRented ;
	}
}
