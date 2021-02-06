import components.RawData;
import components.Webhook;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

public class Compiler {

    public static void main(List<List<String>> WebhookUrls) throws Exception {


        //////////////  INITIAL BREAKDOWN OF THE WEBHOOK DOCUMENT  //////////////

        for (List<String> webhookUrl : WebhookUrls) {
            Webhook webhook = new Webhook(webhookUrl.get(0));
            String date = webhook.getDate();
            String time = webhook.getTime();
            String hour = webhook.getHour();
            String minute = webhook.getMinute();
            Elements trElements = webhook.getTrElements();
            webhook.insert();

            System.out.println(date);
            System.out.println(time);

            for (Element trElement : trElements) {

                Element elementCheck = trElement.select("td").first();
                if (elementCheck != null) {

                    //////////////  ITERATING THROUGH EACH DRIVER RECORD  //////////////

                    if (elementCheck.toString().equals("<td>+</td>")) {
                        //System.out.println(trElement);
                        Element flexRouteScreener = trElement.select("td").get(1);
                        if (flexRouteScreener.toString().contains("CX")) {
                            JSONObject driverJson = new JSONObject(trElement.select("td").get(15).text());

                            //System.out.println(driverJson);


                            //////////////  STORING EVERY FIELD IN THE DATABASE FOR PERMANENT ACCESS  //////////////

                            // Build a list of transporterDetailsList elements

                            Object driversDetails = driverJson.get("transporterDetailsList");
                            String[] driversDetailsStringArray = driversDetails.toString().replace("[", "").replace("]", "").split("},");
                            JSONObject stopsDetails = driverJson.getJSONObject("transporterDeliveryProgress");

                            //    Iterate through each element of "transporterDetailsList"

                            for (int i = 0; i < driversDetailsStringArray.length; i++) {
                                String driverDetailsString;
                                if (i != driversDetailsStringArray.length - 1) {
                                    driverDetailsString = driversDetailsStringArray[i] + "}";
                                } else {
                                    driverDetailsString = driversDetailsStringArray[i];
                                }
                                JSONObject driversDetailsJson = null;
                                try {
                                    driversDetailsJson = new JSONObject(driverDetailsString);
                                } catch (JSONException e) {
                                    System.out.println(driverDetailsString);
                                    System.out.println(driverJson);
                                }

                                //    match the key for "transporterDeliveryProgress" to the "id" from each element of "transporterDetailsList"
                                //    and log those fields as well

                                // This may throw an exception (if there isn't the same IDs for each section)
                                JSONObject stopsDetailsItem = stopsDetails.getJSONObject(driversDetailsJson.getString("id"));

                                JSONObject onRoadRisk = driverJson.getJSONObject("onRoadRisk");

                                //    log each and every field from the record itself

                                RawData thisRawData = new RawData(webhook.getUrlKey(), webhook.getUrl(), date, time, hour, minute,
                                        driverJson.getInt("countOfTotalStops"), driverJson.getInt("countOfCompletedStops"),
                                        driverJson.getString("routeId"), driverJson.getString("routeCode"),
                                        driverJson.getString("companyId"), driverJson.getInt("countTotal"),
                                        driverJson.getInt("countDelivered"), driverJson.getInt("countAttempted"),
                                        driverJson.getInt("countReturning"), driverJson.getInt("countDeliveredAndAttempted"),
                                        driverJson.getDouble("currentCompletion"), driverJson.getString("transporterId"),
                                        driverJson.getString("transporterName"), driversDetailsJson.getString("id"),
                                        driversDetailsJson.getString("name"), driversDetailsJson.getInt("numberOfPackages"),
                                        driverJson.getString("plannedDepartureTime"), driverJson.getString("serviceTypeName"),
                                        driversDetailsJson.getString("id"), stopsDetailsItem.getInt("countOfAttemptedPackages"),
                                        stopsDetailsItem.getInt("countOfCompletedPackages"), stopsDetailsItem.getInt("countOfCompletedStops"),
                                        stopsDetailsItem.getInt("countOfDeliveredPackages"), stopsDetailsItem.getInt("countOfMissingPackages"),
                                        stopsDetailsItem.getInt("countOfReattemptablePackages"), stopsDetailsItem.getInt("countOfRemainingPackages"),
                                        stopsDetailsItem.getInt("countOfReturnedPackages"), stopsDetailsItem.getInt("countOfReturningPackages"),
                                        stopsDetailsItem.getInt("countOfTotalPackages"), stopsDetailsItem.getInt("countOfTotalStops"),
                                        stopsDetailsItem.getInt("countOfUndeliverablePackages"), onRoadRisk.getInt("packagesAhead"),
                                        onRoadRisk.getInt("packagesBehind"), onRoadRisk.getString("riskModelUsed"), onRoadRisk.getString("riskStatus"),
                                        onRoadRisk.getInt("stopsAhead"), onRoadRisk.getInt("stopsBehind"), driverJson.getString("kittRiskStatus"),
                                        driverJson.getString("startTime"), driverJson.getString("departureTime"), driverJson.getString("calculationTime"),
                                        driverJson.getDouble("initialHour"), driverJson.getDouble("packageRate"),
                                        driverJson.getDouble("delta"), driverJson.getInt("remainingStops"),
                                        driverJson.getDouble("targetCompletion"), driverJson.getInt("timeLeft"),
                                        driverJson.getString("departureEndTime"), driverJson.getDouble("stopsRate"));
                                thisRawData.insert();
                            }
                        }
                    }
                }
            }
        }
    }
}
