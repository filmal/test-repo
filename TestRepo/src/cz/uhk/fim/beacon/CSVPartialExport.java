package cz.uhk.fim.beacon;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;

import cz.uhk.fim.beacon.data.Measurement;
import cz.uhk.fim.beacon.data.scan.WifiScan;

/**
 * Created by filmal on 6. 6. 2016.
 */
public class CSVPartialExport {
	public static final String CSV_FILENAME = "exportVybranychDat.csv";
	public static final String DELIM = ";";
	private List<Measurement> measurements;
	private LocalDateTime time;
	
	
	public CSVPartialExport(List<Measurement> measurements) {
		this.measurements = measurements;
	}
	
	
	public void createPartialCSVFile() throws FileNotFoundException {
		System.out.println("Export: start");
		LocalDateTime zacatekMereniKostel = LocalDateTime.of(2016, 6, 6, 15, 15);
		LocalDateTime zacatekMereniBilaVez = LocalDateTime.of(2016, 6, 6, 15, 30);
		
		
		PrintWriter pw = new PrintWriter(new File(CSV_FILENAME));
		writeHeaderToFile(pw);
	
		for (Measurement m: measurements) {
			
			time = m.getDateTime();
			
			if (time.isAfter(zacatekMereniKostel) && time.isBefore(zacatekMereniBilaVez)) {
				List<WifiScan> wifiScans = m.getWifiScans();
				writeFieldToFile(wifiScans, pw, "Kostel");
			
			} else {
				if (time.isAfter(zacatekMereniBilaVez)) {
					List<WifiScan> wifiScans = m.getWifiScans();
					writeFieldToFile(wifiScans, pw, "BilaVez");
				}
			}
			
		}
		
		pw.close();
		System.out.println("Export: finished");
	}
	
	
	private void writeHeaderToFile(PrintWriter pw) {
		String field;
		field = "mac"+DELIM+"ssid"+DELIM+"signalStrength"+DELIM+"channel"+DELIM+"frequency"+DELIM+"place";
		pw.println(field);
	}
	
	
	private void writeFieldToFile(List<WifiScan> wifiScans, PrintWriter pw, String place) {
		String field;
		for (WifiScan ws : wifiScans) {
			//field = ws.getId() + DELIM + ws.getSsid() + DELIM + ws.getSignalStrength() + DELIM + ws.getChannel() + DELIM + ws.getFrequency() + DELIM + place;
			//pw.println(field);
			//System.out.println(field + DELIM + time);
			//System.out.println(field);
		}
	}
	
	

}
