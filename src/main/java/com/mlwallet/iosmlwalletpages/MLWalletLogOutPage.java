package com.mlwallet.iosmlwalletpages;

import org.openqa.selenium.By;

public class MLWalletLogOutPage {

    public static By objHamburgerMenu = By.xpath("(//*[@name=''])[2]");
    
    public static By objHamburgerMenu1 = By.xpath("(//*[@name=''])[4]");

    public static By objLogoutBtn = By.xpath("//*[@name='Logout']");
    
    public static By objPopUpLogoutBtn = By.xpath("//*[@name='Are you sure you would like to logout?']/following-sibling::XCUIElementTypeOther[@name='Logout']");

    public static By objTroubleSignIn = By.xpath("//*[name='Trouble signing in?']");

    public static By objChangeNumber = By.xpath("//*[@name='724506']");
    
//    public static By objHamburgerMenu = By.xpath("(//*[android.view.ViewGroup]/child::android.view.ViewGroup/child::android.widget.TextView)[3]");
//    public static By objHamburgerMenuForFullyVerifiedTier = By.xpath("//*[@text and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[./parent::*[@class='android.view.ViewGroup'] and (./preceding-sibling::* | ./following-sibling::*)[@class='android.widget.ImageView']]]]");
//    public static By objLogoutBtn = By.xpath("//*[@text='Logout']");
//    public static By objTroubleSignIn = By.xpath("//*[@text='Trouble signing in?']");
//    public static By objChangeNumber = By.xpath("//*[contains(@text,'Number')]");
    
    public static By objLogoOutPopupMsg = By.xpath("//*[@label='Are you sure you would like to logout?']");
    
    public static By objCancelBtn = By.xpath("//*[@label='Cancel']");
    
    public static By objHome = By.xpath("//*[@label='Home']");



}
