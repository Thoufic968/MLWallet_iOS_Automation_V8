package com.mlwallet.ios_scripts;



import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.business.mlwallet.MLWalletiOSBusinessLogic;

public class MLWallet_iOS_TestCases {

	private MLWalletiOSBusinessLogic MLWalletBusinessLogic;

	@BeforeTest
	public void init() throws InterruptedException {
		MLWalletBusinessLogic = new MLWalletiOSBusinessLogic("mlwallet");
	}

	@Test(priority = 1)
	public void mlwallet_login_scenario() throws Exception
	{
		MLWalletBusinessLogic.mlWalletLogin("9999999996");
	}

//	@AfterTest
	public void After() {
		MLWalletBusinessLogic.tearDown();
	}
}
