package com.mlwallet.iosmlwalletpages;

import org.openqa.selenium.By;

public class MLWalletTransactionHistoryPage {
	
	public static By objRecentTransaction=By.xpath("//*[@name='Recent Transactions']");
	public static By objSeeMoreBtn=By.xpath("//*[@name='See More']");

	public static By objTransactionHistory = By.xpath("//*[@name='Transaction History']");
	
	public static By objBillsPayTab=By.xpath("//*[@name='Billspay']");
	public static By objeLoadTab=By.xpath("//*[@name='eLoad']");
	public static By objSendMoneyTab=By.xpath("//*[@name='Send Money']");
	public static By objWalletToWallet = By.xpath("//*[@name='Wallet to Wallet']");
	public static By objKwartaPadala = By.xpath("(//*[@name='Kwarta Padala'])[1]");
	public static By objSenderName = By.xpath("(//*[@name='Sender Name'])[3]");
	public static By objCashInTab=By.xpath("//*[@name='Cash In']");
	public static By objCashOutTab=By.xpath("//*[@name='Cash Out']");
	public static By objReceiveMoneyTab=By.xpath("//*[@name='Receive Money']");
	public static By objBalanceAdjustmentTab=By.xpath("//*[@name='Balance Adjustment']");
	public static By objMlShopTab=By.xpath("//*[@name='ML Shop']");
//	public static By objPayBillsHistory=By.xpath("//*[@text='Pay Bills' or @text='No Recent Transaction']");
	//public static By objPayBillsTransctionList=By.xpath("//*[@text='Pay Bills']/(following-sibling::android.widget.TextView)[2]");
	
	
	
	public static By objPayBillsTransctionList1(String moduleName)
	{
	   return	By.xpath("//*[@text='"+moduleName+"']/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup/(child::android.widget.TextView)[1]");
	}
	public static By objBillHistory(String billModule,String transaction)
	{
		return By.xpath("//*[@text='"+billModule+"' or @text='"+transaction+"']");
	}

	public static By  objAllTab = By.xpath("//*[@text='All']");

	public static By objFirstTransaction = By.xpath("(//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.widget.ScrollView']]/*/*/*/*[@class='android.view.ViewGroup' and ./*[./*[./*[@text]] and ./*[@text]]])[1]");
	
	public static By objTransactionDetails = By.xpath("//*[@name='Transaction Details']");
	
	public static By objReceiverMobileNo= By.xpath("(//*[@name='Receiver Mobile No.'])[3]");
	
	public static By objReceiverName = By.xpath("(//*[@name='Receiver Name'])[3]");
	
	public static By objPaymentMethod = By.xpath("(//*[@name='Payment Method'])[3]");

	public static By objLoadType = By.xpath("(//*[@name='Load Type'])[3]");
	
	public static By objBiller = By.xpath("(//*[@resource-id='Biller'])[2]");
	
	public static By objTransactionType = By.xpath("(//*[@name='Transaction Type'])[3]");
	
	public static By objAmount = By.xpath("(//*[@name='Amount to Send'])[3]");
	
	public static By objServiceFee = By.xpath("(//*[@name='Service Fee'])[3]");
	
	public static By objTotalAmount = By.xpath("(//*[@name='Total'])[3]");
	
	public static By objTotalCashIn = By.xpath("(//*[@name='Total Cash In'])[3]");
	
	public static By objTotalCashOut = By.xpath("(//*[@resource-id='Total Cash Out'])[2]");
	
	public static By objReferenceNumberInTransactionDetails = By.xpath("(//*[@name='Reference Number'])[3]");
	
	public static By objDate = By.xpath("(//*[XCUIElementTypeOther]/descendant::XCUIElementTypeStaticText)[5]");
	
	public static By objBank = By.xpath("(//*[@resource-id='Bank'])[2]");
	public static By objAmountReceived = By.xpath("(//*[@resource-id='Amount Received'])[2]");
}