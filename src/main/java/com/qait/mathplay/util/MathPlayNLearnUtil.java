package com.qait.mathplay.util;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.Logger;

public class MathPlayNLearnUtil {

	private static final Logger logger = Logger.getLogger(MathPlayNLearnUtil.class);

	/*
	 * Method returns the stack trace of exception in string format. Used for
	 * logging of exception.
	 */
	public static String getExceptionDescriptionString(Exception e) {
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		e.printStackTrace(printWriter);
		return stringWriter.toString();
	}
	
	/**
	 * Method to send push notifications
	 * @param certificateFilePath
	 * @param password
	 * @param deviceToken
	 * @param alertMessage
	 */
/*	public static void sendNotification(String certificateFilePath, String password, String deviceToken, String alertMessage)
			throws UnrecoverableKeyException, KeyManagementException,
			KeyStoreException, NoSuchAlgorithmException, CertificateException,
			IOException, MalformedTokenStringException, InterruptedException {
		
		final PushManager<SimpleApnsPushNotification> pushManager = new PushManager<SimpleApnsPushNotification>(
				ApnsEnvironment.getSandboxEnvironment(),
				SSLContextUtil.createDefaultSSLContext(certificateFilePath, password), null, null, null,
				new PushManagerConfiguration(), "Friend Request");

		pushManager.start();

		final byte[] token = TokenUtil.tokenStringToByteArray(deviceToken);

		final ApnsPayloadBuilder payloadBuilder = new ApnsPayloadBuilder();
		payloadBuilder.setAlertBody(alertMessage);

		final String payload = payloadBuilder.buildWithDefaultMaximumLength();
		pushManager.getQueue().put(new SimpleApnsPushNotification(token, payload));

		pushManager.shutdown(600000);
	}*/
}
