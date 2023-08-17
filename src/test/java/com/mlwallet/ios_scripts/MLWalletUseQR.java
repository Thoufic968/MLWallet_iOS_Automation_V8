package com.mlwallet.ios_scripts;


import com.business.mlwallet.MLWalletiOSBusinessLogic;
import com.driverInstance.DriverInstance;
import com.propertyfilereader.PropertyFileReader;

import org.testng.annotations.*;



public class MLWalletUseQR {


	private MLWalletiOSBusinessLogic MLWalletBusinessLogic;
	public static PropertyFileReader prop = new PropertyFileReader("//Users//admin/Desktop//MLWallet//properties//testdata.properties");

	@BeforeSuite
	public void init() throws InterruptedException {
		MLWalletBusinessLogic = new MLWalletiOSBusinessLogic("mlwallet");
	}





 //   @Test(priority = 1) pass
    public void useQRGenerateQRCode_QR_TC_01() throws Exception {
        MLWalletBusinessLogic.useQRGenerateQRCode_QR_TC_01("true");
    }

  //  @Test(priority = 2) pass
    public void useQRSendMoneyToAnyMLWalletUser_QR_TC_02() throws Exception {
        MLWalletBusinessLogic.useQRSendMoneyToAnyMLWalletUser_QR_TC_02();
    }

   //  @Test(priority = 3) pass
    public void useQRInvalidQRCodeValidation_QR_TC_03() throws Exception {
        MLWalletBusinessLogic.useQRInvalidQRCodeValidation_QR_TC_03();
    }

     @Test(priority = 4) 
    public void useQR_WalletToWalletReceivedMoneyValidation_QR_TC_04() throws Exception {
        MLWalletBusinessLogic.useQR_WalletToWalletReceivedMoneyValidation_QR_TC_04("10");
    }

   // @Test(priority = 5) pass
    public void useQRScanningReceiverQRCOde_QR_TC_05() throws Exception {
        MLWalletBusinessLogic.useQRScanningReceiverQRCOde_QR_TC_05();
    }

    // @Test(priority = 6) pass
    public void useQRPageUIValidation_QR_TC_06() throws Exception {
        MLWalletBusinessLogic.useQRPageUIValidation_QR_TC_06();
    }

   //   @Test(priority = 7) pass
    public void useQRPageBackBtnValidation_QR_TC_07() throws Exception {
        MLWalletBusinessLogic.useQRPageBackBtnValidation_QR_TC_07();
    }

   // @Test(priority = 8) pass
    public void useQR_GenerateQRPageBackBtnValidation_QR_TC_08() throws Exception {
        MLWalletBusinessLogic.useQR_GenerateQRPageBackBtnValidation_QR_TC_08();
    }

     //  @Test(priority = 9) passs
    public void useQR_GenerateQRPageUIValidation_QR_TC_11() throws Exception {
        MLWalletBusinessLogic.useQR_GenerateQRPageUIValidation_QR_TC_11();
    }

     //  @Test(priority = 10) pass
    public void useQR_ToMLWalletScreenUIValidation_QR_TC_12() throws Exception {
        MLWalletBusinessLogic.useQR_ToMLWalletScreenUIValidation_QR_TC_12();
    }

    //    @Test(priority = 11) pass
    public void useQR_EmptyAmountFieldValidation_QR_TC_13() throws Exception {
        MLWalletBusinessLogic.useQR_EmptyAmountFieldValidation_QR_TC_13();
    }

    //@Test(priority = 12) passs
    public void useQR_ConfirmDetailsScreenUIValidation_QR_TC_14() throws Exception {
        MLWalletBusinessLogic.useQR_ConfirmDetailsScreenUIValidation_QR_TC_14("10");
    }

    //@Test(priority = 13) pass
    public void useQR_OneTimePinScreenUIValidation_QR_TC_15() throws Exception {
        MLWalletBusinessLogic.useQR_OneTimePinScreenUIValidation_QR_TC_15();
    }

    //        @Test(priority = 14) pass
    public void useQR_SendMoneyToMlWalletSuccessScreenUIValidation_QR_TC_16() throws Exception {
        MLWalletBusinessLogic.useQR_SendMoneyToMlWalletSuccessScreenUIValidation_QR_TC_16();
    }

    // @Test(priority = 15) pass
    public void useQR_RecentTransactionInHomeScreenUIValidation_QR_TC_17() throws Exception {
        MLWalletBusinessLogic.useQR_RecentTransactionInHomeScreenUIValidation_QR_TC_17();
    }

   // @Test(priority = 16) pass
    public void useQR_TransactionDetailsScreenUIValidation_QR_TC_18() throws Exception {
        MLWalletBusinessLogic.useQR_TransactionDetailsScreenUIValidation_QR_TC_18();
    }

