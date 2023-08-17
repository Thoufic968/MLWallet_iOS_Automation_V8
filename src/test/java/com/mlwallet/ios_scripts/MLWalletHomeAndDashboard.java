package com.mlwallet.ios_scripts;


import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.business.mlwallet.MLWalletiOSBusinessLogic;

public class MLWalletHomeAndDashboard {
  
	private MLWalletiOSBusinessLogic MLWalletBusinessLogic;

//	@BeforeTest
	@BeforeSuite
	public void init() throws InterruptedException {
		MLWalletBusinessLogic = new MLWalletiOSBusinessLogic("mlwallet");
		
	
	
    }

// ====================================================================================================//


  /*  @Test(priority = 1) //pass
    public void mlWalletHomePageUIValidation() throws Exception {
        MLWalletBusinessLogic.mlWalletHomePageUIValidation_HD_TC_01();
    }

    @Test(priority = 2) //pass
    public void mlWalletLogOutFromHomePage_HD_TC_02() throws Exception {
        MLWalletBusinessLogic.mlWalletLogOutFromHomePage_HD_TC_02();
    }

    @Test(priority = 3) //pass
    public void mlWalletSettingsNavigationFromHomePage_HD_TC_03() throws Exception {
        MLWalletBusinessLogic.mlWalletSettingsNavigationFromHomePage_HD_TC_03();
    }

    @Test(priority = 4) //pass
    public void mlWalletHomePageEyeIconValidation_HD_TC_04() throws Exception {
        MLWalletBusinessLogic.mlWalletHomePageEyeIconValidation_HD_TC_04();
    } 

    @Test(priority = 5) //pass
    public void mlWalletAccountTierLevelVerification_HD_TC_05() throws Exception {
        MLWalletBusinessLogic.mlWalletAccountTierLevelVerification_HD_TC_05();
    }

    @Test(priority = 6) //pass
    public void mlWalletHomePageRecentTransaction_HD_TC_06() throws Exception {
        MLWalletBusinessLogic.mlWalletHomePageRecentTransaction_HD_TC_06();
    }

    @Test(priority = 7) //pass
    public void mlWalletHomePageWithdrawCash_HD_TC_07() throws Exception {
        MLWalletBusinessLogic.mlWalletHomePageWithdrawCash_HD_TC_07();
    }

    @Test(priority = 8) //pass
    public void mlWalletHomePageTopUpMLWallet_HD_TC_08() throws Exception {
        MLWalletBusinessLogic.mlWalletHomePageTopUpMLWallet_HD_TC_08();
    }

    @Test(priority = 9) //pass
    public void mlWalletHomePageShopHD_TC_09() throws Exception {
        MLWalletBusinessLogic.mlWalletHomePageShopHD_TC_09();
    } 

    @Test(priority = 10) //pass
    public void mlWalletHomePageKwartaPadalaRatesValidation_HD_TC_10() throws Exception {
        MLWalletBusinessLogic.mlWalletHomePageKwartaPadalaRatesValidation_HD_TC_10();
    } 

    @Test(priority = 11) // pass
    public void mlWalletVerificationTierPerksNavigationFromHomePageHamburgerMenu_HD_TC_11() throws Exception {
        MLWalletBusinessLogic.mlWalletVerificationTierPerksNavigationFromHomePageHamburgerMenu_HD_TC_11();
    }

    @Test(priority = 12) //pass
    public void mlWalletSupportPageNavigation_HD_TC_12() throws Exception {
        MLWalletBusinessLogic.mlWalletSupportPageNavigation_HD_TC_12();
    }

    @Test(priority = 13) //pass
    public void mlWalletAboutPageNavigation_HD_TC_13() throws Exception {
        MLWalletBusinessLogic.mlWalletAboutPageNavigation_HD_TC_13();
    }

    @Test(priority = 14) //pass
    public void mlWalletVerificationTierPerksAsSemiVerifiedUser_HD_TC_14() throws Exception {
        MLWalletBusinessLogic.mlWalletVerificationTierPerksAsSemiVerifiedUser_HD_TC_14();
    }

    @Test(priority = 15)
    public void mlWalletVerificationTierPerksAsFullyVerifiedUser_HD_TC_15() throws Exception {
        MLWalletBusinessLogic.mlWalletVerificationTierPerksAsFullyVerifiedUser_HD_TC_15();
    }
    
    @Test(priority = 16)
    public void mlWalletVerificationTierPerksAsBranchVerifiedUser_HD_TC_17() throws Exception {
        MLWalletBusinessLogic.mlWalletVerificationTierPerksAsBranchVerifiedUser_HD_TC_17();
    }*/

    @Test(priority = 17)
    public void mlWalletVerificationTierPerksAsBuyerTierUser_HD_TC_18() throws Exception {
        MLWalletBusinessLogic.mlWalletVerificationTierPerksAsBuyerTierUser_HD_TC_18();
    }

    @Test(priority = 18)
    public void mlWalletHamburgerMenuTransactionBtnValidation_HD_TC_19() throws Exception {
        MLWalletBusinessLogic.mlWalletHamburgerMenuTransactionBtnValidation_HD_TC_19();
    }

    @Test(priority = 19)
    public void mlWalletHomePageIIconValidationAsBranchVerifiedUser_HD_TC_20() throws Exception {
        MLWalletBusinessLogic.mlWalletHomePageIIconValidationAsBranchVerifiedTierUser_HD_TC_20();
    }

    @Test(priority = 20)
    public void mlWalletHomePageIIconValidationAsBuyerTierVerifiedUser_HD_TC_21() throws Exception {
        MLWalletBusinessLogic.mlWalletHomePageIIconValidationAsBuyerTierUser_HD_TC_21();
    }

    @Test(priority = 21)
    public void mlWalletHomePageIIconValidationAsSemiVerifiedTierUser_HD_TC_22() throws Exception {
        MLWalletBusinessLogic.mlWalletHomePageIIconValidationAsSemiVerifiedTierUser_HD_TC_22();
    }

    @Test(priority = 22)
    public void mlWalletHomePageIIconValidationAsFullyVerifiedTierUser_HD_TC_23() throws Exception {
        MLWalletBusinessLogic.mlWalletHomePageIIconValidationAsFullyVerifiedTierUser_HD_TC_23();
    }
    
  //  @Test(priority = 23)
    public void mlWalletHomePageDoYouHaveAnotherAcButtonFunctionality_HD_TC_24() throws Exception {
        MLWalletBusinessLogic.mlWalletHomePageDoYouHaveAnotherAcButtonFunctionality_HD_TC_24();
    }
    
  //  @Test(priority = 24) pass
    public void mlWalletVerificationAllTierPerksLoggingInAsSemiVerifiedUser_HD_TC_16() throws Exception {
        MLWalletBusinessLogic.mlWalletVerificationAllTierPerksLoggingInAsSemiVerifiedUser_HD_TC_16();
    }



}