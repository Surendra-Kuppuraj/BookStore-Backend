/**
 * 
 */
package com.sk.bookstore.resource;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.sk.bookstore.resource.constant.MessageEnum;
import com.sk.bookstore.resource.util.ResponseMessage;

/**
 * @author Surendra Kumar
 *
 */
@RestController
@RequestMapping("/image")
public class ImageResource {
	private static final Logger LOGGER = LoggerFactory.getLogger(ImageResource.class);

	private static final String BOOK_IMAGE_STORAGE_PATH = "src/main/resources/static/image/book/";
	private static final String IMAGE_FILE_EXTENSTION = ".png";

	@PostMapping("/add")
	public ResponseEntity<ResponseMessage> newImage(@RequestParam("id") final Long id, final HttpServletRequest request,
			final HttpServletResponse response) {
		final String fileName = id + IMAGE_FILE_EXTENSTION;
		return saveImageFile(fileName, getMultipartFile(request));
	}

	@PostMapping("/update")
	public ResponseEntity<ResponseMessage> updateImage(@RequestParam("id") final Long id,
			final HttpServletRequest request, final HttpServletResponse response) {
		final String fileName = id + IMAGE_FILE_EXTENSTION;
		if (!deleteImageFile(fileName)) {
			return new ResponseEntity<>(new ResponseMessage(MessageEnum.CREATE_FAILED), HttpStatus.BAD_REQUEST);
		}
		return saveImageFile(fileName, getMultipartFile(request));
	}

	private MultipartFile getMultipartFile(final HttpServletRequest request) {
		final MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Iterator<String> it = multipartRequest.getFileNames();
		// TODO Use MultipartFile getFile("id.png"); after review.
		return multipartRequest.getFile(it.next());
	}

	private ResponseEntity<ResponseMessage> saveImageFile(final String fileName, final MultipartFile multipartFile) {
		try (BufferedOutputStream stream = new BufferedOutputStream(
				new FileOutputStream(new File(BOOK_IMAGE_STORAGE_PATH + fileName)));) {
			stream.write(multipartFile.getBytes());
			return new ResponseEntity<>(new ResponseMessage(MessageEnum.CREATE_SUCCESS), HttpStatus.OK);
		} catch (IOException ioe) {
			LOGGER.error(
					"The Image file " + fileName + " cannot be deleted successfully due to following reason " + ioe);
			return new ResponseEntity<>(new ResponseMessage(MessageEnum.CREATE_FAILED), HttpStatus.BAD_REQUEST);
		}
	}

	private boolean deleteImageFile(final String fileName) {
		boolean isDeleted = false;
		final Path path = Paths.get(BOOK_IMAGE_STORAGE_PATH + fileName);
		try {
			isDeleted = Files.deleteIfExists(path);
			LOGGER.info("The Image file has been deleted successfully: " + path);
		} catch (IOException ioe) {
			LOGGER.error("The Image file " + path + " could not been deleted due to following reason " + ioe);
		}
		return isDeleted;
	}

}