   // @Test(priority = 17) pass
    public void useQR_CameraPermissionPopupValidation_QR_TC_19() throws Exception {
        MLWalletBusinessLogic.useQR_CameraPermissionPopupValidation_QR_TC_19();
    }

   // @Test(priority = 18) pass
    public void useQR_CameraPermissionPopupDenyButtonFunctionality_QR_TC_20() throws Exception {
        MLWalletBusinessLogic.useQR_CameraPermissionPopupDenyButtonFunctionality_QR_TC_20();
    }

   // @Test(priority = 19) pass
    public void useQR_CameraPermissionPopupOnlyThisTimeButtonFunctionality_QR_TC_21() throws Exception {
        MLWalletBusinessLogic.useQR_CameraPermissionPopupOnlyThisTimeButtonFunctionality_QR_TC_21();
    }

    // @Test(priority = 20) pending
    public void useQR_CameraPermissionPopupWhileUsingTheAppButtonFunctionality_QR_TC_22() throws Exception {
        MLWalletBusinessLogic.useQR_CameraPermissionPopupWhileUsingTheAppButtonFunctionality_QR_TC_22();
    }

//     //@Test(priority = 21) Non Feasible
//    public void useQR_SendMoneyToMLWalletInternetInterruptionWhileEnteringOTP_QR_TC_23() throws Exception {
//        MLWalletBusinessLogic.useQR_SendMoneyToMLWalletInternetInterruptionWhileEnteringOTP_QR_TC_23();
//    }

   // @Test(priority = 22) Non Feasible
    public void useQR_ScanQRCodeAfterClickingOnDenyButton_QR_TC_24() throws Exception {
        MLWalletBusinessLogic.useQR_ScanQRCodeAfterClickingOnDenyButton_QR_TC_24();
    }

   //  @Test(priority = 23) Non Feasible
    public void UseQR_SendMoneyToMLWalletLocationPopupValidation_QR_TC_026() throws Exception {
        MLWalletBusinessLogic.UseQR_SendMoneyToMLWalletLocationPopupValidation_QR_TC_026();
    }

    // @Test(priority = 24)  Non Feasible
    public void useQR_SendMoneyToMLWalletLocationDenyFunctionality_QR_TC_027() throws Exception {
        MLWalletBusinessLogic.useQR_SendMoneyToMLWalletLocationDenyFunctionality_QR_TC_027();
    }

 

   // @Test(priority = 28)  Non Feasible
    public void useQR_CameraPermissionRequiredPopupValidation_QR_TC_31() throws Exception {
        MLWalletBusinessLogic.useQR_CameraPermissionRequiredPopupValidation_QR_TC_31();
    }

   //   @Test(priority = 29) pass
    public void useQR_TransactionValidationAfterMinimizingTheApp_QR_TC_33() throws Exception {
        MLWalletBusinessLogic.useQR_TransactionValidationAfterMinimizingTheApp_QR_TC_33();
    }

    //  @Test(priority = 30)
    public void useQR_SendMoneyToMLWalletWhenReceiverQRCodeCapturedByCamera_QR_TC_40() throws Exception {
        MLWalletBusinessLogic.useQR_SendMoneyToMLWalletWhenReceiverQRCodeCapturedByCamera_QR_TC_40();
    }

     //   @Test(priority = 31)
    public void useQR_SendMoneyToMLWalletInOTPPopupValidation_QR_TC_41() throws Exception {
        MLWalletBusinessLogic.useQR_SendMoneyToMLWalletInOTPPopupValidation_QR_TC_41();
    }

        /* @Test(priority = 32)
    public void useQR_SendMoneyToMLWalletTransactionInAppOTPPopupUIValidation_QR_TC_42() throws Exception {
        MLWalletBusinessLogic.useQR_SendMoneyToMLWalletTransactionInAppOTPPopupUIValidation_QR_TC_42();
    }

    @Test(priority = 33)
    public void useQR_SendMoneyToMLWalletTransactionNewOTPAfterSixtySecondsValidation_QR_TC_43() throws Exception {
        MLWalletBusinessLogic.useQR_SendMoneyToMLWalletTransactionNewOTPAfterSixtySecondsValidation_QR_TC_43();
    }

    @Test(priority = 34)
    public void useQR_SendMoneyToMLWalletTransactionOTPCancelBtnFunctionality_QR_TC_44() throws Exception {
        MLWalletBusinessLogic.useQR_SendMoneyToMLWalletTransactionOTPCancelBtnFunctionality_QR_TC_44();
    }

    @Test(priority = 35)
    public void useQR_SendMoneyToMLWalletOTPContinueBtnFunctionality_QR_TC_45() throws Exception {
        MLWalletBusinessLogic.useQR_SendMoneyToMLWalletOTPContinueBtnFunctionality_QR_TC_45();
    } */

}