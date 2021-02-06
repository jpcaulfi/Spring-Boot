package components;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Webhook {

    String urlKey;
    String url;
    String date;
    String time;
    String year;
    String month;
    String day;
    String hour;
    String minute;
    int indexCounter;
    int indexRescuesPlanned;
    int indexRoutesAtRisk;
    int indexRescueRecommendations;
    int indexRoster;
    int indexLast;
    int[] indexArray;
    Elements trElements;

    public Webhook(String url) throws IOException {

        this.url = url;
        this.urlKey = this.url.substring(url.length() - 40);

        // DATE & TIME DATA

        this.year = url.substring(8).split("/")[4];
        this.month = url.substring(8).split("/")[5];
        this.day = url.substring(8).split("/")[6];
        this.hour = url.substring(8).split("/")[7];
        this.minute = "00";
        if (!url.substring(8).split("/")[8].contains("DB")) {
            this.minute = url.substring(8).split("/")[8];
        }
        this.date = this.year + "-" + this.month + "-" + this.day;
        this.time = this.hour + ":" + this.minute;

        // CRAWLS URL FOR INDICES

        Document doc = Jsoup.connect(url).get();
        this.trElements = doc.select("tr");
        this.indexCounter = 0;
        this.indexRescuesPlanned = 0;
        this.indexRoutesAtRisk = 0;
        this.indexRescueRecommendations = 0;
        this.indexRoster = 0;
        this.indexLast = 0;
        for (int i = 0; i < trElements.size(); i++) {
            if (String.valueOf(trElements.get(i).child(0).text()).equals("Completed")) {
                if (this.indexCounter == 1) {
                    this.indexRoutesAtRisk = i + 1;
                } else if (this.indexCounter == 2) {
                    this.indexRescueRecommendations = i + 1;
                } else if (this.indexCounter == 3) {
                    this.indexRoster = i + 1;
                } else {
                    this.indexRescuesPlanned = i + 1;
                }
                this.indexCounter += 1;
            }
            this.indexLast = i;
        }
        this.indexArray = new int[] {this.indexCounter, this.indexRescuesPlanned, this.indexRoutesAtRisk, this.indexRescueRecommendations, this.indexRoster, this.indexLast};
    }

    public void insert() {
        String statement = "INSERT INTO webhooks (urlKey, url, date, time) VALUES ('"+this.urlKey+"', '"+this.url+"', '"+this.date+"', '"+this.time+"')";
        Database.commitToDB(statement);
    }

    public String getUrl() {
        return url;
    }

    public String getUrlKey() {
        return urlKey;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getYear() {
        return year;
    }

    public String getMonth() {
        return month;
    }

    public String getDay() {
        return day;
    }

    public String getHour() {
        return hour;
    }

    public String getMinute() {
        return minute;
    }

    public int getIndexCounter() {
        return indexCounter;
    }

    public int getIndexRescuesPlanned() {
        return indexRescuesPlanned;
    }

    public int getIndexRoutesAtRisk() {
        return indexRoutesAtRisk;
    }

    public int getIndexRescueRecommendations() {
        return indexRescueRecommendations;
    }

    public int getIndexRoster() {
        return indexRoster;
    }

    public int getIndexLast() {
        return indexLast;
    }

    public int[] getIndexArray() {
        return indexArray;
    }

    public Elements getTrElements() {
        return trElements;
    }

    public int isADuplicate() throws SQLException {
        String statement = "SELECT * FROM webhooks WHERE urlKey = '" + this.urlKey + "'";
        ResultSet result = Database.pullFromDB(statement);
        if (result.getFetchSize() == 0) {
            return 0;
        } else {
            return 1;
        }
    }
}
