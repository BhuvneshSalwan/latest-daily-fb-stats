package MainApp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONArray;
import org.json.JSONObject;

import Entities.DailyReport;
import HbConf.DBDao;

public class Common {

	public void generateReport(String account_id) {
		Common object = new Common();

		try {
			Object adIds[] = object.getAdIds(account_id);
			for (int i = 0; i < adIds.length; i++) {

				if (adIds[i] == null || adIds[i].equals("null")) {
					continue;
				} else {
					HashMap<String, String> adsDet = new HashMap<String, String>();
					adsDet = (HashMap<String, String>) adIds[i];

					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.DATE, -1);

					java.sql.Date start1 = new java.sql.Date(cal.getTimeInMillis());

					String url = "https://graph.facebook.com/v2.7/" + adsDet.get("id")
							+ "/insights?fields=campaign_name,impressions,inline_link_clicks,spend,ad_name,ad_id,adset_id,campaign_id,adset_name,actions,reach,total_action_value,action_values,clicks&action_attribution_windows=['28d_view','28d_click']&action_report_time=conversion&time_range={'since':'"
							+ start1 + "','until':'" + start1
							+ "'}&access_token=CAAWXmQeQZAmcBANADF6ew1ZBXAAifj7REIcHmbTVjkAR5q6GAnRjrpcuVhhV435LHMXpb8HzUKzQaUU4uwkxIl5xpYSgzUNog43JX4qxe0pqVBvjHZCsPfgIpRRGY7xfFC2hb1Hi1s9EH0IhQu4KlnTGcsdgIq5FN2ufeNHOeEB9YGck36aah1rPHrdi10ZD";

					System.out.println("Url is : " + url);

					URL obj = new URL(url);
					HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

					con.setRequestMethod("GET");

					int responseCode = con.getResponseCode();

					if (responseCode != 200) {
						System.out.println("sleeping");

						i--;
						Thread.sleep(2 * 60 * 1000);
						continue;
					}

					BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
					String inputLine;
					StringBuffer response = new StringBuffer();
					while ((inputLine = in.readLine()) != null) {
						response.append(inputLine);
					}
					in.close();

					if (response.toString().equals("[]")) {
					} else {
						JSONObject ob = new JSONObject(response.toString());

						JSONArray arr = new JSONArray(ob.getJSONArray("data").toString());
						System.out.println("length is" + arr.length());
						if (arr.length() != 0) {

							for (int n = 0; n < arr.length(); n++) {
								DailyReport report = new DailyReport();

								System.out.println(arr.getJSONObject(n));

								String startDate = arr.getJSONObject(n).getString("date_start");

								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

								report.setAdId(arr.getJSONObject(n).getString("ad_id"));
								report.setCampaignId(arr.getJSONObject(n).getString("campaign_id"));
								report.setAdsetId(arr.getJSONObject(n).getString("adset_id"));
								report.setCampaignName(arr.getJSONObject(n).getString("campaign_name"));
								report.setAdName(arr.getJSONObject(n).getString("ad_name"));
								report.setAdSetName(arr.getJSONObject(n).getString("adset_name"));
								report.setStartDate(sdf.parse(startDate));
								report.setEndDate(sdf.parse(startDate));
								report.setUpdated_at(new Date());
								report.setCreated_at(new Date());
								report.setImpressions(arr.getJSONObject(n).getInt("impressions"));
								
								if (arr.getJSONObject(n).has("clicks")) {
									report.setClicks(arr.getJSONObject(n).getInt("clicks"));
								} else {
									report.setClicks(0);
								}
								
								if (arr.getJSONObject(n).has("inline_link_clicks")) {
									report.setLink_click_value(arr.getJSONObject(n).getInt("inline_link_clicks"));
								} else {
									report.setLink_click_value(0);
								}
															
								report.setSpend(arr.getJSONObject(n).getDouble("spend"));
								report.setReach(arr.getJSONObject(n).getInt("reach"));

								report.setAccount_id(adsDet.get("account_id"));
								report.setCreative_Id(adsDet.get("creative_id"));
								report.setEffective_Status(adsDet.get("effective_status"));
								report.setStatus(adsDet.get("status"));

								if (arr.getJSONObject(n).has("action_values")) {
									if (!arr.getJSONObject(n).isNull("action_values")) {
										JSONArray ar = arr.getJSONObject(n).getJSONArray("action_values");
										if (ar.length() != 0) {
											for (int j = 0; j < ar.length(); j++) {
												if (ar.getJSONObject(j).getString("action_type")
														.equals("offsite_conversion.fb_pixel_purchase")) {

													if (ar.getJSONObject(j).has("value")) {
														report.setOC_fb_pixel_purchase_priceValue(
																ar.getJSONObject(j).getDouble("value"));
													} else {
														report.setOC_fb_pixel_purchase_priceValue(0);
													}

													if (ar.getJSONObject(j).has("28d_click")) {
														report.setFb_pixel_purchase_28d_click_value(
																ar.getJSONObject(j).getDouble("28d_click"));
													} else {
														report.setFb_pixel_purchase_28d_click_value(0);
													}
													if (ar.getJSONObject(j).has("28d_view")) {
														report.setFb_pixel_purchase_28d_view_value(
																ar.getJSONObject(j).getDouble("28d_view"));
													} else {
														report.setFb_pixel_purchase_28d_view_value(0);
													}
												}

											}
										}
									}
								} else {
									report.setOC_fb_pixel_purchase_priceValue(0);
									report.setFb_pixel_purchase_28d_click_value(0);
									report.setFb_pixel_purchase_28d_view_value(0);
								}

								if (arr.getJSONObject(n).has("actions")) {
									if (!arr.getJSONObject(n).isNull("actions")) {
										JSONArray ar = arr.getJSONObject(n).getJSONArray("actions");
										if (ar.length() != 0) {
											for (int j = 0; j < ar.length(); j++) {
												if (ar.getJSONObject(j).getString("action_type")
														.equals("offsite_conversion.fb_pixel_lead")) {

													if (ar.getJSONObject(j).has("value")) {
														report.setOC_fb_pixel_lead(ar.getJSONObject(j).getInt("value"));
													} else {
														report.setOC_fb_pixel_lead(0);
													}

													if (ar.getJSONObject(j).has("28d_click")) {
														report.setOC_fb_pixel_lead_28d_click(
																ar.getJSONObject(j).getInt("28d_click"));
													} else {
														report.setOC_fb_pixel_lead_28d_click(0);
													}

													if (ar.getJSONObject(j).has("28d_view")) {
														report.setOC_fb_pixel_lead_28d_view(
																ar.getJSONObject(j).getInt("28d_view"));
													} else {
														report.setOC_fb_pixel_lead_28d_view(0);
													}
												} else if (ar.getJSONObject(j).getString("action_type")
														.equals("leadgen.other")) {

													if (ar.getJSONObject(j).has("value")) {
														report.setLeadgenOther(ar.getJSONObject(j).getInt("value"));
													} else {
														report.setLeadgenOther(0);
													}

													if (ar.getJSONObject(j).has("28d_click")) {
														report.setLeadgenOther_28d_click(
																ar.getJSONObject(j).getInt("28d_click"));
													} else {
														report.setLeadgenOther_28d_click(0);
													}
													if (ar.getJSONObject(j).has("28d_view")) {
														report.setLeadgenOther_28d_view(
																ar.getJSONObject(j).getInt("28d_view"));
													} else {
														report.setLeadgenOther_28d_click(0);
													}
												} else if (ar.getJSONObject(j).getString("action_type")
														.equals("offsite_conversion.fb_pixel_purchase")) {

													if (ar.getJSONObject(j).has("value")) {
														report.setOC_fb_pixel_purchase_value(
																ar.getJSONObject(j).getInt("value"));
													} else {
														report.setOC_fb_pixel_purchase_value(0);
													}

													if (ar.getJSONObject(j).has("28d_click")) {
														report.setOC_fb_pixel_purchase_28d_click(
																ar.getJSONObject(j).getInt("28d_click"));
													} else {
														report.setOC_fb_pixel_purchase_28d_click(0);
													}
													if (ar.getJSONObject(j).has("28d_view")) {
														report.setOC_fb_pixel_purchase_28d_view(
																ar.getJSONObject(j).getInt("28d_view"));
													} else {
														report.setOC_fb_pixel_purchase_28d_view(0);
													}
												} /*else if (ar.getJSONObject(j).getString("action_type")
														.equals("link_click")) {

													if (ar.getJSONObject(j).has("value")) {
														report.setLink_click_value(ar.getJSONObject(j).getInt("value"));
													} else {
														report.setLink_click_value(0);
													}
												}*/ else if (ar.getJSONObject(j).getString("action_type")
														.equals("post_like")) {

													if (ar.getJSONObject(j).has("value")) {
														report.setPost_like_value(ar.getJSONObject(j).getInt("value"));
													} else {
														report.setPost_like_value(0);
													}
												} else if (ar.getJSONObject(j).getString("action_type")
														.equals("post_engagement")) {

													if (ar.getJSONObject(j).has("value")) {
														report.setPost_engagement(ar.getJSONObject(j).getInt("value"));
													} else {
														report.setPost_engagement(0);
													}
												} else if (ar.getJSONObject(j).getString("action_type")
														.equals("page_engagement")) {

													if (ar.getJSONObject(j).has("value")) {
														report.setPage_engagement(ar.getJSONObject(j).getInt("value"));
													} else {
														report.setPage_engagement(0);
													}
												}
											}
										}
									}
								}

								else {
									report.setOC_fb_pixel_lead(0);
									report.setOC_fb_pixel_lead_28d_click(0);
									report.setOC_fb_pixel_lead_28d_view(0);
									report.setLeadgenOther(0);
									report.setLeadgenOther_28d_view(0);
									report.setLeadgenOther_28d_click(0);
									report.setOC_fb_pixel_purchase_value(0);
									report.setOC_fb_pixel_purchase_28d_click(0);
									report.setOC_fb_pixel_purchase_28d_view(0);
									report.setLink_click_value(0);
									report.setPost_like_value(0);
									report.setPost_engagement(0);
									report.setPage_engagement(0);
								}
								DBDao.getDao().saveOrUpdate(report);
								// Thread.sleep(1000);
							}
						} // if length is not 0 }
					} // else
				}
				Thread.sleep(2000);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Object[] getAdIds(String account_id) throws Exception {
		Object adIds[];
		String url = "https://graph.facebook.com/v2.7/act_" + account_id
				+ "/ads?fields=id,status,effective_status,updated_time,creative&limit=2500&access_token=CAAWXmQeQZAmcBANADF6ew1ZBXAAifj7REIcHmbTVjkAR5q6GAnRjrpcuVhhV435LHMXpb8HzUKzQaUU4uwkxIl5xpYSgzUNog43JX4qxe0pqVBvjHZCsPfgIpRRGY7xfFC2hb1Hi1s9EH0IhQu4KlnTGcsdgIq5FN2ufeNHOeEB9YGck36aah1rPHrdi10ZD";

		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
		con.setRequestMethod("GET");

		int responseCode = con.getResponseCode();
		/*
		 * if (responseCode == 400) { Thread.sleep(5 * 60 * 1000); return
		 * getAdIds(); }
		 */ BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		if (response.toString().equals("[]")) {
			return null;
		} else {
			JSONObject ob = new JSONObject(response.toString());
			System.out.println(ob.getJSONArray("data"));
			JSONArray arr = new JSONArray(ob.getJSONArray("data").toString());
			adIds = new Object[arr.length()];
			System.out.println(adIds.length);
			for (int i = 0; i < arr.length(); i++) {
				HashMap<String, String> adsDet = new HashMap<String, String>();
				adsDet.put("id", arr.getJSONObject(i).getString("id"));
				adsDet.put("status", arr.getJSONObject(i).getString("status"));
				adsDet.put("effective_status", arr.getJSONObject(i).getString("effective_status"));
				adsDet.put("creative_id", arr.getJSONObject(i).getJSONObject("creative").getString("id"));
				adsDet.put("account_id", account_id);
				adIds[i] = adsDet;
			}
		}
		return adIds;
	}
}