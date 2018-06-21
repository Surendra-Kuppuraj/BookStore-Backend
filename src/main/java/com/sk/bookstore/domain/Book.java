package com.sk.bookstore.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Surendra Kumar
 *
 */
@Entity
public class Book implements Serializable {

	private static final long serialVersionUID = 743920232113L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String title;
	private String author;
	private String publisher;
	private String publicationDate;
	private String language;
	private String category;
	private int numberOfPages;
	private String format;
	private String isbn;
	private double shippingWeight;
	private double listPrice;
	private double ourPrice;
	private boolean active = true;

	@Column(columnDefinition = "text")
	private String description;
	private int inStockNumber;

	@Transient
	private MultipartFile bookImage;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public double getShippingWeight() {
		return shippingWeight;
	}

	public void setShippingWeight(double shippingWeight) {
		this.shippingWeight = shippingWeight;
	}

	public double getListPrice() {
		return listPrice;
	}

	public void setListPrice(double listPrice) {
		this.listPrice = listPrice;
	}

	public double getOurPrice() {
		return ourPrice;
	}

	public void setOurPrice(double ourPrice) {
		this.ourPrice = ourPrice;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getInStockNumber() {
		return inStockNumber;
	}

	public void setInStockNumber(int inStockNumber) {
		this.inStockNumber = inStockNumber;
	}

	public MultipartFile getBookImage() {
		return bookImage;
	}

	public void setBookImage(MultipartFile bookImage) {
		this.bookImage = bookImage;
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getTitle(), getAuthor(), getPublisher(), getPublicationDate(), getLanguage(),
				getCategory(), getNumberOfPages(), getFormat(), getIsbn(), getShippingWeight(), getListPrice(),
				getOurPrice(), isActive(), getDescription(), getInStockNumber(), getBookImage());
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return Boolean.TRUE;
		}
		if (obj instanceof Book) {
			Book book = (Book) obj;
			return Objects.equals(author, book.author) && Objects.equals(bookImage, book.bookImage)
					&& Objects.equals(category, book.category) && Objects.equals(format, book.format)
					&& Objects.equals(language, book.language) && Objects.equals(publicationDate, book.publicationDate)
					&& Objects.equals(description, book.description) && Objects.equals(id, book.id)
					&& Objects.equals(isbn, book.isbn) && Objects.equals(publisher, book.publisher)
					&& Objects.equals(title, book.title);
		}
		return Boolean.FALSE;
	}

	@Override
	public String toString() {
		StringBuffer outputBuffer = new StringBuffer().append("Book [id=").append(id).append("title=").append(title)
				.append(", author=").append(author).append(", publisher=").append(publisher)
				.append(", publicationDate=").append(publicationDate).append(", language=").append(language)
				.append(", category=").append(category).append(", numberOfPages=").append(numberOfPages)
				.append(", format=").append(format).append(", isbn=").append(isbn).append(", shippingWeight=")
				.append(shippingWeight).append(" listPrice=").append(listPrice).append(", ourPrice=").append(ourPrice)
				.append(", active=").append(active).append(", description=").append(description)
				.append(", inStockNumber=").append(inStockNumber).append(", bookImage=").append(bookImage).append("]");
		return outputBuffer.toString();
	}

}
