package com.mlwallet.ios_scripts;


import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.business.mlwallet.MLWalletiOSBusinessLogic;
import com.propertyfilereader.PropertyFileReader;

public class MLWalletTopUpGames {

	private MLWalletiOSBusinessLogic MLWalletBusinessLogic;
	public static PropertyFileReader prop = new PropertyFileReader("//Users//admin/Desktop//MLWallet//properties//testdata.properties");

	@BeforeSuite
	public void init() throws InterruptedException {
		MLWalletBusinessLogic = new MLWalletiOSBusinessLogic("mlwallet");
	}
	
	
	//========================================================================================================================================

   

 /*   @Test(priority = 2)
    public void topUPGamesLoadSelectionScreenNavigation_TUG_TC_05() throws Exception {
        MLWalletBusinessLogic.topUPGamesLoadSelectionScreenNavigation_TUG_TC_05();
    }

    @Test(priority = 3)
    public void topUpGamesSuccessfulLoadTransaction_TUG_TC_06() throws Exception {
        MLWalletBusinessLogic.topUpGamesSuccessfulLoadTransaction_TUG_TC_06();
    }


    @Test(priority = 4)
    public void topUPGamesTransactionDetailsPageNavigation_TUG_TC_09() throws Exception {
        MLWalletBusinessLogic.topUPGamesTransactionDetailsPageNavigation_TUG_TC_09();
    }

    @Test(priority = 5)
    public void topUpGamesGameIDRequiredErrorMsgValidation_TUG_TC_10() throws Exception {
        MLWalletBusinessLogic.topUpGamesGameIDRequiredErrorMsgValidation_TUG_TC_10();
    }

    @Test(priority = 6)
    public void topUpGamesEmailAddressRequiredErrorMsgValidation_TUG_TC_11() throws Exception {
        MLWalletBusinessLogic.topUpGamesEmailAddressRequiredErrorMsgValidation_TUG_TC_11();
    }

    @Test(priority = 7)
    public void topUpGamesEmailAddressInvalidErrorMsgValidation_TUG_TC_12() throws Exception {
        MLWalletBusinessLogic.topUpGamesEmailAddressInvalidErrorMsgValidation_TUG_TC_12();
    }

    @Test(priority = 8)
    public void topUpGamesMobileNumberInvalidErrorMsgValidation_TUG_TC_13() throws Exception {
        MLWalletBusinessLogic.topUpGamesMobileNumberInvalidErrorMsgValidation_TUG_TC_13();
    }

    @Test(priority = 9)
    public void topUpGamesLandingPageNavigation_TUG_TC_14() throws Exception {
        MLWalletBusinessLogic.topUpGamesLandingPageNavigation_TUG_TC_14();
    }

    @Test(priority = 10)
    public void topUpGamesTransactionDetailsPageUIValidation_TUG_TC_15() throws Exception {
        MLWalletBusinessLogic.topUpGamesTransactionDetailsPageUIValidation_TUG_TC_15();
    }

    @Test(priority = 11)
    public void topUpGamesSaveToFavorites_TUG_TC_16() throws Exception {
        MLWalletBusinessLogic.topUpGamesSaveToFavorites_TUG_TC_16();
    }

    @Test(priority = 12)
    public void topUpGamesSelectRecipientWithSavedFavorites_TUG_TC_29() throws Exception {
        MLWalletBusinessLogic.topUpGamesSelectRecipientWithSavedFavorites_TUG_TC_29();
    }

    @Test(priority = 13)
    public void topUpGamesSRecipientAlreadySavedMsgValidation_TUG_TC_17() throws Exception {
        MLWalletBusinessLogic.topUpGamesSRecipientAlreadySavedMsgValidation_TUG_TC_17();
    }

    @Test(priority = 14) 
    public void topUpGamesRemoveFromFavorites_TUG_TC_18() throws Exception {
        MLWalletBusinessLogic.topUpGamesRemoveFromFavorites_TUG_TC_18();
    }

    @Test(priority = 15) 
    public void topUpGamesTransactionValidationInTransactionHistory_TUG_TC_19() throws Exception {
        MLWalletBusinessLogic.topUpGamesTransactionValidationInTransactionHistory_TUG_TC_19();
    }

    @Test(priority = 16)
    public void topUpGamesTopUpAgainFunctionalityAfterFirstSuccessfulTransaction_TUG_TC_27() throws Exception {
        MLWalletBusinessLogic.topUpGamesTopUpAgainFunctionalityAfterFirstSuccessfulTransaction_TUG_TC_27();
    }

    @Test(priority = 17)
    public void topUpGamesRecentTransactionValidation_TUG_TC_28() throws Exception {
        MLWalletBusinessLogic.topUpGamesRecentTransactionValidation_TUG_TC_28();
    }*/

}