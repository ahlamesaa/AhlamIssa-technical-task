package com.luminous.integration.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Base64;

import org.apache.commons.validator.routines.UrlValidator;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * @author Ahlam-issa
 *
 */
public class WebServiceApiUtil {
	protected static final Logger LOG = LoggerFactory.getLogger(WebServiceApiUtil.class);

	private WebServiceApiUtil() {
	}

	public static ResponseEntity<?> httpGet(final String url, MultiValueMap<String, String> headers,
			final Class<?> clazz) {
		if (headers == null) {
			headers = new LinkedMultiValueMap<>();
		}
		addHeaders(headers);

		final RestTemplate restTemplate = new RestTemplate();
		final HttpEntity<?> httpEntity = new HttpEntity<>(headers);

		final ResponseEntity<?> exchange = restTemplate.exchange(url, HttpMethod.GET, httpEntity, clazz);

		return exchange;
	}

	
	public static ResponseEntity<byte[]> httpGetPdf(final String url, MultiValueMap<String, String> headers) {
		if (headers == null) {
			headers = new LinkedMultiValueMap<>();
			headers.add("content-type", "application/pdf");
			headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
			headers.add("Pragma", "no-cache");
			headers.add("Expires", "0");
		} else {
			addHeaders(headers);
		}

		final RestTemplate restTemplate = new RestTemplate();
		final HttpEntity<byte[]> httpEntity = new HttpEntity<>(headers);

		final ResponseEntity<byte[]> exchange = restTemplate.exchange(url, HttpMethod.GET, httpEntity, byte[].class);

		return exchange;
	}

	
	public static ResponseEntity<?> httpPatch(final String url, final Object body,
			MultiValueMap<String, String> headers, final Class<?> clazz) {
		if (headers == null) {
			headers = new LinkedMultiValueMap<>();

		}
		addHeaders(headers);
		final HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(
				HttpClientBuilder.create().build());
		final RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);
		final HttpEntity<?> httpEntity = new HttpEntity<>(body, headers);

		final ResponseEntity<?> exchange = restTemplate.exchange(url, HttpMethod.PATCH, httpEntity, clazz);

		return exchange;
	}

	public static Object httpGet(final String url, MultiValueMap<String, String> headers, final Class<?> clazz,
			final int timeout) {
		if (headers == null) {
			headers = new LinkedMultiValueMap<>();
		}
		addHeaders(headers);
		final HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(
				HttpClientBuilder.create().build());

		// Connect timeout
		clientHttpRequestFactory.setConnectTimeout(timeout);

		// Read timeout
		clientHttpRequestFactory.setReadTimeout(timeout);

		final RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);

		final HttpEntity<?> httpEntity = new HttpEntity<Object>(headers);

		final ResponseEntity<?> exchange = restTemplate.exchange(url, HttpMethod.GET, httpEntity, clazz);

		return exchange.getBody();
	}

	public static <T> ResponseEntity<?> httPOST(final String url, final T body, MultiValueMap<String, String> headers,
			final Class<?> clazz) {
		if (headers == null) {
			headers = new LinkedMultiValueMap<>();
		}
		addHeaders(headers);

		final HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(
				HttpClientBuilder.create().build());
		final RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);
		final HttpEntity<T> bodyAndHeaders = new HttpEntity<>(body, headers);

		final ResponseEntity<?> response = restTemplate.exchange(url, HttpMethod.POST, bodyAndHeaders, clazz);
		if (response.getStatusCodeValue() >= 200 && response.getStatusCodeValue() < 300) {
			return response;
		}
		return null;
	}

	public static <T> ResponseEntity<?> httPOST(final String url, final T body, MultiValueMap<String, String> headers,
			final Class<?> clazz, final int timeout) {
		if (headers == null) {
			headers = new LinkedMultiValueMap<>();
		}
		addHeaders(headers);

		final HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(
				HttpClientBuilder.create().build());
		// Connect timeout
		clientHttpRequestFactory.setConnectTimeout(timeout);

		// Read timeoutApiHttpRestUtil
		clientHttpRequestFactory.setReadTimeout(timeout);
		final RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);
		final HttpEntity<T> bodyAndHeaders = new HttpEntity<>(body, headers);

		final ResponseEntity<?> response = restTemplate.exchange(url, HttpMethod.POST, bodyAndHeaders, clazz);
		if (response.getStatusCodeValue() >= 200 && response.getStatusCodeValue() < 300) {
			return response;
		}
		return null;
	}

	private static void addHeaders(final MultiValueMap<String, String> headers) {
		headers.add("content-type", "application/json");
		headers.add("user-agent", "*");
	}

	public static String getBasicAuthHeader(final String apiUsername, final String apiPassword) {
		final String auth = apiUsername + ":" + apiPassword;
		final byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes());
		return "Basic " + new String(encodedAuth);
	}

	public static String getValidURL(final String url) {
		final UrlValidator urlValidator = new UrlValidator();

		return urlValidator.isValid(url) ? url : null;
	}

	public static Boolean checkServerUpAndRunning(final String ip) {
		int count = 0;
		String commandLine = null;
		try {
			final Process p = Runtime.getRuntime().exec("ping " + ip);
			final BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
			final BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			// Read the output from the command
			while ((commandLine = stdInput.readLine()) != null) {
				LOG.debug(commandLine);
				count++;
				if (count == 2) {
					return commandLine.contains("bytes from " + ip);
				}
			}
			boolean errorExist = false;
			// Read any errors from the attempted command
			while ((commandLine = stdError.readLine()) != null) {
				LOG.error(commandLine);
				errorExist = true;
			}
			if (errorExist) {
				return false;
			}
		} catch (final IOException ex) {
			LOG.error(ex.getMessage());
			return false;
		}
		return false;
	}
}
