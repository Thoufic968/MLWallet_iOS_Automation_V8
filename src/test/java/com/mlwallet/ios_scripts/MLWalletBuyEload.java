package com.mlwallet.ios_scripts;


import com.business.mlwallet.MLWalletiOSBusinessLogic;
import com.propertyfilereader.PropertyFileReader;

import org.testng.annotations.*;


public class MLWalletBuyEload {

	private MLWalletiOSBusinessLogic MLWalletBusinessLogic;
	public static PropertyFileReader prop = new PropertyFileReader("//Users//admin/Desktop//MLWallet//properties//testdata.properties");

	@BeforeSuite
	public void init() throws InterruptedException {
		MLWalletBusinessLogic = new MLWalletiOSBusinessLogic("mlwallet");
	}




    //===================================buy eload=======================================


    @Test(priority = 1) //pass
    public void buyELoadTransactionDetails_BE_TC_01() throws Exception
    {
        MLWalletBusinessLogic.buyELoadTransactionDetails_BE_TC_01(prop.getproperty("Branch_Verified"),4);
    }

    @Test(priority = 2) //pass
    public void buyELoadInvalidMobileNumber_BE_TC_02() throws Exception
    {
        MLWalletBusinessLogic.buyELoadInvalidMobileNumber_BE_TC_02();
    }

    @Test(priority = 3) //pass
    public void buyELoadWithoutInputMobNumber_BE_TC_03() throws Exception
    {
        MLWalletBusinessLogic.buyELoadWithoutInputMobNumber_BE_TC_03();
    }

    @Test(priority = 4) //pass
    public void buyELoadWithoutTelecommunicationSelected_BE_TC_04() throws Exception
    {
        MLWalletBusinessLogic.buyELoadWithoutTelecommunicationSelected_BE_TC_04();
    }

//    @Test(priority = 5)  //pending
//    public void buyELoadInsufficientBalance() throws Exception
//    {
//        MLWalletBusinessLogic.buyELoadInsufficientBalance_BE_TC_05(prop.getproperty("Branch_Verified"),4);
//    }

//    @Test(priority = 6)  //pending
//    public void buyELoadMaxLimitPerTransaction_BE_TC_09() throws Exception {
//        MLWalletBusinessLogic.buyELoadMaxLimitPerTransaction_BE_TC_09(prop.getproperty("Fully_Verified"),3);
//    }

    @Test(priority = 7) //pass
    public void buyELoadTransactionPageUIValidation_BE_TC_10() throws Exception {
        MLWalletBusinessLogic.buyELoadTransactionPageUIValidation_BE_TC_10(prop.getproperty("Branch_Verified"));
    }

    @Test(priority = 8) //pass
    public void buyELoadNextButtonFunctionalityOnELoadTransactionPage_BE_TC_11() throws Exception {
        MLWalletBusinessLogic.buyELoadNextButtonFunctionalityOnELoadTransactionPage_BE_TC_11(prop.getproperty("Branch_Verified"),prop.getproperty("sunMobileNumber"),4,"true");
    }

    @Test(priority = 9) //pass
    public void buyELoadLoadSelectionPageBackBtnValidation_BE_TC_12() throws Exception {
        MLWalletBusinessLogic.buyELoadLoadSelectionPageBackBtnValidation_BE_TC_12();
    }

    @Test(priority = 10) //pass
    public void buyELoadLoadSelectionPageUIValidation_BE_TC_13() throws Exception {
        MLWalletBusinessLogic.buyELoadLoadSelectionPageUIValidation_BE_TC_13(prop.getproperty("Branch_Verified"),4);
    }

    @Test(priority = 11) //pass
    public void buyELoadLoadSelectionChangeBtnFunctionality_BE_TC_14() throws Exception {
        MLWalletBusinessLogic.buyELoadLoadSelectionChangeBtnFunctionality_BE_TC_14(prop.getproperty("Branch_Verified"),4);
    }

    @Test(priority = 12) //pass
    public void buyELoadTransactionDetailsPageUIValidation__BE_TC_15() throws Exception {
        MLWalletBusinessLogic.buyELoadTransactionDetailsPageUIValidation_BE_TC_15(prop.getproperty("Branch_Verified"),4);
    }

