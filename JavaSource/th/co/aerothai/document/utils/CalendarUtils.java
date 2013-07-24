package th.co.aerothai.document.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class CalendarUtils {
	public static Locale LOCALE_TH = new Locale("th", "TH");
	public static Locale LOCALE_US = new Locale("en", "US");
	public static Locale DEFAULT_LOCALE = Locale.getDefault();
	private static final SimpleDateFormat DATE_FORMATTER_TH = new SimpleDateFormat("dd-MM-yyyy", LOCALE_TH);
	private static final SimpleDateFormat DATE_FORMATTER_US= new SimpleDateFormat("dd-MM-yyyy", LOCALE_US);

	public static Calendar getDateInstance(){
		Calendar cal = Calendar.getInstance(DEFAULT_LOCALE);
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal;
	}
	public static Calendar getDateInstance(Locale aLocale){
		Calendar cal = Calendar.getInstance(aLocale);
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal;
	}
	
	public static Calendar getDateTimeInstance() {
		Calendar cal = Calendar.getInstance(DEFAULT_LOCALE);
		return cal;
	}
	
	public static String format(Date aDate, Locale aLocale) {
		if (aLocale.equals(LOCALE_TH)) {
			return DATE_FORMATTER_TH.format(aDate);
		} else {
			return DATE_FORMATTER_US.format(aDate);
		}
	}
	
	public static Date getExpiredDate(){
		Calendar cal = Calendar.getInstance(DEFAULT_LOCALE);
		cal.set(3000, Calendar.DECEMBER,31,23,59,59);
		return cal.getTime();
	}
	
	public static int getFiscalYear(Calendar calendar) {
		//get fiscal year (default with calendar year)
		int fiscalYear = calendar.get(Calendar.YEAR);
		//get calendar month
		int calendarMonth = calendar.get(Calendar.MONTH);
		//check month >= october then fiscal year = calendar year + 1
		if (calendarMonth>=Calendar.OCTOBER) {
			fiscalYear = fiscalYear + 1;
		}
		
		return fiscalYear;
	}
	
	/**
	 * แปลงปีปฏิทินให้เป็นปีงบประมาณ
	 * @param aDate
	 * @param aMonth
	 * @return
	 */
	public static int toFinancialYear(Locale aFromLocale, Locale aToLocale, Date aDate){
		if ((!aFromLocale.equals(LOCALE_TH) && !aFromLocale.equals(LOCALE_US)) 
				|| (!aToLocale.equals(LOCALE_TH) && !aToLocale.equals(LOCALE_US)) ){
			throw new IllegalArgumentException("Locale ต้องเป็น th_TH หรือ en_Us เท่านั้น");
		}
		Calendar cal = Calendar.getInstance(aFromLocale);
		cal.setTime(aDate);
		int year = cal.get(Calendar.YEAR);
		if (cal.get(Calendar.MONTH) > Calendar.SEPTEMBER) {
			year++;
		}
		if (aToLocale.equals(LOCALE_US)){
			if (aFromLocale.equals(LOCALE_TH)){
				year += 543;
			}
		} else {
			if (aFromLocale.equals(LOCALE_US)){
				year -= 543;
			}
		}
		
		
		return year;
	}
	
}
