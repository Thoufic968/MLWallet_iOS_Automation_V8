package com.gmail;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.StringUtils;
//import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.Label;
import com.google.api.services.gmail.model.ListLabelsResponse;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;

import io.restassured.path.json.JsonPath;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.ArrayList;

import java.util.List;

import org.apache.commons.codec.binary.Base64;

/* class to demonstrate use of Gmail list labels API */
public class GmailQuickstart {
  /**
   * Application name.
   */
  private static final String APPLICATION_NAME = "Build";
  
  private static final String USER_ID = "me";
  /**
   * Global instance of the JSON factory.
   */
  private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
  
//	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
  /**
   * Directory to store authorization tokens for this application.
   */
//  private static final String TOKENS_DIRECTORY_PATH = "tokens";

  /**
   * Global instance of the scopes required by this quickstart.
   * If modifying these scopes, delete your previously saved tokens/ folder.
   */
  //GMAIL_LABELS  MAIL_GOOGLE_COM
  private static final List<String> SCOPES = Collections.singletonList(GmailScopes.MAIL_GOOGLE_COM);
  private static final String CREDENTIALS_FILE_PATH = 
		  System.getProperty("user.dir")+
		  File.separator + "src" +
		  File.separator + "main" +
		  File.separator + "resources" +
	//	  File.separator + "credentials"+
		  File.separator + "credentials.json" ;
		
  
  private static final String TOKENS_DIRECTORY_PATH=System.getProperty("user.dir")+
		  File.separator + "src" +
		  File.separator + "main" +
		  File.separator + "resources" +
		  File.separator + "credentials";
		//  "/credentials.json";
  
