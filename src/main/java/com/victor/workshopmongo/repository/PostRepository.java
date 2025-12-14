package com.victor.workshopmongo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.victor.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

	// querry method
	List<Post> findByTitleContainingIgnoreCase(String text);
	
	@Query("{ 'title': { $regex: ?0, $options: 'i' } }")
	List<Post> findBySearchTitle(String text);
	
	// consulta com vários critérios
	@Query("{ $and: [ { date: { $gte: ?1 } }\r\n"
			+ ", { date: { $lte: ?2 } }, ... , { $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, ... , { 'comments.text': { $regex: ?0, $options: 'i' } } ] }] }")
	List<Post> fullSearch(String text, Date minDate, Date maxDate);
	
}