    @Test(priority = 13)// pass
    public void buyELoadSelectFromContactsBtnFunctionality_BE_TC_16() throws Exception {
        MLWalletBusinessLogic.buyELoadSelectFromContactsBtnFunctionality_BE_TC_16(prop.getproperty("Branch_Verified"),"true");
    }

    @Test(priority = 14) //pass
    public void buyELoadContactsPageUIValidation_BE_TC_17() throws Exception {
        MLWalletBusinessLogic.buyELoadContactsPageUIValidation_BE_TC_17(prop.getproperty("Branch_Verified"));
    }

    @Test(priority = 15) //pass
    public void buyELoadContactsPageBackBtnValidation_BE_TC_18() throws Exception {
        MLWalletBusinessLogic.buyELoadContactsPageBackBtnValidation_BE_TC_18(prop.getproperty("Branch_Verified"));
    }

//    @Test(priority = 16)
//    public void buyELoadSearchFieldValidation_BE_TC_19() throws Exception {
//        MLWalletBusinessLogic.buyELoadSearchFieldValidation_BE_TC_19(prop.getproperty("Branch_Verified"));
//    }

//    @Test(priority = 17)
//    public void buyELoadAddContactToFavorites_BE_TC_20() throws Exception {
//        MLWalletBusinessLogic.buyELoadAddContactToFavorites_BE_TC_20(prop.getproperty("Branch_Verified"));
//    }
//
//    @Test(priority = 18)
//    public void buyELoadAddedContactToFavoritesValidation_BE_TC_21() throws Exception {
//        MLWalletBusinessLogic.buyELoadAddedContactToFavoritesValidation_BE_TC_21(prop.getproperty("Branch_Verified"));
//    }
//
//    @Test(priority = 19)
//    public void buyELoadSearchAddedFavoriteContact_BE_TC_22() throws Exception {
//        MLWalletBusinessLogic.buyELoadSearchAddedFavoriteContact_BE_TC_22(prop.getproperty("Branch_Verified"));
//    }
//
//    @Test(priority = 20)
//    public void buyELoadSearchUnFavoriteContact_BE_TC_23() throws Exception {
//        MLWalletBusinessLogic.buyELoadSearchUnFavoriteContact_BE_TC_23(prop.getproperty("Branch_Verified"));
//    }

    @Test(priority = 21) // pass
    public void buyELoadPromoConfirmationPopupValidation_BE_TC_24() throws Exception {
        MLWalletBusinessLogic.buyELoadPromoConfirmationPopupValidation_BE_TC_24(prop.getproperty("Branch_Verified"),4);
    }

    @Test(priority = 22) //pass
    public void buyELoadOTPPageUIValidation_BE_TC_25() throws Exception {
        MLWalletBusinessLogic.buyELoadOTPPageUIValidation_BE_TC_25(prop.getproperty("Branch_Verified"),4);
    }

    @Test(priority = 23) //pass
    public void buyELoadSuccessfulTransactionUIValidation_BE_TC_26() throws Exception {
        MLWalletBusinessLogic.buyELoadSuccessfulTransactionUIValidation_BE_TC_26(prop.getproperty("Branch_Verified"),4,"true");
    }

    @Test(priority = 24) //pass
    public void buyELoadRecentTransactionUIValidation_BE_TC_27() throws Exception {
        MLWalletBusinessLogic.buyELoadRecentTransactionUIValidation_BE_TC_27("true");
    }