  /**
   * Creates an authorized Credential object.
   *
   * @param HTTP_TRANSPORT The network HTTP Transport.
   * @return An authorized Credential object.
   * @throws IOException If the credentials.json file cannot be found.
   */
  private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT)
      throws IOException {
    // Load client secrets.
//	  System.out.println(CREDENTIALS_FILE_PATH);
	
  //  InputStream in = GmailQuickstart.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
	  InputStream in =new FileInputStream(new File(CREDENTIALS_FILE_PATH));
	 
    if (in == null) {
      throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
    }
    GoogleClientSecrets clientSecrets =
        GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

    // Build flow and trigger user authorization request.
    GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
        .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
        .setAccessType("offline")
        .build();
    LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
    Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    //returns an authorized Credential object.
    return credential;
  }
  
  /********************************************
   * 
   * @param args
   * @throws IOException
   * @throws GeneralSecurityException
   */
  
  
  public static Gmail getService() throws IOException, GeneralSecurityException {
      // Build a new authorized API client service.
      final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
      Gmail service = new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
              .setApplicationName(APPLICATION_NAME)
              .build();
      return service;
  }
  
  public static Message getMessage(Gmail service, String userId, List<Message> messages, int index)
          throws IOException {
      Message message = service.users().messages().get(userId, messages.get(index).getId()).execute();
      
      return message;
  }
  
	public static List<Message> listMessagesMatchingQuery(Gmail service, String userId, String query)
			throws IOException {
		ListMessagesResponse response = service.users().messages().list(userId).setQ(query).execute();
		List<Message> messages = new ArrayList<Message>();
		while (response.getMessages() != null) {
			messages.addAll(response.getMessages());
			if (response.getNextPageToken() != null) {
				String pageToken = response.getNextPageToken();
				response = service.users().messages().list(userId).setQ(query).setPageToken(pageToken).execute();
			} else {
				break;
			}
		}
		return messages;
	}
  
  
	public static void getMailBody(Gmail service, String searchString) throws IOException {

		// Access Gmail inbox
		
		

		Gmail.Users.Messages.List request = service.users().messages().list(USER_ID).setQ(searchString);

		ListMessagesResponse messagesResponse = request.execute();
		request.setPageToken(messagesResponse.getNextPageToken());

		// Get ID of the email you are looking for
		String messageId = messagesResponse.getMessages().get(0).getId();
		
		Message message = service.users().messages().get(USER_ID, messageId).execute();

		// Print email body

		String emailBody = StringUtils
				.newStringUtf8(Base64.decodeBase64(message.getPayload().getParts().get(0).getBody().getData()));

		System.out.println("Email body : " + emailBody);

	}
	
  
  public static HashMap<String, String> getGmailData(String query) {
      try {
          Gmail service = getService();
          List<Message> messages = listMessagesMatchingQuery(service, USER_ID, query);
          Message message = getMessage(service, USER_ID, messages, 0);
          JsonPath jp = new JsonPath(message.toString());
          String subject = jp.getString("payload.headers.find { it.name == 'Subject' }.value");
        //  String sub1=String.valueOf(subject.contains(query));
      //    String body = new String(Base64.getDecoder().decode(jp.getString("payload.parts[0].body.data")));
          
         
          String  body=new String (jp.getString("payload.parts[0].body.data"));
//          String decode=new String(Base64.getDecoder().decode(body));
          System.out.println();
          String emailBody = StringUtils
  				.newStringUtf8(Base64.decodeBase64(body));
     ///     System.out.println(emailBody);
          
                  
            
         
          String link = null;
          String link1=null;
          String str1=null;
          String arr[] = emailBody.split("\n");
          for(String s: arr) {
              s = s.trim();
              if(s.startsWith("<http") || s.startsWith("<https")) {
            	  
                  link = s.trim();
                  if(link.contains("#email-notification-preferences"))
                  {
                	  continue;
                  }

                  link1=link;   
                  str1 = link1.substring(1, link1.length() - 1);
               //   System.out.println(str1);
                  
               
              }
          }
          HashMap<String, String> hm = new HashMap<String, String>();
         // hm.put("subject", subject);
          hm.put("subject", subject);
          hm.put("body", body);
          hm.put("link", str1);
          return hm;
      } catch (Exception e) {
      		System.out.println("email not found....");
          throw new RuntimeException(e);
      }
  }
  
  public static int getTotalCountOfMails() {
      int size;
      try {
          final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
          Gmail service = new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                  .setApplicationName(APPLICATION_NAME)
                  .build();
          List<com.google.api.services.gmail.model.Thread> threads = service.
                  users().
                  threads().
                  list("me").
                  execute().
                  getThreads();
           size = threads.size();
      } catch (Exception e) {
          System.out.println("Exception log " + e);
          size = -1;
      }
      return size;
  }
  
	public static boolean isMailExist(String messageTitle) {

		try {
			final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
			Gmail service = new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
					.setApplicationName(APPLICATION_NAME).build();
			
			ListMessagesResponse response = service.users().messages().list("me").setQ("subject:" + messageTitle)
					.execute();
			List<Message> messages = getMessages(response);
			return messages.size() != 0;
		} catch (Exception e) {
			System.out.println("Exception log" + e);
			return false;
		}
	}
      
      private static List<Message> getMessages(ListMessagesResponse response) {
          List<Message> messages = new ArrayList<Message>();
          try {
              final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
              Gmail service = new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                      .setApplicationName(APPLICATION_NAME)
                      .build();
              while (response.getMessages() != null) {
                  messages.addAll(response.getMessages());
                  if (response.getNextPageToken() != null) {
                      String pageToken = response.getNextPageToken();
                      response = service.users().messages().list(USER_ID)
                              .setPageToken(pageToken).execute();
                  } else {
                      break;
                  }
              }
              return messages;
          } catch (Exception e) {
              System.out.println("Exception log " + e);
              return messages;
          }
      }
  
  
  
  

  public static void main(String... args) throws IOException, GeneralSecurityException {
  //   Build a new authorized API client service.
//    final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
//    Gmail service = new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
//        .setApplicationName(APPLICATION_NAME)
//        .build();
//
//    // Print the labels in the user's account.
//    String user = "me";
//    ListLabelsResponse listResponse = service.users().labels().list(user).execute();
//    List<Label> labels = listResponse.getLabels();
//    if (labels.isEmpty()) {
//      System.out.println("No labels found.");
//    } else {
//      System.out.println("Labels:");
//      for (Label label : labels) {
//        System.out.printf("- %s\n", label.getName());
//      }
//    }
  
    
    
	  
    //Fwd: Version 11.0.62 (282) of QA - ML Money iOS for iOS Available
//    HashMap<String, String> hm = getGmailData("subject:Version 11.0.62 (300) of QA - ML Money iOS for iOS Available\r\n"
//    		+ "");
//   
//    System.out.println(hm.get("subject"));
//    System.out.println("=================");
//    System.out.println(hm.get("body"));
//    
//    
//    
//    
//    System.out.println("=================");
//    
//    System.out.println("Link is : ");
//    System.out.println(hm.get("link"));
//    
//    System.out.println("=================");
//    System.out.println("Total count of emails is :"+getTotalCountOfMails()); 
//    
//    System.out.println("=================");
//    boolean exist = isMailExist("Hi");
//    System.out.println("title exist or not: " + exist);
    
  // getMailBody(getService(), "version 11.0 ");
    getMailBody(getService(), "subject:Fwd: Version 11.0.62 (282) of QA - ML Money iOS for iOS Available");
  
  }
 
}
