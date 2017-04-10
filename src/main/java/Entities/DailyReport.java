package Entities;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "new_fb_daily_test_stats")
public class DailyReport {
	
		@Id
		@GeneratedValue
		@Column(name = "id")
		private int id;
		
		@Column(name = "account_id")
		private String account_id;

		public String getAccount_id() {
			return account_id;
		}

		public void setAccount_id(String account_id) {
			this.account_id = account_id;
		}
		
		@Column(name = "effective_status")
		private String effective_status;

		public String getEffective_Status() {
			return effective_status;
		}

		public void setEffective_Status(String effective_status) {
			this.effective_status = effective_status;
		}
		
		@Column(name = "status")
		private String status;

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}
		
		@Column(name = "creative_id")
		private String creative_id;

		public String getCreative_Id() {
			return creative_id;
		}

		public void setCreative_Id(String creative_id) {
			this.creative_id = creative_id;
		}

		@Column(name = "campaign_name")
		private String campaignName;
		
		@Column(name = "ad_id")
		private String adId;
		
		@Column(name = "adset_id")
		private String adsetId;
		
		@Column(name = "campaign_id")
		private String campaignId;

		@Column(name = "adset_name")
		private String adSetName;

		@Column(name = "ad_name")
		private String adName;
		
		@Column(name = "start_date")
		@Temporal(TemporalType.DATE)
		private Date startDate;
		
		@Column(name = "end_date")
		@Temporal(TemporalType.DATE)
		private Date endDate;
		
		@Column(name = "created_at")
		@Temporal(TemporalType.DATE)
		private Date created_at;
		
		@Column(name = "updated_at")
		@Temporal(TemporalType.DATE)
		private Date updated_at;

		@Column(name = "impressions")
		private int impressions;

		@Column(name = "clicks")
		private int clicks;
		
		@Column(name = "spend")
		private double spend;
		
		@Column(name = "fb_pixel_lead")
		private double OC_fb_pixel_lead;
		
		@Column(name = "fb_pixel_lead_28d_view")
		private double OC_fb_pixel_lead_28d_view;
		
		@Column(name = "fb_pixel_lead_28d_click")
		private double OC_fb_pixel_lead_28d_click;
		
		@Column(name = "leadgenOther")
		private double leadgenOther;
		
		@Column(name = "fb_pixel_purchase_28d_click_value")
		private double fb_pixel_purchase_28d_click_value;
		
		@Column(name = "fb_pixel_purchase_28d_view_value")
		private double fb_pixel_purchase_28d_view_value;
		
		public double getFb_pixel_purchase_28d_click_value() {
			return fb_pixel_purchase_28d_click_value;
		}

		public void setFb_pixel_purchase_28d_click_value(double fb_pixel_purchase_28d_click_value) {
			this.fb_pixel_purchase_28d_click_value = fb_pixel_purchase_28d_click_value;
		}

		public double getFb_pixel_purchase_28d_view_value() {
			return fb_pixel_purchase_28d_view_value;
		}

		public void setFb_pixel_purchase_28d_view_value(double fb_pixel_purchase_28d_view_value) {
			this.fb_pixel_purchase_28d_view_value = fb_pixel_purchase_28d_view_value;
		}


		
		public double getOC_fb_pixel_lead() {
			return OC_fb_pixel_lead;
		}

		public void setOC_fb_pixel_lead(double oC_fb_pixel_lead) {
			OC_fb_pixel_lead = oC_fb_pixel_lead;
		}

		public double getLeadgenOther() {
			return leadgenOther;
		}

		public void setLeadgenOther(double leadgenOther) {
			this.leadgenOther = leadgenOther;
		}

		@Column(name = "leadgenOther_28d_click")
		private double leadgenOther_28d_click;
		
		@Column(name = "leadgenOther_28d_view")
		private double leadgenOther_28d_view;
		
		
		
		@Column(name = "fb_pixel_purchase_28d_click")
		private double OC_fb_pixel_purchase_28d_click;
		
		@Column(name = "fb_pixel_purchase_28d_view")
		private double OC_fb_pixel_purchase_28d_view;
		
		@Column(name = "fb_pixel_purchase")
		private double OC_fb_pixel_purchase_value;
		
		@Column(name = "fb_pixel_purchase_value")
		private double OC_fb_pixel_purchase_priceValue;
		
		@Column(name = "link_click")
		private double link_click_value;
		
		@Column(name = "post_like")
		private double post_like_value;
		
		@Column(name = "post_engagement")
		private double post_engagement;
		
		@Column(name = "page_engagement")
		private double page_engagement;
		
		public double getPage_engagement() {
			return page_engagement;
		}

		public void setPage_engagement(double page_engagement) {
			this.page_engagement = page_engagement;
		}

		public double getPost_engagement() {
			return post_engagement;
		}

		public void setPost_engagement(double post_engagement) {
			this.post_engagement = post_engagement;
		}

		public double getPost_like_value() {
			return post_like_value;
		}

		public void setPost_like_value(double post_like_value) {
			this.post_like_value = post_like_value;
		}

		public double getLink_click_value() {
			return link_click_value;
		}

		public void setLink_click_value(double link_click_value) {
			this.link_click_value = link_click_value;
		}

		public double getOC_fb_pixel_purchase_priceValue() {
			return OC_fb_pixel_purchase_priceValue;
		}

		public void setOC_fb_pixel_purchase_priceValue(double oC_fb_pixel_purchase_priceValue) {
			OC_fb_pixel_purchase_priceValue = oC_fb_pixel_purchase_priceValue;
		}

		public double getOC_fb_pixel_purchase_28d_click() {
			return OC_fb_pixel_purchase_28d_click;
		}

		public void setOC_fb_pixel_purchase_28d_click(double oC_fb_pixel_purchase_28d_click) {
			OC_fb_pixel_purchase_28d_click = oC_fb_pixel_purchase_28d_click;
		}

		public double getOC_fb_pixel_purchase_28d_view() {
			return OC_fb_pixel_purchase_28d_view;
		}

		public void setOC_fb_pixel_purchase_28d_view(double oC_fb_pixel_purchase_28d_view) {
			OC_fb_pixel_purchase_28d_view = oC_fb_pixel_purchase_28d_view;
		}

		public double getOC_fb_pixel_purchase_value() {
			return OC_fb_pixel_purchase_value;
		}

		public void setOC_fb_pixel_purchase_value(double oC_fb_pixel_purchase_value) {
			OC_fb_pixel_purchase_value = oC_fb_pixel_purchase_value;
		}

		public double getLeadgenOther_28d_click() {
			return leadgenOther_28d_click;
		}

		public void setLeadgenOther_28d_click(double leadgenOther_28d_click) {
			this.leadgenOther_28d_click = leadgenOther_28d_click;
		}

		public double getLeadgenOther_28d_view() {
			return leadgenOther_28d_view;
		}

		public void setLeadgenOther_28d_view(double leadgenOther_28d_view) {
			this.leadgenOther_28d_view = leadgenOther_28d_view;
		}

		public double getOC_fb_pixel_lead_28d_view() {
			return OC_fb_pixel_lead_28d_view;
		}

		public void setOC_fb_pixel_lead_28d_view(double oC_fb_pixel_lead_28d_view) {
			OC_fb_pixel_lead_28d_view = oC_fb_pixel_lead_28d_view;
		}

		public double getOC_fb_pixel_lead_28d_click() {
			return OC_fb_pixel_lead_28d_click;
		}

		public void setOC_fb_pixel_lead_28d_click(double oC_fb_pixel_lead_28d_click) {
			OC_fb_pixel_lead_28d_click = oC_fb_pixel_lead_28d_click;
		}

		@Column(name = "reach")
		private int reach;
		
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getCampaignName() {
			return campaignName;
		}

		public void setCampaignName(String campaignName) {
			this.campaignName = campaignName;
		}

		public String getAdId() {
			return adId;
		}

		public void setAdId(String adId) {
			this.adId = adId;
		}

		public String getAdsetId() {
			return adsetId;
		}

		public void setAdsetId(String adsetId) {
			this.adsetId = adsetId;
		}

		public String getCampaignId() {
			return campaignId;
		}

		public void setCampaignId(String campaignId) {
			this.campaignId = campaignId;
		}

		public String getAdSetName() {
			return adSetName;
		}

		public void setAdSetName(String adSetName) {
			this.adSetName = adSetName;
		}

		public String getAdName() {
			return adName;
		}

		public void setAdName(String adName) {
			this.adName = adName;
		}

		public Date getStartDate() {
			return startDate;
		}

		public void setStartDate(Date startDate) {
			this.startDate = startDate;
		}

		public Date getEndDate() {
			return endDate;
		}

		public void setEndDate(Date endDate) {
			this.endDate = endDate;
		}

		public Date getCreated_at() {
			return created_at;
		}

		public void setCreated_at(Date created_at) {
			this.created_at = created_at;
		}

		public Date getUpdated_at() {
			return updated_at;
		}

		public void setUpdated_at(Date updated_at) {
			this.updated_at = updated_at;
		}

		public int getImpressions() {
			return impressions;
		}

		public void setImpressions(int impressions) {
			this.impressions = impressions;
		}

		public int getClicks() {
			return clicks;
		}

		public void setClicks(int clicks) {
			this.clicks = clicks;
		}

		public double getSpend() {
			return spend;
		}

		public void setSpend(double spend) {
			this.spend = spend;
		}

		public int getReach() {
			return reach;
		}

		public void setReach(int reach) {
			this.reach = reach;
		}
}