    @Test(priority = 25) //pass
    public void buyELoadTransactionDetailsUIValidation_BE_TC_28() throws Exception {
        MLWalletBusinessLogic.buyELoadTransactionDetailsUIValidation_BE_TC_28();
    }

//    @Test(priority = 26)
//    public void buyELoadLocationPopupValidation_BE_TC_51() throws Exception {
//        MLWalletBusinessLogic.buyELoadLocationPopupValidation_BE_TC_51(prop.getproperty("Branch_Verified"), 4);
//    }
//
//    @Test(priority = 27)
//    public void buyELoadLocationDenyFunctionality_BE_TC_52() throws Exception {
//        MLWalletBusinessLogic.buyELoadLocationDenyFunctionality_BE_TC_52(prop.getproperty("Branch_Verified"), 4);
//    }
//
//    @Test(priority = 28)
//    public void buyELoadLocationPermissionDenyCloseBtnFunctionality_BE_TC_53() throws Exception {
//        MLWalletBusinessLogic.buyELoadLocationPermissionDenyCloseBtnFunctionality_BE_TC_53(prop.getproperty("Branch_Verified"), 4);
//    }
//
//    @Test(priority = 29)
//    public void buyELoadLocationPermissionDenyOpenSettingsBtnFunctionality_BE_TC_54() throws Exception {
//        MLWalletBusinessLogic.buyELoadLocationPermissionDenyOpenSettingsBtnFunctionality_BE_TC_54(prop.getproperty("Branch_Verified"), 4);
//    }
//
//    @Test(priority = 30)
//    public void buyELoadLocationPopUpAllowFunctionality_BE_TC_55() throws Exception {
//        MLWalletBusinessLogic.buyELoadLocationPopUpAllowFunctionality_BE_TC_55(prop.getproperty("Branch_Verified"), 4);
//    }
//
//    @Test(priority = 31)
//    public void buyELoadContactsPermissionPopup_BE_TC_56() throws Exception {
//        MLWalletBusinessLogic.buyELoadContactsPermissionPopup_BE_TC_56(prop.getproperty("Branch_Verified"));
//    }
//
//    @Test(priority = 32)
//    public void buyELoadContactPermissionPopupAllowBtnFunctionality_BE_TC_57() throws Exception {
//        MLWalletBusinessLogic.buyELoadContactPermissionPopupAllowBtnFunctionality_BE_TC_57();
//    }
//
//    @Test(priority = 33)
//    public void buyELoadContactPermissionPopupDenyBtnFunctionality_BE_TC_58() throws Exception {
//        MLWalletBusinessLogic.buyELoadContactPermissionPopupDenyBtnFunctionality_BE_TC_58();
//    }
//
//    @Test(priority = 34)
//    public void buyELoadContactPermissionTwoDenyBtnFunctionality_BE_TC_59() throws Exception {
//        MLWalletBusinessLogic.buyELoadContactPermissionTwoDenyBtnFunctionality_BE_TC_59();
//    }
//
//    @Test(priority = 35)
//    public void buyELoadContactPermissionTwoAllowBtnFunctionality_BE_TC_60() throws Exception {
//        MLWalletBusinessLogic.buyELoadContactPermissionTwoAllowBtnFunctionality_BE_TC_60();
//    }
//
//    @Test(priority = 36)
//    public void buyELoadInternetInterruptionWhileEnteringOTP_BE_TC_61() throws Exception {
//        MLWalletBusinessLogic.buyELoadInternetInterruptionWhileEnteringOTP_BE_TC_61(prop.getproperty("Branch_Verified"), 4);
//    }
//
//    @Test(priority = 37)
//    public void buyELoadLocationPermissionAskMeLaterButtonFunctionality_BE_TC_62() throws Exception {
//        MLWalletBusinessLogic.buyELoadLocationPermissionAskMeLaterButtonFunctionality_BE_TC_62();
//    }
//
//    @Test(priority = 38)
//    public void buyELoadLocationPermissionTwoDenyBtnFunctionality_BE_TC_63() throws Exception {
//        MLWalletBusinessLogic.buyELoadLocationPermissionTwoDenyBtnFunctionality_BE_TC_63();
//    }
//
//    @Test(priority = 39)
//    public void buyELoadLocationPermissionTwoAllowBtnFunctionality_BE_TC_64() throws Exception {
//        MLWalletBusinessLogic.buyELoadLocationPermissionTwoAllowBtnFunctionality_BE_TC_64();
//    }
//
//    @Test(priority = 40)
//    public void buyELoadContactPopupNotDisplayedAfterClickingODenyButtonValidation_BE_TC_65() throws Exception {
//        MLWalletBusinessLogic.buyELoadContactPopupNotDisplayedAfterClickingODenyButtonValidation_BE_TC_65();
//    }
//
//    @Test(priority = 41)
//    public void buyELoadNewTransactionBtnFunctionality_BE_TC_66() throws Exception {
//        MLWalletBusinessLogic.buyELoadNewTransactionBtnFunctionality_BE_TC_66();
//    }

//    @Test(priority = 42)
//    public void buyELoadTransactionValidationAfterMinimizingApp_BE_TC_069() throws Exception {
//        MLWalletBusinessLogic.buyELoadTransactionValidationAfterMinimizingApp_BE_TC_069(prop.getproperty("Branch_Verified"), 4);
//    }






}